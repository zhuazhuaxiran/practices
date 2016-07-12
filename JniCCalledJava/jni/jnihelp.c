#include "jnihelp.h"


/*Register several native methods for one class*/
int registerNativeMethods(JNIEnv *env,const char *className,
		JNINativeMethod *gMethods,int numMethods){

	 return jniRegisterNativeMethods(env, className, gMethods, numMethods);
}



static jclass findClass(JNIEnv* env, const char* className) {
    return (*env)->FindClass(env, className);
}

int jniRegisterNativeMethods(JNIEnv* env, const char* className,
    const JNINativeMethod* gMethods, int numMethods)
{

//	my_log("Registering %s natives", className);
	my_log("Registering  natives");

    jclass clazz= findClass(env, className);
    if(clazz==NULL){
    	my_log("clazz==NULL");
    	return -1;
    }
    /**
     * jint RegisterNatives(JNIEnv *env, jclass clazz,
				const JNINativeMethod *methods, jint nMethods);
     */
    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
//        my_log("RegisterNatives failed for '%s'", className);
    	  my_log("RegisterNatives failed for ");
        return -1;
    }

    return 0;
}
