
package com.vincent.sourvide.view;

import com.vincent.sourvide.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.*;

public class WindLevelAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int i);
    }


    private static Handler mHand;
    private static int mOnSelected;
    private static Runnable runnable;


    public static Dialog showAlert(Context context, int i, final OnAlertSelectId alertDo)
    {
        mOnSelected = 1;
        Dialog dialog = new Dialog(context, R.style.MMTheme_DataSheet);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.wind_level_layout, null);
        SeekBar seekbar = (SeekBar)linearlayout.findViewById(R.id.wind_bar);
        linearlayout.setMinimumWidth(10000);
        seekbar.setMax(100);

        
        seekbar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() 
		        {
		
		            public void onProgressChanged(SeekBar seekbar1, int j, boolean flag)
		            {
		                
		               /* WindLevelAlert.mHand.removeCallbacks(WindLevelAlert.runnable);
		                WindLevelAlert.mHand.postDelayed(WindLevelAlert.runnable, 500L);*/
		            }
		
		            public void onStartTrackingTouch(SeekBar seekbar1)
		            {
		            }
		
		            public void onStopTrackingTouch(SeekBar seekbar1)
		            {
		               /* if (seekbar1.getProgress() < 17)
		                {
		                    seekbar1.setProgress(0);
		                    return;
		                }
		                if (seekbar1.getProgress() >= 17 && seekbar1.getProgress() < 50)
		                {
		                    seekbar1.setProgress(33);
		                    return;
		                }
		                if (seekbar1.getProgress() >= 50 && seekbar1.getProgress() < 83)
		                {
		                    seekbar1.setProgress(66);
		                    return;
		                } else
		                {
		                    seekbar1.setProgress(100);
		                    return;
		                }*/
		            }
		        }
		);
        
        /*mHand = new Handler();
        runnable = new Runnable() 
        {

            public void run()
            {
                alertDo.onClick(WindLevelAlert.mOnSelected);
            }

        };*/

        dialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() 
        {

            public void onCancel(DialogInterface dialoginterface)
            {
            }

        }
        		);
        
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
