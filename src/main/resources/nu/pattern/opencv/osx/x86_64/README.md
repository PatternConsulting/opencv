# OS X x86_64

```shell
cmake -DBUILD_SHARED_LIBS=OFF -DEIGEN_INCLUDE_PATH=/usr/local/include/eigen3 ..
```

```shell
git clone git://github.com/Itseez/opencv.git itseez-opencv
cd itseez-opencv
git checkout 2.4
mkdir build
cd build
cmake -DBUILD_SHARED_LIBS=OFF -DCMAKE_OSX_ARCHITECTURES=x86_64 -DCMAKE_C_FLAGS=-m64 -DCMAKE_CXX_FLAGS=-m64 .. > cmake.log
make -j8
```
