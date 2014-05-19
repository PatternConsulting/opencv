package nu.pattern;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

/**
 * Helper functions for native libraries.
 *
 * @author Gili Tzabari
 * @see http://stackoverflow.com/questions/1007861/how-do-i-get-a-list-of-jni-libraries-which-are-loaded
 */
public class NativeLibraries {
  private final Field loadedLibraryNames;
  private final Field systemNativeLibraries;
  private final Field nativeLibraries;
  private final Field nativeLibraryFromClass;
  private final Field nativeLibraryName;

  /**
   * Creates a new NativeLibraries.
   * <p/>
   *
   * @throws NoSuchFieldException if one of ClassLoader's fields cannot be found
   */
  public NativeLibraries() throws NoSuchFieldException {
    this.loadedLibraryNames = ClassLoader.class.getDeclaredField("loadedLibraryNames");
    loadedLibraryNames.setAccessible(true);

    this.systemNativeLibraries = ClassLoader.class.getDeclaredField("systemNativeLibraries");
    systemNativeLibraries.setAccessible(true);

    this.nativeLibraries = ClassLoader.class.getDeclaredField("nativeLibraries");
    nativeLibraries.setAccessible(true);

    Class<?> nativeLibrary = null;
    for (Class<?> nested : ClassLoader.class.getDeclaredClasses()) {
      if (nested.getSimpleName().equals("NativeLibrary")) {
        nativeLibrary = nested;
        break;
      }
    }
    this.nativeLibraryFromClass = nativeLibrary.getDeclaredField("fromClass");
    nativeLibraryFromClass.setAccessible(true);

    this.nativeLibraryName = nativeLibrary.getDeclaredField("name");
    nativeLibraryName.setAccessible(true);
  }

  /**
   * Returns the names of native libraries loaded across all class loaders.
   * <p/>
   *
   * @return a list of native libraries loaded
   */
  public List<String> getLoadedLibraries() {
    try {
      @SuppressWarnings("UseOfObsoleteCollectionType")
      final Vector<String> result = (Vector<String>) loadedLibraryNames.get(null);
      return result;
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * Returns the native libraries loaded by the system class loader.
   * <p/>
   *
   * @return a Map from the names of native libraries to the classes that loaded them
   */
  public Map<String, Class<?>> getSystemNativeLibraries() {
    try {
      Map<String, Class<?>> result = new HashMap<>();
      @SuppressWarnings("UseOfObsoleteCollectionType")
      final Vector<Object> libraries = (Vector<Object>) systemNativeLibraries.get(null);
      for (Object nativeLibrary : libraries) {
        String libraryName = (String) nativeLibraryName.get(nativeLibrary);
        Class<?> fromClass = (Class<?>) nativeLibraryFromClass.get(nativeLibrary);
        result.put(libraryName, fromClass);
      }
      return result;
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * Returns a Map from the names of native libraries to the classes that loaded them.
   * <p/>
   *
   * @param loader the ClassLoader that loaded the libraries
   * @return an empty Map if no native libraries were loaded
   */
  public Map<String, Class<?>> getNativeLibraries(final ClassLoader loader) {
    try {
      Map<String, Class<?>> result = new HashMap<>();
      @SuppressWarnings("UseOfObsoleteCollectionType")
      final Vector<Object> libraries = (Vector<Object>) nativeLibraries.get(loader);
      for (Object nativeLibrary : libraries) {
        String libraryName = (String) nativeLibraryName.get(nativeLibrary);
        Class<?> fromClass = (Class<?>) nativeLibraryFromClass.get(nativeLibrary);
        result.put(libraryName, fromClass);
      }
      return result;
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * The same as {@link #getNativeLibraries()} except that all ancestor classloaders are processed
   * as well.
   * <p/>
   *
   * @param loader the ClassLoader that loaded (or whose ancestors loaded) the libraries
   * @return an empty Map if no native libraries were loaded
   */
  public Map<String, Class<?>> getTransitiveNativeLibraries(final ClassLoader loader) {
    Map<String, Class<?>> result = new HashMap<>();
    ClassLoader parent = loader.getParent();
    while (parent != null) {
      result.putAll(getTransitiveNativeLibraries(parent));
      parent = parent.getParent();
    }
    result.putAll(getNativeLibraries(loader));
    return result;
  }

  /**
   * Converts a map of library names to the classes that loaded them to a map of library names to
   * the classloaders that loaded them.
   * <p/>
   *
   * @param libraryToClass a map of library names to the classes that loaded them
   * @return a map of library names to the classloaders that loaded them
   */
  public Map<String, ClassLoader> getLibraryClassLoaders(Map<String, Class<?>> libraryToClass) {
    Map<String, ClassLoader> result = new HashMap<>();
    for (Entry<String, Class<?>> entry : libraryToClass.entrySet())
      result.put(entry.getKey(), entry.getValue().getClassLoader());
    return result;
  }

  /**
   * Returns a list containing the classloader and its ancestors.
   * <p/>
   *
   * @param loader the classloader
   * @return a list containing the classloader, its parent, and so on
   */
  public static List<ClassLoader> getTransitiveClassLoaders(ClassLoader loader) {
    List<ClassLoader> result = new ArrayList<>();
    ClassLoader parent = loader.getParent();
    result.add(loader);
    while (parent != null) {
      result.add(parent);
      parent = parent.getParent();
    }
    return result;
  }
}
