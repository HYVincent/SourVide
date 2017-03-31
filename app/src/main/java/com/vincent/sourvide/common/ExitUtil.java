package com.vincent.sourvide.common;

import android.content.Context;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

/**
 * description 锛�
 * project name锛歋ourVide
 * author : Vincent
 * creation date: 2017/2/11 23:43
 *
 * @version 1.0
 */
public class ExitUtil {
    private static boolean isQuit=false;
    /**
     * 鍦╫nBackPressed()鏂规硶涓皟鐢�,浣跨敤姝ゅ嚱鏁版椂涓嶈兘璋冪敤onBackPressed()鐖剁被鏂规硶锛屽簲璇ュ幓鎺夛紝
     @Override
     public void onBackPressed() {
     ExitUtils.isQuit(this,"鍐嶆寜涓�娆￠��鍑篴pp");
     }
      *true 閫�鍑�
     *@author Vincent QQ1032006226
     *created at 2016/9/27 11:03
     */
    public static boolean isQuit(Context context, String exitMsg){
        if (isQuit == false) {
            isQuit = true;
//            ToastUtils.showLongToast(exitMsg);
            Toast.makeText(context, exitMsg, Toast.LENGTH_LONG).show();
            TimerTask task = null;
            task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 2000);
//            MyLog.d("閫�鍑烘彁绀�","鐢ㄦ埛姝ｅ湪閫�鍑�");
            return true;
        } else {
//            MyLog.d("app is exit", "app is exit");
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            return false;
        }
    }


}
