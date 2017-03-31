
package com.vincent.sourvide.view;

import java.util.Timer;
import java.util.TimerTask;

import android.view.View;

public abstract class OnSingleClickListener
    implements android.view.View.OnClickListener
{

    public static final int mDelay = 200;
    public boolean mEnable;

    public OnSingleClickListener()
    {
        mEnable = true;
    }

    public abstract void doOnClick(View view);

    public void onClick(View view)
    {
        if (mEnable)
        {
            mEnable = false;
            doOnClick(view);
            
            (new Timer()).schedule(new TimerTask() {

                public void run()
                {
                    mEnable = true;
                }
            }
            , 200L);
            
        }
    }
}
