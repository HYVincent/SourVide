package com.vincent.sourvide.activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;  
import android.app.Activity;
import android.os.Message;

import com.vincent.sourvide.data.NetworkStateCallback;
import com.vincent.sourvide.net.Defines;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.R;

public class NetworkTestActivity extends Activity 
{
      
	private TextView myTextView;
	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */
	

	 String str_Text;
	 NetWorkManager mNetworkManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_test_activity_main);
		//DevInfo DevInfo = new DevInfo();
		
		//MyHandler = new MyHandler(sendtd.getLooper());
	    int servip = 0x79285c27;
		int port = 6354;
		
		Log.d("UIs:",  "init");
		
		mNetworkManager = NetWorkManager.getInstance(this, port, servip);
		
		mNetworkManager.setNetworkStateCallback
		(
				new NetworkStateCallback() 
				{
			
				@Override
				public void notifyMessage(Message msg) 
				{
					int dev_id = msg.arg1;
		        	int datalen = msg.arg2;
		        	String str = null;
		            switch(msg.what)
		            {
		                case Defines.MAIN_MSG_TYPE_DATA:
		               {
		            	   str += "recv["+ Integer.toHexString(dev_id)+"]:len="+ Integer.toString(datalen)+"\n";
		                	break;
		                }
		                
		                case Defines.MAIN_MSG_TYPE_NETWORK_DOWN:
		                {
		                	str += "dev id = "+ Integer.toHexString(dev_id)+": link down"+"\n";
		                	break;
		                }
		                
		                case  Defines.MAIN_MSG_TYPE_NETWORK_UP:
		                {
		                	str += "dev id = "+ Integer.toHexString(dev_id)+": link up"+"\n";
		                	break;
		                }
		                
		                case Defines.MAIN_MSG_TYPE_FIND_NEW_WIFI_DEV:
		                {
		                	str += "dev id = "+ Integer.toHexString(dev_id)+": find a new wifi device"+"\n";
		                    break;
		                }
		                
		                case Defines.MAIN_MSG_TYPE_MISS_NEW_WIFI_DEV:
		                {
		                	str += "dev id = "+ Integer.toHexString(dev_id)+": loss a new wifi device"+"\n";
		                    break;
		                }
		                
		                case Defines.MAIN_MSG_TYPE_ADD_NEW_WIFI_DEV_SUCCESS:
		                {
		                	str_Text += "dev id = "+ Integer.toHexString(dev_id)+": Add new wifi device"+"\n";
		                    break;
		                }
		                
		                case Defines.MAIN_MSG_TYPE_DEL_WIFI_DEV_SUCCESS:
		                {
		                	str_Text += "dev id = "+ Integer.toHexString(dev_id)+": Del wifi device"+"\n";
		                    break;
		                }
		                
		            }
					
		            if(str_Text != null)
		            {
		            	myTextView.setText(str);
		            }
				}
		});
		//mNetworkManager.setIPAndPort(port, servip);
		//devinfo.addserv(0x80000000, servip, (short)port, true);
		//devinfo.addwifi(0xCB000012);

	    myTextView = (TextView)findViewById(R.id.myTextView);
	
		str_Text += "dd";
		myTextView.setText(str_Text);
	}
	
 
}
