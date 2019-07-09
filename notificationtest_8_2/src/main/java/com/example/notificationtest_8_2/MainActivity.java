package com.example.notificationtest_8_2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    //还能提交吗8.2
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button_notify);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button_notify)
        {
            //定义跳转意图。点击这个通知后，跳到另一个页面
            Intent intent=new Intent(this,NotificationText_Activity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
            NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            Notification notification=new NotificationCompat.Builder(this)
                    .setContentTitle("通知标题")
                    .setContentText("今晚放电影啦！")
                    .setWhen(System.currentTimeMillis())//显示通知时间
                    .setSmallIcon(R.mipmap.ic_launcher)//设置通知小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)//点击后跳转到其他页面
                    .setAutoCancel(true) //点击后自动消失
                    .build();
            manager.notify(1,notification);

        }

    }
}






















