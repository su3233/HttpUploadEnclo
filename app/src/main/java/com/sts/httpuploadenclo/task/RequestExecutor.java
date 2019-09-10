package com.sts.httpuploadenclo.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public enum RequestExecutor {
    INSTANCE;
    private ExecutorService executorService;

    RequestExecutor() {
        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * 使用线程池执行请求
     *
     * @param request
     */
    public void execute(Request request, HttpListener httpListener) {
        executorService.execute(new RequestTask(request, httpListener));
    }
}
