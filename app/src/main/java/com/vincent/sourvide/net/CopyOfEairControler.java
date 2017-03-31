package com.vincent.sourvide.net;

import android.content.Context;

import com.vincent.sourvide.data.EairInfo;

public class CopyOfEairControler 
{
	private static byte SENSOR_STATE_MASK_O3 = 0x01;
	private static byte SENSOR_STATE_MASK_O1 = 0x02;
	private static byte SENSOR_STATE_MASK_UV = 0x04;
	private static byte SENSOR_STATE_MASK_DUST = 0x08;
	private static byte SENSOR_STATE_MASK_GAS = 0x10;
	
	private static int STATE_DATA_INDEX_PM25 = 0;
	private static int STATE_DATA_INDEX_HCHO = 2;
	private static int STATE_DATA_INDEX_TEMPRATURE = 4;
	private static int STATE_DATA_INDEX_XIDU = 5;
	private static int STATE_DATA_INDEX_WIFI_MODE = 6;//no use
	private static int STATE_DATA_INDEX_Air_LEVEL = 7;
	private static int STATE_DATA_INDEX_WORKMODE = 8;
	private static int STATE_DATA_INDEX_WIND_SPEED = 9;
	private static int STATE_DATA_INDEX_FUNC_SET = 10;
	private static int STATE_DATA_INDEX_SENSOR_STATE = 11;
	private static int STATE_DATA_INDEX_TIMER_MODE = 12;
	private static int STATE_DATA_INDEX_TIMER_TIMELEFT = 13;
	private static int STATE_DATA_INDEX_HEPA_TIME = 15;
	private static int STATE_DATA_INDEX_FILTER_TIME = 17;
	private static int STATE_DATA_INDEX_CAR_TIME = 19;
	private static int STATE_DATA_INDEX_CHA_TIME = 21;
	
	
	
	private static int CMD_DATA_INDEX_QUERY_STATE = 0;
	private static int CMD_DATA_INDEX_WIFI_STATE = 1; //no use
	private static int CMD_DATA_INDEX_POWER_KEY = 2; 
	private static int CMD_DATA_INDEX_FUNC_KEY = 3; //no use
	private static int CMD_DATA_INDEX_SET_WINDSPEED = 4;
	private static int CMD_DATA_INDEX_SET_TIMER = 5;
	private static int CMD_DATA_INDEX_REFRESH = 6;
	private static int CMD_DATA_INDEX_RESET = 7;
	private static int CMD_DATA_INDEX_OPEN_AUTOMODE = 8;
	private static int CMD_DATA_INDEX_OPEN_O1 = 9;
	private static int CMD_DATA_INDEX_OPEN_O3 = 10;
	private static int CMD_DATA_INDEX_OPEN_UV = 11;
	
	private static int CONTROL_PACKERT_SIZE = 12;
	
	private static byte MASK_SHORT_PRESS = 0x40;
	
	private Context mContext;
	private byte[] mPacket= new byte[CONTROL_PACKERT_SIZE];
	
	public CopyOfEairControler(Context context) 
	{
		mContext = context;
	}
	private void initPacket()
	{
		for (int i = 0; i < mPacket.length; i++) 
		{
			mPacket[i] = 0;
		}
	}
	
	private  void sendControlData(byte[] data, int sn)
	{
		NetWorkManager netManager = NetWorkManager.getInstance(mContext);
		data[0] = 0x1;
		netManager.SendPkt(sn, data, data.length);
		//airQueryState(sn);
	}
	
	public  void airClose(int wifiSN)
    {
		initPacket();
		mPacket[CMD_DATA_INDEX_POWER_KEY] = (byte) (MASK_SHORT_PRESS|1);
		sendControlData(mPacket, wifiSN);
    }


    public void airOpenUV(int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_OPEN_UV] = (byte) (6);
		sendControlData(mPacket, wifiSN);
    }
    
    public  void airOpenO3(int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_OPEN_O3] = (byte) (8);
		sendControlData(mPacket, wifiSN);
    }

    public  void airOpenO1(int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_OPEN_O1] = (byte) (7);
		sendControlData(mPacket, wifiSN);
    }
    
    public  void airQueryState(int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_QUERY_STATE] = 1;
		sendControlData(mPacket, wifiSN);
    }
    
    public  void airOpenAutoMode(int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_OPEN_AUTOMODE] = (byte) (4);
		sendControlData(mPacket, wifiSN);
    }

    public  void airSetTimer(byte hour, int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_SET_TIMER] = (byte) ((hour<<4)|(byte)13);
		sendControlData(mPacket, wifiSN);
    }


    public  void airSetWind(byte level, int wifiSN)
    {
    	initPacket();
		mPacket[CMD_DATA_INDEX_SET_WINDSPEED] = (byte) ((level<<4)|(byte)14);
		sendControlData(mPacket, wifiSN);
    }


    
	public void parseInfo(EairInfo eairInfo, byte stateData[])
	{		
		/*eairInfo.air = stateData[STATE_DATA_INDEX_Air_LEVEL];
		eairInfo.hcho = ((int)stateData[STATE_DATA_INDEX_HCHO+1]<<8)|(int)stateData[STATE_DATA_INDEX_HCHO];
		eairInfo.temprature = stateData[STATE_DATA_INDEX_TEMPRATURE];
		eairInfo.workMode = stateData[STATE_DATA_INDEX_WORKMODE];
		if(eairInfo.workMode == 7)
		{
			eairInfo.modeAuto = true;
		}
		else
		{
			eairInfo.modeAuto = false;
		}
		
		eairInfo.wind = stateData[STATE_DATA_INDEX_WIND_SPEED];
		
		byte sensorState = stateData[STATE_DATA_INDEX_SENSOR_STATE];	
		
		boolean temp;
		temp = (sensorState & SENSOR_STATE_MASK_O3) == 0?false:true;
		eairInfo.o3Open = temp;
		temp = (sensorState & SENSOR_STATE_MASK_O1) == 0?false:true;
		eairInfo.o1Open = temp;
		temp = (sensorState & SENSOR_STATE_MASK_UV) == 0?false:true;
		eairInfo.uvOpen = temp;
		eairInfo.dustOpen = (sensorState & SENSOR_STATE_MASK_DUST) == 0?false:true;
		eairInfo.gasOpen = (sensorState & SENSOR_STATE_MASK_GAS) == 0?false:true;
		
		
		eairInfo.timerMode = stateData[STATE_DATA_INDEX_TIMER_MODE];
		eairInfo.timer = ((int)stateData[STATE_DATA_INDEX_TIMER_TIMELEFT+1]<<8)|(int)stateData[STATE_DATA_INDEX_TIMER_TIMELEFT];
		eairInfo.hepa = ((int)stateData[STATE_DATA_INDEX_HEPA_TIME+1]<<8)|(int)stateData[STATE_DATA_INDEX_HEPA_TIME];
		eairInfo.filterTime = ((int)stateData[STATE_DATA_INDEX_FILTER_TIME+1]<<8)|(int)stateData[STATE_DATA_INDEX_FILTER_TIME];
		eairInfo.cha = ((int)stateData[STATE_DATA_INDEX_CHA_TIME+1]<<8)|(int)stateData[STATE_DATA_INDEX_CHA_TIME];
		eairInfo.car = ((int)stateData[STATE_DATA_INDEX_CAR_TIME+1]<<8)|(int)stateData[STATE_DATA_INDEX_CAR_TIME];
*/
	}
}
