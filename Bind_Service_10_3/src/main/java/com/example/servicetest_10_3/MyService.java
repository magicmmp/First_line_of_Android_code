package com.example.servicetest_10_3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
    private DownloadBinder mBinder=new DownloadBinder();
    String TAG="MyService";
    private int progress=0;


    public MyService() {

    }

    //用来调用服务的方法
    class DownloadBinder extends Binder
    {
        //获取下载进度
        public int getDownloadProgress()
        {
           return serviceProgress();
        }
    }

    //计算当前进度
    public int serviceProgress()
    {
        progress=progress+10;
        if(progress>100)
            progress=0;
        return progress;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "了解服务的生命周期：onCreate:创建服务 ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "了解服务的生命周期：onStartCommand: 运行服务的下载任务");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "了解服务的生命周期：onDestroy: 服务被销毁");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return  mBinder;
    }
}























