

package com.vincent.sourvide.common;

import java.io.File;
import java.security.MessageDigest;
import java.util.Date;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.vincent.sourvide.R;


public class CommonUnit
{


    public static final String MD5(String s)
    {
        String s1;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte[] _tmp = new byte[40];
            messagedigest.update(s.getBytes("iso-8859-1"), 0, s.length());
            s1 = convertToHex(messagedigest.digest());
        }
        catch (Exception exception)
        {
            return "";
        }
        return s1;
    }

    public static boolean checkByteArrayEqual(byte abyte0[], byte abyte1[])
    {
        int i = abyte0.length;
        boolean flag = true;
        int j = 0;
        do
        {
            if (j >= i)
                return flag;
            if (abyte0[j] != abyte1[j])
                flag = false;
            j++;
        } while (true);
    }

    public static boolean checkNetwork(Context context)
    {
        NetworkInfo networkinfo = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkinfo != null && networkinfo.isConnected();
    }

    private static String convertToHex(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = abyte0.length;
        int j = 0;
        do
        {
            if (j >= i)
                return stringbuffer.toString();
            int k = 0xf & abyte0[j] >>> 4;
            int l = 0;
            do
            {
                if (k >= 0 && k <= 9)
                    stringbuffer.append((char)(k + 48));
                else
                    stringbuffer.append((char)(97 + (k - 10)));
                k = 0xf & abyte0[j];
            } while (++l < 1);
            j++;
        } while (true);
    }

    public static final void deleteTempFile()
    {
        File file = new File(Settings.CACHE_PATH);
        if (file.exists())
        {
            File afile[] = file.listFiles();
            int i = afile.length;
            for (int j = 0; j < i; j++)
                afile[j].delete();

        }
    }

    public static int dip2px(Context context, float f)
    {
        return (int)(0.5F + f * context.getResources().getDisplayMetrics().density);
    }

    public static double getLocationDistance(double d, double d1, double d2, double d3)
    {
        double d4 = (3.1415926535897931D * d) / 180D;
        double d5 = (3.1415926535897931D * d2) / 180D;
        double d6 = d4 - d5;
        double d7 = (3.1415926535897931D * (d1 - d3)) / 180D;
        return (double)(Math.round(10000D * (6378137D * (2D * Math.asin(Math.sqrt(Math.pow(Math.sin(d6 / 2D), 2D) + Math.cos(d4) * Math.cos(d5) * Math.pow(Math.sin(d7 / 2D), 2D)))))) / 10000L);
    }

    public static final int getPhoneHour()
    {
        return (new Date(System.currentTimeMillis())).getHours();
    }

    public static String getPm25Grade(Context context, int i)
    {
        if (i <= 50)
            return context.getResources().getString(R.string.pm_best);
        if (i <= 100)
            return context.getResources().getString(R.string.pm_good);
        if (i <= 150)
            return context.getResources().getString(R.string.pm_mild);
        if (i <= 200)
            return context.getResources().getString(R.string.pm_mezzo);
        if (i <= 300)
            return context.getResources().getString(R.string.pm_severe);
        else
            return context.getResources().getString(R.string.pm_severity);
    }

    public static boolean isWifiConnect(Context context)
    {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(1).isConnected();
    }

    public static int px2dip(Context context, float f)
    {
        return (int)(0.5F + f / context.getResources().getDisplayMetrics().density);
    }

    public static void toastShow(Context context, int i)
    {
        Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
    }

    public static void toastShow(Context context, String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
