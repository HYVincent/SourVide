package com.vincent.sourvide.net;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.view.View.OnClickListener;

public class WifiAdmin 
{   
    // ����WifiManager����    
    private WifiManager mWifiManager;   
    // ����WifiInfo����    
    private WifiInfo mWifiInfo;   
    // ɨ��������������б�    
 //   private List<ScanResult> mWifiList;   
    // ���������б�    
 //   private List<WifiConfiguration> mWifiConfiguration;   
    // ����һ��WifiLock    
    WifiLock mWifiLock;   
    private static WifiAdmin singleton;
  
   
	 public static WifiAdmin getInstance(Context context)
	 {
		 if(singleton == null)
		 {
			 singleton = new WifiAdmin(context);
		 }

		 return singleton;
	 }
	 
    // ������    
    private WifiAdmin(Context context) {   
        // ȡ��WifiManager����    
        mWifiManager = (WifiManager) context   
                .getSystemService(Context.WIFI_SERVICE);   
        // ȡ��WifiInfo����    
        mWifiInfo = mWifiManager.getConnectionInfo();   
    }   
   
    // ��WIFI    
    public void openWifi() {   
        if (!mWifiManager.isWifiEnabled()) {   
            mWifiManager.setWifiEnabled(true);   
        }   
    }   
   
    // �ر�WIFI    
    public void closeWifi() {   
        if (mWifiManager.isWifiEnabled()) {   
            mWifiManager.setWifiEnabled(false);   
        }   
    }   
   
    // ��鵱ǰWIFI״̬    
    public int checkState() {   
        return mWifiManager.getWifiState();   
    }   
   
    // ����WifiLock    
    public void acquireWifiLock() {   
        mWifiLock.acquire();   
    }   
   
    // ����WifiLock    
    public void releaseWifiLock() {   
        // �ж�ʱ������    
        if (mWifiLock.isHeld()) {   
            mWifiLock.acquire();   
        }   
    }   
   
    // ����һ��WifiLock    
    public void creatWifiLock() {   
        mWifiLock = mWifiManager.createWifiLock("Test");   
    }   
   
    public void removewifi(String SSID) 
    {
    	WifiConfiguration tempConfig = this.IsExsits(SSID);             
    
        if(tempConfig != null) {    
            mWifiManager.removeNetwork(tempConfig.networkId);    
        }  
    }
    
    /*
    // �õ����úõ�����    
    public List<WifiConfiguration> getConfiguration() {   
        return mWifiConfiguration;   
    }   
   
    // ָ�����úõ������������    
    public void connectConfiguration(int index) {   
        // �����������úõ�������������    
        if (index > mWifiConfiguration.size()) {   
            return;   
        }   
        // �������úõ�ָ��ID������    
        mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId,   
                true);   
    }   
   
    public void startScan() {   
        mWifiManager.startScan();   
        // �õ�ɨ����    
        mWifiList = mWifiManager.getScanResults();   
        // �õ����úõ���������    
        mWifiConfiguration = mWifiManager.getConfiguredNetworks();   
    }   
   
    // �õ������б�    
    public List<ScanResult> getWifiList() {   
        return mWifiList;   
    }   
   
    // �鿴ɨ����    
    public StringBuilder lookUpScan() {   
        StringBuilder stringBuilder = new StringBuilder();   
        for (int i = 0; i < mWifiList.size(); i++) {   
            stringBuilder   
                    .append("Index_" + new Integer(i + 1).toString() + ":");   
            // ��ScanResult��Ϣת����һ���ַ�����    
            // ���аѰ�����BSSID��SSID��capabilities��frequency��level    
            stringBuilder.append((mWifiList.get(i)).toString());   
            stringBuilder.append("/n");   
        }   
        return stringBuilder;   
    }  */
 
    // �õ�MAC��ַ    
    public String getMacAddress() {   
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();   
    }   
   
    // �õ�������BSSID    
    public String getBSSID() {   
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();   
    }   
   
    // �õ�IP��ַ    
    public int getIPAddress() {   
        return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();   
    }   
   
    // �õ����ӵ�ID    
    public int getNetworkId() {   
        return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();   
    }   
   
    // �õ�WifiInfo��������Ϣ��    
    public String getWifiInfo() {   
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();   
    }   

   public static boolean isWifiConnect(Context context)
    {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }
   
    // ���һ�����粢����    
    public boolean addNetwork(WifiConfiguration wcg) {   
         int wcgID = mWifiManager.addNetwork(wcg);   
         boolean b =  mWifiManager.enableNetwork(wcgID, true);   
         System.out.println("a--" + wcgID);  
         System.out.println("b--" + b);  
         return b;
    }   
   
    // �Ͽ�ָ��ID������    
    public void disconnectWifi(int netId) {   
        mWifiManager.disableNetwork(netId);   
        mWifiManager.disconnect();   
    }   
   
//Ȼ����һ��ʵ��Ӧ�÷�����ֻ��֤��û������������  
   
    public WifiConfiguration CreateWifiInfo(String SSID, String Password, int Type)   
    {   
          WifiConfiguration config = new WifiConfiguration();     
           config.allowedAuthAlgorithms.clear();   
           config.allowedGroupCiphers.clear();   
           config.allowedKeyManagement.clear();   
           config.allowedPairwiseCiphers.clear();   
           config.allowedProtocols.clear();   
           config.SSID = "\"" + SSID + "\"";     
            
          WifiConfiguration tempConfig = this.IsExsits(SSID);             
          if(tempConfig != null) {    
              mWifiManager.removeNetwork(tempConfig.networkId);    
          }  
            
          if(Type == 1) //WIFICIPHER_NOPASS  
          {   
        	   config.wepKeys[0] = "\"" + "\"";        	
               //config.wepKeys[0] = "";                            
               //config.hiddenSSID = true;   
               config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);   
               config.wepTxKeyIndex = 0;   
          }   
          if(Type == 2) //WIFICIPHER_WEP  
          {   
              config.hiddenSSID = true;  
              config.wepKeys[0]= "\""+Password+"\"";   
              config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);   
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);   
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);   
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);   
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);   
              config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);   
              config.wepTxKeyIndex = 0;   
          }   
          if(Type == 3) //WIFICIPHER_WPA  
          {   
          config.preSharedKey = "\""+Password+"\"";   
          config.hiddenSSID = true;     
          config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);     
          config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);                           
          config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);                           
          config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);                      
          //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);    
          config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);  
          config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);  
          config.status = WifiConfiguration.Status.ENABLED;     
          }  
           return config;   
    }   
      
    private WifiConfiguration IsExsits(String SSID)    
    {    
        List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();  
        
        if(existingConfigs == null)
        {
        	return null;
        }
        
        for (WifiConfiguration existingConfig : existingConfigs)     
           {    
             if (existingConfig.SSID.equals("\""+SSID+"\""))    
             {    
                 return existingConfig;    
             }    
           }    
        return null;     
    }  
    
}   
