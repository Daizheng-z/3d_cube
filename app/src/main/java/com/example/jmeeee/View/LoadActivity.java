package com.example.jmeeee.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jmeeee.Model.DBHelper;
import com.example.jmeeee.Presenter.ParaHandler;
import com.example.jmeeee.R;

public class LoadActivity extends AppCompatActivity {


    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        dbHelper = new DBHelper(LoadActivity.this, "cube_db", null, 1);
        db = dbHelper.getReadableDatabase();

        Button btnDefaultload;
        Button btnLoad1;
        Button btnLoad2;
        Button btnLoad3;

        btnDefaultload = findViewById(R.id.btn_defaultload);
        String[] names = dbHelper.getloadfilename();
        btnLoad1 = findViewById(R.id.btn_load1);
        btnLoad1.setText(names[0]);
        btnLoad2 = findViewById(R.id.btn_load2);
        btnLoad2.setText(names[1]);
        btnLoad3 = findViewById(R.id.btn_load3);
        btnLoad3.setText(names[2]);

        btnDefaultload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = dbHelper.load(1);//从数据库读取数据
                ParaHandler.fromid = 1;
                Intent intent = new Intent();
                intent.putExtra("bundle", bundle);//添加存档信息
                intent.putExtra("activity", "LoadActivity");//注明是从存档界面跳转
                intent.setClass(LoadActivity.this, JmeFragActivity.class);
                ParaHandler.setIsfromloadfile(true);
                startActivity(intent
                );
                finish();
            }

        });
        btnLoad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = dbHelper.load(2);
                ParaHandler.fromid = 2;
                Intent intent = new Intent();
                intent.putExtra("bundle", bundle);
                intent.putExtra("activity", "LoadActivity");
                intent.setClass(LoadActivity.this, JmeFragActivity.class);
                ParaHandler.setIsfromloadfile(true);
                startActivity(intent
                );
                finish();
            }
        });
        btnLoad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = dbHelper.load(3);
                ParaHandler.fromid = 3;
                Intent intent = new Intent();
                intent.putExtra("bundle", bundle);
                intent.putExtra("activity", "LoadActivity");
                intent.setClass(LoadActivity.this, JmeFragActivity.class);
                ParaHandler.setIsfromloadfile(true);
                startActivity(intent
                );
                finish();
            }
        });
        btnLoad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = dbHelper.load(4);
                ParaHandler.fromid = 4;
                Intent intent = new Intent();
                intent.putExtra("bundle", bundle);
                intent.putExtra("activity", "LoadActivity");
                intent.setClass(LoadActivity.this, JmeFragActivity.class);
                ParaHandler.setIsfromloadfile(true);
                startActivity(intent
                );
                finish();
            }

        });
    }


}
