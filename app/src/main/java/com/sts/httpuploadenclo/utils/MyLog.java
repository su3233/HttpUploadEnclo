package com.sts.httpuploadenclo.utils;

import android.util.Log;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe 自定义log类
 */
public class MyLog {
    private static final String TAG = "HTTP_UPLOAD_ENCLO";
    private MyLog myLog;
    private static final Boolean DEBUG = false;

    public MyLog getInstance() {
        if (myLog == null) {
            synchronized (MyLog.class) {
                if (myLog == null) {
                    myLog = new MyLog();
                }
            }
        }
        return myLog;
    }

    public static String getMessage(Object object) {
        return object == null ? "null" : object.toString();
    }

    public static void i(Object msg) {
        if (DEBUG) {
            Log.i(TAG, getMessage(msg));
        }
    }

    public static void e(Object msg) {
        if (DEBUG) {
            Log.e(TAG, getMessage(msg));
        }
    }

    public static void d(Object msg) {
        if (DEBUG) {
            Log.d(TAG, getMessage(msg));
        }
    }

    public static void w(Object msg) {
        if (DEBUG) {
            Log.w(TAG, getMessage(msg));
        }
    }

}
