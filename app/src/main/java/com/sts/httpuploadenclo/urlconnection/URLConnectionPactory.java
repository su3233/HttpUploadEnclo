package com.sts.httpuploadenclo.urlconnection;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.internal.huc.OkHttpURLConnection;
import okhttp3.internal.huc.OkHttpsURLConnection;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe 把okhttp转换为URLConnection
 */
public class URLConnectionPactory {
    private static URLConnectionPactory instance;

    public static URLConnectionPactory getInstance() {
        if (instance == null) {
            synchronized (URLConnectionPactory.class) {
                if (instance == null) {
                    instance = new URLConnectionPactory();
                }
            }
        }
        return instance;
    }

    private OkHttpClient okhttpClient;

    private URLConnectionPactory() {
        okhttpClient = new OkHttpClient();
    }

    /**
     * 打开url
     *
     * @param url
     * @return
     */
    public HttpURLConnection openUrl(URL url) {
        return openUrl(url);
    }

    public HttpURLConnection openUrl(URL url, Proxy proxy) {
        String protocol = url.getProtocol();   // http or https，
        OkHttpClient copy = okhttpClient.newBuilder().proxy(proxy).build();
        if (protocol.equals("http")) return new OkHttpURLConnection(url, copy);
        if (protocol.equals("https")) return new OkHttpsURLConnection(url, copy);
        throw new IllegalArgumentException("Unexpected protocolr" + protocol);
    }
}