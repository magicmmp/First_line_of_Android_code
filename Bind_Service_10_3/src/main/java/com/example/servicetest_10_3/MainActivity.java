package com.example.servicetest_10_3;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            //解除服务的绑定
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            downloadBinder = (MyService.DownloadBinder) service;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //用来获取后台服务下载进度
        Button get_progress = (Button) findViewById(R.id.get_progress);
        get_progress.setOnClickListener(this);

        Intent startIntent=new Intent(this,MyService.class);
        startService(startIntent);//启动服务
        bindService(startIntent, connection, BIND_AUTO_CREATE); // 绑定服务
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.get_progress:
                int progress=downloadBinder.getDownloadProgress();
                Log.d("MainActivity", "后台下载进度："+progress);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection); // 解绑服务，以免内存泄漏
    }
}



















