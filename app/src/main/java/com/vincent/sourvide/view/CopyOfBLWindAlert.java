
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

public class CopyOfBLWindAlert
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
        //SeekBar seekbar = (SeekBar)linearlayout.findViewById(R.id.wind_bar);
        
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
        
        
        final OnCheckedChangeListener mChangeListener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) 
			{
				
				
			}
		};
        
		final OnClickListener mOnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int id = v.getId();
				if (id == R.id.wind_0)
                {
            		CopyOfBLWindAlert.mOnSelected = 0;
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
                	if (id == R.id.wind_1)
                {
                	CopyOfBLWindAlert.mOnSelected = 1;
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
                if (id == R.id.wind_2)
                {
                	CopyOfBLWindAlert.mOnSelected = 2;
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
                if (id == R.id.wind_3)
                {
                	CopyOfBLWindAlert.mOnSelected = 3;
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
                if (id == R.id.wind_4)
                {
                	CopyOfBLWindAlert.mOnSelected = 4;
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
                if (id == R.id.wind_5)
                {
                	CopyOfBLWindAlert.mOnSelected = 5;
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
                if (id == R.id.wind_6)
                {
                	CopyOfBLWindAlert.mOnSelected = 6;
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
                if (id == R.id.wind_7)
                {
                	CopyOfBLWindAlert.mOnSelected = 7;
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
                if (id == R.id.wind_8)
                {
                	CopyOfBLWindAlert.mOnSelected = 8;
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
                	CopyOfBLWindAlert.mOnSelected = 9;
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
                

            	CopyOfBLWindAlert.mHand.removeCallbacks(CopyOfBLWindAlert.runnable);
            	CopyOfBLWindAlert.mHand.postDelayed(CopyOfBLWindAlert.runnable, 500L);
                
                /*View parent = (View)seekbar1.getParent().getParent();
                if(parent != null)
                {
                	TextView text = (TextView)parent.findViewById(R.id.wind_in_set);
	                if(text != null)
	                {
	                	String s = seekbar1.getContext().getResources().getString(R.string.text_wind_in);
	                	text.setText(s+BLWindAlert.mOnSelected);
	                }
                }*/
				
			}
		};
		
		wind0.setOnCheckedChangeListener(mChangeListener);
		wind1.setOnCheckedChangeListener(mChangeListener);
		wind2.setOnCheckedChangeListener(mChangeListener);
		wind3.setOnCheckedChangeListener(mChangeListener);
		wind4.setOnCheckedChangeListener(mChangeListener);
		wind5.setOnCheckedChangeListener(mChangeListener);
		wind6.setOnCheckedChangeListener(mChangeListener);
		wind7.setOnCheckedChangeListener(mChangeListener);
		wind8.setOnCheckedChangeListener(mChangeListener);
		wind9.setOnCheckedChangeListener(mChangeListener);
        
		wind0.setOnClickListener(mOnClickListener);
		wind1.setOnClickListener(mOnClickListener);
		wind2.setOnClickListener(mOnClickListener);
		wind3.setOnClickListener(mOnClickListener);
		wind4.setOnClickListener(mOnClickListener);
		wind5.setOnClickListener(mOnClickListener);
		wind6.setOnClickListener(mOnClickListener);
		wind7.setOnClickListener(mOnClickListener);
		wind8.setOnClickListener(mOnClickListener);
		wind9.setOnClickListener(mOnClickListener);
		
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
            //seekbar.setProgress(0);
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
            //seekbar.setProgress(999);
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
            //seekbar.setProgress(888);
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
            //seekbar.setProgress(777);
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
            //seekbar.setProgress(666);
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
            //seekbar.setProgress(555);
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
            //seekbar.setProgress(444);
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
            //seekbar.setProgress(333);
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
            //seekbar.setProgress(222);
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
            //seekbar.setProgress(111);
            break;
        }
        /*
        seekbar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() 
		        {
		
		            public void onProgressChanged(SeekBar seekbar1, int j, boolean flag)
		            {
		            	if (j < 111)
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
		                if (j >= 111 && j < 222)
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
		                if (j >= 222 && j < 333)
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
		                if (j >= 333 && j < 444)
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
		                if (j >= 444 && j < 555)
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
		                if (j >= 555 && j < 666)
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
		                if (j >= 666 && j < 777)
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
		                if (j >= 777 && j < 888)
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
		                if (j >= 888 && j < 999)
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
		                }
		            }
		        }
		);*/
        
        
        
       // SeekBar seekbar_windout = (SeekBar)linearlayout.findViewById(R.id.windout_bar);
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
        
        //seekbar_windout.setMax(999);
        
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
            //seekbar_windout.setProgress(0);
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
            //seekbar_windout.setProgress(999);
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
            //seekbar_windout.setProgress(888);
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
            //seekbar_windout.setProgress(777);
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
            //seekbar_windout.setProgress(666);
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
            //seekbar_windout.setProgress(555);
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
            //seekbar_windout.setProgress(444);
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
            //seekbar_windout.setProgress(333);
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
            //seekbar_windout.setProgress(222);
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
            //seekbar_windout.setProgress(111);
            break;
        }
        
        
       final OnClickListener mOnClickWindOut = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int id = v.getId();
				if (id == R.id.windout_0)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 0;
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
                else if (id == R.id.windout_1)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 1;
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
                if (id == R.id.windout_2)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 2;
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
                if (id == R.id.windout_3)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 3;
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
                if (id == R.id.windout_4)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 4;
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
                if (id == R.id.windout_5)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 5;
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
                if (id == R.id.windout_6)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 6;
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
                if (id == R.id.windout_7)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 7;
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
                if (id == R.id.windout_8)
                {
                	CopyOfBLWindAlert.mOnSelectedWindout = 8;
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
                	CopyOfBLWindAlert.mOnSelectedWindout = 9;
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
                
				
				
                CopyOfBLWindAlert.mHand.removeCallbacks(CopyOfBLWindAlert.runnable);
                CopyOfBLWindAlert.mHand.postDelayed(CopyOfBLWindAlert.runnable, 500L);
                
                /*View parent = (View)seekbar1.getParent().getParent();
                if(parent != null)
                {
                	TextView windoutText = (TextView)parent.findViewById(R.id.wind_out_set);
	                if(windoutText != null)
	                {
	                	String s = seekbar1.getContext().getResources().getString(R.string.text_wind_out);
	                	windoutText.setText(s+BLWindAlert.mOnSelectedWindout);
	                }
                }*/
				
			}
		};
		
		
		windout_0.setOnCheckedChangeListener(mChangeListener);
		windout_1.setOnCheckedChangeListener(mChangeListener);
		windout_2.setOnCheckedChangeListener(mChangeListener);
		windout_3.setOnCheckedChangeListener(mChangeListener);
		windout_4.setOnCheckedChangeListener(mChangeListener);
		windout_5.setOnCheckedChangeListener(mChangeListener);
		windout_6.setOnCheckedChangeListener(mChangeListener);
		windout_7.setOnCheckedChangeListener(mChangeListener);
		windout_8.setOnCheckedChangeListener(mChangeListener);
		windout_9.setOnCheckedChangeListener(mChangeListener);
        
		windout_0.setOnClickListener(mOnClickWindOut);
		windout_1.setOnClickListener(mOnClickWindOut);
		windout_2.setOnClickListener(mOnClickWindOut);
		windout_3.setOnClickListener(mOnClickWindOut);
		windout_4.setOnClickListener(mOnClickWindOut);
		windout_5.setOnClickListener(mOnClickWindOut);
		windout_6.setOnClickListener(mOnClickWindOut);
		windout_7.setOnClickListener(mOnClickWindOut);
		windout_8.setOnClickListener(mOnClickWindOut);
		windout_9.setOnClickListener(mOnClickWindOut);
		
		
        /*seekbar_windout.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() 
		        {
		
		            public void onProgressChanged(SeekBar seekbar1, int j, boolean flag)
		            {
			                
		                if (j < 111)
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
		                else if (j >= 111 && j < 222)
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
		                if (j >= 222 && j < 333)
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
		                if (j >= 33 && j < 444)
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
		                if (j >= 444 && j < 555)
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
		                if (j >= 555 && j < 666)
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
		                if (j >= 666 && j < 777)
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
		                if (j >= 777 && j < 888)
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
		                if (j >= 888 && j < 999)
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
		            	if (seekbar1.getProgress() < 111)
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
		                }
		            }
		        }
		);
        */
        
        mHand = new Handler();
        runnable = new Runnable() 
        {

            public void run()
            {
                alertDo.onClick(CopyOfBLWindAlert.mOnSelected, CopyOfBLWindAlert.mOnSelectedWindout);
                
                
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
