package com.example.jmeeee.View;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jmeeee.Model.DBHelper;
import com.example.jmeeee.Presenter.ParaHandler;
import com.example.jmeeee.R;

public class SaveActivity extends AppCompatActivity {


    DBHelper dbHelper;
    SQLiteDatabase db;
    Button btnSave1;
    Button btnSave2;
    Button btnSave3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        dbHelper = new DBHelper(SaveActivity.this, "cube_db", null, 1);
        db = dbHelper.getWritableDatabase();
        final String given_stop_time = getIntent().getStringExtra("stop_time");

        String[] names =dbHelper.getloadfilename();
        btnSave1 = findViewById(R.id.btn_save1);
        btnSave2 = findViewById(R.id.btn_save2);
        btnSave3 = findViewById(R.id.btn_save3);
        btnSave1.setText(names[0]);
        btnSave2.setText(names[1]);
        btnSave3.setText(names[2]);


        //存档一
        btnSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.save(given_stop_time, 2);
                Toast.makeText(SaveActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                String[] names =dbHelper.getloadfilename();
                btnSave1.setText(names[0]);
                Log.i("TAG", "onClick: ----------->fromid"+ParaHandler.fromid);
                if(ParaHandler.fromid!=2)
                {
                    dbHelper.copystate(ParaHandler.fromid,2);
                }
                dbHelper.savestate(ParaHandler.temp_state,dbHelper.readMaxstatenum(2)+1,2);


            }
        });

        //存档二
        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    dbHelper.save(given_stop_time, 3);//将数据保存至数据库
                    //Toast.makeText(SaveActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();//提示消息
                }catch (Exception e)
                {
                    Toast.makeText(SaveActivity.this, "保存失败！", Toast.LENGTH_SHORT).show();
                }
                String[] names =dbHelper.getloadfilename();//获取保存存档的时间作为存档按钮的名字
                btnSave2.setText(names[1]);//设置存档按钮的名字
                if(ParaHandler.fromid!=3)
                {
                    dbHelper.copystate(ParaHandler.fromid,3);
                }
                dbHelper.savestate(ParaHandler.temp_state,dbHelper.readMaxstatenum(3)+1,3);

            }
        });

        //存档三
        btnSave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.save(given_stop_time, 4);
                Toast.makeText(SaveActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                String[] names =dbHelper.getloadfilename();
                btnSave3.setText(names[2]);
                if(ParaHandler.fromid!=4)
                {
                    dbHelper.copystate(ParaHandler.fromid,4);
                }
                dbHelper.savestate(ParaHandler.temp_state,dbHelper.readMaxstatenum(4)+1,4);
            }
        });
    }

}
