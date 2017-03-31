
package com.vincent.sourvide.activity;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.sourvide.MyApplaction;
import com.vincent.sourvide.data.ManageDevice;
import com.vincent.sourvide.data.ManageDeviceDao;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.view.OnSingleClickListener;
import com.vincent.sourvide.R;



public class EditDeviceInfoActivity extends TitleActivity
{

    private ManageDevice mDevice;
    private LinearLayout mDeviceLockLayout;
    private boolean mLock;
    private ImageView mLockBox;
    private TextView mMacText;
    private EditText mNameText;
    private Button mSaveButton;
    private TextView mSerialText;


    private void closeInputMethod()
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService("input_method");
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
            inputmethodmanager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    @SuppressWarnings("unchecked")
	private void editDevice()
    {
    	
    	mDevice.setDeviceName( mNameText.getText().toString());

        try 
        {
			(new ManageDeviceDao(getHelper())).createOrUpdate(mDevice);
			
		} 
        catch (java.sql.SQLException e) 
		{

			e.printStackTrace();
		}

        back();
                
    }

    private void findView()
    {
        mNameText = (EditText)findViewById(R.id.user_name);
        mMacText = (TextView)findViewById(R.id.mac);
        mSerialText = (TextView)findViewById(R.id.serial_number);
        mSaveButton = (Button)findViewById(R.id.btn_save);
        mLockBox = (ImageView)findViewById(R.id.lock);
        mDeviceLockLayout = (LinearLayout)findViewById(R.id.device_lock_layout);
    }

    private void initView()
    {
    	mNameText.setText(mDevice.getDeviceName());
    	mNameText.setSelection(mDevice.getDeviceName().length());
        
        //mMacText.setText(toMacFormat(mDevice.getDeviceMac()));
        mSerialText.setText(mDevice.getQrInfo());
        /*if (mDevice.getDeviceLock() == 1)
        {
            mLockBox.setImageResource(R.drawable.on);
            mLock = true;
            return;
        } else
        {
            mLockBox.setImageResource(R.drawable.off);
            mLock = false;
            return;
        }*/
    }

    private void setListener()
    {
        mSaveButton.setOnClickListener(new OnSingleClickListener() {

            public void doOnClick(View view)
            {
                editDevice();
            }
        }
);
        mDeviceLockLayout.setOnClickListener(new OnSingleClickListener() {

            public void doOnClick(View view)
            {
                /*if (mLock)
                {
                    mLockBox.setImageResource(0x7f020047);
                    mLock = false;
                    return;
                } else
                {
                    mLockBox.setImageResource(0x7f020048);
                    mLock = true;
                    return;
                }*/
            }

        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        
        NetWorkManager.getInstance(this).setDeviceStatusChangeListener(null);
        
	 mDevice = MyApplaction.mEditDevice;
	 MyApplaction.mEditDevice = null;
	 
        if(mDevice == null)
        {
        	finish();
        	return;
        }
        
        
        setContentView(R.layout.edit_device_info_layout);
        setBackVisible();
        setTitle(R.string.edit_name);

        findView();
        setListener();
        initView();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
            closeInputMethod();
        
        return super.onTouchEvent(motionevent);
    }

    public String toMacFormat(String s)
    {
        String s1 = "";
        
        int i = 0;
        do
        {
            if (i >= s.length() / 2)
                return s1;
            
            if (i == -1 + s.length() / 2)
                s1 = (new StringBuilder(String.valueOf(s1))).append(s.substring(i * 2, 2 + i * 2)).toString();
            else
                s1 = (new StringBuilder(String.valueOf(s1))).append(s.substring(i * 2, 2 + i * 2)).append(":").toString();
            i++;
            
        } while (true);
    }





}
