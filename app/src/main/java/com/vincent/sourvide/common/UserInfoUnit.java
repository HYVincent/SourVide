// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.vincent.sourvide.common;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoUnit
{

    private SharedPreferences mUserInfoPreferences;
    private String password;
    private boolean rememberPassword;
    private String userName;

    public UserInfoUnit(Context context)
    {
        mUserInfoPreferences = context.getSharedPreferences("USER_INFO_FILE", 0);
        setUserName(mUserInfoPreferences.getString("userName", "defaultUser"));
        setPassword(mUserInfoPreferences.getString("password", "defaultPassword"));
        setRememberPassword(mUserInfoPreferences.getBoolean("rememberPassword", false));
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    public boolean isRememberPassword()
    {
        return rememberPassword;
    }

    public void login(String s, String s1, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mUserInfoPreferences.edit();
        editor.putString("userName", s);
        editor.putString("password", s1);
        editor.putBoolean("rememberPassword", flag);
        editor.commit();
        setUserName(s);
        setPassword(s1);
        setRememberPassword(flag);
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setRememberPassword(boolean flag)
    {
        rememberPassword = flag;
    }

    public void setUserName(String s)
    {
        userName = s;
    }
}
