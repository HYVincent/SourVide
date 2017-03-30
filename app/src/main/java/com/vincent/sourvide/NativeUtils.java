package com.vincent.sourvide;

/**
 * description ：
 * project name：SourVide
 * author : Vincent
 * creation date: 2017/3/27 23:20
 *
 * @version 1.0
 */

public  class NativeUtils {

    public  static native  int JniNetWorkInit(int app_sn, int group_id);
    public static native int JniNetWorkExit();

    public static native int JniNetWorkPause();
    public static native int JniNetWorkResume();

    public static native int JniEntryScanMode();
    public static native int JniExitScanMode();

    public static native int JniAddDevie(int sn);  //阻塞

    public static native int JniDelDevie(int sn);  //阻塞

    public static native int JniActiveDevice(int sn);

    public static native int JniDisActiveDevice(int sn);

    public static native int JniDisActiveAll();

    public static native int JniGetDeviceLinkStatus(int sn);

    public static native int JniGetServiceLinkStatus();

    public static native int JniApConfig(byte[] ssid, byte[] password, int add_flag);  //阻塞
    public static native int JniCancelAPConfig();

    public static native int JniSendData(int sn, int datalen, byte[] data);  //阻塞

}
