

package com.vincent.sourvide.activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.*;

import com.vincent.sourvide.MyApplaction;
import com.vincent.sourvide.NativeUtils;
import com.vincent.sourvide.adapter.DeviceAdapter;
import com.vincent.sourvide.common.LogManager;
import com.vincent.sourvide.data.DataInfo;
import com.vincent.sourvide.data.DelDeviceCallBack;
import com.vincent.sourvide.data.DeviceStatusChangeListener;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.data.ManageDevice;
import com.vincent.sourvide.data.ManageDeviceDao;
import com.vincent.sourvide.data.NetworkStateCallback;
import com.vincent.sourvide.net.Defines;
import com.vincent.sourvide.net.EairControler;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.net.WifiAdmin;
import com.vincent.sourvide.view.FullProgressDialog;
import com.vincent.sourvide.view.ListOptionAlert;
import com.vincent.sourvide.R;


public class DeviceActivity extends TitleActivity implements DeviceStatusChangeListener,NetworkStateCallback
{
	
	private LinearLayout mAddDevice;
    private boolean mCanExit;
    private List<ManageDevice> mDeviceList;
    private ListView mDeviceListView;
    private DeviceAdapter mDeviceAdapter;
    private Timer mExitTimer;
    private Timer mRefreshDeviceTimer;
   
    private NetWorkManager mNetworkManager;
	private FullProgressDialog mProgressDialog;
	private EairControler mEairController;
	private TextView mNoDeviceView;
	private Dialog mOptionDialog;

	private DeviceAdapter mSimpleAdapter;
	
	private int mOptionDeviceIndex=-1;
	
    private class CheckUpdateTask extends AsyncTask
    {

		@Override
		protected Object doInBackground(Object... arg0) {
			return null;
		}
    }

    class DeleteDeviceThread extends Thread
    {

        public void run()
        {
        }
    }


   
	
    public DeviceActivity()
    {
        //mDeviceList = new ArrayList<ManageDevice>();
    	mDeviceList = MyApplaction.allDeviceList;
    }


    private void findView()
    {
    	mDeviceListView = (ListView)findViewById(R.id.device_list_view);
        mAddDevice = (LinearLayout)findViewById(R.id.add_device_layout);
        mNoDeviceView = (TextView)findViewById(R.id.no_device_view);
        
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

    private void setListener()
	    {
	        mAddDevice.setOnClickListener
	        (
	        		new android.view.View.OnClickListener() 
			        {
			            public void onClick(View view)
			            {

					if (!WifiAdmin.isWifiConnect(DeviceActivity.this) )
					{
						Toast.makeText(DeviceActivity.this, getResources().getString(R.string.wifi_disconnect), Toast.LENGTH_SHORT).show();
						return;
					}
					
			            	Intent intent = new Intent();
			            	intent.setClass(DeviceActivity.this, ScanDeviceActivity.class);
			                startActivity(intent);

			            }
			        }
	        
	        );
	  
        mDeviceListView.setOnItemClickListener
        (
        		new android.widget.AdapterView.OnItemClickListener() 
	            {
			            public void onItemClick(AdapterView adapterview, View view, int i, long l)
			            {

			                //EairApplaction.mNetWorkManager.JniDisActiveAll();
			                
			            	
			            	if(!LogManager.isLogEnable())
			            	{
				            	ManageDevice device = mDeviceList.get(i);
				            	 
				            	/*if(EairApplaction.mNetWorkManager.JniGetServiceLinkStatus() <=  0)
						        {
				            		Toast.makeText(DeviceActivity.this, getResources().getString(R.string.check_network), Toast.LENGTH_SHORT).show();
				            		return;
						        }
				            	
				            	if(EairApplaction.mNetWorkManager.JniGetDeviceLinkStatus(device.getEairInfo().sn) <=  0)
						        {
				            		Toast.makeText(DeviceActivity.this, getResources().getString(R.string.this_device_online), Toast.LENGTH_SHORT).show();
				            		return;
						        }*/
				            	
				            	
				            	MyApplaction.mControlDevice = device;
				            	
				            	/*showProgressDialog();*/
				            	//EairControler controller = EairControler.getInstance(DeviceActivity.this);
				            	//controller.queryState(device.terminalId);
				            	
				            	
				            	//controller.queryTimerInfo(device.terminalId);
				            	//controller.queryDelayInfo(device.terminalId);
				            	
				            	//DelayTaskTag dtt = new DelayTaskTag();
				            	//controller.setDelay(dtt, device.terminalId);

				            	//TimerTag tt[] = new TimerTag[7];
				            	//TimerTag.initDefaultTimers(tt);
				            	//controller.setTimer(tt, device.terminalId);
				            	
				               Intent intent = new Intent();
					           intent.setClass(DeviceActivity.this, DeviceDetailActivity.class);
					           startActivity(intent);
					            
				            	
			            	}
			            	else
			            	{
			            		ManageDevice device = mDeviceList.get(i);
				            	MyApplaction.mControlDevice = device;
				            	
				            	//device.getEairInfo().workMode = 1;
	
				            	//Intent intent = new Intent();
				               // intent.setClass(DeviceActivity.this, CenterDeviceHomePageActivity.class);
				            	//intent.setClass(DeviceActivity.this, T1DeviceHomePageActivity.class);
				            	//intent.setClass(DeviceActivity.this, DeviceHomePageActivity.class);
				            	//intent.setClass(DeviceActivity.this, OpenActivity.class);
				              //startActivity(intent);
			            	}
			            }
                }
        );
        
        mDeviceListView.setOnItemLongClickListener
        (
        		new android.widget.AdapterView.OnItemLongClickListener() 
        		{
		            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
		            {
		            	int[] location = new  int[2] ;
		                view.getLocationOnScreen(location);

		                mOptionDeviceIndex = i;
						
		            	showOptionDialog(location[1]);
						return true; 
		            }
        		}
        );
    }

    public void onBackPressed()
    {
    	//mNetworkManager.exit();
        if (mCanExit)
        {
			if(MyApplaction.mNetWorkManager != null)
			{
				MyApplaction.mNetWorkManager.networkDestory();
				MyApplaction.mNetWorkManager = null;
			}
            //finish();
            mApplication.finish();

	        System.exit(0);

            return;
        } 
        else
        {
            mCanExit = true;
            Toast.makeText(this, R.string.double_click_exit, 0).show();
            if(mExitTimer == null)
            {
            	mExitTimer = new Timer();
            	
            	mExitTimer.schedule
                (
                	new TimerTask() 
                	{
    	                public void run()
    	                {
    	                    mCanExit = false;
    	                }
                    }, 1000L
                );
            	
            }
            else
            {
            	 mExitTimer.cancel();
            	 mExitTimer = null;
            }


            
            return;
        }
        
    }


    private void refreshState()
    {
    	
    	if(MyApplaction.mNetWorkManager == null)
    		return;
    	
    	//SimpleAdapter mSimpleAdapter;
    	
    		
    	    //		EairApplaction.mNetWorkManager.JniDisActiveAll();
    	    		    		
    		    	//ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*�������д������*/

    		    	for(ManageDevice device:mDeviceList)
    		    	{
    		    		int wifi_sn = device.terminalId;
    		    		
    		    			/*HashMap<String, Object> map = new HashMap<String, Object>();  
				        map.put("device_icon", R.drawable.icon);//����ͼƬ            map.put("ItemTitle", "��"+i+"��");  
				        map.put("device_name",  getResources().getString(R.string.goodneight_device));  
				        map.put("qr_message",    "ID:"+Integer.toString(wifi_sn & 0x7fffffff));  
				        map.put("eair", "");  
				        
				        if(NetWorkManager.getInstance(this).JniGetDeviceLinkStatus(wifi_sn) >  0)
				        {
					        map.put("device_state", getResources().getString(R.string.device_online));
				        }
				        else
				        {
				        	map.put("device_state", getResources().getString(R.string.device_offline));
				        }
				        
				        listItem.add(map);  */
				        

			    		//Log.e("yubihai2", "size:"+mDeviceList.size()+" id:"+"ID:"+Integer.toString(wifi_sn & 0x7fffffff));
			    		
//			    		EairApplaction.mNetWorkManager.JniActiveDevice(wifi_sn);
						NativeUtils.JniActiveDevice(wifi_sn);

    		    	}

    		    	
    		    	//��Ҫ�󶨵�����              
    		        //ÿһ�еĲ���//��̬�����е�����Դ�ļ���Ӧ�����岼�ֵ�View��
    		        	/*mSimpleAdapter = new SimpleAdapter(this, listItem, R.layout.device_list_item_layout, 
    		             new String[] {"device_icon","device_name", "qr_message", "eair", "device_state"}, 
    		             new int[] {R.id.device_icon,R.id.device_name,R.id.qr_message, R.id.eair, R.id.device_state});*/

    		    	if(mDeviceList.size() > 0)
    		    	{
						mNoDeviceView.setVisibility(View.GONE);
						if(mSimpleAdapter == null)
						{
							mSimpleAdapter = new DeviceAdapter(this, mDeviceList);
							mDeviceListView.setAdapter(mSimpleAdapter); 
						}
						mSimpleAdapter.notifyDataSetChanged();
    		    	}
    		    	else
    		    	{
    		    		mNoDeviceView.setVisibility(View.VISIBLE);
    		    	}
    		    	
	
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.device_list_layout);
        setTitle(R.string.device_mange);
        setTitleBackgroundColor(getResources().getColor(R.color.color_main_bg));
        setRightButtonClickListener(R.drawable.option_point, mOptionClick);
        
        findView();     

        setListener(); 
      
        mEairController = EairControler.getInstance(this);
        mNetworkManager = NetWorkManager.getInstance(DeviceActivity.this);
    }

	protected void onPause()
    {
           super.onPause();
        if (mRefreshDeviceTimer != null)
        {
            mRefreshDeviceTimer.cancel();
            mRefreshDeviceTimer = null;
        }
        
        
        if(mProgressDialog != null)
        {
        	mProgressDialog.dismissProressDialog();
        }

        if(mOptionDialog != null)
        {
        	mOptionDialog.dismiss();
        }
        
       // mNetworkManager.setDeviceStatusChangeListener(null);
        
        mNetworkManager.setNetworkStateCallback(null);

    }

    protected void onResume()
    {
        super.onResume();
        
        MyApplaction.mControlDevice = null;
        
       // test();
       //refreshDeviceList();
        refreshState();
        if(mRefreshDeviceTimer == null)
        {
        	mRefreshDeviceTimer = new Timer();
            mRefreshDeviceTimer.schedule
            (
            		new TimerTask() 
    		        {
    		            public void run()
    		            {
    		                refreshDeviceList();
    		            }
    		        }, 0L, 2000L
    		 );
        }
        
        
        mNetworkManager.setDeviceStatusChangeListener(this);
        
        mNetworkManager.setNetworkStateCallback(this);
    }

    private void getAllDeviceState()
    {
        Iterator iterator = MyApplaction.allDeviceList.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                //mPullToRefreshScrollView.onRefreshComplete();
                return;
            }
        	
            ManageDevice managedevice = (ManageDevice)iterator.next();
 
                if (managedevice != null)
                {
                   // managedevice1.setTclEairInfo(mBlHoneyWellDataParse.tclEairInfo(senddataresultinfo.data));
                    mDeviceAdapter.notifyDataSetChanged();
                }
        } while (true);
    }
    
    private final OnClickListener mOptionClick = new OnClickListener() 
    {
		
		@Override
		public void onClick(View arg0) 
		{
			Intent intent = new Intent();
            intent.setClass(DeviceActivity.this, OptionActivity.class);
            startActivity(intent);
		}
	};

	@Override
	public void doCallBack(int type, int data, DataInfo datainfo) 
	{
		if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_RESULT)
		{
			if(data != 0)
			{
				dismissProgessDialog();
			}
		}
		else if(type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_DATA)
		{
			//if(datainfo.len == EairControler.CONTROL_PACKERT_IN_SIZE)
			{

				if(MyApplaction.mControlDevice != null)
				{
					EairInfo eairinfo = MyApplaction.mControlDevice.getEairInfo();

					if(eairinfo.sn != datainfo.sn)
					{
						return;
					}
					
				    mEairController.parseInfo(datainfo.sn, eairinfo, datainfo.pkt);
					
					runOnUiThread(new Runnable() {
	
						@Override
						public void run() {


							Intent intent = new Intent();
				            intent.setClass(DeviceActivity.this, DeviceDetailActivity.class);
				            startActivity(intent);
							
							dismissProgessDialog();
						}
					});
				}
				else
				{
					
					/*for(ManageDevice md:mDeviceList)
					{
						EairInfo eairinfo = md.getEairInfo();
						if(eairinfo.sn == datainfo.sn);
						{
							mEairController.parseInfo(datainfo.sn, eairinfo, datainfo.pkt);
						}
					}*/
				}
			}
		}
		
	}

	private final OnDismissListener mDialogListener = new OnDismissListener() 
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
			mProgressDialog.showProgressDialog(DeviceActivity.this, mDialogListener);
		}
	}
	
	private void dismissProgessDialog()
	{
		if(mProgressDialog != null)
		{
			mProgressDialog.dismissProressDialog();
		}
	}
	
	private void showOptionDialog(int yOffset)
    {

		//NetWorkManager.getInstance(this).setDelDeviceCallBack(mDelCallback);
		
		mOptionDialog = ListOptionAlert.showAlert(this, yOffset, new ListOptionAlert.OnAlertSelectId() 
            {
                public void onClick(int i)
                {

                	if(mOptionDialog != null)
                	{
                		mOptionDialog.dismiss();
		            	mOptionDialog = null;
                	}
                	
                	if(i == 0 && mOptionDeviceIndex >= 0)
                	{
						MyApplaction.mEditDevice = mDeviceList.get(mOptionDeviceIndex);
						
						Intent intent = new Intent();
						intent.setClass(DeviceActivity.this, EditDeviceInfoActivity.class);
						startActivity(intent);
						mOptionDeviceIndex = -1;
                	}
					else if(i == 1 && mOptionDeviceIndex >= 0)
					{
							if(mDeviceList.get(mOptionDeviceIndex) != null)
							{
								//showProgressDialog();
								NetWorkManager.getInstance(DeviceActivity.this).removeWifiDev(mDeviceList.get(mOptionDeviceIndex).terminalId);
								
								try 
						        {
									(new ManageDeviceDao(getHelper())).deleteDevice(mDeviceList.get(mOptionDeviceIndex));
									mDeviceList.remove(mOptionDeviceIndex);
									refreshState();
									mOptionDeviceIndex = -1;
								} 
								catch (java.sql.SQLException e) 
								{
									e.printStackTrace();
								}
							}
					}
                	
                }
            });
		
		mOptionDialog.setOnDismissListener(mOptionDialogListener);
    }
	
	private final OnDismissListener mOptionDialogListener = new OnDismissListener() 
    {
		
		@Override
		public void onDismiss(DialogInterface arg0) 
		{
			mOptionDialog = null;
		}
	};	
	
	private final DelDeviceCallBack mDelCallback = new DelDeviceCallBack() 
    {

		@Override
		public void onDeviceDeled(int id, int res) 
		{
			
			dismissProgessDialog();
			
			if(res == 0)
			{
				
				if(mDeviceList.get(mOptionDeviceIndex) != null
						&& mDeviceList.get(mOptionDeviceIndex).terminalId == id)
				{
					try 
			        {
						(new ManageDeviceDao(getHelper())).deleteDevice(mDeviceList.get(mOptionDeviceIndex));
						mDeviceList.remove(mOptionDeviceIndex);
						refreshState();
					} 
					catch (java.sql.SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
			else
			{
				
			}
			
			mOptionDeviceIndex = -1;
			
		}
		
		
	};
	
		@Override
	public void notifyMessage(Message msg) {
		
        switch(msg.what)
        {
            case Defines.MAIN_MSG_TYPE_NETWORK_DOWN:
            {
            	refreshDeviceList();
            	break;
            }
            
            case  Defines.MAIN_MSG_TYPE_NETWORK_UP:
            {
            	refreshDeviceList();
            	break;
            }
        }

	}


}
