/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class slac_aida_NativeChannelProvider */

#ifndef _Included_slac_aida_NativeChannelProvider
#define _Included_slac_aida_NativeChannelProvider
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaServiceInit
 * Signature: ()V
 */
JNIEXPORT void
JNICALL Java_slac_aida_NativeChannelProvider_aidaServiceInit
  (JNIEnv *, jclass);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaChannelConfig
 * Signature: (Ljava/lang/String;)Ledu/stanford/slac/aida/lib/model/AidaChannelConfig;
 */
JNIEXPORT jobject JNICALL Java_slac_aida_NativeChannelProvider_aidaChannelConfig
  (JNIEnv *, jobject, jstring);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestTable
 * Signature: (Ljava/lang/String;Ljava/util/List;)Ledu/stanford/slac/aida/lib/model/AidaTable;
 */
JNIEXPORT jobject JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestTable
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestBoolean
 * Signature: (Ljava/lang/String;Ljava/util/List;)Z
 */
JNIEXPORT jboolean JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestBoolean
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestByte
 * Signature: (Ljava/lang/String;Ljava/util/List;)B
 */
JNIEXPORT jbyte JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestByte
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestShort
 * Signature: (Ljava/lang/String;Ljava/util/List;)S
 */
JNIEXPORT jshort JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestShort
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestInteger
 * Signature: (Ljava/lang/String;Ljava/util/List;)I
 */
JNIEXPORT jint JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestInteger
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestLong
 * Signature: (Ljava/lang/String;Ljava/util/List;)J
 */
JNIEXPORT jlong JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestLong
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestFloat
 * Signature: (Ljava/lang/String;Ljava/util/List;)F
 */
JNIEXPORT jfloat JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestFloat
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestDouble
 * Signature: (Ljava/lang/String;Ljava/util/List;)D
 */
JNIEXPORT jdouble JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestDouble
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestString
 * Signature: (Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestString
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestBooleanArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[Z
 */
JNIEXPORT jbooleanArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestBooleanArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestByteArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestByteArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestShortArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[S
 */
JNIEXPORT jshortArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestShortArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestIntegerArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[I
 */
JNIEXPORT jintArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestIntegerArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestLongArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[J
 */
JNIEXPORT jlongArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestLongArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestFloatArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[F
 */
JNIEXPORT jfloatArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestFloatArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestDoubleArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestDoubleArray
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     slac_aida_NativeChannelProvider
 * Method:    aidaRequestStringArray
 * Signature: (Ljava/lang/String;Ljava/util/List;)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_slac_aida_NativeChannelProvider_aidaRequestStringArray
  (JNIEnv *, jobject, jstring, jobject);

#ifdef __cplusplus
}
#endif
#endif
