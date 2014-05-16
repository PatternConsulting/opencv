# OpenCV 2.4.9 (packaged by [Pattern](http://pattern.nu))

[OpenCV](http://opencv.org) Java bindings packaged with native libraries, seamlessly delivered as a Maven dependency.

## Usage

### [Maven](http://maven.apache.org/)

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

### [SBT](http://scala-sbt.org)

```scala
"nu.pattern" % "opencv" % "2.4.9"
```

## Rationale

Developers wishing to use the Java API for OpenCV would typically go through the process of building the project, and building it for each platform they wished to support (_e.g._, 32-bit Linux, OS X). This project provides those binaries for inclusion as dependency in Maven, Ivy, and SBT projects.
  
## Acknowledgements

- [Greg Borenstein](https://github.com/atduskgreg), who's advice and [OpenCV for Processing](https://github.com/atduskgreg/opencv-processing) informed this project's development. 
