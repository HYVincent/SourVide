
package com.vincent.sourvide.activity;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

import com.vincent.sourvide.MyApplaction;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.data.ManageDevice;
import com.vincent.sourvide.data.ManageDeviceDao;
import com.vincent.sourvide.data.ScanDevice;
import com.vincent.sourvide.R;


public class LoadingActivity extends BaseActivity
{

	private void insertOneTest()
	{
		String name = getResources().getString(R.string.default_name);
	
		ScanDevice newdevice = new ScanDevice();
		
		EairInfo eairinfo = new EairInfo();
		eairinfo.sn = 0xcc001234;
		eairinfo.deviceType = 0;
		MyApplaction app = (MyApplaction)getApplication();
		if(app != null)
		{
			newdevice.deviceType = (short)0;
			newdevice.deviceName = name;
			newdevice.mac = Integer.toString(eairinfo.sn & 0x7fffffff);
			newdevice.id = eairinfo.sn;
			app.checkCreateOrUpdateScanDevice(newdevice, eairinfo);
		}
	}
    private void querAllDevice()
    {
        Iterator iterator = null;
        ManageDeviceDao managedevicedao;

        if (MyApplaction.mNetWorkManager == null)
        {
            mApplication.initNetWork();
        }
        /*else
        {
            EairApplaction.mNetWorkManager.networkRestart();
        }
        
        EairApplaction.mNetWorkManager.probeRestart();*/
        
	try 
	{
		managedevicedao = new ManageDeviceDao(getHelper());
		MyApplaction.allDeviceList.clear();
		MyApplaction.allDeviceList.addAll(managedevicedao.queryAllDevices());
		
		if(MyApplaction.allDeviceList.size() == 0)
		{
			insertOneTest();
			MyApplaction.allDeviceList.addAll(managedevicedao.queryAllDevices());
		}
		
		for(ManageDevice md: MyApplaction.allDeviceList)
		{
			EairInfo eairinfo = new EairInfo();
			
			eairinfo.sn = md.terminalId;
			md.setEairInfo(eairinfo);
		}

		
	}        
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
        
        /* iterator = EairApplaction.allDeviceList.iterator();
        while(iterator.hasNext())
        {
        	ManageDevice managedevice = (ManageDevice)iterator.next();
           ScanDevice scandevice = new ScanDevice();
            scandevice.deviceName = managedevice.getDeviceName();
            scandevice.deviceType = managedevice.getDeviceType();
            scandevice.id = managedevice.getTerminalId();
            scandevice.lock = managedevice.getDeviceLock();
            scandevice.mac = managedevice.getDeviceMac();
            scandevice.password = managedevice.getDevicePassword();
            scandevice.publicKey = managedevice.getPublicKey();
            scandevice.subDevice = (short)managedevice.getSubDevice();
            EairApplaction.mNetWorkManager.addDevice(scandevice);
        }*/
        
    }

    private void toActivity()
    {
    	
    	Intent intent = new Intent();
        intent.setClass(this, DeviceActivity.class); //�豸����
        startActivity(intent);

    	//Intent intent = new Intent();
        //intent.setClass(this, ConfigDeviceActivity.class);  //�����豸����
        //startActivity(intent);
    	
    	
    	// Intent intent = new Intent();
        //intent.setClass(this, NetworkTestActivity.class); //�������
    	// startActivity(intent);
    	
    	/*Intent intent = new Intent();
        intent.setClass(this, DeviceDetailActivity.class); 
        startActivity(intent);*/
    	
        finish();
    	
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        
        setContentView(R.layout.loading_layout);
        
        querAllDevice();
        
        (new Timer()).schedule(
        		
        		new TimerTask() 
		        {
		            public void run()
		            {
		                toActivity();
		            }
		        }, 
		        2000L);
	}

}
