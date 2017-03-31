package com.vincent.sourvide.net;


import android.content.Context;
import android.widget.Toast;

import com.vincent.sourvide.NativeUtils;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.R;


public class EairControler 
{
	public final static int CMD_QUERY_DEVICE_STATUS=0x01;
	//public final static int CMD_A2M_SET_BOIL=0x02;
	//public final static int CMD_A2M_SET_PUMP=0x03;
	//public final static int CMD_A2M_SET_WARM=0x04;
	//public final static int CMD_A2M_SET_KILL=0x05;
	//public final static int CMD_A2M_QUERY_DEVICE_STATUS=0x06;
	
	//private final int CMD_M2A_REPORT_STATUS=0x06;

	private final int INDEX_CMD = 0x02;
	private final int INDEX_DATA_LEN = 0x03;    //���ػ�״̬
	private final int INDEX_SET_HOUR =7;   //ģʽ�� 1--��ˮ��2--���£�3--������4--ȫ�Զ������� 5--ȫ�Զ���ˮ
	private final int INDEX_SET_MIN=8; //ˮʵ���¶�
	private final int INDEX_REAL_TEMP=9; //����ģʽ��ˮ���¶�
	private final int INDEX_SET_TEMP=10; //���״̬ 0--ͣ��1---ת
	private final int INDEX_ON_OFF=11; //��ˮ״̬ 0--���ٳ�ˮ�� 1---�ڳ�ˮ
  	private final int INDEX_WORK_STOP=12; //���ϴ���
  	private final int INDEX_ERROR1=13; //���ϴ���
  	private final int INDEX_ERROR2=14; //���ϴ���
  	private final int INDEX_ERROR3=15; //���ϴ���
  	private final int INDEX_ERROR4=16; //���ϴ���
	
	

	private static int CONTROL_PACKERT_OUT_SIZE = 64;
	private Context mContext;
	private byte[] mPacket= new byte[CONTROL_PACKERT_OUT_SIZE];
	
	private EairControler(Context context) 
	{
		mContext = context;
	}
	
	private void initPacket()
	{
		for (int i = 0; i < mPacket.length; i++) 
		{
			mPacket[i] = 0;
		}
		
		mPacket[0] = 0x55;
		mPacket[1] = (byte) 0xAA;
		mPacket[2] = 00;
		mPacket[3] = 00;
		mPacket[4] = 0x20;
	}
	
	private  void sendControlData(byte[] data, int sn, int len)
	{
		NetWorkManager netManager = NetWorkManager.getInstance(mContext);
		
//		if(netManager.JniGetServiceLinkStatus() <= 0)
		if(NativeUtils.JniGetServiceLinkStatus()<0)
		{
			Toast.makeText(mContext, mContext.getResources().getString(R.string.network_lost), Toast.LENGTH_SHORT).show();
			return;
		}
		
		netManager.SendPkt(sn, data, len);
	}
	
	public void queryState(int wifiSN)
	{
	    initPacket();
		mPacket[INDEX_CMD] = 0x00;
		mPacket[INDEX_DATA_LEN] = 0x00;
		sendControlData(mPacket, 0, 7);
	}
	
	public void SetTemp(int temp)
	{
		 initPacket();
		 mPacket[INDEX_CMD] = 0x01;
		 mPacket[INDEX_DATA_LEN] = 0x01;
		 mPacket[7] = (byte)temp;
		 sendControlData(mPacket, 0, 8);
	}
	
	public void SetTime(int hour, int  min)
	{
		initPacket();
		mPacket[INDEX_CMD] = 0x02;
		mPacket[INDEX_DATA_LEN] = 0x02;
		mPacket[7] = (byte)hour;
		mPacket[8] = (byte)min;
		sendControlData(mPacket, 0, 9);
	}
	
	public void SetWorkOrStop(int status)
	{
		initPacket();
		mPacket[INDEX_CMD] = 0x04;
		mPacket[INDEX_DATA_LEN] = 0x01;
		mPacket[7] = (byte)status;
		sendControlData(mPacket, 0, 8);
	}
	
	public void SetOnOrOff(int status)
	{
		initPacket();
		 mPacket[INDEX_CMD] = 0x03;
		 mPacket[INDEX_DATA_LEN] = 0x01;
		 mPacket[7] = (byte)status;
		 sendControlData(mPacket, 0, 8);
	}
	
	
	public void powerOn(int wifiSN, EairInfo eairInfo, int On)
	{
		initPacket();
		//mPacket[CMD_INDEX] = CMD_SET_DEVICE_CFG;
		sendControlData(mPacket, wifiSN, 20);
	}
	
	
    private int getDataByIndex(int index, byte stateData[], int dataBytes)
    {
    	if(index < stateData.length)
    	{
    		int low = stateData[index];
    		if(low < 0)
    		{
    			int i = 128+low;
    			low = i|0x80;
    		}
    		if(dataBytes < 2)
    		{
    			return low;
    		}
    		
    		int high = stateData[index+1];
    		if(high < 0)
    		{
    			int i = 128+high;
    			high = i|0x80;
    		}

    		return ((high<<8)|(low));
    	}
    	
    	return 0;
    }


    
	public void parseInfo(int sn, EairInfo eairInfo, byte stateData[])
	{		
		int cmd;
		if(stateData == null )
		{
			return;
		}
		
		if(stateData.length < 20)
		{
			return;
		}
		
		cmd = stateData[INDEX_CMD];
		if(cmd == 0)
		{
			eairInfo.init = true;
		  	eairInfo.set_hour = stateData[INDEX_SET_HOUR];
		  	eairInfo.set_min  = stateData[INDEX_SET_MIN];
		  	eairInfo.real_temp = stateData[INDEX_REAL_TEMP];
		  	eairInfo.set_temp = stateData[INDEX_SET_TEMP];
		  	eairInfo.on_off   = stateData[INDEX_ON_OFF];
		  	eairInfo.work_stop = stateData[INDEX_WORK_STOP];
		  	eairInfo.error1 = stateData[INDEX_ERROR1];
		  	eairInfo.error2 = stateData[INDEX_ERROR2];
		  	eairInfo.error3 = stateData[INDEX_ERROR3];
		  	eairInfo.error4 = stateData[INDEX_ERROR4];
		}
	}
	
	
	public static EairControler mInstance;

	public static EairControler getInstance(Context context)
	{
		if(mInstance == null )
		{
			mInstance = new EairControler(context);
		}
		
		return mInstance;
	}
}
