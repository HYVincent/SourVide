/*
 * TypeDef.h
 *
 *  Created on: 2014年11月5日
 *      Author: Administrator
 */

#ifndef _TYPEDEF_H_
#define _TYPEDEF_H_

#include <jni.h>
#include <android/log.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <sys/time.h>
#include <sys/ioctl.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>
#include <stdarg.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <fcntl.h>
#include "termios.h"
#include <sys/wait.h>

#include <errno.h>
#include <signal.h>
#include <unistd.h>
#include <signal.h>
#include <semaphore.h>
#include <assert.h>


#include <sys/socket.h> 
#include <sys/ioctl.h>                  /*ioctl 命令*/  
//#include <Linux/if_ether.h>             /*ethhdr 结构*/  
  #include <net/if.h>                     /*ifreq 结构*/  
#include <netinet/in.h>                 /*in_addr结构*/  
//#include <Linux/ip.h>                   /*iphdr 结构*/  
//#include <Linux/udp.h>                  /*udphdr 结构*/ 
//#include <Linux/tcp.h>                  /*tcphdr 结构*/  
#include <Linux/socket.h>                  /*tcphdr 结构*/  


typedef signed char     int8;
typedef signed  short   int16;
typedef signed int  int32;

typedef unsigned char   uint8;
typedef unsigned short   uint16;
typedef unsigned int     uint32;

typedef unsigned int     boolean;

#ifndef FALSE
#define FALSE  (0)
#endif

#ifndef false
#define false  (0)
#endif

#ifndef TRUE
#define TRUE (1)
#endif

#ifndef true
#define true (1)
#endif

#ifndef NULL
#define  NULL  (0)
#endif


#define  LOG_TAG    "JNI_NETWORK"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

#define  JNI_LOG_TAG    "JNI_LOG"
#define  JNI_LOGI(...)  __android_log_print(ANDROID_LOG_INFO,JNI_LOG_TAG,__VA_ARGS__)


#endif /* TYPEDEF_H_ */
