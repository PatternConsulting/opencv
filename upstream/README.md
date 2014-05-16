# Upstream

Java resources produced by the OpenCV build process. This project extracts them to standard locations.

Aritfact | Origin | Comments
--- | --- | ---
`res/` | `build/modules/java/test/.build/res/` | Test fixtures.
`opencv-249.jar` | `build/modules/java/test/.build/bin/opencv-249.jar` | Java API with sources.
`opencv-test.jar` | `build/modules/java/test/.build/jar/opencv-test.jar` | Compiled test classes.

A primary goal of this package is to avoid making any modifications to the artifacts produced by the upstream OpenCV project. The listed resources will be incorporated into standard Maven conventions.
