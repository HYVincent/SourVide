

package com.vincent.sourvide.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vincent.sourvide.R;

public class BLTimeAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int i);
    }

    static Handler mHand;
    static Runnable runnable;


    private static void setChecked(View parent, int checkIndex)
    {
    	final CheckedTextView modeManual = (CheckedTextView)parent.findViewById(R.id.check_text_mode_manual);
        final CheckedTextView modeWinter = (CheckedTextView)parent.findViewById(R.id.check_text_mode_winter);
        final CheckedTextView modeFix = (CheckedTextView)parent.findViewById(R.id.check_text_mode_fix);
        final CheckedTextView modeAuto = (CheckedTextView)parent.findViewById(R.id.check_text_mode_auto);
        
        modeManual.setChecked(false);
        modeWinter.setChecked(false);
        modeFix.setChecked(false);
        modeAuto.setChecked(false);
        
        if(checkIndex == 1)
        {
        	modeManual.setChecked(true);
        }
        else  if(checkIndex == 2)
        {
        	modeWinter.setChecked(true);
        }
        else  if(checkIndex == 3)
        {
        	modeFix.setChecked(true);
        }
        else
        {
        	modeAuto.setChecked(true);
        }
    }
    public static Dialog showAlert(Context context, int workMode, final OnAlertSelectId alertDo)
    {
        Dialog dialog = new Dialog(context, R.style.MMTheme_DataSheet);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.bl_timer_layout, null);
        linearlayout.setMinimumWidth(10000);
        
        setChecked(linearlayout, workMode);
        
        final CheckedTextView modeManual = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_manual);
        final CheckedTextView modeWinter = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_winter);
        final CheckedTextView modeFix = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_fix);
        final CheckedTextView modeAuto = (CheckedTextView)linearlayout.findViewById(R.id.check_text_mode_auto);
        
        
        setChecked(linearlayout, workMode);
        
        modeManual.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				setChecked((View)arg0.getParent(), 1);
				alertDo.onClick(1);
			}
		});
        
        modeWinter.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						setChecked((View)arg0.getParent(), 2);
						alertDo.onClick(2);
					}
				});
		 
        modeFix.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					setChecked((View)arg0.getParent(), 3);
					alertDo.onClick(3);
				}
			});
		 
        modeAuto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					setChecked((View)arg0.getParent(), 4);
					alertDo.onClick(4);
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
