package com.sts.httpuploadenclo.task;

import java.util.List;
import java.util.Map;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class Response {
    /**
     * 服务器响应码
     */
    private int responseCode;
    /**
     * 服务器响应数据
     */
    private String result;

    private Exception exception;
    /**
     * 请求对象，响应数据时也能拿到请求对象
     */
    private Request request;
    /**
     * 响应头，获取结果的时候同时获取到响应头
     */
    private Map<String, List<String>> responseHeaders;


    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResult() {
        return result;
    }


     Exception getException() {
        return exception;
    }


    public Request getRequest() {
        return request;
    }
    Response(int responseCode, String result, Exception exception, Request request) {
        this.responseCode = responseCode;
        this.result = result;
        this.exception = exception;
        this.request = request;
    }

    Response(int responseCode, String result, Exception exception, Request request, Map<String, List<String>> responseHeaders) {
        this.responseCode = responseCode;
        this.result = result;
        this.responseHeaders = responseHeaders;
        this.exception = exception;
        this.request = request;
    }
}
