package com.example.newsapp_4_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到按钮控件对象
        Button send=(Button)findViewById(R.id.button);
        //将当前类注册为监听器
        send.setOnClickListener(this);
    }

    @Override
    //重写OnClickListener接口的方法
    public void onClick(View v)
    {
        //响应代码
    }
}
