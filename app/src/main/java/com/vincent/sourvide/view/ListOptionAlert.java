

package com.vincent.sourvide.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;


import com.vincent.sourvide.R;

public class ListOptionAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int i);
    }

    static Handler mHand;
    static Runnable runnable;


    public static Dialog showAlert(Context context, int yOffset, final OnAlertSelectId alertDo)
    {
        Dialog dialog = new Dialog(context, R.style.MMTheme_AlertOption);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_option_layout, null);
        //linearlayout.setMinimumWidth(5000);

        
        final TextView edit = (TextView)linearlayout.findViewById(R.id.list_option_edit);
        final TextView del = (TextView)linearlayout.findViewById(R.id.list_option_del);
        final TextView clearAll = (TextView)linearlayout.findViewById(R.id.list_option_clear_all);
        		        
        
        
        edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				alertDo.onClick(0);
			}
		});
        
        del.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						alertDo.onClick(1);
					}
				});

        clearAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				alertDo.onClick(2);
			}
		});
        
        android.view.WindowManager.LayoutParams layoutparams = dialog.getWindow().getAttributes();
        //layoutparams.x = 0;
        layoutparams.y = 2+yOffset; 
        layoutparams.gravity = Gravity.RIGHT | Gravity.TOP;

        dialog.onWindowAttributesChanged(layoutparams);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearlayout);
        dialog.show();
        return dialog;
    }
    
	
}
