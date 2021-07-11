package com.example.jmeeee.View;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jmeeee.Model.DBHelper;
import com.example.jmeeee.Presenter.ParaHandler;
import com.example.jmeeee.Presenter.Stop_watch;
import com.example.jmeeee.R;


public class JmeFragActivity extends AppCompatActivity {

    Handler mhandle = new Handler();
    boolean isPause = false;//是否暂停
    long currentSecond = 0;//当前毫秒数
    Runnable mRunnable;
    TextView tvStopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jme_frag);
        final Button btn_setmouseball = findViewById(R.id.button_ismouseball);
        btn_setmouseball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ParaHandler.getIsmouseball())
                {
                    ParaHandler.setIsmouseball(false);
                    btn_setmouseball.setText("固定");
                }else
                {
                    ParaHandler.setIsmouseball(true);
                    btn_setmouseball.setText("观察");
                }
            }
        });
        Button btn_setroll = findViewById(R.id.button_roll);
        btn_setroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParaHandler.setIsroll(true);
            }
        });
        Button  btn_save=findViewById(R.id.button_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("stop_time",tvStopwatch.getText());
                intent.setClass(JmeFragActivity.this, SaveActivity.class);
                startActivity(intent
                );
            }
        });


        //计时器
        tvStopwatch = findViewById(R.id.tv_stopwatch);
        String stop_time;
        //从intent中获取存档中的stop_time字段用于显示秒表的读数
        if(getIntent().getStringExtra("activity").equals("LoadActivity"))//如果来源是存档界面
        {
            stop_time = getIntent().getBundleExtra("bundle").getString("stop_time");//获取存档中的秒表时间
            tvStopwatch.setText(stop_time);//给秒表设置好时间
            currentSecond= Stop_watch.HMStoLONG(stop_time);//给秒表计数变量赋值
        }
        //秒表点击事件
        tvStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPause)
                {
                    mhandle.removeCallbacks(mRunnable);//停止计时进程
                    mRunnable=null;
                    tvStopwatch.setTextColor(Color.WHITE);//将
                    isPause=false;
                }else
                {
                    if(mRunnable==null) {
                        mRunnable = new Runnable() {//计时进程
                            @Override
                            public void run() {
                                currentSecond = currentSecond + 1000;
                                tvStopwatch.setText(Stop_watch.getFormatHMS(currentSecond));
                                mhandle.postDelayed(this, 1000);
                            }
                        };
                        mhandle.postDelayed(mRunnable, 0);//开始计时进程
                    }
                    tvStopwatch.setTextColor(Color.RED);
                    isPause=true;
                }

            }
        });
    }

    @Override
    protected void onDestroy() {

        DBHelper dbHelper = new DBHelper(JmeFragActivity.this, "cube_db", null, 1);
        dbHelper.save(tvStopwatch.getText().toString(),1);
        if(ParaHandler.fromid!=1)
        {
            dbHelper.copystate(ParaHandler.fromid,1);
        }
        dbHelper.savestate(ParaHandler.temp_state,dbHelper.readMaxstatenum(1)+1,1);
        ParaHandler.setIsmouseball(true);
        ParaHandler.temp_state="";
        ParaHandler.read_state="";
        ParaHandler.fromid=0;
        super.onDestroy();
        this.getDelegate().onDestroy();
    }


}


