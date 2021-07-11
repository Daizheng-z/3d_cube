package com.example.jmeeee.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.example.jmeeee.Presenter.ParaHandler;
import com.example.jmeeee.Presenter.Stop_watch;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

import static android.content.ContentValues.TAG;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table loadfile_table(id integer primary key autoincrement,creat_time varchar(20),stop_time varchar(20))";
        //execSQL函数用于执行SQL语句
        db.execSQL(sql);
        //插入4条记录用作存档
        String sql_insert = "insert into loadfile_table (creat_time,stop_time) values (\"新存档\",\"00:00:00\")";
        db.execSQL(sql_insert);
        db.execSQL(sql_insert);
        db.execSQL(sql_insert);
        db.execSQL(sql_insert);
        //创建魔方状态表用于存储魔方状态
        String sql2 = "create table cubestate_table(id integer primary key autoincrement,state varchar(5000),statenum integer,loadfileid integer)";
        db.execSQL(sql2);
        String sql_insertstate1 = "insert into cubestate_table (state,statenum,loadfileid) values (\"\","+0+","+1+")";
        db.execSQL(sql_insertstate1);
        String sql_insertstate2 = "insert into cubestate_table (state,statenum,loadfileid) values (\"\","+0+","+2+")";
        db.execSQL(sql_insertstate2);
        String sql_insertstate3 = "insert into cubestate_table (state,statenum,loadfileid) values (\"\","+0+","+3+")";
        db.execSQL(sql_insertstate3);
        String sql_insertstate4 = "insert into cubestate_table (state,statenum,loadfileid) values (\"\","+0+","+4+")";
        db.execSQL(sql_insertstate4);

    }

    //存档函数
    public void save(String stop_time, int id) {
        SQLiteDatabase db=getWritableDatabase();//得到一个可写数据库对象
        ContentValues cv = new ContentValues();//新建一个键值对
        cv.put("creat_time", Stop_watch.getcurrenttime());//放入当前时间
        cv.put("stop_time", stop_time);//放入秒表时间
        db.update("loadfile_table", cv, "id=?", new String[]{String.valueOf(id)});//更新存档记录
        db.close();
    }

    //读档函数
    public Bundle load( int id) {
        SQLiteDatabase db = getReadableDatabase();
        Bundle bundle = new Bundle();
        Cursor cursor = db.query("loadfile_table", new String[]{"id", "creat_time", "stop_time"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        while (cursor.moveToNext()) {
            String creat_time = cursor.getString(cursor.getColumnIndex("creat_time"));
            String stop_time = cursor.getString(cursor.getColumnIndex("stop_time"));
            String state = loadstate(id);
            bundle.putString("stop_time", stop_time);
            bundle.putString("creat_time", creat_time);
            bundle.putString("state", state);
            ParaHandler.read_state = state;

        }
        db.close();
        return bundle;
    }

    //存储魔方状态
    public void savestate(String state,int statenum,int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql_insertstate = "insert into cubestate_table (state,statenum,loadfileid) values (\""+state+"\","+statenum+","+id+")";
        db.execSQL(sql_insertstate);
        db.close();
    }

    //读取当前最大的魔方状态编号
    public int readMaxstatenum(int loadfileid)
    {
        SQLiteDatabase db=getReadableDatabase();
        int result = -1;
        Cursor cursor=db.rawQuery("select max(statenum) from cubestate_table where loadfileid = ?",new String[]{String.valueOf(loadfileid)});
        Log.i(TAG, "readMaxstatenum: --------->cursur"+cursor.toString());
        cursor.moveToFirst();
        result = cursor.getInt(0);
        db.close();
        return result;
    }

    //读取魔方状态
    public String loadstate(int loadfileid)
    {
        SQLiteDatabase db=getReadableDatabase();//得到一个可读的数据库
        String state="";//结果字符串
        Cursor cursor = db.query("cubestate_table",new String[]{"state"},"loadfileid = ?",new String[]{String.valueOf(loadfileid)},null,null,"statenum");//按存档编号查询并按状态编号排序，得到一个游标
        cursor.moveToFirst();//将游标移至第一条记录
        state+=cursor.getString(0);//取出游标中的魔方状态连接至结果字符串
        while (cursor.moveToNext()) {//游标向下移动直至最后一条记录
            state+=cursor.getString(0);//取出游标中的魔方状态连接至结果字符串
        }
        db.close();
        return state;
    }

    //复制存档的状态
    public void copystate(int fromid,int toid)
    {
        SQLiteDatabase db=getWritableDatabase();//得到一个可写的数据库
        db.delete("cubestate_table","loadfileid = ?",new String[]{String.valueOf(toid)});//删除目标存档原有的记录
        if(fromid==0)return;//如果是新开始的魔方则退出此函数
        Cursor cursor = db.query("cubestate_table",new String[]{"state","statenum","loadfileid"},"loadfileid = ?",new String[]{String.valueOf(fromid)},null,null,"statenum");//按条件查询所有当前存档的魔方状态记录并得到一个游标用于访问查询结果
        cursor.moveToFirst();//将游标移动到第一条记录
        String tempinsert1 = "insert into cubestate_table (state,statenum,loadfileid) values (\""+cursor.getString(0)+"\","+cursor.getInt(1)+","+toid+")";//将第一条记录以目标存档的编号插入数据库
        db.execSQL(tempinsert1);
        while (cursor.moveToNext()) {//移动游标直至最后
            String tempinsert = "insert into cubestate_table (state,statenum,loadfileid) values (\""+cursor.getString(0)+"\","+cursor.getInt(1)+","+toid+")";//插入记录
            db.execSQL(tempinsert);
        }
        db.close();
    }

//    public void saveCube()
//    {
//        SQLiteDatabase db=getWritableDatabase();
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    Vector3f loc = ParaHandler.Rcube[i][j][k].getLocalTranslation();
//                    Quaternion rot = ParaHandler.Rcube[i][j][k].getLocalRotation();
//                    ContentValues cv = new ContentValues();
//                    cv.put("location", loc.toString());
//                    cv.put("rotation", rot.toString());
//                    db.update("cube_table", cv, "id=?", new String[]{String.valueOf(count++)});
//                    db.close();
//                }
//            }
//        }
//    }

    //获取三个时间用作存档名
    public String[] getloadfilename() {
        SQLiteDatabase db=getReadableDatabase();
        String[] strings = new String[3];
        strings[0]=load(2).getString("creat_time");
        strings[1]=load(3).getString("creat_time");
        strings[2]=load(4).getString("creat_time");
        db.close();
        return strings;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
