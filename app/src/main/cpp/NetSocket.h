
#ifndef _NET_SOCKET_H_
#define _NET_SOCKET_H_

#define SOCKET_RCV_BUFF_LEN  (512)


#define  MAIN_MSG_TYPE_GET_APP_SN     (0)  //
#define  MAIN_MSG_TYPE_DATA   (1)
#define  MAIN_MSG_TYPE_NETWORK_UP  (2)
#define  MAIN_MSG_TYPE_NETWORK_DOWN  (3)
#define  MAIN_MSG_TYPE_FIND_NEW_WIFI_DEV (4)
#define  MAIN_MSG_TYPE_MISS_NEW_WIFI_DEV (5)  //发现一个wifi设备
#define  MAIN_MSG_TYPE_ADD_NEW_WIFI_DEV_SUCCESS  (6)  //增加一个wifi device
#define  MAIN_MSG_TYPE_DEL_WIFI_DEV_SUCCESS     (7)          //删除一个wifi device
#define  MAIN_MSG_TYPE_SET_WIFI_PASSWORD_SUCCESS     (8)  //删除一个wifi device

#define  MAIN_MSG_TYPE_ONE_SECOND     (10)                           //删除一个wifi device



typedef struct
{
     int socket_fd;     
     uint32 isRun;
     uint32 isPause;
     uint16 send_index;
}AppInfo_t;


#pragma pack(1)
typedef struct
{
    uint8 h55aa[2];
    uint8 ucCmd;
    uint8 ucDatalen;
    uint8 ucDevType;
    uint16 usindex;
}led_tcp_pkt_t;
#pragma pack()


uint32 UserInit(uint32 sn, uint32 grp_id, uint32 mode);
uint32 ReciveThread( void );
uint32 TimerThread( void );
int create_socket(void);
int close_socket(void);
uint32  UserNetWorkExit(void);
uint32 UserGetServiceLinkStatus(void);
void sys_delay(uint32 delay);
uint32 UserSendData(uint32 sn, uint32 datalen, uint8 *buff);
void sendKeepAlivePkt(void);
uint32 UserNetWorkPause(void);
uint32 UserNetWorkResume(void);

#endif

