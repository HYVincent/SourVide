package com.vincent.sourvide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vincent.sourvide.R;

/**
 * description ：
 * project name：SourVide
 * author : Vincent
 * creation date: 2017/3/31 11:38
 *
 * @version 1.0
 */

public class TestActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        findViewById(R.id.ffffffff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"fffffffffff",Toast.LENGTH_LONG).show();
            }
        });
    }
}
