package com.example.jmeeee.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jmeeee.R;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        TextView tv_tips=findViewById(R.id.tv_tips);
        tv_tips.setText("使用说明：\n" +
                "基本操作：\n" +
                "    1.进入魔方界面时，默认为观察模式，可在屏幕上滑动\n三维旋转查看魔方。\n" +
                "    2.点击固定按钮，即可在魔方上的任意位置滑动以旋转\n某一层。\n" +
                "    3.点击打乱按钮，即可获得一" +
                "个打乱后的魔方。\n" +
                "    4.点击保存按钮即可保存当前状态及秒表时间。\n" +
                "注意事项：\n" +
                "    1.观察模式时，垂直方向只可在180度范围内运动，水\n平方向可以在360度范围内运动。\n" +
                "    \n");
    }
}
