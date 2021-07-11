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

public class JmeBattleActivity extends AppCompatActivity {
    Handler mhandle = new Handler();
    boolean isPause = false;//是否暂停
    boolean isPaused = false;
    long currentSecond = 0;//当前毫秒数
    Runnable mRunnable;
    TextView tvStopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jme_battle);
        Stop_watch stop_watch = new Stop_watch();
        final Button btn_setmouseball = findViewById(R.id.button_b_ismouseball);
        ParaHandler.setIsroll(true);
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

        //计时器
        tvStopwatch = findViewById(R.id.tv_b_stopwatch);
        //秒表点击事件
        tvStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPause)
                {
                    //计时运行时点击了时间戳
                    mhandle.removeCallbacks(mRunnable);//停止计时进程
                    mRunnable=null;
                    tvStopwatch.setTextColor(Color.WHITE);
                    isPause=false;
                    isPaused = true;
                }else
                {
                    //计时停止时点击了时间戳
                    if(mRunnable==null) {
                        mRunnable = new Runnable() {//计时进程
                            @Override
                            public void run() {
                                tvStopwatch.setText(stop_watch.getmillitime(isPaused));
                                isPaused=false;
                                mhandle.postDelayed(this, 0);
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
        super.onDestroy();
        this.getDelegate().onDestroy();
    }
}