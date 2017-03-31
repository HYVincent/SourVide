package com.vincent.sourvide.data;



public class EairInfo 
{
    private static final long serialVersionUID = 0x63b1987cf974b596L;
    
    public int deviceType;
    public int sn;
    public boolean powerOn;
    
    public boolean init;
    
    public int set_hour;
    public int set_min;
    public int real_temp;
    public int set_temp;
    public int on_off;
    public int work_stop;
    public int error1;
    public int error2;
    public int error3;
    public int error4;

    //public int workmode; //����ģʽ��1--��ˮ��2--���£�3--������4--ȫ�Զ�������5--ȫ�Զ���ˮ

    //public boolean isBoil;  //��ˮ
    //public boolean isPump;  //��ˮ
   // public boolean isWarm; //����
    //public boolean isKill; //����
    //public boolean isAutoKill; //ȫ�Զ�����
    //public boolean isAutoBoil; //ȫ����ˮ

    //public int fixTemp;
    //public int temperature;     //ʵ��ˮ��
    //public int temperature_cfg; //�趨 �¶�
    
    //public int waterLevel;
    //public boolean motorState; //���״̬
    //public boolean hasBottle;   //ˮ��
    
    //public static final int WORK_MODE_IDLE = 0;
    //public static final int WORK_MODE_BOIL = 1;
    //public static final int WORK_MODE_WARM = 2;
    //public static final int WORK_MODE_PUMP = 3;
    //public static final int WORK_MODE_KILL = 4;
    //public int errorCode;
    
    
    
    public EairInfo()
    {
    	
    	fakeValue();
    }

    public void fakeValue()
    {
    	set_hour = 0;
    	set_min  = 10;
    	work_stop = 0;
    	on_off = 0;
    	real_temp = 0;
    	set_temp = 40;
    	init = false;
    }
    
}


