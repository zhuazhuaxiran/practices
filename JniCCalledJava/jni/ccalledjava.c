#include "ccalledjava.h"
const char *classPathName="com/example/jniccalledjava/CCalledJava";
const JavaVM *g_jvm;

static JNINativeMethod gMethods[] = {
    { "wrap_main", "()I",(int*) wrap_main }
};

jint JNI_OnLoad(JavaVM* vm, void* reserved){
	g_jvm=vm;
	JNIEnv* env = NULL;
	jint ret = -1;
	ret = (*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4);
	if (ret != JNI_OK) {
		my_log("ERROR: GetEnv failed");
		return -1;
	}

	ret = registerNativeMethods(env, classPathName, gMethods, NELEM(gMethods));
	if (ret != 0) {
		my_log("ERROR: registerNatives failed");
		return -1;
	}
	return JNI_VERSION_1_4;
}

int wrap_main(){
	JNIEnv *env;
	JavaVM *jvm;

	jclass cls;
	jmethodID mid;
	jfieldID fid;
	jobject obj;
	int ret;

	//获取JavaVM对象
	jvm=g_jvm;
	my_log("begin c called java");
	ret = (*jvm)->GetEnv(jvm, (void**) &env, JNI_VERSION_1_4);
		if (ret != JNI_OK) {
			my_log("ERROR: GetEnv failed");
			return -1;
		}
	// 先获得class对象
	cls = (*env)->FindClass(env, classPathName);
	if (cls != NULL) {
		//获取方法ID，通过方法名和签名，调用静态方法
		mid = (*env)->GetStaticMethodID(env, cls, "helloJni",
				" (Ljava/lang/String;)Ljava/lang/String;");
		if (mid != 0) {
			const char *name = "static method called";
			jstring arg = (*env)->NewStringUTF(env, name);
			jstring result = (jstring) (*env)->CallStaticObjectMethod(env, cls,
					mid, arg);
			const char *str = (*env)->GetStringUTFChars(env, result, 0);
			my_log("Result of helloJni:%s\n", str);
			(*env)->ReleaseStringUTFChars(env, result, 0);
		}else{
			my_log("GetStaticMethodID error");
		}
	}else{
		my_log("findlcass error");
		return -1;
	}

	my_log("begin create object");

	/* 新建一个对象*/
	//调用指定的构造函数,构造函数的名称统一为<init>
//	mid = (*env)->GetMethodID(env, cls, "<init>", "(Ljava/lang/String;)V");


//	mid = (*env)->GetMethodID(env, cls, "<init>", "()V");

//	if (mid != 0) {
//		jstring name = (*env)->NewStringUTF(env, "mName create by c");
//		jvalue value;
//		value.l = name;
//		obj = (*env)->NewObjectA(env, cls, mid, &value);

//		obj = (*env)->NewObject(env, cls, mid);
//		if (obj == 0) {
//			my_log("Create object failed");
//			return -1;
//		}
//	}else{
//		my_log("get constructor fail");
//		return -1;
//	}

	my_log("begin GetFieldID");

	//获取属性id，通过属性名和签名
//	fid = (*env)->GetFieldID(env, obj, "mName", "Ljava/lang/String");
//	if (fid != 0) {
//		const char *name = "field name";
//		jstring arg = (*env)->NewStringUTF(env, name);
//		(*env)->SetObjectField(env, obj, fid, arg); //修改属性
//	}

	my_log("begin getName");
	//调用成员方法
	mid = (*env)->GetMethodID(env, cls, "getName", "()Ljava/lang/String;");
	if (mid != 0) {
		jstring result = (jstring) (*env)->CallObjectMethod(env, obj, mid);
		const char* str = (*env)->GetStringUTFChars(env, result, 0);
		my_log("Result of getName:%s\n", str);
		(*env)->ReleaseStringUTFChars(env, result, 0);
	}
	(*jvm)->DestroyJavaVM(jvm);
	return 0;
}
