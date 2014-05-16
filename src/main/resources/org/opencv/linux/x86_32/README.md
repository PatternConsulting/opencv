# Linux x86_32

## Preparation

### Command

```shell
cmake -DBUILD_SHARED_LIBS=OFF ..
```

## Output

```
-- Detected version of GNU GCC: 46 (406)
-- Could NOT find ZLIB (missing:  ZLIB_LIBRARY ZLIB_INCLUDE_DIR) 
-- Could NOT find TIFF (missing:  TIFF_LIBRARY TIFF_INCLUDE_DIR) 
-- Could NOT find JPEG (missing:  JPEG_LIBRARY JPEG_INCLUDE_DIR) 
-- Could NOT find Jasper (missing:  JASPER_LIBRARY JASPER_INCLUDE_DIR) 
-- Could NOT find PNG (missing:  PNG_LIBRARY PNG_PNG_INCLUDE_DIR) 
-- checking for module 'gtk+-2.0'
--   package 'gtk+-2.0' not found
-- checking for module 'gthread-2.0'
--   package 'gthread-2.0' not found
-- checking for module 'gstreamer-base-0.10'
--   package 'gstreamer-base-0.10' not found
-- checking for module 'libdc1394-2'
--   package 'libdc1394-2' not found
-- checking for module 'libdc1394'
--   package 'libdc1394' not found
-- checking for module 'libv4l1'
--   package 'libv4l1' not found
-- Looking for linux/videodev.h
-- Looking for linux/videodev.h - not found
-- Looking for linux/videodev2.h
-- Looking for linux/videodev2.h - found
-- Looking for sys/videoio.h
-- Looking for sys/videoio.h - not found
-- checking for module 'libavcodec'
--   package 'libavcodec' not found
-- checking for module 'libavformat'
--   package 'libavformat' not found
-- checking for module 'libavutil'
--   package 'libavutil' not found
-- checking for module 'libswscale'
--   package 'libswscale' not found
-- Looking for libavformat/avformat.h
-- Looking for libavformat/avformat.h - not found
-- Looking for ffmpeg/avformat.h
-- Looking for ffmpeg/avformat.h - not found
-- Could NOT find PythonLibs (missing:  PYTHON_INCLUDE_DIRS) (Required is at least version "2.7.3")
Traceback (most recent call last):
  File "<string>", line 1, in <module>
ImportError: No module named numpy.distutils
-- Found apache ant 1.8.2: /usr/bin/ant
-- 
-- General configuration for OpenCV 2.4.9 =====================================
--   Version control:               2.4.9-57-g0ebde64
-- 
--   Platform:
--     Host:                        Linux 3.11.0-15-generic i686
--     CMake:                       2.8.7
--     CMake generator:             Unix Makefiles
--     CMake build tool:            /usr/bin/make
--     Configuration:               Release
-- 
--   C/C++:
--     Built as dynamic libs?:      NO
--     C++ Compiler:                /usr/bin/c++  (ver 4.6)
--     C++ flags (Release):         -fPIC   -W -Wall -Werror=return-type -Werror=address -Werror=sequence-point -Wformat -Werror=format-security -Wmissing-declarations -Wundef -Winit-self -Wpointer-arith -Wshadow -Wsign-promo -fdiagnostics-show-option -pthread -march=i686 -fomit-frame-pointer -msse -msse2 -msse3 -mfpmath=sse -ffunction-sections   -DNDEBUG
--     C++ flags (Debug):           -fPIC   -W -Wall -Werror=return-type -Werror=address -Werror=sequence-point -Wformat -Werror=format-security -Wmissing-declarations -Wundef -Winit-self -Wpointer-arith -Wshadow -Wsign-promo -fdiagnostics-show-option -pthread -march=i686 -fomit-frame-pointer -msse -msse2 -msse3 -mfpmath=sse -ffunction-sections   -O0 -DDEBUG -D_DEBUG
--     C Compiler:                  /usr/bin/gcc
--     C flags (Release):           -fPIC   -fsigned-char -W -Wall -Werror=return-type -Werror=address -Werror=sequence-point -Wformat -Werror=format-security -Wmissing-declarations -Wmissing-prototypes -Wstrict-prototypes -Wundef -Winit-self -Wpointer-arith -Wshadow -fdiagnostics-show-option -pthread -march=i686 -fomit-frame-pointer -msse -msse2 -msse3 -mfpmath=sse -ffunction-sections -O2 -DNDEBUG  -DNDEBUG
--     C flags (Debug):             -fPIC   -fsigned-char -W -Wall -Werror=return-type -Werror=address -Werror=sequence-point -Wformat -Werror=format-security -Wmissing-declarations -Wmissing-prototypes -Wstrict-prototypes -Wundef -Winit-self -Wpointer-arith -Wshadow -fdiagnostics-show-option -pthread -march=i686 -fomit-frame-pointer -msse -msse2 -msse3 -mfpmath=sse -ffunction-sections -g  -O0 -DDEBUG -D_DEBUG
--     Linker flags (Release):      
--     Linker flags (Debug):        
--     Precompiled headers:         YES
-- 
--   OpenCV modules:
--     To be built:                 core flann imgproc highgui features2d calib3d ml video legacy objdetect photo gpu ocl nonfree contrib java stitching superres ts videostab
--     Disabled:                    world
--     Disabled by dependency:      -
--     Unavailable:                 androidcamera dynamicuda python viz
-- 
--   GUI: 
--     QT:                          NO
--     GTK+ 2.x:                    NO
--     GThread :                    NO
--     GtkGlExt:                    NO
--     OpenGL support:              NO
--     VTK support:                 NO
-- 
--   Media I/O: 
--     ZLib:                        zlib (ver 1.2.7)
--     JPEG:                        libjpeg (ver 62)
--     PNG:                         build (ver 1.5.12)
--     TIFF:                        build (ver 42 - 4.0.2)
--     JPEG 2000:                   build (ver 1.900.1)
--     OpenEXR:                     build (ver 1.7.1)
-- 
--   Video I/O:
--     DC1394 1.x:                  NO
--     DC1394 2.x:                  NO
--     FFMPEG:                      NO
--       codec:                     NO
--       format:                    NO
--       util:                      NO
--       swscale:                   NO
--       gentoo-style:              NO
--     GStreamer:                   NO
--     OpenNI:                      NO
--     OpenNI PrimeSensor Modules:  NO
--     PvAPI:                       NO
--     GigEVisionSDK:               NO
--     UniCap:                      NO
--     UniCap ucil:                 NO
--     V4L/V4L2:                    NO/YES
--     XIMEA:                       NO
--     Xine:                        NO
-- 
--   Other third-party libraries:
--     Use IPP:                     NO
--     Use Eigen:                   NO
--     Use TBB:                     NO
--     Use OpenMP:                  NO
--     Use GCD                      NO
--     Use Concurrency              NO
--     Use C=:                      NO
--     Use Cuda:                    NO
--     Use OpenCL:                  YES
-- 
--   OpenCL:
--     Version:                     dynamic
--     Include path:                /home/user/Projects/itseez-opencv/3rdparty/include/opencl/1.2
--     Use AMD FFT:                 NO
--     Use AMD BLAS:                NO
-- 
--   Python:
--     Interpreter:                 /usr/bin/python (ver 2.7.3)
-- 
--   Java:
--     ant:                         /usr/bin/ant (ver 1.8.2)
--     JNI:                         /usr/lib/jvm/java-8-oracle/include /usr/lib/jvm/java-8-oracle/include/linux /usr/lib/jvm/java-8-oracle/include
--     Java tests:                  YES
-- 
--   Documentation:
--     Build Documentation:         NO
--     Sphinx:                      NO
--     PdfLaTeX compiler:           NO
-- 
--   Tests and samples:
--     Tests:                       YES
--     Performance tests:           YES
--     C/C++ Examples:              NO
-- 
--   Install path:                  /usr/local
-- 
--   cvconfig.h is in:              /home/user/Projects/itseez-opencv/build
-- -----------------------------------------------------------------
-- 
-- Configuring done
-- Generating done
-- Build files have been written to: /home/user/Projects/itseez-opencv/build
```

## Build

```shell
make -j8
```
