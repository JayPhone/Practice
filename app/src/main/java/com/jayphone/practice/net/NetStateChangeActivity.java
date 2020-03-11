package com.jayphone.practice.net;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by JayPhone on 2020/3/10
 * 检测网络状态步骤
 */
public class NetStateChangeActivity extends AppCompatActivity {
    private static final String TAG = "NetStateChangeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * 1.获得ConnectivityManager对象(ConnectivityManager主要用于查看网络状态和管理网络连接相关的操作)
         * 2.获取ConnectivityManager对象对应的NetworkInfo对象(NetworkInfo对象包含网络连接的所有信息)
         * 3.根据需要从NetworkInfo对象取出关于网络连接的信息
         * 需要android.permission.ACCESS_NETWORK_STATE权限
         */
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            //获取所有网络连接的信息
            Network[] networks = connectivityManager.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            for (Network network : networks) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                sb.append((networkInfo.getTypeName() + " connect is " + networkInfo.isConnected()));
            }
            Log.e(TAG, sb.toString());
        } else {
            //获取WIFI连接的信息
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            Boolean isWifiConn = networkInfo.isConnected();
            Log.e(TAG, "是否连接wifi: " + isWifiConn);

            //获取移动数据连接的信息
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            Boolean isMobileConn = networkInfo.isConnected();
            Log.e(TAG, "是否连接移动网络: " + isMobileConn);
        }
    }
}
