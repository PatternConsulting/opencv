# Releasing a New Version

* Build OpenCV with -DBUILD_SHARED_LIBS=OFF
* Build OpenCV with JDK 1.7 or the jar files won't work for 1.7 users.
* Run cmake with > cmake.log and include the log in each platform's directory.