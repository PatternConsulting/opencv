VERSION=$1
SHORT_VERSION=`echo $VERSION | tr -d .`
BASE_DIR=opencv/opencv-$VERSION

echo "Version: $VERSION"
echo "Short Version: $SHORT_VERSION"
echo "Base Dir: $BASE_DIR"

# Java
echo "Cleaning up Java..."
rm upstream/*.jar
rm -rf upstream/res/*
echo "Copying Java..."
cp $BASE_DIR/target/osx/x86_64/bin/opencv-$SHORT_VERSION.jar upstream
cp $BASE_DIR/target/osx/x86_64/modules/java/pure_test/.build/build/jar/opencv-test.jar upstream
cp -r $BASE_DIR/target/osx/x86_64/modules/java/pure_test/.build/res/* upstream/res

# OSX
echo "Cleaning up OSX..."
rm src/main/resources/nu/pattern/opencv/osx/x86_64/cmake.log
rm src/main/resources/nu/pattern/opencv/osx/x86_64/*.dylib
echo "Copying OSX..."
cp $BASE_DIR/target/osx/x86_64/cmake.log src/main/resources/nu/pattern/opencv/osx/x86_64
cp $BASE_DIR/target/osx/x86_64/lib/libopencv_java$SHORT_VERSION.dylib src/main/resources/nu/pattern/opencv/osx/x86_64

# Linux
echo "Cleaning up Linux x86_32..."
rm src/main/resources/nu/pattern/opencv/linux/x86_32/cmake.log
rm src/main/resources/nu/pattern/opencv/linux/x86_32/*.so
echo "Copying Linux x86_32..."
cp $BASE_DIR/target/linux/x86_32/cmake.log src/main/resources/nu/pattern/opencv/linux/x86_32
cp $BASE_DIR/target/linux/x86_32/lib/libopencv_java$SHORT_VERSION.so src/main/resources/nu/pattern/opencv/linux/x86_32

echo "Cleaning up Linux x86_64..."
rm src/main/resources/nu/pattern/opencv/linux/x86_64/cmake.log
rm src/main/resources/nu/pattern/opencv/linux/x86_64/*.so
echo "Copying Linux x86_64..."
cp $BASE_DIR/target/linux/x86_64/cmake.log src/main/resources/nu/pattern/opencv/linux/x86_64
cp $BASE_DIR/target/linux/x86_64/lib/libopencv_java$SHORT_VERSION.so src/main/resources/nu/pattern/opencv/linux/x86_64

# Windows
echo "Cleaning up Windows..."
rm src/main/resources/nu/pattern/opencv/windows/x86_32/*.dll
rm src/main/resources/nu/pattern/opencv/windows/x86_64/*.dll
echo "Copying Windows..."
cp $BASE_DIR/target/windows/opencv/build/java/x86/opencv_java$SHORT_VERSION.dll src/main/resources/nu/pattern/opencv/windows/x86_32
cp $BASE_DIR/target/windows/opencv/build/java/x64/opencv_java$SHORT_VERSION.dll src/main/resources/nu/pattern/opencv/windows/x86_64

