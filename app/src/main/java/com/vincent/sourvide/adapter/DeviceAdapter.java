
package com.vincent.sourvide.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincent.sourvide.MyApplaction;
import com.vincent.sourvide.NativeUtils;
import com.vincent.sourvide.activity.EditDeviceInfoActivity;
import com.vincent.sourvide.data.ManageDevice;
import com.vincent.sourvide.view.OnSingleClickListener;
import com.vincent.sourvide.R;

public class DeviceAdapter extends BaseAdapter
{
    class ViewHolder
    {

        TextView deviceEair;
        ImageView deviceIcon;
        TextView deviceName;
        TextView deviceState;
        TextView qrInfo;

        ViewHolder()
        {
        }
    }


    private Context mContext;
    private List<ManageDevice> mDeviceList;
    private LayoutInflater mInflater;

    public DeviceAdapter(Context context, List<ManageDevice> list)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDeviceList = list;
    }

    public int getCount()
    {
        return mDeviceList.size();
    }

    public ManageDevice getItem(int i)
    {
        return (ManageDevice)mDeviceList.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(final int position, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
		
	if (view == null)
	{
		viewholder = new ViewHolder();
		
		view = mInflater.inflate(R.layout.device_list_item_layout, null);
		
		viewholder.deviceIcon = (ImageView)view.findViewById(R.id.device_icon);
		viewholder.deviceName = (TextView)view.findViewById(R.id.device_name);
		viewholder.deviceEair = (TextView)view.findViewById(R.id.eair);
		viewholder.deviceState = (TextView)view.findViewById(R.id.device_state);
		viewholder.qrInfo = (TextView)view.findViewById(R.id.qr_message);
		view.setTag(viewholder);
	} 
	else
	{
		viewholder = (ViewHolder)view.getTag();
	}
		ManageDevice md = mDeviceList.get(position);
		if(md != null && md.getDeviceName().length() > 0)
		{
			viewholder.deviceName.setText(md.getDeviceName());
		}
		else
		{
			viewholder.deviceName.setText(R.string.goodneight_device);
			
		}

		if(md != null)
		{
//			if(NetWorkManager.getInstance(mContext).JniGetDeviceLinkStatus(md.terminalId) >  0)
			if(NativeUtils.JniGetDeviceLinkStatus(md.terminalId)>0)
			{
				viewholder.deviceState.setText(mContext.getResources().getString(R.string.device_online));
			}
			else
			{
				viewholder.deviceState.setText(mContext.getResources().getString(R.string.device_offline));
			}
		}
		else
		{
			viewholder.deviceState.setText(mContext.getResources().getString(R.string.device_offline));
		}

		 
        viewholder.deviceIcon.setImageResource(R.drawable.device_icon_selector);
        viewholder.qrInfo.setText("ID:"+Integer.toString(getItem(position).terminalId & 0x7fffffff));
        viewholder.deviceEair.setText("");

        
        /*if (getItem(position).getEairInfo() != null)
        {
            //if (getItem(position).getEairInfo().state == 2)
            
             if (getItem(position).getEairInfo().air == 2)
            {
                viewholder.deviceEair.setVisibility(8);
                viewholder.deviceState.setText(R.string.un_open);
            } 
             else
            {
                viewholder.deviceEair.setVisibility(0);
                switch (getItem(position).getEairInfo().air)
                {
                case 2: // '\002'
                    viewholder.deviceState.setText(R.string.air_good);
                    break;

                case 3: // '\003'
                    viewholder.deviceState.setText(R.string.air_normal);
                    break;

                case 4: // '\004'
                    viewholder.deviceState.setText(R.string.air_bad);
                    break;

                case 1: // '\001'
                    viewholder.deviceState.setText(R.string.air_best);
                    break;
                }
            }
        }*/
        
        viewholder.deviceIcon.setOnClickListener
        (
        		new OnSingleClickListener() 
			        {
			           	
			            public void doOnClick(View view1)
			            {
			                MyApplaction.mEditDevice = getItem(position);
			                Intent intent = new Intent();
			                intent.setClass(mContext, EditDeviceInfoActivity.class);
			                mContext.startActivity(intent);
			            }
			
			        }
        	);
        
        return view;
    }

}
