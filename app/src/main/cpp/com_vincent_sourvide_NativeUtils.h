/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_vincent_sourvide_NativeUtils */

#ifndef _Included_com_vincent_sourvide_NativeUtils
#define _Included_com_vincent_sourvide_NativeUtils
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniNetWorkInit
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkInit
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniNetWorkExit
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkExit
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniNetWorkPause
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkPause
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniNetWorkResume
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkResume
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniEntryScanMode
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniEntryScanMode
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniExitScanMode
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniExitScanMode
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniAddDevie
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniAddDevie
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniDelDevie
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniDelDevie
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniActiveDevice
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniActiveDevice
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniDisActiveDevice
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniDisActiveDevice
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniDisActiveAll
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniDisActiveAll
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniGetDeviceLinkStatus
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniGetDeviceLinkStatus
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniGetServiceLinkStatus
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniGetServiceLinkStatus
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniApConfig
 * Signature: ([B[BI)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniApConfig
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jint);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniCancelAPConfig
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniCancelAPConfig
  (JNIEnv *, jclass);

/*
 * Class:     com_vincent_sourvide_NativeUtils
 * Method:    JniSendData
 * Signature: (II[B)I
 */
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniSendData
  (JNIEnv *, jclass, jint, jint, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif