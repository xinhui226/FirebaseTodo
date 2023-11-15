#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
extern "C" jstring
Java_com_xinhui_mob201_core_utils_NativeUtils_greet(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("Hello from Native (CPP)");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_xinhui_mob201_core_utils_NativeUtils_provideWebClientId(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("234694547372-opeqgbmai4crea3i7jvqht1u7vqh5dni.apps.googleusercontent.com");
}