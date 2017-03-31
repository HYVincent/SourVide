// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.vincent.sourvide.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences; 
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.vincent.sourvide.NativeUtils;
import com.vincent.sourvide.data.ApConfigCallback;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.net.WifiAdmin;
import com.vincent.sourvide.R;



import android.net.wifi.WifiInfo;


public class ConfigDeviceActivity extends TitleActivity
{

    private LinearLayout mConfigStartLayout;
    private LinearLayout mConfigingLayout;
    private boolean mInConfig;
    private EditText mPassWord;
    private String mSSID;
    private String strPassWord;
    
    private CheckBox mShowPassword;
    private EditText mSsidValue;
    private LinearLayout mSubmitButton;
    private SharedPreferences mWifiSharedPreferences;

 

    
	NetWorkManager mNetworkManager;
    
    public ConfigDeviceActivity()
    {
        mInConfig = false;
        
        /*ByteArrayBuffer bab=new ByteArrayBuffer(10);
        bab.length();
        bab.toByteArray();*/
    }

    private void closeInputMethod()
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
            inputmethodmanager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    private void findView()
    {
        mConfigingLayout = (LinearLayout)findViewById(R.id.config_layout);
        mConfigStartLayout = (LinearLayout)findViewById(R.id.config_start_layout);
        mSsidValue = (EditText)findViewById(R.id.ssid);
        mPassWord = (EditText)findViewById(R.id.password);
        mShowPassword = (CheckBox)findViewById(R.id.pass_show);
        mSubmitButton = (LinearLayout)findViewById(R.id.config_start);
        
        /*WifiAdmin wa = WifiAdmin.getInstance(this);
        String ssid = wa.getBSSID();
        if(wa.getBSSID() != null)
        {
        	mSsidValue.setText(ssid);
        }*/
    }

    private void initView()
    {

		mConfigingLayout.setVisibility(View.GONE);
		mSubmitButton.setVisibility(View.VISIBLE);
		mConfigStartLayout.setVisibility(View.VISIBLE);
				
        mShowPassword.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {


            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if (flag)
                {
                    mPassWord.setInputType(144);
                    mPassWord.setSelection(mPassWord.getText().length());
                    return;
                } else
                {
                    mPassWord.setInputType(129);
                    mPassWord.setSelection(mPassWord.getText().length());
                    return;
                }
            }

        }
);

      /*
       mSubmitButton.setOnClickListener(new OnSingleClickListener() {
            public void doOnClick(View view)
            {
                mInConfig = true;
               // mConfigingLayout.setVisibility(0);
               // mConfigStartLayout.setVisibility(8);
               // mSubmitButton.setVisibility(8);
                mSSID = mSsidValue.getText().toString();
                newDeviceConfigWiFi();
                mPassWord.setEnabled(false);
                mSsidValue.setEnabled(false);
            }

        }
);   */
    
        
  //  /*
    mSubmitButton.setOnClickListener(new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
        	

        	if(!mInConfig)
        	{
    	        mInConfig = true;
                mConfigingLayout.setVisibility(View.VISIBLE);
                mConfigStartLayout.setVisibility(View.GONE);
                mSubmitButton.setVisibility(View.GONE);
               mSSID = mSsidValue.getText().toString();
               strPassWord = mPassWord.getText().toString();
               newDeviceConfigWiFi();
               mPassWord.setEnabled(false);
               mSsidValue.setEnabled(false);
         
               mNetworkManager.setAPConfigCallback(mAPConfigCallback1);
               mNetworkManager.configAP(mSSID, strPassWord);
             
        	}
           
       }
}); 
       
    } 

    private void newDeviceConfigWiFi()
    {
    	android.content.SharedPreferences.Editor editor = mWifiSharedPreferences.edit();
        editor.putString(mSSID, mPassWord.getText().toString());
        editor.commit();
           
    }

    private void toHomePageActivity()
    {
        Intent intent = new Intent();
        intent.setClass(this, DeviceActivity.class);
        startActivity(intent);
        finish();
    }

    public void back()
    {
        if (mInConfig)
        {
            mInConfig = false;
            mNetworkManager.cancleAPConfig();
        } 
        
//    	mNetworkManager.JniExitScanMode();
        NativeUtils.JniExitScanMode();

    	hideInputMethod();
        finish();
    }

    public void checkWifi()
    {
    	WifiManager wifimanager;
        wifimanager = (WifiManager)getApplication().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mSSID = "";
        String s;
        String s1 = mSsidValue.getText().toString();
        
        if(s1 == null || !s1.isEmpty())
        {
        	return;
        }
        
        WifiInfo wifiinfo = wifimanager.getConnectionInfo();
        s = wifiinfo.getSSID();
        
        if(s != null)
        {
        	mSSID = s;
        }
        
        mSSID = mSSID.replace("\"", "");

        if (WifiAdmin.isWifiConnect(this) 
        		&& mSSID != null 
        		&& !mSSID.isEmpty())
        {
            mSsidValue.setText(mSSID);
            mSsidValue.setSelection(mSsidValue.length());
            mSsidValue.clearFocus();
            mSsidValue.requestFocus();
            
            String passwd = mWifiSharedPreferences.getString(mSSID, null);
            if(passwd != null)
            {
            	passwd = passwd.replace("\"", "");
            	mPassWord.setText(passwd);
            	mPassWord.setSelection(passwd.length());
            	mPassWord.clearFocus();
            	mPassWord.requestFocus();
            }
        }

    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.config_device_layout);
        setTitle(R.string.add_device);
        setBackVisible();
        //mBroadLinkConfig = new BroadLinkConfig(this);
        mWifiSharedPreferences = getSharedPreferences("shared_wifi", 0);
        findView();
        initView();
        
    	mNetworkManager = NetWorkManager.getInstance(this);
    	//mNetworkManager.ChgFindMode(false);
    	
    	//mNetworkManager.JniEntryScanMode();
    	
        
    }

    protected void onResume()
    {
        super.onResume();
        checkWifi();
        
        
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
            closeInputMethod();
        
        return super.onTouchEvent(motionevent);
    }
    
    
    private ApConfigCallback mAPConfigCallback1 = new ApConfigCallback() 
	{
		
		@Override
		public void notifyConfigResult(Message msg) 
		{
			int resultCode = msg.arg1;
			
			if(resultCode == 0)
			{
		        Toast.makeText(ConfigDeviceActivity.this, "��������ʧ�ܣ����ӳ�ʱ. ��ȷ���豸�Ƿ���APģ��", Toast.LENGTH_SHORT).show();
				mInConfig = false;
				//mNetworkManager.exit();
				toHomePageActivity();
			}
			else
			{	
          		Toast.makeText(ConfigDeviceActivity.this, "����������ɣ�����", Toast.LENGTH_SHORT).show(); 
                toHomePageActivity();
                //mNetworkManager.exit();
                mInConfig = false;				
			}
			
		}
	};
    
    
    
}

