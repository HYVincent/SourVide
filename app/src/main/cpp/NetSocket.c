
#include <jni.h>

#include "TypeDef.h"
#include "NetSocket.h"

pthread_t  g_udp_thread_id= -1;
pthread_t  g_time_thread_id= -1;

uint32 g_printf_debug_on=1;
int g_inited = 0;
AppInfo_t g_AppInfo;

uint8 rcv_buff[SOCKET_RCV_BUFF_LEN];

uint32 UserInit(uint32 sn, uint32 grp_id, uint32 mode)
{
    uint32 rc = 0;

    if(g_inited == 0)
    {
        memset(&g_AppInfo, 0, sizeof(AppInfo_t));
        g_AppInfo.isRun = 1;
        
        rc = pthread_create(&g_udp_thread_id, NULL, (void *)&ReciveThread, (void *)NULL);
        if(rc != 0)
        {
            printf("\n udp_ser_init: create udp  thread err = %d", rc);
            return -1;
        } 

        rc = pthread_create(&g_time_thread_id, NULL, (void *)&TimerThread, (void *)NULL);
        if(rc != 0)
        {
            printf("\n udp_ser_init: create udp  thread err = %d", rc);
            return -1;
        } 
    }

     g_inited =1;
     
    return 0;
}


fd_set sock_fds, save_fds;
uint32 ReciveThread( void )
{    
    struct timeval time_cnt;    
    int sock_result;    
    int fd;
    int nread;
    int len;
  
     FD_ZERO(&sock_fds);
    create_socket();
        
    while(g_AppInfo.isRun)
    {      
        time_cnt.tv_sec = 0;
        time_cnt.tv_usec = 100000;  /*100ms*/
        save_fds = sock_fds;

        sock_result = select(FD_SETSIZE, &save_fds, (fd_set *)0, (fd_set *)0, (struct timeval *) (&time_cnt));
        if(sock_result == 0)
        {
            continue;
        }

        if(sock_result < 0)
        {
            continue;
        }

        if(FD_ISSET( g_AppInfo.socket_fd, &save_fds))
        {
            ioctl(g_AppInfo.socket_fd, FIONREAD, &nread);
            if(nread > 0)
            {   
                if(nread <= SOCKET_RCV_BUFF_LEN)
                {
                    len= read(g_AppInfo.socket_fd, rcv_buff, nread);
                }
                else
                {
                    len = read(g_AppInfo.socket_fd, rcv_buff, SOCKET_RCV_BUFF_LEN);
                }

                if(rcv_buff[2] != 0x80)
                {
                    SendMsgToUi(MAIN_MSG_TYPE_DATA, 0, rcv_buff, len);
                }
            }
            else
            {         
                close_socket();
            }
        }    
    }

    close_socket();
    return 0;
}




uint32 TimerThread( void )
{
    uint32 timer_cnt = 0;

    while(g_AppInfo.isRun)
    {
        sys_delay(1000);
        
        timer_cnt++;        
        if((g_AppInfo.socket_fd <= 0) && (g_AppInfo.isPause == 0))
        {
            create_socket();
        }
        
        if((timer_cnt % 3) == 0)
        {
            sendKeepAlivePkt();
        }
    }
}



int create_socket(void)
{
    int addr_len;
    int rc = 0;
    struct sockaddr_in servaddr;
    int reuse=1;
    uint16 src_port = 6354;
    int fd;

    if(g_AppInfo.socket_fd > 0)
    {
         return 0;
    }

    fd = socket(AF_INET, SOCK_STREAM, 0);
    if(fd <= 0)
    {
        JNI_LOGI("udp socket create error: \n");
        return -1;
    }

      bzero(&servaddr, sizeof(servaddr));
     servaddr.sin_family = AF_INET;
     servaddr.sin_addr.s_addr = inet_addr("192.168.1.1");
     servaddr.sin_port = htons(src_port);

     reuse = connect(fd, (struct sockaddr *)&servaddr, sizeof(servaddr));
     if(reuse != 0)
     {
         g_AppInfo.socket_fd = -1;
         SendMsgToUi(MAIN_MSG_TYPE_NETWORK_DOWN, 0, NULL, 0);
     }
     else
     {
         g_AppInfo.socket_fd = fd;         
         SendMsgToUi(MAIN_MSG_TYPE_NETWORK_UP, 0, NULL, 0);
         FD_SET(g_AppInfo.socket_fd, &sock_fds);
     }

      return 0;
}


int close_socket(void)
{
    if(g_AppInfo.socket_fd > 0)
    {
        FD_CLR(g_AppInfo.socket_fd, &sock_fds);
        close(g_AppInfo.socket_fd);
        g_AppInfo.socket_fd = -1;
        SendMsgToUi(MAIN_MSG_TYPE_NETWORK_DOWN, 0, NULL, 0);
    }
    return 0;
}



uint32  UserNetWorkExit(void)
{
    if(g_inited == 1)
    {
        g_AppInfo.isRun = 0;

        sys_delay(100);
        if(g_udp_thread_id > 0)
        {
            pthread_join(g_udp_thread_id ,NULL);
        }

        if(g_time_thread_id > 0)
        {
            pthread_join(g_time_thread_id ,NULL);
        }
    }
    
    g_inited = 0;
}


uint32 UserGetServiceLinkStatus(void)
{
    if(g_AppInfo.socket_fd > 0)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}


void sendKeepAlivePkt(void)
{
    uint8 buff[8];
    buff[0] = 0x55;
    buff[1] = 0xaa;
    buff[2] = 0x80;
    buff[3] = 0x00;
    buff[4] = 0x11;    
    buff[5] = 0x00;
    buff[6] = 0x00;

    UserSendData(0, 7, buff);
}


uint32 UserSendData(uint32 sn, uint32 datalen, uint8 *buff)
{
    
    if((g_AppInfo.socket_fd > 0) && (buff != NULL) && (datalen > 0))
    {
         write(g_AppInfo.socket_fd, buff, datalen);
    }
}

void sys_delay(uint32 delay)
{
    struct timeval time_cnt;
    
    time_cnt.tv_sec = delay/1000;
    time_cnt.tv_usec = (delay % 1000) * 1000;  /*1ms*/
    select(0, NULL,  NULL, NULL, (struct timeval *) (&time_cnt));
}

uint32 UserNetWorkPause(void)
{
    g_AppInfo.isPause = 1;
    close_socket();
    return 0;
}


uint32 UserNetWorkResume(void)
{
    g_AppInfo.isPause = 0;
    if(g_AppInfo.socket_fd <= 0)
    {
        create_socket();
    }
}



