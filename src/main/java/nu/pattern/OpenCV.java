package nu.pattern;

import org.opencv.core.Core;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class OpenCV {

  private final static Logger logger = Logger.getLogger(OpenCV.class.getName());

  static enum OS {
    OSX("^[Mm]ac OS X$"),
    LINUX("^[Ll]inux$"),
    WINDOWS("^[Ww]indows.*");

    private final Set<Pattern> patterns;

    private OS(final String... patterns) {
      this.patterns = new HashSet<Pattern>();

      for (final String pattern : patterns) {
        this.patterns.add(Pattern.compile(pattern));
      }
    }

    private boolean is(final String id) {
      for (final Pattern pattern : patterns) {
        if (pattern.matcher(id).matches()) {
          return true;
        }
      }
      return false;
    }

    public static OS getCurrent() {
      final String osName = System.getProperty("os.name");

      for (final OS os : OS.values()) {
        if (os.is(osName)) {
          logger.log(Level.FINEST, "Current environment matches operating system descriptor \"{0}\".", os);
          return os;
        }
      }

      throw new UnsupportedOperationException(String.format("Operating system \"%s\" is not supported.", osName));
    }
  }

  static enum Arch {
    X86_32("i386", "i686"),
    X86_64("amd64", "x86_64");

    private final Set<String> patterns;

    private Arch(final String... patterns) {
      this.patterns = new HashSet<String>(Arrays.asList(patterns));
    }

    private boolean is(final String id) {
      return patterns.contains(id);
    }

    public static Arch getCurrent() {
      final String osArch = System.getProperty("os.arch");

      for (final Arch arch : Arch.values()) {
        if (arch.is(osArch)) {
          logger.log(Level.FINEST, "Current environment matches architecture descriptor \"{0}\".", arch);
          return arch;
        }
      }

      throw new UnsupportedOperationException(String.format("Architecture \"%s\" is not supported.", osArch));
    }
  }

  private static class UnsupportedPlatformException extends RuntimeException {
    private UnsupportedPlatformException(final OS os, final Arch arch) {
      super(String.format("Operating system \"%s\" and architecture \"%s\" are not supported.", os, arch));
    }
  }

  private static class TemporaryDirectory {
    final Path path;

    public TemporaryDirectory() {
      try {
        path = Files.createTempDirectory(null);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public Path getPath() {
      return path;
    }

    public TemporaryDirectory markDeleteOnExit() {
      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
          delete();
        }
      });

      return this;
    }

    public void delete() {
      if (!Files.exists(path)) {
        return;
      }

      try {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
          @Override
          public FileVisitResult postVisitDirectory(final Path dir, final IOException e)
              throws IOException {
            Files.deleteIfExists(dir);
            return super.postVisitDirectory(dir, e);
          }

          @Override
          public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
              throws IOException {
            Files.deleteIfExists(file);
            return super.visitFile(file, attrs);
          }
        });
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }

  /**
   * Attempts first to load {@link Core#NATIVE_LIBRARY_NAME} without additional setup. If that succeeds, the system already has the appropriate OpenCV library available. If that fails (with {@link UnsatisfiedLinkError}), this call will write the appropriate native library from the class path to a temporary directory, then add that directory to {@code java.library.path}. Afterwards, subsequent {@link System#loadLibrary(String)} calls with {@link Core#NATIVE_LIBRARY_NAME} will succeed without modification. This has the benefit of keeping client libraries decoupled from Pattern's packages.
   */
  public static void loadLibrary() {
    try {
      /* Prefer loading the installed library. */
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    } catch (final UnsatisfiedLinkError ule) {
      /* Failing that, deploy and run a packaged binary. */

      final OS os = OS.getCurrent();
      final Arch arch = Arch.getCurrent();
      final String location;

      switch (os) {
        case LINUX:
          switch (arch) {
            case X86_32:
              location = "/nu/pattern/opencv/linux/x86_32/libopencv_java249.so";
              break;
            case X86_64:
              location = "/nu/pattern/opencv/linux/x86_64/libopencv_java249.so";
              break;
            default:
              throw new UnsupportedPlatformException(os, arch);
          }
          break;
        case OSX:
          switch (arch) {
            case X86_64:
              location = "/nu/pattern/opencv/osx/x86_64/libopencv_java249.dylib";
              break;
            default:
              throw new UnsupportedPlatformException(os, arch);
          }
          break;
        default:
          throw new UnsupportedPlatformException(os, arch);
      }

      logger.log(Level.FINEST, "Selected native binary \"{0}\".", location);

      final InputStream binary = OpenCV.class.getResourceAsStream(location);
      final Path destination = new TemporaryDirectory().markDeleteOnExit().getPath().resolve("./" + location).normalize();

      try {
        logger.log(Level.FINEST, "Copying native binary to \"{0}\".", destination);

        Files.createDirectories(destination.getParent());
        Files.copy(binary, destination);

        logger.log(Level.FINEST, "Loading native binary at \"{0}\".", destination);

        final String originalLibaryPath = System.getProperty("java.library.path");
        System.setProperty("java.library.path", originalLibaryPath + System.getProperty("path.separator") + destination.getParent());

        /* See https://github.com/atduskgreg/opencv-processing/blob/master/src/gab/opencv/OpenCV.java for clarification. */
        final Field systemPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        systemPathsField.setAccessible(true);
        systemPathsField.set(null, null);

        logger.log(Level.FINEST, "System library path now \"{0}\".", System.getProperty("java.library.path"));

        logger.log(Level.FINEST, "Native library \"{0}\" maps to \"{1}\".", new Object[]{Core.NATIVE_LIBRARY_NAME, System.mapLibraryName(Core.NATIVE_LIBRARY_NAME)});

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

      } catch (final IOException ioe) {
        throw new IllegalStateException(String.format("Error writing native library to \"%s\".", destination), ioe);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      }

      logger.log(Level.FINEST, "Completed native OpenCV library loading.");
    }
  }
}
