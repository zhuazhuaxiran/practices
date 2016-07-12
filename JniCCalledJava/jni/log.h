#ifndef NATIVEHELPER_LOG_H_
#define NATIVEHELPER_LOG_H_

#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>
#include <jni.h>
#include <stdarg.h>
#define TAG_DEBUG		"JNITESTDONG"

#define STRLEN 256

#define LOGE(s) __android_log_print(ANDROID_LOG_INFO, TAG_DEBUG, "%s",s)
#ifdef __cplusplus
extern "C"
{
#endif

void my_log(const char *format, ...);

#ifdef  __cplusplus
}
#endif

#endif
