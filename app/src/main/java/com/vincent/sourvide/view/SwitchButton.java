package com.vincent.sourvide.view;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.vincent.sourvide.R;



public class SwitchButton extends View implements View.OnTouchListener  
{  
  
    private boolean IsOpen;//��¼��ǰ��ť�Ƿ��,trueΪ��,flaseΪ�ر� ���˵��޸�   
    private boolean OnSlip = false;//��¼�û��Ƿ��ڻ����ı���    
    private float DownX, NowX;//����ʱ��x,��ǰ��x,    
    private Rect Btn_On, Btn_Off;//�򿪺͹ر�״̬��,�α��Rect    
    private boolean isChgLsnOn = false;  
    private OnChangedListener chgLsn;  
    private Bitmap bg_on, bg_off, slip_btn;  
  
    public SwitchButton(Context context)  
    {  
        super(context);  
  
        init();  
    }  
  
    public SwitchButton(Context context, AttributeSet attrs)  
    {  
        super(context, attrs);  
  
        init();  
    }  
  
    private void init()  
    {
    	//��ʼ��    
    	//����ͼƬ��Դ    
  
        bg_on = BitmapFactory.decodeResource(getResources(),  
                R.drawable.switch_bg_open);  
        bg_off = BitmapFactory.decodeResource(getResources(),  
                R.drawable.switch_bg);  
        slip_btn = BitmapFactory.decodeResource(getResources(),  
                R.drawable.switch_open);  
        
        Btn_On = new Rect(0, 0, slip_btn.getWidth(), slip_btn.getHeight());  
        Btn_Off = new Rect(bg_off.getWidth() - slip_btn.getWidth(), 0,  
                bg_off.getWidth(), slip_btn.getHeight());  
        setOnTouchListener(this);// ���ü�����,Ҳ����ֱ�Ӹ�дOnTouchEvent  
    }  
  
    @Override  
    protected void onDraw(Canvas canvas)  
    {  
        //��ͼ����    
        super.onDraw(canvas);  
  
        Matrix matrix = new Matrix();  
        Paint paint = new Paint();  
        float x;  
  
        //���������жϳ�ʼ״̬  
        if (IsOpen)  
        {  
            NowX = bg_on.getWidth();  
        }  
        else  
        {  
            NowX = 0;  
        }  
        {  
            if (NowX < (bg_on.getWidth() / 2))//������ǰ�������εı�����ͬ,�ڴ����ж�    
            {  
                canvas.drawBitmap(bg_off, matrix, paint);//�����ر�ʱ�ı���    
            }  
            else  
            {  
                canvas.drawBitmap(bg_on, matrix, paint);//������ʱ�ı���    
            }  
  
            if (OnSlip)//�Ƿ����ڻ���״̬,    
            {  
                if (NowX >= bg_on.getWidth())//�Ƿ񻮳�ָ����Χ,�������α��ܵ���ͷ,����������ж�    
                {  
                    x = bg_on.getWidth() - slip_btn.getWidth() / 2;//��ȥ�α�1/2�ĳ���...    
                }  
  
                else  
                {  
                    x = NowX - slip_btn.getWidth() / 2;  
                }  
            }  
            else  
            {//�ǻ���״̬    
                if (IsOpen)//�������ڵĿ���״̬���û��α��λ��    
                {  
                    x = Btn_Off.left;  
                }  
                else  
                {  
                    x = Btn_On.left;  
                }  
            }  
            if (x < 0)//���α�λ�ý����쳣�ж�...    
            {  
                x = 0;  
            }  
            else if (x > bg_on.getWidth() - slip_btn.getWidth())  
            {  
                x = bg_on.getWidth() - slip_btn.getWidth();  
            }  
  
            canvas.drawBitmap(slip_btn, x, 0, paint);//�����α�.    
        }  
    }  
  
    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag;
  //label0:
        {
            switch (motionevent.getAction())
            {
            case 1: // '\001'
            	OnSlip = false;
                if (motionevent.getX() >= (float)(bg_on.getWidth() / 2))
                {
                	IsOpen = true;
                    NowX = bg_on.getWidth() - slip_btn.getWidth();
                } else
                {
                	IsOpen = false;
                    NowX = 0.0F;
                }
                if (chgLsn != null)
                	chgLsn.onChanged(IsOpen, this);
                break;

            case 2: // '\002'
            	NowX = motionevent.getX();
                break;

            case 0: // '\0'
                boolean i = motionevent.getX() != (float)bg_off.getWidth();
                flag = false;
                if (i == false)
                {
                    boolean j = motionevent.getY() != (float)bg_off.getHeight();
                    flag = false;
                    if (j == false)
                    {
                    	OnSlip = true;
                        DownX = motionevent.getX();
                        NowX = DownX;
                        break;
                    }
                }
                
                break ;//label0;

            default:
            	OnSlip = false;
                if (motionevent.getX() >= (float)(bg_on.getWidth() / 2))
                {
                	IsOpen = true;
                    NowX = bg_on.getWidth() - slip_btn.getWidth();
                } else
                {
                	IsOpen = false;
                    NowX = 0.0F;
                }
                if (chgLsn != null)
                	chgLsn.onChanged(IsOpen, this);
                break;
            }
            
            
            invalidate();
            flag = true;
        }
        
        
        return flag;
    }
    
    public void setOnChangedListener(OnChangedListener l)  
    {
    	//���ü�����,��״̬�޸ĵ�ʱ��    
        isChgLsnOn = true;  
        chgLsn = l;  
    }  
  
    public void setCheck(boolean isCheck)  
    {  
        IsOpen = isCheck;  
        invalidate();  
    }  
  
    public interface OnChangedListener  
    {  
        abstract void onChanged(boolean checkState, View v);  
    }  
}  

