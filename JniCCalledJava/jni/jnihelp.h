#ifndef NATIVEHELPER_JNIHELP_H_
#define NATIVEHELPER_JNIHELP_H_


#include "log.h"
#ifndef NELEM
# define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))
#endif

#ifdef __cplusplus
extern "C" {
#endif

int registerNativeMethods(JNIEnv *env,const char *className,
		JNINativeMethod *gMethods,int numMethods);

/*
 * Register one or more native methods with a particular class.
 * "className" looks like "java/lang/String".
 */
int jniRegisterNativeMethods(JNIEnv* env, const char* className, const JNINativeMethod* gMethods, int numMethods);


#ifdef __cplusplus
}
#endif

#endif
