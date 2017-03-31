// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.vincent.sourvide.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vincent.sourvide.R;



public class AboutActivity extends TitleActivity
{
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        
        setContentView(R.layout.about);
        
        setBackVisible();
        setTitle(R.string.option_text_about);
        setTitleBackgroundColor(getResources().getColor(R.color.color_main_bg));
        
        TextView tv = (TextView)findViewById(R.id.version_tv);
        tv.setText(getVersionName());
        
        Button bt = (Button)findViewById(R.id.checkUpdate_btn);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Toast.makeText(AboutActivity.this, getResources().getString(R.string.no_new_version), Toast.LENGTH_SHORT).show();
				
			}
		});
        
    }
	

    private String getVersionName()
    {
        String s;
        try
        {
            s = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return "";
        }
        return s;
    }
    
    private void startBrower()
    {
    	Uri uri = Uri.parse("www.good-night.cn");  
    	Intent it = new Intent(Intent.ACTION_VIEW, uri);  
    	startActivity(it);
    }

}
