cmake_minimum_required(VERSION 3.22.1)

project("sa")

add_library(
        sa
        SHARED
        whisper_jni.cpp
)
