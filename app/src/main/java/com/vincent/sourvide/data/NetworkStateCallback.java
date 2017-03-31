package com.vincent.sourvide.data;

import android.os.Message;

public interface NetworkStateCallback 
{
	public abstract void notifyMessage(Message msg);
}
