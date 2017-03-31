package com.vincent.sourvide.common;

import android.util.Log;

public class LogManager 
{
	private static final boolean LOG_ENABLE = false;
	
	public static boolean isLogEnable()
	{
		return LOG_ENABLE;
	}

	
	public static void LOGE(String msg, String tag)
	{
		if(LOG_ENABLE)
		{
			Log.e(tag, msg);
		}
		
		
	}
	
	public static void LOGD(String msg, String tag)
	{
		if(LOG_ENABLE)
		{
			Log.d(tag, msg);
		}
	}
}
