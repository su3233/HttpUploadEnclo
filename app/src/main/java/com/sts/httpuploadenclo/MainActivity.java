package com.sts.httpuploadenclo;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Output;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.sts.httpuploadenclo.entity.Person;
import com.sts.httpuploadenclo.task.HttpListener;
import com.sts.httpuploadenclo.task.Request;
import com.sts.httpuploadenclo.task.RequestExecutor;
import com.sts.httpuploadenclo.task.Response;
import com.sts.httpuploadenclo.utils.Constants;
import com.sts.httpuploadenclo.utils.MyLog;
import com.sts.httpuploadenclo.utils.ThreadUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_get).setOnClickListener(this);
        findViewById(R.id.bt_post).setOnClickListener(this);
        findViewById(R.id.bt_head).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {//get 和head请求不能getOutputStream()
            case R.id.bt_get:
                getEncloRequest();
                getRequest();
                break;
            case R.id.bt_post:
                postRequest();
                break;
            case R.id.bt_head:
                headRequest();
                break;
        }
    }

    /**
     * 通过封装的httpUrlConnection请求
     */
    private void getEncloRequest() {
        final Request request = new Request("http://api.nohttp.net");
        request.add("useName", "zhangsan");
        RequestExecutor.INSTANCE.execute(request, new HttpListener() {
            @Override
            public void onSuccess(Response response) {
                MyLog.e("接收到的响应结果"+response.getResult());
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    //--------------------------------Head请求----------------------------
    private void headRequest() {
        ThreadUtils.executor(new Runnable() {
            @Override
            public void run() {
                executHead();
            }
        });
    }

    private void executHead() {
        //1、建立连接
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            url = new URL(Constants.URL_UPLOAD);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("HEAD");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
                Set<Map.Entry<String, List<String>>> heads = headerFields.entrySet();
                for (Map.Entry<String, List<String>> entry : heads) {
                    String key = entry.getKey();
                    MyLog.e("head  key" + key);
                    List<String> value = entry.getValue();
                    for (String str : value) {
                        MyLog.e("head  value" + str);
                    }
                }
                inputStream = urlConnection.getInputStream();
                readserverData(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //--------------------------------Post请求----------------------------
    private void postRequest() {
        ThreadUtils.executor(new Runnable() {
            @Override
            public void run() {
                executPost();
            }
        });
    }

    private void executPost() {
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URL(Constants.URL_UPLOAD);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("ContentType", "application/json");
            urlConnection.connect();

//            outputStream = urlConnection.getOutputStream();
//            String str = "i like you";
//            outputStream.write(str.getBytes());

            Person person = new Person("zhangsan", "man", "15");
            String json = JSON.toJSONString(person);
            outputStream.write(json.getBytes());

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                readserverData(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //--------------------------------Get请求----------------------------
    private void getRequest() {
        ThreadUtils.executor(new Runnable() {
            @Override
            public void run() {
                executGet();
            }
        });
    }

    private void executGet() {
        //1、建立连接
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            url = new URL(Constants.URL_UPLOAD);
            urlConnection = (HttpURLConnection) url.openConnection();
//        if (urlConnection instanceof HttpsURLConnection) {
//            ((HttpsURLConnection) urlConnection).setHostnameVerifier();
//            ((HttpsURLConnection) urlConnection).setSSLSocketFactory();
//        }
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //2、发送数据

            //3、拿到响应
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                readserverData(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 读取服务器返回流数据
     *
     * @param inputStream
     * @throws IOException
     */
    private void readserverData(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        String data = new String(outputStream.toByteArray());
        MyLog.i("服务器响应数据:" + data);
    }

}
