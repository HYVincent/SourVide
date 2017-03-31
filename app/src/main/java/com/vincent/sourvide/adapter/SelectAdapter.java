
package com.vincent.sourvide.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincent.sourvide.data.ScanDevice;
import com.vincent.sourvide.R;


public class SelectAdapter extends BaseAdapter
{
    class ViewHolder
    {

        TextView deviceEair;
        ImageView deviceIcon;
        TextView deviceName;
        TextView deviceState;
        TextView qrInfo;
        CheckBox check;
        
        ViewHolder()
        {
        }
    }


    private Context mContext;
    private List<ScanDevice> mDeviceList;
    private List<CheckBox> mCheckBoxs;
    private LayoutInflater mInflater;

    public SelectAdapter(Context context, List<ScanDevice> list)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDeviceList = list;
        mCheckBoxs = new ArrayList<CheckBox>();
    }

    public int getCount()
    {
        return mDeviceList.size();
    }

    public ScanDevice getItem(int i)
    {
        return (ScanDevice)mDeviceList.get(i);
    }

    public boolean isChecked(int i)
    {
    	CheckBox cb = mCheckBoxs.get(i);
    	if(cb != null)
    	{
    		return cb.isChecked();
    	}
    	
    	return false;
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
			
			view = mInflater.inflate(R.layout.select_list_item_layout, null);
	
			viewholder.deviceIcon = (ImageView)view.findViewById(R.id.device_icon);
			viewholder.deviceName = (TextView)view.findViewById(R.id.device_name);
			viewholder.qrInfo = (TextView)view.findViewById(R.id.qr_message);
			viewholder.check = (CheckBox)view.findViewById(R.id.check_button);
			mCheckBoxs.add(viewholder.check);
			view.setTag(viewholder);
		} 
		else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		viewholder.deviceIcon.setImageResource(R.drawable.best_fan);
		viewholder.deviceName.setText(R.string.goodneight_device);
		viewholder.qrInfo.setText(getItem(position).mac);

	return view;
    }

}
