package com.sts.httpuploadenclo.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class Request {
    /**
     * 请求url
     */
    private String url;
    /**
     * 请求的方法GET/POST
     */
    private RequestMethod requestMethod;
    /**
     * 请求添加参数集合
     */
    private List<KeyValue> keyValues;
    /**
     * ssl证书
     */
    private SSLSocketFactory sslSocketFactory;
    /**
     * 服务器人认证规则
     */
    private HostnameVerifier hostnameVerifier;

    public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public Request(String url) {
        this(url, RequestMethod.GET);
    }

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.requestMethod = method;
        keyValues = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }


    public void add(String key, String value) {
        keyValues.add(new KeyValue(key, value));
    }

    /**
     * 添加文件
     *
     * @param key
     * @param value
     */
    public void add(String key, File value) {
        keyValues.add(new KeyValue(key, value));
    }

    public void add(String key, int value) {
        keyValues.add(new KeyValue(key, Integer.toString(value)));
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", requestMethod=" + requestMethod +
                ", keyValues=" + keyValues.toString() +
                '}';
    }
}
