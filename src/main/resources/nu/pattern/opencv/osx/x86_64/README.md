# OS X x86_64

```shell
git clone git://github.com/Itseez/opencv.git itseez-opencv
cd itseez-opencv
git checkout 2.4
mkdir build
cd build
cmake -DBUILD_SHARED_LIBS=OFF -DCMAKE_OSX_ARCHITECTURES=x86_64 -DCMAKE_C_FLAGS=-m64 -DCMAKE_CXX_FLAGS=-m64 .. > cmake.log
make -j8
```
