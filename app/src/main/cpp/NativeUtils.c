#include <jni.h>
#include "TypeDef.h"
#include "com_vincent_sourvide_NativeUtils.h"

extern unsigned int g_printf_debug_on;
static  JavaVM *g_jvm = NULL; 
static  jobject g_obj = NULL; 
uint32 SendMsgToUi(int msgType, int DevId, uint8* buff, int len);

extern int UserInit(jint sn, jint group_id, int i);
extern int UserNetWorkExit(void);
extern int UserNetWorkPause(void);
extern int UserNetWorkResume(void);
extern int UserEntryScanMode(void);
extern int UserNetWorkResume(void);
extern int UserAddDevie(uint32 sn);
extern int UserDelDevie(uint32 sn);
extern int UserActiveDevice(uint32 sn);
extern int UserDisActiveDevice(uint32 sn);
extern int UserSendData(uint32 SN, uint16 datalen, uint8 *data);
extern int UserDisActiveAll(void);
extern int UserGetServiceLinkStatus(void);
extern int UserApConfig(uint8 *ssid, uint32 ssid_len, uint8 *password, uint32 ps_len, uint32 add_wifi_flg);
extern int UserCancelAPConfig(void);

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkInit
  (JNIEnv *env, jobject jo,  jint sn, jint group_id)
{

    JNI_LOGI("JNI_INTERFACE: JniNetWorkInit!!!!!!!!");
    //???????JVM???????????????
    (*env)->GetJavaVM(env,&g_jvm);

    //?????????(g_obj = obj)
    g_obj = (*env)->NewGlobalRef(env,jo);

    return UserInit(sn, group_id, 0);
}


JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkApInit
  (JNIEnv *env, jobject jo,   jint sn, jint group_id)
{

    JNI_LOGI("JNI_INTERFACE: JniNetWorkApInit!!!!!!!!");
    //???????JVM??????????????? 
    (*env)->GetJavaVM(env,&g_jvm); 

    //?????????(g_obj = obj) 
    g_obj = (*env)->NewGlobalRef(env,jo); 
    
    g_printf_debug_on = 1;

    return UserInit(0, group_id, 1);
}


JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkExit
  (JNIEnv *env, jobject jo)
{
	if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniNetWorkExit!!!!!!!!");
	}

    (*env)->DeleteGlobalRef(env, g_obj);
    return UserNetWorkExit();
}


JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkPause
  (JNIEnv *env, jobject jo)
{
    int rc = 0;

	if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniNetWorkPause!!!!!!!!");
	}

    rc = UserNetWorkPause();
    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniNetWorkResume
  (JNIEnv *env, jobject jo)
{
    int rc = 0;

    if(g_printf_debug_on != 0)
    {
        JNI_LOGI("JNI_INTERFACE: JniNetWorkResume!!!!!!!!");
    }
    
    rc = UserNetWorkResume();
    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniEntryScanMode
  (JNIEnv *env, jobject jo)
{
    int rc = 0;
    
	if(g_printf_debug_on != 0)
	{
       JNI_LOGI("JNI_INTERFACE: JniEntryScanMode!!!!!!!!");
	}

   //rc = UserEntryScanMode();

    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniExitScanMode
  (JNIEnv *env, jobject jo)
{
    int rc = 0;

	if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniExitScanMode!!!!!!!!");
	}

   // rc = UserExitScanMode();

    return rc;
}


JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniAddDevie
  (JNIEnv *env, jobject jo, jint sn)
{
    int rc = 0;
    
	if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniAddDevie SN = %X!!!!!!!!", sn);
	}

   // rc = UserAddDevie(sn);

    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniDelDevie
  (JNIEnv *env, jobject jo, jint sn)
{
    int rc = 0;

    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniDelDevie SN = %X!!!!!!!!", sn);
	}

   // rc = UserDelDevie(sn);

   return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniActiveDevice
  (JNIEnv *env, jobject jo, jint sn)
{
    int rc = 0;
    
    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniActiveDevice SN = %X!!!!!!!!", sn);
	}

   // rc = UserActiveDevice(sn);

    return rc;
}
    
    
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniDisActiveDevice
  (JNIEnv *env, jobject jo, jint sn)
{
    int rc = 0;
    
    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniDisActiveDevice SN = %X!!!!!!!!", sn);
	}

  // rc = UserDisActiveDevice(sn);

    return rc;
}
    
    
    
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniDisActiveAll
  (JNIEnv *env, jobject jo)
{
    int rc = 0;
    
    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniDisActiveAll!!!!!!!!");
	}

   // rc = UserDisActiveAll();
    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniGetDeviceLinkStatus
  (JNIEnv *env, jobject jo, jint sn)
{
    int rc = 0;
    
   // rc = UserGetDeviceLinkStatus(sn);

    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniGetDeviceLinkStatus SN = %X, rc=%d !!", sn, rc);
	}

    return rc;
}


JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniGetServiceLinkStatus
  (JNIEnv *env, jobject jo)
{
    int rc = 0;
    
    rc = UserGetServiceLinkStatus();

    if(g_printf_debug_on != 0)
   	{
           JNI_LOGI("JNI_INTERFACE: JniGetServiceLinkStatus, rc=%u!!!!!!!!", rc);
   	}
    return rc;
}

//????????
JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniApConfig
    (JNIEnv *env, jobject jo, jbyteArray ssid, jbyteArray password,  jint add_wifi)
{
    int rc = 0;    
    jbyte *jb_ssid;
    jbyte *jb_password;
    int ssid_len;
    int ps_len;

    jb_ssid = (*env)-> GetByteArrayElements(env,ssid,0);
    jb_password = (*env)-> GetByteArrayElements(env,password,0);
        
    ssid_len =  (*env)->GetArrayLength(env, ssid);
    ps_len =  (*env)->GetArrayLength(env, password);

    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniApConfig, ssid_len=%d, ps_len=%d", ssid_len, ps_len);
	}

   // rc = UserApConfig(jb_ssid, ssid_len,  jb_password, ps_len, add_wifi);

    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniCancelAPConfig
(JNIEnv *env, jobject jo)
{
    int rc = 0;

    if(g_printf_debug_on != 0)
	{
        JNI_LOGI("JNI_INTERFACE: JniCancelAPConfig!!!!!!!!");
	}
    
   // UserCancelAPConfig();

    return rc;
}

JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_JniSendData
    (JNIEnv *env, jobject jo, jint sn, jint datalen, jbyteArray data)
{
    int rc = 0;
    jbyte *p;

    p = (*env)-> GetByteArrayElements(env,data,0);  
    
    rc = UserSendData(sn, datalen, p);

    if(g_printf_debug_on != 0)
  	{
          JNI_LOGI("JNI_INTERFACE: send data: sn=%x, len=%d!", sn, datalen);
  	}

    return rc;
}


JNIEXPORT jint JNICALL Java_com_vincent_sourvide_NativeUtils_registerUserInfo
    (JNIEnv *env, jobject jo, jstring user_sn, jstring name, jstring phone, jstring province, jstring city, jstring district, jstring other)
{
    int rc = 0;


    return rc;
}



char MainCmdString[16][64] = {
      "MAIN_MSG_TYPE_GET_APP_SN",
      "MAIN_MSG_TYPE_DATA",
      "MAIN_MSG_TYPE_NETWORK_UP",
      "MAIN_MSG_TYPE_NETWORK_DOWN",
      "MAIN_MSG_TYPE_FIND_NEW_WIFI_DEV",
      "MAIN_MSG_TYPE_MISS_NEW_WIFI_DEV",
      "MAIN_MSG_TYPE_ADD_NEW_WIFI_DEV_SUCCESS",
      "MAIN_MSG_TYPE_DEL_WIFI_DEV_SUCCESS",
      "MAIN_MSG_TYPE_SET_WIFI_PASSWORD_SUCCESS",
      "MAIN_MSG_NULL",
      "MAIN_MSG_TYPE_ONE_SECOND",
      "MAIN_MSG_NULL",
      "MAIN_MSG_NULL",
      "MAIN_MSG_NULL",
      "MAIN_MSG_NULL",
      "MAIN_MSG_NULL"};
                                                 
 uint32 SendMsgToUi(int msgType, int DevId, uint8* buff, int len)
 {
    #if 1
     int i;
     JNIEnv *JniEnv; 
     jclass JNIJavacClass; 
     jfieldID   JvBuffId;
     jmethodID CtoJavaId;

      //Attach????? 
     if((*g_jvm)->AttachCurrentThread(g_jvm, &JniEnv, NULL) != JNI_OK) 
     { 
         LOGI("%s: AttachCurrentThread() failed", __FUNCTION__); 
         return false;
     } 

      //?????????? 
     JNIJavacClass = (*JniEnv)->GetObjectClass(JniEnv,g_obj);
     if(JNIJavacClass == NULL) 
     { 
         LOGI("FindClass() Error....."); 
         goto error; 
     } 

     CtoJavaId = (*JniEnv)->GetStaticMethodID(JniEnv, JNIJavacClass,"SendMsgToUi","(III[B)V");//????java?§Ö?show??????ID??????????????void????  
     JvBuffId  = (*JniEnv)->GetFieldID(JniEnv,  JNIJavacClass, "CtoJvBuff", "[B");
     
     jbyteArray  array =   (*JniEnv)->GetObjectField(JniEnv, g_obj, JvBuffId);

      jsize length = (*JniEnv)->GetArrayLength(JniEnv, array);

      if(length < len)
      {
          goto error;
      }

      (*JniEnv)->SetByteArrayRegion(JniEnv, array, 0, len, (jbyte*)buff);

    // jbyte *pointer = (*JniEnv)->GetByteArrayElements(JniEnv, array, NULL); //??????????
     
    //for (i=0; i<len; i++)
    //{
    //    pointer[i] = buff[i]; //?????????????
    //}
    
    //(*JniEnv)->ReleaseByteArrayElements(JniEnv, array, pointer, 0); //?????—¨???????????
    
    (*JniEnv)->CallStaticVoidMethod(JniEnv, JNIJavacClass,CtoJavaId,msgType,DevId, len, array);

    if(g_printf_debug_on != 0)
    {
            JNI_LOGI("SendMsgToUi: msgType=%s, DevId=%X, len=%d", MainCmdString[msgType], DevId, len);
    }

error:   
    
      if((*g_jvm)->DetachCurrentThread(g_jvm) != JNI_OK) 
     { 
         LOGI("%s: DetachCurrentThread() failed", __FUNCTION__); 
     } 

     #endif
     return true;
 }
     


