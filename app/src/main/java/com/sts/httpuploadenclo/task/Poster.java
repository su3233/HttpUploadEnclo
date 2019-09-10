package com.sts.httpuploadenclo.task;

import android.os.Handler;
import android.os.Looper;

import com.sts.httpuploadenclo.utils.MyLog;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class Poster extends Handler {
    private static Poster poster;

    public static Poster getInstance() {
        if (poster == null) {
            synchronized (MyLog.class) {
                if (poster == null) {
                    poster = new Poster();
                }
            }
        }
        return poster;
    }

    public Poster() {
        super(Looper.getMainLooper());
    }
}
