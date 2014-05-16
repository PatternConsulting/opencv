# OpenCV 2.4.9 (packaged by [Pattern](http://pattern.nu))

[![Build Status](https://travis-ci.org/PatternConsulting/opencv.svg?branch=master)](https://travis-ci.org/PatternConsulting/opencv)

[OpenCV](http://opencv.org) Java bindings packaged with native libraries, seamlessly delivered as a turn-key Maven dependency.

## Usage

### Project

Pattern's OpenCV package is added to your project as any other dependency.

#### [Maven](http://maven.apache.org/)

```xml
<project>
  <!-- ... -->
  <dependencies>
    <!-- ... -->
    <dependency>
      <groupId>nu.pattern</groupId>
      <artifactId>opencv</artifactId>
      <version>2.4.9-3</version>
    </dependency>
    <!-- ... -->
  </dependencies>
  <!-- ... -->
</project>
```

#### [SBT](http://scala-sbt.org)

```scala
"nu.pattern" % "opencv" % "2.4.9-3"
```

### API

Typically, using the upstream [OpenCV Java bindings involves loading the native library](http://docs.opencv.org/doc/tutorials/introduction/desktop_java/java_dev_intro.html#java-sample-with-ant) as follows:

```java
static {
  System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
}
```

Fortunately, this is unchanged except for one caveat. To use the native libraries included with this package, first call [`nu.pattern.OpenCV.loadLibrary()`](https://github.com/PatternConsulting/opencv/blob/master/src/main/java/nu/pattern/OpenCV.java).

This call will first attempt to load from the system-wide installation (exactly as if `System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);` were called without any preceding steps). If that fails, the loader will select a binary from the package appropriate for the runtime environment's operating system and architecture. It will write that native library to a temporary directory (also defined by the environment), add that directory to `java.library.path`. _This involves writing to disk_, so consider the implications. Temporary files will be garbage-collected on clean shutdown.

This approach keeps most clients decoupled from Pattern's package and loader. As long as this is done sufficiently early in execution, any library using the OpenCV Java bindings can use the usual load call as documented by the OpenCV project.

## Debugging

[Java logging](http://docs.oracle.com/javase/8/docs/api/java/util/logging/package-summary.html) is used to produce log messages from `nu.pattern.OpenCV`.

## Rationale

Developers wishing to use the Java API for OpenCV would typically go through the process of building the project, and building it for each platform they wished to support (_e.g._, 32-bit Linux, OS X). This project provides those binaries for inclusion as a typical dependency in Maven, Ivy, and SBT projects.

Apart from testing, this package deliberately specifies no external dependencies. It does, however, make use of modern Java APIs (such as [Java NIO](http://docs.oracle.com/javase/tutorial/essential/io/fileio.html)).

## Contributing

Producing native binaries is the most cumbersome process in maintaining this package. If you can contribute binaries _for the current version_, please make a pull request including the build artifacts and any platform definitions in `nu.pattern.OpenCV`.

## Support

The following platforms are supported by this package:

OS | Architecture
--- | ---
OS X | x86_64
Linux | x86_64
Linux | x86_32

## Credits

This package is maintained by [Michael Ahlers](http://github.com/michaelahlers).
  
## Acknowledgements

- [Greg Borenstein](https://github.com/atduskgreg), who's advice and [OpenCV for Processing](https://github.com/atduskgreg/opencv-processing) project informed this package's development. 
- [Alex Osborne](https://github.com/ato), for helpful [utility class producing temporary directories with Java NIO that are properly garbage-collected on shutdown](https://gist.github.com/ato/6774390).
