package com.vincent.sourvide.data;

public class TimerTag {

	public static final byte MASK_ONOFF=0x01;
	public static final byte MASK_MODE=0x06;
	public static final byte MASK_START_EN=0x08;
	public static final byte MASK_END_EN=0x10;
	
	
	public static final byte WEEK_MASK_7=0x01;
	public static final byte WEEK_MASK_1=0x02;
	public static final byte WEEK_MASK_2=0x04;
	public static final byte WEEK_MASK_3=0x08;
	public static final byte WEEK_MASK_4=0x10;
	public static final byte WEEK_MASK_5=0x20;
	public static final byte WEEK_MASK_6=0x40;

	public static final byte WEEK_MASK_TOTAL=0x7f;
	
	public static final byte TIMER_REPEAT_ONCE=0x00;
	public static final byte TIMER_REPEAT_EVERYWEEK=0x01;
	public static final byte TIMER_REPEAT_EVERYDAY=0x02;
	
	
	public static final int MAX_TIMER=7;
	public static final int TIMER_TAG_SIZE=6;
	
	public boolean onoff;
	public int mode;  //0�����Σ�1��ÿ�죬2ÿ��
	public boolean startEnable;  //��ʼʹ��
	public boolean endEnable;   //����ʹ�ܱ���
	
	public int weekMask; //������Ϊ0;//bit0~bit6,  ������Ϊbit0;
	
	public int startHour;
	public int startMin;
	//public int startSec;

	public int endHour;
	public int endMin;
	//public int endSec;


	/*
	uint8 mode:2;  //0-��Ч��1-���Σ�2-�ظ���
    uint8 start_en:1;  //��ʼʹ��
    uint8 end_en:1;  //����ʹ�ܱ���
    uint8 bit_rserv:4;  //
	uint8 week_bitmap; //bit0~bit6,  ������Ϊbit0;

    uint8 start_hour;
    uint8 start_min;
    uint8 start_sec;

    uint8 end_hour;
    uint8 end_min;
    uint8 end_sec;*/

	public TimerTag()
	{
		
		mode = TIMER_REPEAT_ONCE;
		startEnable = true;
		endEnable = true;
		
		weekMask = 0x02;
		
		startHour = 0x10;
		startMin = 0x11;
		

		endHour = 0x17;
		endMin = 0x18;
		
		onoff = false;
		
		
	}
	
	public TimerTag(TimerTag tt)
	{
		
		mode = tt.mode;
		startEnable = tt.startEnable;
		endEnable = tt.endEnable;
		
		weekMask = tt.weekMask;
		
		startHour = tt.startHour;
		startMin = tt.startMin;
		

		endHour = tt.endHour;
		endMin = tt.endMin;
		
		onoff = tt.onoff;
		
	}
	
	public static void initDefaultTimers(TimerTag[] timers)
	{
		for(int i = 0; i < timers.length; i++)
		{
			TimerTag tt = new TimerTag();
			timers[i]=tt;
		}
	}
	
}
