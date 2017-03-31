
package com.vincent.sourvide.data;

public interface DeviceStatusChangeListener
{
    
    public abstract void doCallBack(int type, int data, DataInfo datainfo);
}
