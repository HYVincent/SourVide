package com.vincent.sourvide.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vincent.sourvide.R;
import com.vincent.sourvide.common.ExitUtil;
import com.vincent.sourvide.common.ToastUtils;
import com.vincent.sourvide.data.DataInfo;
import com.vincent.sourvide.data.DeviceStatusChangeListener;
import com.vincent.sourvide.data.EairInfo;
import com.vincent.sourvide.net.Defines;
import com.vincent.sourvide.net.EairControler;
import com.vincent.sourvide.net.NetWorkManager;
import com.vincent.sourvide.widget.Anticlockwise;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author A18ccms A18ccms_gmail_com
 * @version V1.0
 * @Title: MainActivity.java
 * @Package com.example.smartkettle.activity
 * @Description: TODO(鐢ㄤ竴鍙ヨ瘽鎻忚堪璇ユ枃浠跺仛浠�涔�)
 * @date 2017骞�3鏈�25鏃� 涓嬪崍2:25:41
 */

public class MainActivity extends Activity implements DeviceStatusChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.temperature_text)
    TextView temperatureText;
    @BindView(R.id.time_text)
    Chronometer timeText;
    @BindView(R.id.chronometer_run_time)
    Chronometer chronometerRunTime;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.temp_text)
    TextView tempText;
    @BindView(R.id.timer_text)
    TextView timerText;
    @BindView(R.id.switch_img)
    ImageView switchImg;
    @BindView(R.id.rl_buttom)
    RelativeLayout rlButtom;
    @BindView(R.id.temperature_center_text)
    TextView temperatureCenterText;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.set_time)
    Anticlockwise setTime;
    @BindView(R.id.reduce_imgae)
    ImageView reduceImgae;
    @BindView(R.id.add_imgae)
    ImageView addImgae;
    @BindView(R.id.setbar_selected)
    SeekBar setbarSelected;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    private Vibrator vibrator = null;
    private int currentValues = 40;
    private boolean isStart = false; // true正在计时 false 没有计时
    private int time = -2;
    private boolean isExit = false;

    private int set_flag = 0;

    NetWorkManager mNetworkManager;

    private EairControler mEairController;

    EairInfo mEairInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        chronometerRunTime.start();

        switchImg.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View arg0) {    //长按键事件。
                mEairController.SetOnOrOff(0);
                return false;
            }
        });

        setbarSelected.setMax(50);
        mEairController = EairControler.getInstance(this);
        mNetworkManager = NetWorkManager.getInstance(this);
        mNetworkManager.setDeviceStatusChangeListener(this);
        mEairInfo = mNetworkManager.getmEairInfo();

        if (mEairInfo.init) {
            setbarSelected.setProgress(mEairInfo.set_temp - 40);
            refreshState();
        }


        setTime.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete() {
                if (time == -2) {
                    return;
                }
//	                setTime.initTime(-2);
                toastStr("倒计时结束");
                switchImg.setImageResource(R.drawable.icon_start);
                isStart = false;
                time = -2;
                startShake();
            }
        });

        setbarSelected.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //temperatureCenterText.setText(String.valueOf(currentValues));
                //temperatureCenterText.setSelection(temperatureCenterText.getText().toString().length());
                // currentValues = progress;
                // toastStr(String.valueOf(currentValues));
                //mEairController.SetTemp(progress+40);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mEairController.SetTemp(seekBar.getProgress() + 40);
            }
        });
    }
    @OnClick({R.id.temp_text, R.id.timer_text, R.id.switch_img, R.id.reduce_imgae, R.id.add_imgae})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.temp_text:
                set_flag = 1;
//                ToastUtils.toastDefault("temp");
                break;
            case R.id.timer_text:
                set_flag = 2;
                showDialog(MainActivity.this, llRoot);
                break;
            case R.id.switch_img:
                if (mEairInfo.work_stop == 0) {
                    //toastStr("开始倒计时");
                    //ivSwitch.setImageResource(R.drawable.icon_stop);
                    //isStart = true;
                    //setTime.start();
                    mEairController.SetWorkOrStop(1);
                } else {
                    //isStart = false;
                    //setTime.onPause();
                    // cookingTimeText.setBase(SystemClock.elapsedRealtime());
                    // ToastUtils.showLongToast("暂停计时");
                    //toastStr("暂停计时");
                    //ivSwitch.setImageResource(R.drawable.icon_start);
                    //time = -1;
                    mEairController.SetWorkOrStop(0);
                }
                break;
            case R.id.reduce_imgae:
                if (mEairInfo.set_temp > 40) {
                    mEairInfo.set_temp--;
                    mEairController.SetTemp(mEairInfo.set_temp);
                }
                break;
            case R.id.add_imgae:
                if (mEairInfo.set_temp < 90) {
                    mEairInfo.set_temp++;
                    mEairController.SetTemp(mEairInfo.set_temp);
                }
                break;
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showDialog(final Activity activity, View view) {
        View contentView = LayoutInflater.from(activity).inflate(R.layout.item_layout_choose_time, null);
        final PopupWindow pop = new PopupWindow(activity);
        pop.setContentView(contentView);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//布局参数
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        final NumberPicker numberHour = (NumberPicker) contentView.findViewById(R.id.np_hour);
        numberHour.getChildAt(0).setFocusable(false);
        numberHour.setMinValue(0);
        numberHour.setMaxValue(72);
        numberHour.setValue(mEairInfo.set_hour);
        numberHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        final NumberPicker numberMin = (NumberPicker) contentView.findViewById(R.id.np_min);
        numberMin.getChildAt(0).setFocusable(false);
        numberMin.setMaxValue(60);
        numberMin.setMinValue(0);
        numberMin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberMin.setValue(mEairInfo.set_min);

        numberHour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                mEairController.SetTime(numberHour.getValue(), numberMin.getValue());
                Log.d(TAG, "onValueChange-->修改之后的值?" + String.valueOf(newVal));
            }
        });

        numberMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                mEairController.SetTime(numberHour.getValue(), numberMin.getValue());

                Log.d(TAG, "onValueChange-->修改之后的值 " + String.valueOf(newVal));
            }
        });
        TextView tvOk = (TextView) contentView.findViewById(R.id.tv_ok);
        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
        pop.setTouchable(true);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.anim_menu_bottombar);
        pop.showAsDropDown(view, 0, 30, Gravity.CENTER);
        pop.update();
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 设置亮度
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });
    }


    private void startShake() {
        if (vibrator != null) {
            long[] pattern = {1000, 2000, 1000, 2000}; // 停止 开启 停止 开启
            vibrator.vibrate(pattern, 2); // 重复两次上面的pattern 如果只想震动一次，index设为-1
            new AlertDialog.Builder(this).setMessage("关闭提醒")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setTime.setBase(SystemClock.elapsedRealtime());// 设置从0开始计时
                            setTime.start();
                            dialog.dismiss();
                            stopShake();
                        }
                    }).setCancelable(false).create().show();
        }
    }

    public void stopShake() {
        super.onStop();
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        ExitUtil.isQuit(MainActivity.this, "再点一次退出程序");
    }

    /**
     * Toast
     *
     * @param msg
     */
    private void toastStr(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void doCallBack(int type, int data, DataInfo datainfo) {

        if (type == Defines.DEVICE_STATUS_CHANGE_CALLBACK_TYPE_DATA) {
            mEairController.parseInfo(datainfo.sn, mEairInfo, datainfo.pkt);
            refreshState();
        }

        if (type == Defines.MAIN_MSG_TYPE_NETWORK_DOWN) {
            //Toast.makeText(mContext, mContext.getResources().getString(R.string.network_lost), Toast.LENGTH_SHORT).show();
        }

        if (type == Defines.MAIN_MSG_TYPE_NETWORK_UP) {
            mEairController.queryState(0);
        }
    }

    private void refreshState() {
        if (temperatureCenterText != null) {
            temperatureCenterText.setText(Integer.toString(mEairInfo.real_temp) + ".0");
        }

        String str = "";

        if (mEairInfo.set_hour < 10) {
            str = "0";
        }

        str += Integer.toString(mEairInfo.set_hour);
        str += ":";

        if (mEairInfo.set_min < 10) {
            str += "0";
        }

        str += Integer.toString(mEairInfo.set_min);
        if (timeText != null) {
            timeText.setText(str);
        }

        if (setTime != null) {
            setTime.setText(str);
        }

        if (temperatureText != null) {
            temperatureText.setText(Integer.toString(mEairInfo.set_temp) + ".0");
            setbarSelected.setProgress(mEairInfo.set_temp - 40);
        }

        if (mEairInfo.on_off == 0) {
            Intent intent = new Intent(MainActivity.this, OpenActivity.class);
            startActivity(intent);
        } else {
            if (mEairInfo.work_stop == 0) {
                switchImg.setImageResource(R.drawable.icon_start);
            } else {
                switchImg.setImageResource(R.drawable.icon_stop);
            }
        }
    }

    private void refreshDeviceList() {
        //mDeviceList.clear();
        //mDeviceList.addAll(EairApplaction.allDeviceList);

        runOnUiThread
                (
                        new Runnable() {

                            public void run() {
                                //mDeviceAdapter.notifyDataSetChanged();
                                refreshState();
                            }
                        }
                );
    }



}
