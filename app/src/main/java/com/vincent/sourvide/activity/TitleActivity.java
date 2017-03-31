

package com.vincent.sourvide.activity;

import com.vincent.sourvide.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;


public class TitleActivity extends BaseActivity implements OnTouchListener
{

    private FrameLayout mBody;
    private ImageButton mReturnButton;
    private ImageButton mRightButton;
    private TextView mRightText;
    private TextView mTitle;
    private View mTitleBG;
    
    private void findView()
    {
        mBody = (FrameLayout)findViewById(R.id.body);
        mReturnButton = (ImageButton)findViewById(R.id.btn_return);
        mRightButton = (ImageButton)findViewById(R.id.btn_right);
        mTitle = (TextView)findViewById(R.id.title);
        mTitleBG = (View)findViewById(R.id.title_layout);
        mRightText = (TextView)findViewById(R.id.right_text);
    }

    private void setListener()
    {
        mReturnButton.setOnClickListener(new android.view.View.OnClickListener() {


            public void onClick(View view)
            {
                back();
            }
        }
);
    }

    public void back()
    {
    	//hideInputMethod();
        finish();
    }

    public void onBackPressed()
    {
        back();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        super.setContentView(R.layout.title_layout);
        findView();
        setListener();
    }

    public void setBackVisible()
    {
        mReturnButton.setVisibility(0);
        mReturnButton.setOnTouchListener(this);
    }

    public void setContentView(int i)
    {
        getLayoutInflater().inflate(i, mBody, true);
    }

    public void setRightButtonClickListener(int i, android.view.View.OnClickListener onclicklistener)
    {
        mRightButton.setVisibility(View.VISIBLE);
        mRightButton.setImageResource(i);
        mRightButton.setOnClickListener(onclicklistener);
        mRightButton.setOnTouchListener(this);
    }

    public void setRightTextClickListener(int id, android.view.View.OnClickListener onclicklistener)
    {
    	mRightText.setVisibility(View.VISIBLE);
        mRightText.setOnClickListener(onclicklistener);
        mRightText.setText(id);
        mRightText.setOnTouchListener(this);
    }
    
    public void setTitle(int i)
    {
        mTitle.setText(i);
    }

    public void setTitle(String s)
    {
        mTitle.setText(s);
    }
	
    public void setTitleBackgroundColor(int color )
    {
    	mTitleBG.setBackgroundColor(color);
    }
    
    public void setTitleBackground(Drawable draw )
    {
    	mTitleBG.setBackgroundDrawable(draw);
    }
    
    public void setMainBackgroundResource(int resid )
    {
    	mBody.setBackgroundResource(resid);
    }
    
    public void setMainBackgroundColor(int color)
    {
    	mBody.setBackgroundColor(color);
    	
    }
    
    
	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{       
			v.setBackgroundColor(getResources().getColor(R.color.color_my_half_transparent));
		}     
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{        
			v.setBackgroundColor(getResources().getColor(R.color.color_my_transparent));
		}
		       
		return false;
	}
	
	protected void hideInputMethod() 
	{  
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
        
        if (imm != null && imm.isActive()) 
        {  
        	View focusView = getCurrentFocus();
        	if(focusView != null)
        	{
        		android.os.IBinder binder = focusView.getWindowToken( );
            	if(binder != null)
            	{
            		imm.hideSoftInputFromWindow(binder, 0 ); 
            	}
        	}
        	
        	
        }  
        
    }  
}
