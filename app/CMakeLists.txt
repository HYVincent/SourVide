# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

add_library(    # 需要生成的so文件名
                NativeUtils
                SHARED
                src/main/cpp/NetSocket.c # 配置源文件路径，
                src/main/cpp/NativeUtils.c)

find_library(
              log-lib
              log )



target_link_libraries( # 注意：这里的名称要和add_library的名称一致，否则会报错，无法生成so库
                        NativeUtils
                       # src/main/cpp/NetSocket.c
                       ${log-lib} )