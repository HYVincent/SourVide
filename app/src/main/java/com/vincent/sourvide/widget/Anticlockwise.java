package com.vincent.sourvide.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Date;


/***
 *
 * @author 寮犲皬_鎳�     2015/02/07
 *
 */
@SuppressLint(
        { "ViewConstructor", "SimpleDateFormat" })
public class Anticlockwise extends Chronometer
{
    public Anticlockwise(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO 鑷姩鐢熸垚鐨勬瀯閫犲嚱鏁板瓨鏍�
        mTimeFormat = new SimpleDateFormat("mm:ss");
        this.setOnChronometerTickListener(listener);
    }

    private long mTime;
    private long mNextTime;
    private OnTimeCompleteListener mListener;
    private SimpleDateFormat mTimeFormat;

    public Anticlockwise(Context context)
    {
        super(context);

    }

    /**
     * 閲嶆柊鍚姩璁℃椂
     */
    public void reStart(long _time_s)
    {
        if (_time_s == -1)
        {
            mNextTime = mTime;
        } else
        {
            mTime = mNextTime = _time_s;
        }
        this.start();
    }

    public void reStart()
    {
        reStart(-1);
    }

    /**
     * 缁х画璁℃椂
     */
    public void onResume()
    {
        this.start();
    }

    /**
     * 鏆傚仠璁℃椂
     */
    public void onPause()
    {
        this.stop();
    }

    /**
     * 璁剧疆鏃堕棿鏍煎紡
     *
     * @param pattern
     *            璁℃椂鏍煎紡
     */
    public void setTimeFormat(String pattern)
    {
        mTimeFormat = new SimpleDateFormat(pattern);
    }

    public void setOnTimeCompleteListener(OnTimeCompleteListener l)
    {
        mListener = l;
    }

    OnChronometerTickListener listener = new OnChronometerTickListener()
    {
        @Override
        public void onChronometerTick(Chronometer chronometer)
        {
            if (mNextTime <= 0)
            {
                if (mNextTime == 0)
                {
                    Anticlockwise.this.stop();
                    if (null != mListener)
                        mListener.onTimeComplete();
                }
                mNextTime = 0;
                updateTimeText();
                return;
            }

            mNextTime--;

            updateTimeText();
        }
    };

    /**
     * 鍒濆鍖栨椂闂�
     * @param _time_s
     */
    public void initTime(long _time_s)
    {
        mTime = mNextTime = _time_s;
        updateTimeText();
    }

    private void updateTimeText()
    {
        this.setText(mTimeFormat.format(new Date(mNextTime * 1000)));
    }

    public interface OnTimeCompleteListener
    {
        void onTimeComplete();
    }

}
