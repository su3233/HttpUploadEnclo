package com.sts.httpuploadenclo.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class Poster extends Handler {
    private static Poster poster;

    public Poster getInstance() {
        if (poster == null) {
            synchronized (MyLog.class) {
                if (poster == null) {
                    poster = new Poster();
                }
            }
        }
        return poster;
    }

    private Poster() {
        super(Looper.getMainLooper());
    }
}
