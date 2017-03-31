package com.vincent.sourvide.common;

import android.widget.Toast;

import com.vincent.sourvide.MyApplaction;

/**
 * description ：
 * project name：SourVide
 * author : Vincent
 * creation date: 2017/3/31 18:16
 *
 * @version 1.0
 */

public class ToastUtils {

    public static void toastDefault(String msg){
        Toast.makeText(MyApplaction.getMyApplaction(),msg,Toast.LENGTH_LONG).show();
    }

}
