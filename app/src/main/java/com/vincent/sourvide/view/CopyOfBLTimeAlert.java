

package com.vincent.sourvide.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import com.vincent.sourvide.R;

public class CopyOfBLTimeAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int i);
    }

    static class TimeAdapter extends BaseAdapter
    {

        private Context mContext;
        private LayoutInflater mInflater;
        private int mSelection;
        private ArrayList<String> universityList;

        public int getCount()
        {
            return 0x7fffffff;
        }

        public Object getItemO(int i)
        {
            return getItem(i);
        }

        public String getItem(int i)
        {
            return (String)universityList.get(i % 8);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ViewHolder viewholder;
            if (view == null)
            {
                viewholder = new ViewHolder();
                view = mInflater.inflate(R.layout.galley_item_layout, null);
                viewholder.text = (TextView)view.findViewById(R.id.text);
                view.setTag(viewholder);
            } else
            {
                viewholder = (ViewHolder)view.getTag();
            }
            viewholder.text.setText((CharSequence)universityList.get(i % 8));
            if (mSelection == i % 8)
            {
                viewholder.text.setTextSize(2, 30F);
                viewholder.text.setTextColor(mContext.getResources().getColor(R.color.color_my_blue));
                return view;
            } else
            {
                viewholder.text.setTextSize(2, 20F);
                viewholder.text.setTextColor(mContext.getResources().getColor(R.color.color_gray));
                return view;
            }
        }

        public void setOnselection(int i)
        {
            mSelection = i % 8;
            notifyDataSetChanged();
        }

        public TimeAdapter(Context context, int i, int j)
        {
            universityList = new ArrayList<String>();
            int k = j;
            do
            {
                if (k > i)
                {
                    mContext = context;
                    mInflater = LayoutInflater.from(mContext);
                    return;
                }
                universityList.add((new StringBuilder(String.valueOf(k))).toString());
                k++;
            } while (true);
        }
        
        
        class ViewHolder
        {

            TextView text;

            ViewHolder()
            {
            }
        }
        
    }

    


    static Handler mHand;
    static Runnable runnable;


    public static Dialog showAlert(Context context, int i, int j, int k, final OnAlertSelectId alertDo)
    {
        /*Dialog dialog = new Dialog(context, R.style.MMTheme_DataSheet);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.bl_timer_layout, null);
        linearlayout.setMinimumWidth(10000);
        final Gallery mGridView = (Gallery)linearlayout.findViewById(R.id.timer_gallery);
        Button button = (Button)linearlayout.findViewById(R.id.btn_left);
        Button button1 = (Button)linearlayout.findViewById(R.id.btn_right);
        button.setOnClickListener(new android.view.View.OnClickListener() 
	        {
	
	            public void onClick(View view)
	            {
	                if (mGridView.getSelectedItemPosition() > 0)
	                    mGridView.setSelection(-1 + mGridView.getSelectedItemPosition());
	            }
	
	        }
       );
        
        button1.setOnClickListener(new android.view.View.OnClickListener() 
        {


            public void onClick(View view)
            {
                if (mGridView.getSelectedItemPosition() < 0x7fffffff)
                    mGridView.setSelection(1 + mGridView.getSelectedItemPosition());
            }

        });
        
        final TimeAdapter adapter = new TimeAdapter(context, k, j);
        mGridView.setAdapter(adapter);
        mGridView.setSelection(4000 + (i - j));
        mGridView.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() 
	        {
	
	            public void onItemSelected(AdapterView<?> adapterview, View view, int l, long l1)
	            {
	                adapter.setOnselection(l);
	                CopyOfBLTimeAlert.mHand.removeCallbacks(CopyOfBLTimeAlert.runnable);
	                CopyOfBLTimeAlert.mHand.postDelayed(CopyOfBLTimeAlert.runnable, 1000L);
	            }
	
	            public void onNothingSelected(AdapterView<?> adapterview)
	            {
	            }
	
	        });
        
        mHand = new Handler();
        runnable = new Runnable() 
        {

            public void run()
            {
                alertDo.onClick(Integer.parseInt(adapter.getItem(mGridView.getSelectedItemPosition())));
            }
        };
        
        android.view.WindowManager.LayoutParams layoutparams = dialog.getWindow().getAttributes();
        layoutparams.x = 0;
        layoutparams.y = -1000;
        layoutparams.gravity = 80;
        dialog.onWindowAttributesChanged(layoutparams);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearlayout);
        dialog.show();
        return dialog;*/
    	
    	return null;
    }
}
