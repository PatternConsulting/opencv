# OpenCV 2.4.9 (packaged by [Pattern](http://pattern.nu))

[OpenCV](http://opencv.org) Java bindings packaged with native libraries, seamlessly delivered as a Maven dependency.

## Rationale

Developers wishing to use the Java API for OpenCV would typically go through the process of building the project, and building it for each platform they wished to support (_e.g._, 32-bit Linux, OS X). This project provides those binaries for inclusion as dependency in Maven, Ivy, and SBT projects.
  
## Acknowledgements

- [Greg Borenstein](https://github.com/atduskgreg), who's advice and [OpenCV for Processing](https://github.com/atduskgreg/opencv-processing) project informed this package's development. 
- [Alex Osborne](https://github.com/ato), for helpful [utility class producing temporary directories with Java NIO that are properly garbage-collected on shutdown](https://gist.github.com/ato/6774390).
