
package com.vincent.sourvide.activity;

import java.util.Timer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vincent.sourvide.R;

// Referenced classes of package com.tcl.eair.activity:
//            BaseActivity, DeviceActivity, RegisterActivity, ConfigDeviceActivity

public class LoginActivity extends BaseActivity implements OnClickListener
{
    class LoginTask extends AsyncTask
    {



        LoginTask()
        {
        }

		@Override
		protected Object doInBackground(Object... arg0) 
		{
			// TODO Auto-generated method stub
			return null;
		}
    }


    private boolean mCanExit;
    private Timer mExitTimer;
    private TextView mIntoText;
    private Button mLoginButton;
    private EditText mPassWordText;
    private TextView mRegisterText;
    private CheckBox mRemeberPasswordBox;
    private EditText mUserNameText;


    private boolean checkRight()
    {
        if (mUserNameText.getText().toString().length() == 0)
        {
            //CommonUnit.toastShow(this, 0x7f08002c);
            return false;
        }
        if (mPassWordText.getText().toString().length() == 0)
        {
            //CommonUnit.toastShow(this, 0x7f08002d);
            return false;
        } else
        {
            return true;
        }
    }

    private void closeInputMethod()
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService("input_method");
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
            inputmethodmanager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    private void findView()
    {
        mIntoText = (TextView)findViewById(R.id.into);
        mRegisterText = (TextView)findViewById(R.id.register);
        mUserNameText = (EditText)findViewById(R.id.user_name);
        mPassWordText = (EditText)findViewById(R.id.password);
        mLoginButton = (Button)findViewById(R.id.btn_login);
        mRemeberPasswordBox = (CheckBox)findViewById(R.id.remeber_password);
        mRegisterText.getPaint().setFlags(8);
    }

    private void initView()
    {

    }

    private void setListener()
    {
      if(mLoginButton != null)
      {
    	  mLoginButton.setOnClickListener(this);
      }
    }

    private void toActivity(Class class1)
    {
        Intent intent = new Intent();
        intent.setClass(this, class1);
        startActivity(intent);
    }

    public void onBackPressed()
    {
        if (mCanExit)
        {
            finish();

            return;
        } 
        else
        {
            mCanExit = true;
            Toast.makeText(this, R.string.double_click_exit, 0).show();
            mExitTimer = new Timer();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.login_layout);
        findView();
        setListener();
        initView();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
            closeInputMethod();
        
        return super.onTouchEvent(motionevent);
    }

	@Override
	public void onClick(View v) 
	{
		if(v.getId() == R.id.btn_login)
		{
			//toActivity(.class);
			toActivity(DeviceActivity.class);
			finish();
		}		
	}

}
