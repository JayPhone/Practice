package com.jayphone.practice.libs;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpActivity extends AppCompatActivity {
    private static final String TAG = "OKHttpActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        asyncGetRequest();
//        syncGetRequest();
    }

    /**
     * 异步Get请求
     */
    private void asyncGetRequest() {
        //1.创建OkHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: ");
            }
        });
    }

    /**
     * 同步Get请求
     */
    private void syncGetRequest() {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.直接同步执行
        try {
            Response response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
