

package com.vincent.sourvide.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;


import com.vincent.sourvide.R;

public class T1BLTimeAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int i);
    }

    static Handler mHand;
    static Runnable runnable;


    private static void setChecked(View parent, int checkIndex)
    {
    	final CheckedTextView modeManual = (CheckedTextView)parent.findViewById(R.id.check_text_mode_manual_t1);
        final CheckedTextView modeCircle = (CheckedTextView)parent.findViewById(R.id.check_text_mode_circle_t1);
        final CheckedTextView modeAuto = (CheckedTextView)parent.findViewById(R.id.check_text_mode_auto_t1);
        
        modeManual.setChecked(false);
        modeCircle.setChecked(false);
        modeAuto.setChecked(false);
        
        if(checkIndex == 1)
        {
        	modeManual.setChecked(true);
        }
        else  if(checkIndex == 2)
        {
        	modeCircle.setChecked(true);
        }
        else
        {
        	modeAuto.setChecked(true);
        }
    }
    public static Dialog showAlert(Context context, int workMode, final OnAlertSelectId alertDo)
    {
        Dialog dialog = new Dialog(context, R.style.MMTheme_DataSheet);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.bl_timer_layout_t1, null);
        linearlayout.setMinimumWidth(10000);
        
        setChecked(linearlayout, workMode);
        
        final CheckedTextView modeManual = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_manual_t1);
        final CheckedTextView modeCircle = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_circle_t1);
        final CheckedTextView modeAuto = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_auto_t1);
        
        
        setChecked(linearlayout, workMode);
        
        modeManual.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				setChecked((View)arg0.getParent(), 1);
				alertDo.onClick(1);
			}
		});
        
        modeCircle.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						setChecked((View)arg0.getParent(), 2);
						alertDo.onClick(2);
					}
				});
		 
        modeAuto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					setChecked((View)arg0.getParent(), 3);
					alertDo.onClick(3);
				}
			});

        
        android.view.WindowManager.LayoutParams layoutparams = dialog.getWindow().getAttributes();
        layoutparams.x = 0;
        layoutparams.y = -1000;
        layoutparams.gravity = 80;
        dialog.onWindowAttributesChanged(layoutparams);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearlayout);
        dialog.show();
        return dialog;
    }
    
	
}
