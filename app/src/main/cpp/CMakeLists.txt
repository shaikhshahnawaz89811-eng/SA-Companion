cmake_minimum_required(VERSION 3.22.1)

project("sa")

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(
        whisper/ggml
)

add_library(
        sa
        SHARED
        whisper_jni.cpp
        whisper/src/whisper.cpp
)

target_include_directories(
        sa
        PRIVATE
        whisper/include
        whisper/ggml/include
)

target_link_libraries(
        sa
        ggml
        log
        android
)
