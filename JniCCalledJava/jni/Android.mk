LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS :=	-llog					
LOCAL_MODULE    := ccalledjava
LOCAL_SRC_FILES := ccalledjava.c		\
					log.c				\
					jnihelp.c

include $(BUILD_SHARED_LIBRARY)
