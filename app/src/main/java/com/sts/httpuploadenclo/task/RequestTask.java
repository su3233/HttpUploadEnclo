package com.sts.httpuploadenclo.task;

import com.sts.httpuploadenclo.urlconnection.URLConnectionPactory;
import com.sts.httpuploadenclo.utils.MyLog;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class RequestTask implements Runnable {
    private Request request;
    private HttpListener httpListener;

    public RequestTask(Request request, HttpListener httpListener) {
        this.request = request;
        this.httpListener = httpListener;
    }

    @Override
    public void run() {
        MyLog.e("执行请求" + request.toString());
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(request.getUrl());
//             httpURLConnection = (HttpURLConnection) url.openConnection();
            //一句话切换okHttp和UrlConnetion
            httpURLConnection = URLConnectionPactory.getInstance().openUrl(url);
            if (httpURLConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                SSLSocketFactory sslSocketFactory = request.getSslSocketFactory();
                if (sslSocketFactory != null)
                    httpsURLConnection.setSSLSocketFactory(sslSocketFactory);//证书相关信息

                HostnameVerifier hostnameVerifier = request.getHostnameVerifier();
                if (hostnameVerifier != null)
                    httpsURLConnection.setHostnameVerifier(hostnameVerifier);//主机认证
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        Exception exception = null;
        int responseCode = -1;
        Map<String, List<String>> headers = null;
        Response response = new Response(responseCode, "成功", null, request, headers);

        Message message = new Message(response, httpListener);
        //回调在主线程
        Poster.getInstance().post(message);
    }
}
