# OpenCV 2.4.9 (packaged by [Pattern](http://pattern.nu))

[OpenCV](http://opencv.org) Java bindings packaged with native libraries, seamlessly delivered as a Maven dependency.

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
      <version>2.4.9</version>
    </dependency>
    <!-- ... -->
  </dependencies>
  <!-- ... -->
</project>
```

#### [SBT](http://scala-sbt.org)

```scala
"nu.pattern" % "opencv" % "2.4.9"
```

### API

Typically, using the upstream [OpenCV Java bindings involves loading the native library](http://docs.opencv.org/doc/tutorials/introduction/desktop_java/java_dev_intro.html#java-sample-with-ant) as follows:

```java
static {
  System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
}
```

However, this package requires a slightly different approach. You'll need to call [`nu.pattern.OpenCV.loadLibrary()`](https://github.com/PatternConsulting/opencv/blob/master/src/main/java/nu/pattern/OpenCV.java) prior to using any OpenCV API.

```java
static {
  nu.pattern.OpenCV.loadLibrary();
}
```

This call will first attempt to load from the system-wide installation (exactly as if `System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);` were called), and, failing that, will select a binary from the package depending on the runtime environment's operating system and architecture. It will write that native library t a temporary location (defined by the environment), then load it using [`System#load(String)`](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#load-java.lang.String-). _This involves writing to disk_, so consider the implications. Temporary files will be garbage-collected on clean shutdown.

## Rationale

Developers wishing to use the Java API for OpenCV would typically go through the process of building the project, and building it for each platform they wished to support (_e.g._, 32-bit Linux, OS X). This project provides those binaries for inclusion as dependency in Maven, Ivy, and SBT projects.
  
## Acknowledgements

- [Greg Borenstein](https://github.com/atduskgreg), who's advice and [OpenCV for Processing](https://github.com/atduskgreg/opencv-processing) project informed this package's development. 
- [Alex Osborne](https://github.com/ato), for helpful [utility class producing temporary directories with Java NIO that are properly garbage-collected on shutdown](https://gist.github.com/ato/6774390).
