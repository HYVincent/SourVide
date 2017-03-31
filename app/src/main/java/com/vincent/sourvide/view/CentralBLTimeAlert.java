

package com.vincent.sourvide.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;


import com.vincent.sourvide.R;

public class CentralBLTimeAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int i);
    }

    static Handler mHand;
    static Runnable runnable;


    public static void setChecked(View parent, int checkIndex)
    {
    	final CheckedTextView modeManualNoLimit = (CheckedTextView)parent.findViewById(R.id.central_check_text_mode_manual);
        final CheckedTextView modeLow = (CheckedTextView)parent.findViewById(R.id.central_check_text_mode_low_speed);
        final CheckedTextView modeMid = (CheckedTextView)parent.findViewById(R.id.central_check_text_mode_mid_speed);
        final CheckedTextView modeHigh = (CheckedTextView)parent.findViewById(R.id.central_check_text_mode_high_speed);
        final CheckedTextView modeAuto = (CheckedTextView)parent.findViewById(R.id.central_check_text_mode_auto);
        		
        modeManualNoLimit.setChecked(false);
        modeLow.setChecked(false);
        modeMid.setChecked(false);
        modeHigh.setChecked(false);
        modeAuto.setChecked(false);
        
        if(checkIndex == 1)
        {
        	modeLow.setChecked(true);
        }
        else  if(checkIndex == 2)
        {
        	modeMid.setChecked(true);
        }
        else  if(checkIndex == 3)
        {
        	modeHigh.setChecked(true);
        }
        else  if(checkIndex == 4)
        {
        	modeManualNoLimit.setChecked(true);
        }
        else
        {
        	modeAuto.setChecked(true);
        }
    }
    public static Dialog showAlert(Context context, int workMode, final OnAlertSelectId alertDo)
    {
        Dialog dialog = new Dialog(context, R.style.MMTheme_DataSheet);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.bl_timer_layout_central, null);
        linearlayout.setMinimumWidth(10000);
        
        setChecked(linearlayout, workMode);
        

        final CheckedTextView modeManualNoLimit = (CheckedTextView)linearlayout.findViewById(R.id.central_check_text_mode_manual);
        final CheckedTextView modeLow = (CheckedTextView)linearlayout.findViewById(R.id.central_check_text_mode_low_speed);
        final CheckedTextView modeMid = (CheckedTextView)linearlayout.findViewById(R.id.central_check_text_mode_mid_speed);
        final CheckedTextView modeHigh = (CheckedTextView)linearlayout.findViewById(R.id.central_check_text_mode_high_speed);
        final CheckedTextView modeAuto = (CheckedTextView)linearlayout.findViewById(R.id.central_check_text_mode_auto);
        
        setChecked(linearlayout, workMode);
        
        modeLow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				setChecked((View)arg0.getParent(), 1);
				alertDo.onClick(1);
			}
		});
        
        modeMid.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						setChecked((View)arg0.getParent(), 2);
						alertDo.onClick(2);
					}
				});
		 
        
        modeHigh.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					setChecked((View)arg0.getParent(), 3);
					alertDo.onClick(3);
				}
			});

        modeManualNoLimit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setChecked((View)arg0.getParent(), 4);
				alertDo.onClick(4);
			}
		});
        
        modeAuto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setChecked((View)arg0.getParent(), 5);
				alertDo.onClick(5);
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
