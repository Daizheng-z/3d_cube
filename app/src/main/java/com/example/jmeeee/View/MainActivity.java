package com.example.jmeeee.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jmeeee.Presenter.ParaHandler;
import com.example.jmeeee.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParaHandler.closeAndroidPDialog();//关闭AndroidP提示
        Button btn_start = findViewById(R.id.button1);
        Button btn_load = findViewById(R.id.button2);
        Button btn_tips = findViewById(R.id.button3);
        Button btn_battle = findViewById(R.id.button4);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParaHandler.fromid=0;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, JmeFragActivity.class);
                intent.putExtra("activity","MainActivity");
                startActivity(intent
                );
            }
        });
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoadActivity.class);
                startActivity(intent
                );
            }
        });
        btn_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TipsActivity.class);
                startActivity(intent
                );
            }
        });
        btn_battle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, JmeBattleActivity.class);
                startActivity(intent
                );
            }
        });

    }


}
