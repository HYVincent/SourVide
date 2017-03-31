// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.vincent.sourvide.activity;

import com.vincent.sourvide.R;

import android.os.Bundle;
import android.webkit.WebView;

public class UserGuideActivity extends TitleActivity
{


    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.user_guide);
        
        setBackVisible();
        setTitle(R.string.manual_title);
        setTitleBackgroundColor(getResources().getColor(R.color.color_main_bg));
        
        WebView webview = (WebView)findViewById(R.id.userGuide_wv);
        webview.setBackgroundColor(0);
        try
        {
            webview.getBackground().setAlpha(0);
        }
        catch (Exception exception) { }
        
        if (getString(R.string.locale).equals("en"))
        {
            webview.loadUrl("file:///android_asset/guide.html");
            return;
        } else
        {
            webview.loadUrl("file:///android_asset/guide_cn.html");
            return;
        }
    }
}
