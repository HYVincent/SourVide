package com.vincent.sourvide.activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.vincent.sourvide.MyApplaction;
import com.vincent.sourvide.data.DataInfo;
import com.vincent.sourvide.data.DeviceStatusChangeListener;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.net.Defines;
import com.vincent.sourvide.net.EairControler;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.view.FullProgressDialog;
import com.vincent.sourvide.R;

public class DeviceDetailActivity extends TitleActivity implements OnClickListener,DeviceStatusChangeListener
{

    public static ImageButton device_btn;
    public static TextView device_protect;
    public static int timeLength = 0;
    public static String timeList[] = new String[0];
    private TextView delayBtn;
    private TextView timelistBtn;

    private TextView mInfoText;
    private TextView mTempText;
    
    private TextView mBoilText;
    private TextView mWarmText;
    private TextView mPumpText;
    private TextView mPurifyText;
    
    private TextView mAutoBoil;
    private TextView mAutoKill;
    private TextView mFixTemp;
    
    private EairInfo mEairInfo;
    private EairControler mEairController;
 
    private NetWorkManager mNetworkManager;
	
    private TextView device_slight;
    
    private static TextView mSeekBarText;
    
    private  SeekBar mFixTempSeekBar;
    
    private  int mProgress=0;

    public DeviceDetailActivity()
    {

    }


    private void initView()
    {
        initBg();

        mInfoText = (TextView)findViewById(R.id.state_info);
        mTempText = (TextView)findViewById(R.id.state_info_temp);
        
        device_btn = (ImageButton) findViewById(R.id.device_btn);
        device_btn.setOnClickListener(this);

        
        mBoilText = (TextView)findViewById(R.id.boil);
        mWarmText = (TextView)findViewById(R.id.warm);
        mPumpText = (TextView)findViewById(R.id.pump);
        mPurifyText = (TextView)findViewById(R.id.purify);
        mAutoBoil = (TextView)findViewById(R.id.boil_auto);
        mAutoKill = (TextView)findViewById(R.id.purify_auto);
        
        mBoilText.setOnClickListener(this);
        mWarmText.setOnClickListener(this);
        mPumpText.setOnClickListener(this);
        mPurifyText.setOnClickListener(this);
        
        mAutoBoil.setOnClickListener(this);
        mAutoKill.setOnClickListener(this);
        
        mFixTemp = (TextView)findViewById(R.id.fix_temp);
        
        
        mSeekBarText = (TextView)findViewById(R.id.seek_text);
        mFixTempSeekBar = (SeekBar)findViewById(R.id.seek_fix_temp);
        mFixTempSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        mFixTempSeekBar.setMax(60);	
    }

    
    private  OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			
			if(mSeekBarText != null)
			{
				mSeekBarText.setText(Integer.toString(progress+40));
			}
			
			if(mProgress != progress)
			{
				//EairControler.getInstance(seekBar.getContext()).setWarmTemprature(mEairInfo.sn, mEairInfo,(byte)(progress+40));
				mProgress = progress;
			}
			
		}
	};
	
    public boolean handleMessage(Message message)
    {
        
        return false;
    }

    protected void initBg()
    {
    }

    public void initHongMiHeader()
    {
        
    }

    public void onClick(View view)
    {
        int id = view.getId();
        
        if(id == R.id.boil)  //��ˮ
        {
        	//mEairController.setMode(mEairInfo.sn, mEairInfo, 1);
        }
        else if(id == R.id.warm)  //����
        {
        	//mEairController.setMode(mEairInfo.sn, mEairInfo, 2);
        }
        else if(id == R.id.purify)  //����
        {
        	//mEairController.setMode(mEairInfo.sn, mEairInfo, 3);
        }
        else if(id == R.id.pump)  //��ˮ
        {
        	//if(mEairInfo.isPump)
        	{
        	 //   mEairController.setpupm(mEairInfo.sn,mEairInfo, 0);  //��ˮ
        	}
        	//else
        	{
        	//	 mEairController.setpupm(mEairInfo.sn,mEairInfo, 1);  //��ˮ
        	}
        	
        }
        else if(id == R.id.device_btn) //��Դ
        {
        	if(mEairInfo.powerOn)
        	{
        		mEairController.powerOn(mEairInfo.sn, mEairInfo, 0);
        	}
        	else
        	{
        		mEairController.powerOn(mEairInfo.sn,mEairInfo, 1);
        	}
        }
		else if(id == R.id.boil_auto)  //ȫ�Զ���ˮ
        {
			//mEairController.setMode(mEairInfo.sn, mEairInfo, 5);
        }
        else if(id == R.id.purify_auto)  //ȫ�Զ�����
        {
        	//mEairController.setMode(mEairInfo.sn, mEairInfo, 4);
        }
    }

    protected void onCreate(Bundle bundle)
    {

        super.onCreate(bundle);
        
        setContentView(R.layout.device_detail);
       
        initView();
        
        setTitleBackgroundColor(getResources().getColor(R.color.color_my_transparent));
        setMainBackgroundResource(R.drawable.mainbg);
        
        mEairInfo = MyApplaction.mControlDevice.getEairInfo();
        
        mEairController = EairControler.getInstance(this);
        mNetworkManager = NetWorkManager.getInstance(this);
    	mNetworkManager.setDeviceStatusChangeListener(this);  
    	
    	setBackVisible();
        setTitle(MyApplaction.mControlDevice.getDeviceName());
        
    	//mFixTempSeekBar.setProgress(humi-40);
    	//mProgress = humi-40;
    	
    }


    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
            finish();
        return super.onKeyDown(i, keyevent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        
        return false;
    }

    public void onPause()
    {
        super.onPause();

    }

    private void postQueryState()
    {
    	new Handler().post(new Runnable() {
			
			@Override
			public void run() {
				
				mEairController.queryState(mEairInfo.sn);
				
			}
		});
    }
    
    
    
    protected void onResume()
    {
        super.onResume();
        
        NetWorkManager.getInstance(this).setDeviceStatusChangeListener(this);
        
        refreshView();

        postQueryState();
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {

        super.onStop();
    }

    private void refreshView()
    {
    	
    }
    
	@Override
	public void doCallBack(int type, int data, DataInfo datainfo) {
		
		if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_RESULT)
		{
			
		}
		else if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_DATA)
		{

				if(MyApplaction.mControlDevice != null)
				{
					EairInfo eairinfo = MyApplaction.mControlDevice.getEairInfo();

					if(eairinfo.sn != datainfo.sn)
					{
						return;
					}
					
					mEairController.parseInfo(datainfo.sn, mEairInfo, datainfo.pkt);
					
					runOnUiThread(new Runnable() {
	
						@Override
						public void run() {

							//dismissProgessDialog();
							refreshView();

						}
					});
				}
				else
				{

				}
		}
		
		
	}

	private FullProgressDialog mProgressDialog;
	private OnDismissListener mDialogListener = new OnDismissListener() 
    {
		
		@Override
		public void onDismiss(DialogInterface arg0) 
		{
			
			mProgressDialog = null;
		}
	};	
	
	private void showProgressDialog()
	{
		if(mProgressDialog == null)
		{
			mProgressDialog = new FullProgressDialog();
			mProgressDialog.showProgressDialog(DeviceDetailActivity.this, mDialogListener);
		}
	}
	
	private void dismissProgessDialog()
	{
		if(mProgressDialog != null)
		{
			mProgressDialog.dismissProressDialog();
		}
	}

	
	private Dialog mHumiDialog;
	private void showHumiSetDialog(int xOffset, int yOffset)
    {
		mHumiDialog.setOnDismissListener(mHumiDialogListener);
    }
	
	private final OnDismissListener mHumiDialogListener = new OnDismissListener() 
    {
		
		@Override
		public void onDismiss(DialogInterface arg0) 
		{
			mHumiDialog = null;
		}
	};	
	
	
}

