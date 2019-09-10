package com.sts.httpuploadenclo.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class ThreadUtils {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void executor(Runnable runnable) {
        executorService.execute(runnable);
    }
}
