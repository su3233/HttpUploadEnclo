package com.sts.httpuploadenclo.task;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public interface HttpListener {
    /**
     * 请求成功
     *
     * @param response
     */
    void onSuccess(Response response);

    /**
     * 请求失败
     *
     * @param e
     */
    void onFailed(Exception e);
}
