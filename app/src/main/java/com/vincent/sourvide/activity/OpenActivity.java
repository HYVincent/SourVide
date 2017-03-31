
package com.vincent.sourvide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.vincent.sourvide.R;
import com.vincent.sourvide.data.DataInfo;
import com.vincent.sourvide.data.DeviceStatusChangeListener;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.net.Defines;
import com.vincent.sourvide.net.EairControler;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.view.OnSingleClickListener;

import java.util.List;


public class OpenActivity extends Activity implements DeviceStatusChangeListener{
	
    private boolean mInRefreshIng;
    private TextView mLocationPmText;
    private TextView mLocationWeatherText;
    private Button mOpenButton;
    private TextView mPMText;
    private TextView mPmValueText;
    //private RefreshAirThread mRefreshEairThread;
    
    NetWorkManager mNetworkManager;
    EairInfo mEairInfo;
    private EairControler mEairController;
   

    private void controlEair(byte abyte0[])
    {

    }

    private void findView()
    {
        mOpenButton = (Button)findViewById(R.id.btn_open);
        //mLocationPmText = (TextView)findViewById(R.id.location_pm);
        //mLocationWeatherText = (TextView)findViewById(R.id.location_text);
        //mPMText = (TextView)findViewById(R.id.pm);
        //mPmValueText = (TextView)findViewById(R.id.pm_value);
        
        
        mOpenButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mEairController.SetOnOrOff(1);
			}
		});
    }

    private void initView()
    {
        /*if (mDevice.getWeatherInfo() != null)
        {
            //WeatherInfo weatherinfo = mDevice.getWeatherInfo();
            TextView textview = mLocationWeatherText;
            
            Object aobj[] = new Object[3];
            aobj[0] = weatherinfo.getPm25().getArea()[0];
            aobj[1] = ((com.tcl.eair.net.data.WeatherInfo.Weather)weatherinfo.getWeather().get(0)).getInfo().getNight()[2];
            aobj[2] = ((com.tcl.eair.net.data.WeatherInfo.Weather)weatherinfo.getWeather().get(0)).getInfo().getDay()[2];
            textview.setText(getString(0x7f080077, aobj));
            
            TextView textview1 = mLocationPmText;
            Object aobj1[] = new Object[1];
            aobj1[0] = weatherinfo.getPm25().getArea()[0];
            textview1.setText(getString(0x7f080076, aobj1));
            
            int i = Integer.parseInt(weatherinfo.getPm25().getPm25()[0]);
            mPMText.setText(weatherinfo.getPm25().getPm25()[0]);
            mPmValueText.setText(CommonUnit.getPm25Grade(this, i));
        }*/
    }

    private void setListener()
    {
        mOpenButton.setOnClickListener(new OnSingleClickListener() {

            public void doOnClick(View view)
            {
            	mEairController.SetOnOrOff(1);
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.local_weath_layout);
       // mDevice = EairApplaction.mControlDevice;

        findView();
        setListener();
        

		mEairController = EairControler.getInstance(this);
	    mNetworkManager = NetWorkManager.getInstance(this);
	    mNetworkManager.setDeviceStatusChangeListener(this);  
    	mEairInfo =mNetworkManager.getmEairInfo();
	    
        /*if (mDevice.getWeatherInfo() == null)
        {
            GetWeatherTask getweathertask = new GetWeatherTask();
            ManageDevice amanagedevice[] = new ManageDevice[1];
            amanagedevice[0] = mDevice;
            getweathertask.execute(amanagedevice);
        }*/
    }
    
    
    @Override
	public void doCallBack(int type, int data, DataInfo datainfo) 
	{
		
		if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_DATA)
		{
		    mEairController.parseInfo(datainfo.sn, mEairInfo, datainfo.pkt);
		    refreshState();
		}
		
		if(type == Defines.MAIN_MSG_TYPE_NETWORK_DOWN)
		{
			//Toast.makeText(mContext, mContext.getResources().getString(R.string.network_lost), Toast.LENGTH_SHORT).show(); 
		}
		
		if(type ==  Defines.MAIN_MSG_TYPE_NETWORK_UP)
   	    {
		    mEairController.queryState(0);
   	    }
	}  
	
	private void refreshState()
	{
		
		if(mEairInfo.on_off != 0)
		{
			Intent intent = new Intent(OpenActivity.this,MainActivity.class);
			startActivity(intent);
		}
	}

	 private void refreshDeviceList()
	    {
	    	//mDeviceList.clear();
	        //mDeviceList.addAll(EairApplaction.allDeviceList);
	        
	        runOnUiThread
	        (
	        		new Runnable() 
			        {
			
			            public void run()
			            {
			                //mDeviceAdapter.notifyDataSetChanged();
			            	refreshState();
			            }
			        }
	        );
	    }
	

    protected void onPause()
    {
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
        initView();
        mInRefreshIng = true;
    }

}
