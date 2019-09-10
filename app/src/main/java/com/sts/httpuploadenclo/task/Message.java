package com.sts.httpuploadenclo.task;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class Message implements Runnable {
    /**
     * 服务响应
     */
    private Response response;

    /**
     * 回调接口
     *
     * @param response
     */

    private HttpListener httpListener;

    public Message(Response response, HttpListener httpListener) {
        this.response = response;
        this.httpListener = httpListener;
    }


    @Override
    public void run() {
        //回调在主线程
        Exception exception = response.getException();
        if (exception != null) {//请求失败
            httpListener.onFailed(exception);
        } else {
            httpListener.onSuccess(response);
        }
    }
}
