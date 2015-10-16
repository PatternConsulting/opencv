# Releasing a New Version

* Windows DLLs are simply copied from the OpenCV Window download.
* Linux builds are done in seperate 32 and 64 bit Ubuntu VMs.
* Build OpenCV with -DBUILD_SHARED_LIBS=OFF
* Build OpenCV with JDK 1.7 or the jar files won't work for 1.7 users.
* Run cmake with > cmake.log and include the log in each platform's directory.
* Update pom.xml and README.md with new versions.
* Merge develop into master and tag the release.