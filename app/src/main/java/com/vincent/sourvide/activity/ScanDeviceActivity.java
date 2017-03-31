
package com.vincent.sourvide.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.vincent.sourvide.MyApplaction;
import com.vincent.sourvide.NativeUtils;
import com.vincent.sourvide.adapter.SelectAdapter;
import com.vincent.sourvide.data.AddDeviceCallBack;
import com.vincent.sourvide.data.DataInfo;
import com.vincent.sourvide.data.DeviceStatusChangeListener;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.data.ManageDevice;
import com.vincent.sourvide.data.ScanDevice;
import com.vincent.sourvide.data.ScanDeviceListener;
import com.vincent.sourvide.net.Defines;
import com.vincent.sourvide.net.EairControler;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.view.FullProgressDialog;
import com.vincent.sourvide.R;

public class ScanDeviceActivity extends TitleActivity implements AddDeviceCallBack, DeviceStatusChangeListener
{

    private boolean mInConfig;

    private View mConfirmButton;
    
    NetWorkManager mNetworkManager;
	
    private ListView mDeviceListView;
    private TextView mNoDevice;
    private List<ScanDevice> mGotDevice;
    private List<ScanDevice> mListNewDevice;
    private List<ScanDevice> mListAllDevice;
    
    
    private List<ScanDevice> mCheckedDevice;
    private List<ScanDevice> mQueryDevice;
    
    private FullProgressDialog mProgressDialog;
    private SelectAdapter mSimpleAdapter;
    private ScanDevice mNewDevice;
    private ScanDevice mProcDevice;
    private EairControler mEairController;
    private boolean mLastOneAdd;
    private boolean mShowAll = false;
    
    private CheckBox mShowAllCheck;
    private CheckBox mShowNewCheck;
    private boolean mBeingScan=false;
    public ScanDeviceActivity()
    {
        mInConfig = false;
    }


    private void findView()
    {
    	mConfirmButton = (View)findViewById(R.id.btn_confirm);
    	mDeviceListView = (ListView)findViewById(R.id.scaning_device_list);
    	mNoDevice = (TextView)findViewById(R.id.no_device_found);
        
    }

    private OnCheckedChangeListener mCheckProc = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton button, boolean arg1) {
			
			int id = button.getId();
			
			if(id == R.id.show_all)
			{
				if(arg1 == true)
				{
					mShowAllCheck.setChecked(true);
					mShowAllCheck.setEnabled(false);
					
					mShowNewCheck.setChecked(false);
					mShowNewCheck.setEnabled(true);
					
					mShowAll = true;
					
					mGotDevice = mListAllDevice;
					
					refreshView();
				}
				
			}
			else if(id == R.id.show_new)
			{
				if(arg1 == true)
				{
					mShowNewCheck.setChecked(true);
					mShowNewCheck.setEnabled(false);
					
					mShowAllCheck.setChecked(false);
					mShowAllCheck.setEnabled(true);
					mShowAll = false;
					
					mGotDevice = mListNewDevice;
					
					refreshView();
				}
			}
		}
	};
    private void initView()
    {

    	mShowAllCheck = (CheckBox)findViewById(R.id.show_all);
    	mShowNewCheck = (CheckBox)findViewById(R.id.show_new);
    	mShowNewCheck.setChecked(true);
    	mShowAllCheck.setOnCheckedChangeListener(mCheckProc);
    	mShowNewCheck.setOnCheckedChangeListener(mCheckProc);
    	
    	
    	mConfirmButton.setVisibility(View.VISIBLE);
    	mConfirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				mNetworkManager.setScanDeviceListener(null);
				
				if(mSimpleAdapter != null)
				{
					showProgressDialog();
					for(int i = 0; i < mSimpleAdapter.getCount(); i++)
					{
						if(mSimpleAdapter.isChecked(i))
						{
							mGotDevice.get(i).checked = true;
						}
					}
					
					mCheckedDevice.clear();
					mQueryDevice.clear();
					
					for(ScanDevice sd:mGotDevice)
					{
						if(sd.checked)
						{
							mCheckedDevice.add(sd);
						}
					}
					
					if(mCheckedDevice.size() == 0)
					{
						back();
					}
					else
					{
						SaveConfig();
					}
				}
				else
				{
					back();
				}
				
			}
		});
    	
    } 



    public void back()
    {
    	exitScan();
        finish();
    }


  	 private void queryProc()
	{
		
  		if(mQueryDevice.size() > 0)
  		{
	  		ScanDevice sd = mQueryDevice.get(0);
	
//			EairApplaction.mNetWorkManager.JniActiveDevice(sd.id);
			NativeUtils.JniActiveDevice(sd.id);
			
			 mProcDevice = sd;
			 mEairController.queryState(mProcDevice.id);
			 mQueryDevice.remove(0);
  		}
  		else
  		{
  			back();
  		}

	}
  	 
    private void SaveConfig()  
	{
    	
    		ScanDevice di = mCheckedDevice.get(0);
		mNewDevice = di;
		NetWorkManager.getInstance(this).AddNewWifiDev(di.id);
		mCheckedDevice.remove(0);

		
	}
    
    private boolean isNewID(int id)
    {
    	 for(ScanDevice di:mGotDevice)
	  	   {
	  		   if(di != null)
	  		   {
	  			   if (di != null
	  					   && di.id == id)
	                 {
	  	   				 return true;
	                 }
	  		   }
	  	   }
    	 
    	return false;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	dismissProgessDialog();
    	exitScan();
    	//smNetworkManager.JniExitScanMode();
    }
    
    private boolean isNotAddedDevice(int id)
    {
    	MyApplaction ea = (MyApplaction)getApplication();
    			
    	for(ManageDevice md:ea.allDeviceList)
    	{
    		EairInfo ei = md.getEairInfo();
    		if(ei != null)
    		{
    			if(ei.sn == id)
    			{
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
    
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.scandevice_layout);
        setTitle(R.string.search_device);
        setTitleBackgroundColor(getResources().getColor(R.color.color_main_bg));
        setBackVisible();
        findView();
        initView();
        
        
        //mGotDevice = new ArrayList<ScanDevice>();
        mListNewDevice = new ArrayList<ScanDevice>();
        mListAllDevice = new ArrayList<ScanDevice>();
        mGotDevice = mListNewDevice;
        
        mQueryDevice = new ArrayList<ScanDevice>();
        mCheckedDevice = new ArrayList<ScanDevice>(); 
    	mNetworkManager = NetWorkManager.getInstance(this);
    	
    	//mNetworkManager.JniEntryScanMode();
    	mEairController = EairControler.getInstance(this);
//		mNetworkManager.JniEntryScanMode();
		NativeUtils.JniEntryScanMode();
		mBeingScan = true;
    	mNetworkManager.setScanDeviceListener
    	(
			new ScanDeviceListener() 
			{
	
			    public void deviceInfoCallBack(int id)
			    {
			    	ScanDevice info = new ScanDevice();
			    	info.id = id;
			    	info.mac = Integer.toString(id & 0x7fffffff);
			    	info.deviceName = info.mac;
			    	
			        if (info != null && info.id != 0)
			        {
			        	if(!isNewID(info.id))
			        	{
			        		mListAllDevice.add(info);
			        		
			        		if(isNotAddedDevice(info.id))
			        		{
			        			mListNewDevice.add(info);
			        		}
			                
			        		refreshView();
			        	}
			        }
			    }
			}
	);
    	
    	mNetworkManager.setAddDeviceCallBack(this);
    	
    }

    @Override
    protected void onPause() 
    {
    	super.onPause();
    	//mNetworkManager.JniExitScanMode();
    }
    
    protected void onResume()
    {
	super.onResume();
	//mNetworkManager.JniEntryScanMode();
	mNetworkManager.setDeviceStatusChangeListener(this);
    }

   private void refreshView()
   {
	   //ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*�������д������*/
	   
	   if(mGotDevice.size() > 0)
	   {
		   mNoDevice.setVisibility(View.GONE);
	   }
	   else
	   {
		   mNoDevice.setVisibility(View.VISIBLE);
	   }
	   
	   
	   
	   /*for(ScanDevice di:mGotDevice)
	   {
		   if(di != null)
		   {
			   if (di != null)
               {
	   				HashMap<String, Object> map = new HashMap<String, Object>();  
	   				map.put("device_icon", R.drawable.icon);//����ͼƬ            map.put("ItemTitle", "��"+i+"��");  
	   				map.put("device_name",  getResources().getString(R.string.goodneight_device));  
	   				map.put("qr_message",    "ID:"+Integer.toString(di.id & 0x7fffffff));      
	   				listItem.add(map);  
               }
		   }
	   }*/
       
    	 //��Ҫ�󶨵�����              
	        //ÿһ�еĲ���//��̬�����е�����Դ�ļ���Ӧ�����岼�ֵ�View��
	       /* mSimpleAdapter = new SimpleAdapter(this, listItem, R.layout.select_list_item_layout, 
	             new String[] {"device_icon","device_name", "qr_message"}, 
	             new int[] {R.id.device_icon,R.id.device_name,R.id.qr_message});*/
	   		mSimpleAdapter = new SelectAdapter(this, mGotDevice);
	   
	        mDeviceListView.setAdapter(mSimpleAdapter); 
	        mDeviceListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					mProgressDialog = null;
				}
			});
	        
   }
   
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
			mProgressDialog.showProgressDialog(ScanDeviceActivity.this, mDialogListener);
		}
	}
	
	private void dismissProgessDialog()
	{
		if(mProgressDialog != null)
		{
			mProgressDialog.dismissProressDialog();
		}
	}


	@Override
	public void onDeviceAdded(int wifi_sn, int res) 
	{

		/*
		if(true)
		{

			if(res == 0)
	       	{	
	       			
	       			String name = getResources().getString(R.string.default_name);

					EairInfo eairinfo = new EairInfo();
					eairinfo.sn = wifi_sn;
					eairinfo.deviceType = EairControler.TYPE_T2;
					EairApplaction app = (EairApplaction)getApplication();
					if(app != null)
					{
						mNewDevice.deviceType = (short)EairControler.TYPE_T2;
						mNewDevice.deviceName = name;
						mNewDevice.mac = Integer.toString(wifi_sn & 0x7fffffff);

						app.checkCreateOrUpdateScanDevice(mNewDevice, eairinfo);

						EairApplaction.mNetWorkManager.JniActiveDevice(eairinfo.sn);
					}

					String s = getResources().getString(R.string.add_device) + eairinfo.sn + getResources().getString(R.string.success);
					Toast.makeText(this,  s, Toast.LENGTH_SHORT).show();
	       	}
	       	else
	       	{
	       		String s = getResources().getString(R.string.add_device) + wifi_sn + getResources().getString(R.string.fail);
				Toast.makeText(this,  s, Toast.LENGTH_SHORT).show();
	       	}
			
			back();
			return;
		}*/

		
       	if(res == 0)
       	{	
       			
       			mQueryDevice.add(mNewDevice);
       	}
       	else
       	{
       		String s = getResources().getString(R.string.add_device) + wifi_sn + getResources().getString(R.string.fail);
			Toast.makeText(this,  s, Toast.LENGTH_SHORT).show();
       	}

       	if(mCheckedDevice.size() > 0)
       	{
       		SaveConfig();
       	}
       	else
       	{
       		
       		if(mQueryDevice.size() > 0)
       		{
       			//mNetworkManager.JniExitScanMode();
       			exitScan();
	       		new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						queryProc();
					}
				}, 3000L);
       		}
       		else
       		{
       			back();
       		}
       	}
		
	}
	
	
	@Override
	public void doCallBack(int type, int data, DataInfo datainfo) 
	{
		
		
		
		if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_RESULT)
		{
			if(data != 0)
			{
				//dismissProgessDialog();
				
				if(datainfo != null)
				{	
					String s = getResources().getString(R.string.add_device) + Integer.toString(datainfo.sn & 0x7fffffff) + getResources().getString(R.string.fail);
					Toast.makeText(this,  s, Toast.LENGTH_SHORT).show();
				}
				
			}
			
			return;
			
		}
		else if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_DATA)
		{

			if(mProcDevice == null || datainfo == null)
			{
				return;
			}
			
			if(datainfo.sn != mProcDevice.id)
			{
				return;
			}
			
			String s = getResources().getString(R.string.add_device) + Integer.toString(datainfo.sn & 0x7fffffff) + getResources().getString(R.string.success);
			Toast.makeText(this,  s, Toast.LENGTH_SHORT).show();
			
			
			boolean newOne = true;
			for(ManageDevice md: MyApplaction.allDeviceList)
			{
				if(md.terminalId == datainfo.sn)
				{
					newOne = false;
					break;
				}
			}

			if(newOne)
			{
				
				EairInfo eairinfo = new EairInfo();
				//if(eairinfo != null)
				{
					mEairController.parseInfo(datainfo.sn, eairinfo, datainfo.pkt);
				}
	
				String name = getResources().getString(R.string.default_name);
				
				MyApplaction app = (MyApplaction)getApplication();
				if(app != null)
				{
					mProcDevice.deviceType = (short)eairinfo.deviceType;
					mProcDevice.deviceName = name;
					mProcDevice.mac = Integer.toString(eairinfo.sn & 0x7fffffff);
					app.checkCreateOrUpdateScanDevice(mProcDevice, eairinfo);

//					EairApplaction.mNetWorkManager.JniActiveDevice(eairinfo.sn);
					NativeUtils.JniActiveDevice(eairinfo.sn);
				}
			}

		}
		
		if(mQueryDevice.size() > 0)
		{
			queryProc();
		}
		else
		{
			dismissProgessDialog();
			back();
		}
		
		
	}
	
	private void exitScan()
	{
		
		if(mBeingScan)
		{
			mBeingScan = false;
//			mNetworkManager.JniExitScanMode();
			NativeUtils.JniExitScanMode();
		}
		
	}
	
}

