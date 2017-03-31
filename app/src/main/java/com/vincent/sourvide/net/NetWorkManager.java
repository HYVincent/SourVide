
package com.vincent.sourvide.net;

import android.util.Log;
import android.widget.Toast;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
import android.content.Context;
//import android.net.NetworkInfo; 


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

//import java.util.Iterator;







import android.net.wifi.WifiManager;

import com.vincent.sourvide.NativeUtils;
import com.vincent.sourvide.data.AddDeviceCallBack;
import com.vincent.sourvide.data.ApConfigCallback;
import com.vincent.sourvide.data.DataInfo;
import com.vincent.sourvide.data.DelDeviceCallBack;
import com.vincent.sourvide.data.DeviceStatusChangeListener;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.data.NetworkStateCallback;
import com.vincent.sourvide.data.ScanDeviceListener;
import com.vincent.sourvide.R;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


public class NetWorkManager 
{
      
	private static Context mContext;
	private static int GROUP_ID=1002;//
	
	 private Looper looper = Looper.myLooper();  
	 
	 private static Handler mainHandler;// = new MainHandler(Looper.getMainLooper());  
	 private static Handler subHandler=null;
	 
	 private String str_Text;
	 private static ScanDeviceListener mScanDeviceListener;
	 private static DeviceStatusChangeListener mChangeListener;
	 private static NetworkStateCallback mNetworkStateCallback;
	 private static ApConfigCallback mAPConfigCallback;
	 private static NetWorkManager singleton;
	 private static WifiAdmin mWifiAdmin;
	 private static AddDeviceCallBack mAddDeviceCallBack;   
	 private static DelDeviceCallBack mDelDeviceCallBack;   
	 private int add_wifi_status = 0;
	 private int new_wifi_sn = 0;
	 private int time_cnt = 0;
	 private boolean mInConfig;
	 private String mSSID;
	 private String strPassWord;
	 private boolean mNetworkLost;
	 private int time_sum;
	 
	 int test_index = 0;
	 byte[] CtoJvBuff;
	 static CustomThread subThreadId;
	
	 public static EairInfo mEairInfo = new EairInfo();
	    
	 public void setAddDeviceCallBack(AddDeviceCallBack callback)
	 {
		 mAddDeviceCallBack = callback;
	 }
	 
	 public static EairInfo getmEairInfo() {
		return mEairInfo;
	}

	public static void setmEairInfo(EairInfo mEairInfo) {
		NetWorkManager.mEairInfo = mEairInfo;
	}




	public void setDelDeviceCallBack(DelDeviceCallBack callback)
	 {
		 mDelDeviceCallBack = callback;
	 }
	 
	 private NetWorkManager(Context context) 
	 {
		 mContext = context;
		 mainHandler = new MainHandler(Looper.getMainLooper());
		 
		 CtoJvBuff = new byte[100]; 
		 
		 SharedPreferences sharedPreferences = mContext.getSharedPreferences("WIFI-CFG", Context.MODE_PRIVATE);
	     int app_sn=sharedPreferences.getInt("APP-ID", 0);
		 
		 NativeUtils.JniNetWorkInit(app_sn, GROUP_ID);
		 
		 subThreadId = new CustomThread();
		 subThreadId.start();
		 
	}
	 public static NetWorkManager getInstance(Context context)
	 {
		 if(singleton == null)
		 {
			 singleton = new NetWorkManager(context);
		 }
		 else
		 {

		 }

		 return singleton;
	 }
	 
	 public static NetWorkManager getInstance(Context context, int port, int ip)
	 {
		 if(singleton == null)
		 {
			 singleton = new NetWorkManager(context);
		 }
		 else
		 {
			 //mDevinfo.init(); 
		 }
		 
		 return singleton;
	 }
	
	public void setScanDeviceListener(ScanDeviceListener listener)
	{
		mScanDeviceListener = listener;
	}
	
	public void setDeviceStatusChangeListener(DeviceStatusChangeListener listener)
	{
		mChangeListener = listener;
	}
	public void setNetworkStateCallback(NetworkStateCallback callback)
	{
		mNetworkStateCallback = callback;
	}
	
	public void setAPConfigCallback(ApConfigCallback callback)
	{
		mAPConfigCallback = callback;
	}
	
	
	public void configAP(String ssid, String passwd)
	{
		mInConfig = true;
		mSSID = ssid;
		strPassWord = passwd;
		SendMsgToSub(Defines.SUB_MSG_TYPE_AP_CONFIG, 0, 0, null);
	}
	
	public void cancleAPConfig()
	{
		NativeUtils.JniCancelAPConfig();
	}

	public void networkDestory()
	{
		//SendMsgToSub(Defines.SUB_MSG_TYPE_THREAD_EXIT, 0, 0, null);
		subHandler.getLooper().quit();
		NativeUtils.JniNetWorkExit();
		singleton = null;
	}	
	
	
	public void AddNewWifiDev(int wifi_sn)
	{
		SendMsgToSub(Defines.SUB_MSG_TYPE_ADD_NEW_DEVICE, wifi_sn, 0, null);
	}
	
	public void removeWifiDev(int wifi_sn)
	{
		SendMsgToSub(Defines.SUB_MSG_TYPE_DEL_DEVICE, wifi_sn, 0, null);
	}
	
	public void SendPkt(int wifi_sn, byte[] buff, int len)
	{
		Log.d("NetWorkManager", "SendPkt");
		SendMsgToSub(Defines.SUB_MSG_TYPE_SEND_DATA, wifi_sn, len, buff);
	}
	
	public int exit()
	{
        return 0;
	}
	

	
	private static void SendMsgToUi(int msgType, int DevId, int len, byte[] buff)
	{
		if(mainHandler != null)
		{
             Message msg = mainHandler.obtainMessage(msgType, DevId, len, buff);
             mainHandler.sendMessage(msg); //������Ϣ 
		}
	}
	
	private static void SendMsgToSub(int msgType, int DevId, int len, byte[] buff)
	{
		if(subHandler != null)
		{
	        Message msg = subHandler.obtainMessage(msgType, DevId, len, buff);
	        subHandler.sendMessage(msg); //������Ϣ 
		}
	}
	

	class MainHandler extends Handler 
    {  
        public MainHandler() {}  
        public MainHandler(Looper looper) 
        {  
            super(looper);  
        }  
        
        @Override  
        public void handleMessage(Message msg) 
        {  
        	int wifi_id = msg.arg1;
        	int datalen = msg.arg2;
        	
            switch(msg.what)
            {

               case Defines.MAIN_MSG_TYPE_SEND_PACKET_RESULT:
               {
            	   int res = msg.arg2;
            	   if(mChangeListener != null)
                   {
                   		mChangeListener.doCallBack(Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_RESULT, res, null);
                   }
            	   break;
               }
               
               case Defines.MAIN_MSG_TYPE_DATA:
               {
            	    byte[] buff;
                    int i = 0;
                    i++;
                    buff = (byte[]) msg.obj;
                    
                    DataInfo di = new DataInfo();
                    di.len = datalen;
                    di.pkt = buff;
                    di.sn = wifi_id;
                    
                    if(mChangeListener != null)
                    {
                    	mChangeListener.doCallBack(Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_DATA, 0, di);
                    	di = null;
                    }
                    
                	break;	
                }
               
               case Defines.MAIN_MSG_TYPE_GET_APP_SN:
               {
            	   SharedPreferences sharedPreferences = mContext.getSharedPreferences("WIFI-CFG", Context.MODE_PRIVATE);
            	   Editor editor = sharedPreferences.edit();
       			   editor.putInt("APP-ID", wifi_id);  	
       			   editor.commit();
                   break;
               }
               
               case Defines.MAIN_MSG_TYPE_NETWORK_DOWN:
               {
            	 //  Toast.makeText(this, "����豸�ɹ�������", Toast.LENGTH_SHORT).show(); 
            	   mNetworkLost = true;
            	   //if(wifi_id == 0)
            	   {
            	       Toast.makeText(mContext, mContext.getResources().getString(R.string.network_lost), Toast.LENGTH_SHORT).show(); 
            	   }
            	   /*else
            	   {
            		   Toast.makeText(mContext, "�豸��"+ Integer.toString(wifi_id & 0x7fffffff)+"���ӳ�ʱ ��", Toast.LENGTH_SHORT).show(); 
            	   }*/
            	   
            	   if(mChangeListener != null)
                   {
                   	    mChangeListener.doCallBack(Defines.MAIN_MSG_TYPE_NETWORK_DOWN, 0, null);
                   }
            	   
            	   break;
               }
               
               case Defines.MAIN_MSG_TYPE_NETWORK_UP:
               {
            	   if(mNetworkLost)
            	   {
            		   mNetworkLost = false;
	            	  if(wifi_id == 0)
	            	  {
	            		  Toast.makeText(mContext, mContext.getResources().getString(R.string.network_recovery), Toast.LENGTH_SHORT).show(); 
	            	  }
            	   }
            	  /*else
            	  {
          	          Toast.makeText(mContext, "�豸��"+ Integer.toString(wifi_id & 0x7fffffff)+"���ӳɹ���", Toast.LENGTH_SHORT).show(); 
            	  }*/
            	   
            	   if(mChangeListener != null)
                   {
                   	    mChangeListener.doCallBack(Defines.MAIN_MSG_TYPE_NETWORK_UP, 0, null);
                   }
                  
            	  break;
               }
               
               
               case Defines.MAIN_MSG_TYPE_FIND_NEW_WIFI_DEV:
               {
            	   if(mScanDeviceListener != null)
	           		{
	           			//DataInfo di = new DataInfo();
	           			//di.sn = 0xC000012;
	           			mScanDeviceListener.deviceInfoCallBack(wifi_id);
	           		}
            	   break;
               }
               
               case Defines.MAIN_MSG_TYPE_SET_WIFI_PASSWORD_SUCCESS:
               {   
                   if(mAPConfigCallback != null)
                   {
                	   mAPConfigCallback.notifyConfigResult(msg);
                   }
                   
                   break;
               }
               
               case Defines.MAIN_MSG_TYPE_ADD_NEW_WIFI_DEV_SUCCESS:
               {
               	/*int wifi_sn;
               	boolean flag = false;
               	
               	if(msg.arg2 == 0)
               	{	
                   SharedPreferences sharedPreferences = mContext.getSharedPreferences("WIFI-CFG", Context.MODE_PRIVATE);
           		   int wifiNum = sharedPreferences.getInt("WIFINum", 0);
           		   
           		   int i = 0;
          	       while(i < wifiNum)
          		   {
            	       wifi_sn = sharedPreferences.getInt("WIFI"+ Integer.toString(i)+"-ID", 0);
            	       if(wifi_sn == wifi_id)
          		       {
                		     flag = true;
          				     break;
       			       }
          	    	   i++;
          		   }
          			   
          		   if(!flag)
          		   {
           	           Editor editor = sharedPreferences.edit();
           		       editor.putInt("WIFI"+ Integer.toString(wifiNum)+"-ID",wifi_id);
           		       editor.putInt("WIFINum", wifiNum+1);  
             		       editor.commit();
          		   }
               	}*/
               	
	               	if(mAddDeviceCallBack != null)
	               	{
	               		wifi_id = msg.arg1;
	               		mAddDeviceCallBack.onDeviceAdded(wifi_id, msg.arg2);
	               	}
            	   break;
               }
               
               case Defines.MAIN_MSG_TYPE_DEL_WIFI_DEV_SUCCESS:
               {
					if(mDelDeviceCallBack != null)
					{
						wifi_id = msg.arg1;
						mDelDeviceCallBack.onDeviceDeled(wifi_id, msg.arg2);
					}
            	   
            	   break;
               }
            }
            
            if(mNetworkStateCallback != null )
            {
            	mNetworkStateCallback.notifyMessage(msg);
            }
        }  
    }  
	
	 class CustomThread extends Thread {
		 
	        @Override
	        public void run()
	        {
	        	//������Ϣѭ���Ĳ���
	            Looper.prepare();//1����ʼ��Looper
	            subHandler = new Handler()
	            {//2����handler��CustomThreadʵ����Looper����
	                public void handleMessage (Message msg)
	                {
	                 	int devic_sn = msg.arg1;
	                 	int data_slen = msg.arg2;
	                 	byte[] data;
	                 	
	                    switch(msg.what)
	                    {
	                        case Defines.SUB_MSG_TYPE_SEND_DATA:
	                        {
	                        	data = (byte[]) msg.obj;
	                        	//int rc = 0;
	                            int rc = NativeUtils.JniSendData(devic_sn,data_slen, data);  //����
	                            Log.d("JniSendData", "JniSendData return");
	                            SendMsgToUi(Defines.MAIN_MSG_TYPE_SEND_PACKET_RESULT, devic_sn, rc, null);
	                        	break;
	                        }
	                        
	                        case Defines.SUB_MSG_TYPE_ADD_NEW_DEVICE:
	                        {
	                        	int rc;
	                        	rc = NativeUtils.JniAddDevie(devic_sn);
	                        	SendMsgToUi(Defines.MAIN_MSG_TYPE_ADD_NEW_WIFI_DEV_SUCCESS, devic_sn, rc, null);
	                        	break;
	                        }
	                        
	                        case Defines.SUB_MSG_TYPE_DEL_DEVICE:
	                        {
	                        	int rc;
	                        	rc = NativeUtils.JniDelDevie(devic_sn);
	                        	SendMsgToUi(Defines.MAIN_MSG_TYPE_DEL_WIFI_DEV_SUCCESS, devic_sn, rc, null);
	                        	break;
	                        }
	                        	
	                        case Defines.SUB_MSG_TYPE_AP_CONFIG:
	                        {
	                        	int dev_sn = 0;
	                        	int add_wifi_flg = 1;
	                        	byte[] ssid = mSSID.getBytes();
	                    		byte[] password = strPassWord.getBytes();
	                    		
	                    		mWifiAdmin = WifiAdmin.getInstance(mContext);
	                     		mWifiAdmin.openWifi(); 
	                     	    while (mWifiAdmin.checkState() == WifiManager.WIFI_STATE_ENABLING) 
	                     	    {
	                     			 try 
	                     			  {
	                     			    // Ϊ�˱������һֱwhileѭ��������˯��100�����⡭��
	                     			     Thread.sleep(100);
	                     			  } 
	                     			  catch (InterruptedException ie) 
	                     			  {
	                     			     Log.e("set password","sleep erre");  
	                     		      }
	                     		  }
	                     		
	                     	    if(mWifiAdmin.addNetwork(mWifiAdmin.CreateWifiInfo("WIFI-CFG", "", 1))) 
	                     	    {
	                    		
	                        	     dev_sn = NativeUtils.JniApConfig(ssid, password, add_wifi_flg);
	                         	    // mWifiAdmin.removewifi("WIFI-CFG");
	                     	    }
	                        	
	                        	if((add_wifi_flg != 0) && (dev_sn != 0))
	                        	{
	                        	   	int wifi_sn;
		                        	boolean flag = false;
		                        	
	                        		SharedPreferences sharedPreferences = mContext.getSharedPreferences("WIFI-CFG", Context.MODE_PRIVATE);
		                    	    int wifiNum = sharedPreferences.getInt("WIFINum", 0);
		                    		   
		                            int i = 0;
		                   	        while(i < wifiNum)
		                   		    {
		                   	    	    wifi_sn = sharedPreferences.getInt("WIFI"+ Integer.toString(i)+"-ID", 0);
		                   			    if(wifi_sn == dev_sn)
		                   			    {
		       					            flag = true;
		                   				    break;
		                   			    }
		                   			    
		                   			    i++;
		                   			}
		                   			   
		                   		    if(!flag)
		                   		    {
		                    	       Editor editor = sharedPreferences.edit();
		                   		       editor.putInt("WIFI"+ Integer.toString(wifiNum)+"-ID",dev_sn);
		                   		       editor.putInt("WIFINum", wifiNum+1);  
		                   		       editor.commit();
		               			   }         		
		                   		    
	                        	}
	                        	
	                        	SendMsgToUi(Defines.MAIN_MSG_TYPE_SET_WIFI_PASSWORD_SUCCESS, dev_sn, 0, null);
	                  
	                        	break;
	                        }
	                        	
	                        case Defines.SUB_MSG_TYPE_CANCEL_AP_CONFIG:
	                        {
								NativeUtils.JniCancelAPConfig();
	                        	break;
	                        }
	                        case Defines.SUB_MSG_TYPE_THREAD_EXIT:
	                        {
	                        	//this.getLooper().quit();
	                        	break;
	                        }
	                    }
	                }
	            };
	            
	            Looper.loop();//4 Start the cycle
	        }
	    }
	 
	static 
	{
		System.out.println("正在加载NativeUtils.so文件..");
		System.loadLibrary("NativeUtils");
	}
}
