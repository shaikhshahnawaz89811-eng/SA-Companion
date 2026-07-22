#include <jni.h>
#include <string>
#include "whisper.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sa_companion_ai_WhisperEngine_test(
        JNIEnv *env,
        jobject
) {
    std::string text = "Whisper.cpp library loaded";
    return env->NewStringUTF(text.c_str());
}
