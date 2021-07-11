package com.example.jmeeee.Presenter;

import com.jme3.scene.Node;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParaHandler {

    public static Node[][][] Rcube=new Node[3][3][3];

    public static boolean ismouseball = true;

    public static String temp_state ="";//本次旋转使用的公式
    public static String read_state ="";//从存档中读取出的公式
    public static int fromid = 0;//当前是哪个存档
    public static boolean isfromloadfile=false;

    public static boolean isIsfromloadfile() {
        return isfromloadfile;
    }

    public static void setIsfromloadfile(boolean isfromloadfile) {
        ParaHandler.isfromloadfile = isfromloadfile;
    }


    public static boolean getIsmouseball() {
        return ismouseball;
    }

    public static void setIsmouseball(boolean ismouseball) {
        ParaHandler.ismouseball = ismouseball;
    }
    public static boolean isroll = false;

    public static boolean getIsroll() {
        return isroll;
    }

    public static void setIsroll(boolean isroll) {
        ParaHandler.isroll = isroll;
    }



    public static void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
