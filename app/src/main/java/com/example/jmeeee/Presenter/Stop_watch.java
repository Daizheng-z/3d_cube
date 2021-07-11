package com.example.jmeeee.Presenter;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stop_watch {
    //秒表相关操作
    private long start_milli = 0;
    private long end_milli = 0;

    //秒表格式化时间工具
    public static String getFormatHMS(long time){

        int ms = (int)(time%1000);//毫秒
        time=time/1000;//总秒数
        int s= (int) (time%60);//秒
        int m= (int) (time/60);//分
        int h=(int) (time/3600);//时
        return String.format("%02d:%02d:%02d",h,m,s);
    }
    //格式化时间转换回总秒数
    public static long HMStoLONG(String time_str)
    {
        String[] HMS = time_str.split(":");
        long result = Integer.valueOf(HMS[2])+Integer.valueOf(HMS[1])*60+3600*Integer.valueOf(HMS[0]);
        result*=1000;
        return result;
    }

    //按格式获取当前时间
    public static String getcurrenttime()
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        return dateFormat.format(new Date(System.currentTimeMillis()));

    }
    public  String getmillitime(Boolean ispaused)
    {
        long milliSecond = 0;
        if(start_milli == 0||ispaused){
            start_milli = System.currentTimeMillis()-end_milli;
            milliSecond = end_milli;
        }else{
            milliSecond = System.currentTimeMillis() - start_milli;
        }
        end_milli=milliSecond;
        Log.i("En",String.valueOf(end_milli));
        //求出现在的毫秒
        long currentmilliSecond = milliSecond % 1000;

        //求出现在的秒
        long totalSeconds = milliSecond / 1000;
        long currentSecond = totalSeconds % 60;

        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;




        return String.format("%02d:%02d:%03d",currentMinute,currentSecond,currentmilliSecond);

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
//        return dateFormat.format(new Date(System.currentTimeMillis()));

    }
}
