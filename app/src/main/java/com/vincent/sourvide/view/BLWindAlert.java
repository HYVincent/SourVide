
package com.vincent.sourvide.view;

import com.vincent.sourvide.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class BLWindAlert
{
    public static interface OnAlertSelectId
    {

        public abstract void onClick(int windinLevel, int windoutLevel);
    }


    private static Handler mHand;
    private static int mOnSelected;
    private static int mOnSelectedWindout;
    private static Runnable runnable;
    

    public static Dialog showAlert(Context context, int winin_level, int winout_level, final OnAlertSelectId alertDo)
    {
        mOnSelected = 1;
        Dialog dialog = new Dialog(context, R.style.MMTheme_DataSheet);
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.bl_seekbar_layout, null);
        linearlayout.setMinimumWidth(10000);
        SeekBar seekbar = (SeekBar)linearlayout.findViewById(R.id.wind_bar);
        
        final CheckBox wind0 = (CheckBox)linearlayout.findViewById(R.id.wind_0);
        final CheckBox wind1 = (CheckBox)linearlayout.findViewById(R.id.wind_1);
        final CheckBox wind2 = (CheckBox)linearlayout.findViewById(R.id.wind_2);
        final CheckBox wind3 = (CheckBox)linearlayout.findViewById(R.id.wind_3);
        final CheckBox wind4 = (CheckBox)linearlayout.findViewById(R.id.wind_4);
        final CheckBox wind5 = (CheckBox)linearlayout.findViewById(R.id.wind_5);
        final CheckBox wind6 = (CheckBox)linearlayout.findViewById(R.id.wind_6);
        final CheckBox wind7 = (CheckBox)linearlayout.findViewById(R.id.wind_7);
        final CheckBox wind8 = (CheckBox)linearlayout.findViewById(R.id.wind_8);
        final CheckBox wind9 = (CheckBox)linearlayout.findViewById(R.id.wind_9);
        seekbar.setMax(1000);
        
        TextView windinText = (TextView)linearlayout.findViewById(R.id.wind_in_set);
        if(windinText != null)
        {
        	String s = context.getResources().getString(R.string.text_wind_in);
        	windinText.setText(s+winin_level);
        }
        switch (winin_level)
        {
        //case 4: // '\b'
        default:
            mOnSelected = 0;
            wind0.setChecked(true);
            wind1.setChecked(false);
            wind2.setChecked(false);
            wind3.setChecked(false);
            wind4.setChecked(false);
            wind5.setChecked(false);
            wind6.setChecked(false);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(0);
            break;
            
        case 9:
            mOnSelected = 9;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(true);
            wind5.setChecked(true);
            wind6.setChecked(true);
            wind7.setChecked(true);
            wind8.setChecked(true);
            wind9.setChecked(true);
            seekbar.setProgress(1000);
            break;

        case 8: // '\004'
            mOnSelected = 8;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(true);
            wind5.setChecked(true);
            wind6.setChecked(true);
            wind7.setChecked(true);
            wind8.setChecked(true);
            wind9.setChecked(false);
            seekbar.setProgress(800);
            break;
        case 7: // '\004'
            mOnSelected = 7;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(true);
            wind5.setChecked(true);
            wind6.setChecked(true);
            wind7.setChecked(true);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(700);
            break;
        case 6: // '\004'
            mOnSelected = 6;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(true);
            wind5.setChecked(true);
            wind6.setChecked(true);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(600);
            break;
        case 5: // '\004'
            mOnSelected = 5;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(true);
            wind5.setChecked(true);
            wind6.setChecked(false);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(500);
            break;
        case 4: // '\004'
            mOnSelected = 4;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(true);
            wind5.setChecked(false);
            wind6.setChecked(false);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(400);
            break;
            
        case 3: // '\004'
            mOnSelected = 3;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(true);
            wind4.setChecked(false);
            wind5.setChecked(false);
            wind6.setChecked(false);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(300);
            break;

        case 2: // '\002'
            mOnSelected = 2;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(true);
            wind3.setChecked(false);
            wind4.setChecked(false);
            wind5.setChecked(false);
            wind6.setChecked(false);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(200);
            break;

        case 1: // '\001'
            mOnSelected = 1;
            wind0.setChecked(true);
            wind1.setChecked(true);
            wind2.setChecked(false);
            wind3.setChecked(false);
            wind4.setChecked(false);
            wind5.setChecked(false);
            wind6.setChecked(false);
            wind7.setChecked(false);
            wind8.setChecked(false);
            wind9.setChecked(false);
            seekbar.setProgress(100);
            break;
        }
        
        seekbar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() 
		        {
		
		            public void onProgressChanged(SeekBar seekbar1, int j, boolean flag)
		            {
		            	if (j < 20)
		                {
		            		BLWindAlert.mOnSelected = 0;
		                    wind0.setChecked(true);
		                    wind1.setChecked(false);
		                    wind2.setChecked(false);
		                    wind3.setChecked(false);
		                    wind4.setChecked(false);
		                    wind5.setChecked(false);
		                    wind6.setChecked(false);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } else
		                if (j >= 20 & j < 200)
		                {
		                	BLWindAlert.mOnSelected = 1;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(false);
		                    wind3.setChecked(false);
		                    wind4.setChecked(false);
		                    wind5.setChecked(false);
		                    wind6.setChecked(false);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } else
		                if (j >= 200 && j < 300)
		                {
		                	BLWindAlert.mOnSelected = 2;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(false);
		                    wind4.setChecked(false);
		                    wind5.setChecked(false);
		                    wind6.setChecked(false);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } else
		                if (j >= 300 && j < 400)
		                {
		                	BLWindAlert.mOnSelected = 3;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(false);
		                    wind5.setChecked(false);
		                    wind6.setChecked(false);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } 
		                else
		                if (j >= 400 && j < 500)
		                {
		                	BLWindAlert.mOnSelected = 4;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(true);
		                    wind5.setChecked(false);
		                    wind6.setChecked(false);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } 
		                else
		                if (j >= 500 && j < 600)
		                {
		                	BLWindAlert.mOnSelected = 5;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(true);
		                    wind5.setChecked(true);
		                    wind6.setChecked(false);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } 
		                else
		                if (j >= 600 && j < 700)
		                {
		                	BLWindAlert.mOnSelected = 6;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(true);
		                    wind5.setChecked(true);
		                    wind6.setChecked(true);
		                    wind7.setChecked(false);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                } 
		                else
		                if (j >= 700 && j < 800)
		                {
		                	BLWindAlert.mOnSelected = 7;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(true);
		                    wind5.setChecked(true);
		                    wind6.setChecked(true);
		                    wind7.setChecked(true);
		                    wind8.setChecked(false);
		                    wind9.setChecked(false);
		                }
		                else
		                if (j >= 800 && j < 980)
		                {
		                	BLWindAlert.mOnSelected = 8;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(true);
		                    wind5.setChecked(true);
		                    wind6.setChecked(true);
		                    wind7.setChecked(true);
		                    wind8.setChecked(true);
		                    wind9.setChecked(false);
		                }
		                else
		                {
		                	BLWindAlert.mOnSelected = 9;
		                    wind0.setChecked(true);
		                    wind1.setChecked(true);
		                    wind2.setChecked(true);
		                    wind3.setChecked(true);
		                    wind4.setChecked(true);
		                    wind5.setChecked(true);
		                    wind6.setChecked(true);
		                    wind7.setChecked(true);
		                    wind8.setChecked(true);
		                    wind9.setChecked(true);
		                }
		                
		            	BLWindAlert.mHand.removeCallbacks(BLWindAlert.runnable);
		            	BLWindAlert.mHand.postDelayed(BLWindAlert.runnable, 500L);
		                
		                View parent = (View)seekbar1.getParent().getParent();
		                if(parent != null)
		                {
		                	TextView text = (TextView)parent.findViewById(R.id.wind_in_set);
			                if(text != null)
			                {
			                	String s = seekbar1.getContext().getResources().getString(R.string.text_wind_in);
			                	text.setText(s+BLWindAlert.mOnSelected);
			                }
		                }
		                
		            }
		
		            public void onStartTrackingTouch(SeekBar seekbar1)
		            {
		            }
		
		            public void onStopTrackingTouch(SeekBar seekbar1)
		            {
		            	if (seekbar1.getProgress() < 111)
		                {
		                    //seekbar1.setProgress(0);
		                    return;
		                }
		            	else if (seekbar1.getProgress() >= 111 && seekbar1.getProgress() < 222)
		                {
		                    //seekbar1.setProgress(111);
		                    return;
		                }
		                else if (seekbar1.getProgress() >= 222 && seekbar1.getProgress() < 333)
		                {
		                    //seekbar1.setProgress(222);
		                    return;
		                }
		                else if (seekbar1.getProgress() >= 333 && seekbar1.getProgress() < 444)
		                {
		                    //seekbar1.setProgress(333);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 444 && seekbar1.getProgress() < 555)
		                {
		                    //seekbar1.setProgress(444);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 555 && seekbar1.getProgress() < 666)
		                {
		                    //seekbar1.setProgress(555);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 666 && seekbar1.getProgress() < 777)
		                {
		                    //seekbar1.setProgress(666);
		                    return;
		                }
		                else if (seekbar1.getProgress() >= 777 && seekbar1.getProgress() < 888)
		                {
		                    //seekbar1.setProgress(777);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 888 && seekbar1.getProgress() < 999)
		                {
		                    //seekbar1.setProgress(888);
		                    return;
		                } 
		                else
		                {
		                    //seekbar1.setProgress(999);
		                    return;
		                }
		            }
		        }
		);
        
        
        
        SeekBar seekbar_windout = (SeekBar)linearlayout.findViewById(R.id.windout_bar);
        final CheckBox windout_0 = (CheckBox)linearlayout.findViewById(R.id.windout_0);
        final CheckBox windout_1 = (CheckBox)linearlayout.findViewById(R.id.windout_1);
        final CheckBox windout_2 = (CheckBox)linearlayout.findViewById(R.id.windout_2);
        final CheckBox windout_3 = (CheckBox)linearlayout.findViewById(R.id.windout_3);
        final CheckBox windout_4 = (CheckBox)linearlayout.findViewById(R.id.windout_4);
        final CheckBox windout_5 = (CheckBox)linearlayout.findViewById(R.id.windout_5);
        final CheckBox windout_6 = (CheckBox)linearlayout.findViewById(R.id.windout_6);
        final CheckBox windout_7 = (CheckBox)linearlayout.findViewById(R.id.windout_7);
        final CheckBox windout_8 = (CheckBox)linearlayout.findViewById(R.id.windout_8);
        final CheckBox windout_9 = (CheckBox)linearlayout.findViewById(R.id.windout_9);
        
        TextView windoutText = (TextView)linearlayout.findViewById(R.id.wind_out_set);
        if(windinText != null)
        {
        	String s = context.getResources().getString(R.string.text_wind_out);
        	windoutText.setText(s+winout_level);
        }
        
        seekbar_windout.setMax(1000);
        
        switch (winout_level)
        {
        //case 4: // '\b'
        default:
        	mOnSelectedWindout = 0;
        	windout_0.setChecked(true);
            windout_1.setChecked(false);
            windout_2.setChecked(false);
            windout_3.setChecked(false);
            windout_4.setChecked(false);
            windout_5.setChecked(false);
            windout_6.setChecked(false);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(0);
            break;
        case 9:
        	mOnSelectedWindout = 9;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(true);
            windout_5.setChecked(true);
            windout_6.setChecked(true);
            windout_7.setChecked(true);
            windout_8.setChecked(true);
            windout_9.setChecked(true);
            seekbar_windout.setProgress(900);
            break;

        case 8: // '\004'
        	mOnSelectedWindout = 8;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(true);
            windout_5.setChecked(true);
            windout_6.setChecked(true);
            windout_7.setChecked(true);
            windout_8.setChecked(true);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(800);
            break;
        case 7: // '\004'
        	mOnSelectedWindout = 7;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(true);
            windout_5.setChecked(true);
            windout_6.setChecked(true);
            windout_7.setChecked(true);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(700);
            break;
        case 6: // '\004'
        	mOnSelectedWindout = 6;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(true);
            windout_5.setChecked(true);
            windout_6.setChecked(true);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(600);
            break;
        case 5: // '\004'
        	mOnSelectedWindout = 5;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(true);
            windout_5.setChecked(true);
            windout_6.setChecked(false);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(500);
            break;
        case 4: // '\004'
        	mOnSelectedWindout = 4;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(true);
            windout_5.setChecked(false);
            windout_6.setChecked(false);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(400);
            break;
            
        case 3: // '\004'
        	mOnSelectedWindout = 3;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(true);
            windout_4.setChecked(false);
            windout_5.setChecked(false);
            windout_6.setChecked(false);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(300);
            break;

        case 2: // '\002'
        	mOnSelectedWindout = 2;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(true);
            windout_3.setChecked(false);
            windout_4.setChecked(false);
            windout_5.setChecked(false);
            windout_6.setChecked(false);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(200);
            break;

        case 1: // '\001'
        	mOnSelectedWindout = 1;
        	windout_0.setChecked(true);
            windout_1.setChecked(true);
            windout_2.setChecked(false);
            windout_3.setChecked(false);
            windout_4.setChecked(false);
            windout_5.setChecked(false);
            windout_6.setChecked(false);
            windout_7.setChecked(false);
            windout_8.setChecked(false);
            windout_9.setChecked(false);
            seekbar_windout.setProgress(100);
            break;
        }
        
        seekbar_windout.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() 
		        {
		
		            public void onProgressChanged(SeekBar seekbar1, int j, boolean flag)
		            {
			                
		                if (j < 20)
		                {
		                	BLWindAlert.mOnSelectedWindout = 0;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(false);
		                    windout_2.setChecked(false);
		                    windout_3.setChecked(false);
		                    windout_4.setChecked(false);
		                    windout_5.setChecked(false);
		                    windout_6.setChecked(false);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                }
		                else if (j >= 20 && j < 200)
		                {
		                	BLWindAlert.mOnSelectedWindout = 1;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(false);
		                    windout_3.setChecked(false);
		                    windout_4.setChecked(false);
		                    windout_5.setChecked(false);
		                    windout_6.setChecked(false);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                }
		                else
		                if (j >= 200 && j < 300)
		                {
		                	BLWindAlert.mOnSelectedWindout = 2;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(false);
		                    windout_4.setChecked(false);
		                    windout_5.setChecked(false);
		                    windout_6.setChecked(false);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                } else
		                if (j >= 300 && j < 400)
		                {
		                	BLWindAlert.mOnSelectedWindout = 3;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(false);
		                    windout_5.setChecked(false);
		                    windout_6.setChecked(false);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                } 
		                else
		                if (j >= 400 && j < 500)
		                {
		                	BLWindAlert.mOnSelectedWindout = 4;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(true);
		                    windout_5.setChecked(false);
		                    windout_6.setChecked(false);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                } 
		                else
		                if (j >= 500 && j < 600)
		                {
		                	BLWindAlert.mOnSelectedWindout = 5;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(true);
		                    windout_5.setChecked(true);
		                    windout_6.setChecked(false);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                } 
		                else
		                if (j >= 600 && j < 700)
		                {
		                	BLWindAlert.mOnSelectedWindout = 6;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(true);
		                    windout_5.setChecked(true);
		                    windout_6.setChecked(true);
		                    windout_7.setChecked(false);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                } 
		                else
		                if (j >= 700 && j < 800)
		                {
		                	BLWindAlert.mOnSelectedWindout = 7;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(true);
		                    windout_5.setChecked(true);
		                    windout_6.setChecked(true);
		                    windout_7.setChecked(true);
		                    windout_8.setChecked(false);
		                    windout_9.setChecked(false);
		                }
		                else
		                if (j >= 800 && j < 980)
		                {
		                	BLWindAlert.mOnSelectedWindout = 8;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(true);
		                    windout_5.setChecked(true);
		                    windout_6.setChecked(true);
		                    windout_7.setChecked(true);
		                    windout_8.setChecked(true);
		                    windout_9.setChecked(false);
		                }
		                else
		                {
		                	BLWindAlert.mOnSelectedWindout = 9;
		                    windout_0.setChecked(true);
		                    windout_1.setChecked(true);
		                    windout_2.setChecked(true);
		                    windout_3.setChecked(true);
		                    windout_4.setChecked(true);
		                    windout_5.setChecked(true);
		                    windout_6.setChecked(true);
		                    windout_7.setChecked(true);
		                    windout_8.setChecked(true);
		                    windout_9.setChecked(true);
		                }
		                
		                BLWindAlert.mHand.removeCallbacks(BLWindAlert.runnable);
		                BLWindAlert.mHand.postDelayed(BLWindAlert.runnable, 500L);
		                
		                View parent = (View)seekbar1.getParent().getParent();
		                if(parent != null)
		                {
		                	TextView windoutText = (TextView)parent.findViewById(R.id.wind_out_set);
			                if(windoutText != null)
			                {
			                	String s = seekbar1.getContext().getResources().getString(R.string.text_wind_out);
			                	windoutText.setText(s+BLWindAlert.mOnSelectedWindout);
			                }
		                }
		                
		            }
		
		            public void onStartTrackingTouch(SeekBar seekbar1)
		            {
		            }
		
		            public void onStopTrackingTouch(SeekBar seekbar1)
		            {
		            	/*if (seekbar1.getProgress() < 111)
		                {
		                    seekbar1.setProgress(0);
		                    return;
		                }
		            	else if (seekbar1.getProgress() >= 111 && seekbar1.getProgress() < 222)
		                {
		                    seekbar1.setProgress(111);
		                    return;
		                }
		                else if (seekbar1.getProgress() >= 222 && seekbar1.getProgress() < 333)
		                {
		                    seekbar1.setProgress(222);
		                    return;
		                }
		                else if (seekbar1.getProgress() >= 333 && seekbar1.getProgress() < 444)
		                {
		                    seekbar1.setProgress(333);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 444 && seekbar1.getProgress() < 555)
		                {
		                    seekbar1.setProgress(444);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 555 && seekbar1.getProgress() < 666)
		                {
		                    seekbar1.setProgress(555);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 666 && seekbar1.getProgress() < 777)
		                {
		                    seekbar1.setProgress(666);
		                    return;
		                }
		                else if (seekbar1.getProgress() >= 777 && seekbar1.getProgress() < 888)
		                {
		                    seekbar1.setProgress(777);
		                    return;
		                } 
		                else if (seekbar1.getProgress() >= 888 && seekbar1.getProgress() < 999)
		                {
		                    seekbar1.setProgress(888);
		                    return;
		                } 
		                else
		                {
		                    seekbar1.setProgress(999);
		                    return;
		                }*/
		            }
		        }
		);
        
        
        mHand = new Handler();
        runnable = new Runnable() 
        {

            public void run()
            {
                alertDo.onClick(BLWindAlert.mOnSelected, BLWindAlert.mOnSelectedWindout);
                
                
            }

        };

        dialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() 
        {

            public void onCancel(DialogInterface dialoginterface)
            {
            }

        }
        		);
        
        dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface arg0) {
				
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
