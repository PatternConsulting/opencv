# Releasing a New Version

## Prepare

1. Download OpenCV Linux/OSX source and Windows binary release to opencv/. Should be a ZIP and a EXE.
2. Unzip source ZIP to create opencv/opencv-VERSION.
3. Run ./create-targets.sh VERSION (e.g. ./create-targets 3.0.0) to create all of the target directories under opencv/opencv-VERSION/target.
4. Update pom.xml, OpenCV.java and README.md with new versions.

## Build OSX and Java Targets

1. Make sure Java is 1.7: `java -version`, `export JAVA_HOME=$(/usr/libexec/java_home -v1.7)` 
2. `cd opencv/opencv-VERSION/target/osx/x86_64`
3. `cmake -DBUILD_SHARED_LIBS=OFF -DEIGEN_INCLUDE_PATH=/usr/local/include/eigen3 ../../.. > cmake.log`
4. `make -j8`
5. `./copy-resources VERSION`. It won't finish, but it should get far enough.
6. `mvn clean test`.

## Build Linux Targets

```
sudo apt update -y
sudo apt full-upgrade -y
sudo reboot
sudo apt install default-jdk ant maven git cmake g++ open-vm-tools open-vm-tools-desktop -y
sudo reboot
git clone https://github.com/openpnp/opencv.git
cd opencv; mkdir opencv; cd opencv
export OPENCV_VERSION=3.4.2
export OPENCV_BITS=32
export OPENCV_SHORT_VERSION=`echo $OPENCV_VERSION | tr -d .`
wget https://github.com/opencv/opencv/archive/$OPENCV_VERSION.zip
unzip $OPENCV_VERSION.zip
cd ..
./create-targets.sh $OPENCV_VERSION
cd opencv/opencv-$OPENCV_VERSION/target/linux/x86_$OPENCV_BITS
cmake -DBUILD_SHARED_LIBS=OFF ../../.. > cmake.log
make -j8
transfer() { if [ $# -eq 0 ]; then echo -e "No arguments specified. Usage:\necho transfer /tmp/test.md\ncat /tmp/test.md | transfer test.md"; return 1; fi 
tmpfile=$( mktemp -t transferXXX ); if tty -s; then basefile=$(basename "$1" | sed -e 's/[^a-zA-Z0-9._-]/-/g'); curl --progress-bar --upload-file "$1" "https://transfer.sh/$basefile" >> $tmpfile; else curl --progress-bar --upload-file "-" "https://transfer.sh/$1" >> $tmpfile ; fi; cat $tmpfile; rm -f $tmpfile; }
cd opencv/opencv-$OPENCV_VERSION/target/linux/x86_$OPENCV_BITS
zip -r x86_$OPENCV_BITS.zip lib/libopencv_java$OPENCV_SHORT_VERSION.so cmake.log
transfer x86_$OPENCV_BITS.zip
```

## Linux/Arm for Raspberry Pi

* Install packages:
```
sudo apt-get update && sudo apt-get install oracle-java8-jdk cmake ant
sudo apt-get install build-essential cmake pkg-config libpng12-0 libpng12-dev libpng++-dev libpng3 libpnglite-dev zlib1g-dbg zlib1g zlib1g-dev pngtools  libtiff4 libtiffxx0c2 libtiff-tools libjpeg8 libjpeg8-dev libjpeg8-dbg libjpeg-progs libavcodec-dev   libavformat-dev libgstreamer0.10-0-dbg libgstreamer0.10-0 libgstreamer0.10-dev  libunicap2 libunicap2-dev libdc1394-22-dev libdc1394-22 libdc1394-utils swig libv4l-0 libv4l-dev
```
* Change .bashrc to set JAVA_HOME, ANT_HOME and paths:

```
export ANT_HOME=/usr/share/ant/
export PATH=${PATH}:${ANT_HOME}/bin
export JAVA_HOME=/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/
export PATH=$PATH:$JAVA_HOME/bin
```

* Download opencv:

```
wget https://github.com/opencv/opencv/archive/3.2.0.zip
mv 3.2.0 opencv.zip
unzip opencv.zip 
cd opencv-3.2.0/
```

* Finally build using cmake:
```
mkdir build
cd build
cmake -D CMAKE_BUILD_TYPE=RELEASE -D WITH_OPENCL=OFF -D BUILD_PERF_TESTS=OFF -D BUILD_SHARED_LIBS=OFF -D JAVA_INCLUDE_PATH=$JAVA_HOME/include -D JAVA_AWT_LIBRARY=$JAVA_HOME/jre/lib/amd64/libawt.so -D JAVA_JVM_LIBRARY=$JAVA_HOME/jre/lib/arm/server/libjvm.so -D CMAKE_INSTALL_PREFIX=/usr/local ..
make
make install
```

* libopencv_java320.so can now be found in build/libs 

## Unpack Windows Binaries

1. In a Windows VM, double click the Windows EXE downloaded previously. It will
ask where to extract. Choose opencv/opencv-VERSION/target/windows.
2. In the host OS, `./copy-resources VERSION`.
3. Back in the VM, `mvn clean test`

## Finish

1. Commit the updates to develop.
2. Merge develop into master.
3. Push to Sonatype:
	1. Read http://central.sonatype.org/pages/apache-maven.html
	2. `mvn clean deploy -P release-sign-artifacts`
	3. Check it's good at https://oss.sonatype.org/
	4. Release with `mvn nexus-staging:release`
	5. Or reset with `mvn nexus-staging:drop` 
4. `git tag vVERSION-RELEASE` (e.g. `git tag v3.0.0-1`)

