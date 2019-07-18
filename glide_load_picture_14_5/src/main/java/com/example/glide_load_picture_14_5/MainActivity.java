package com.example.glide_load_picture_14_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private ImageView bingPicImg;
    boolean isUpdate;
    List<String> urlList;
    int idx;
    private String picName="meinv"; // pic.bmp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idx=(idx+1)%urlList.size();
                changePic(urlList.get(idx),true);
            }
        });

        String p1="http://pic30.nipic.com/20130605/7447430_163105479000_2.jpg";
        String p2="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2600834561,590596787&fm=26&gp=0.jpg";
        String p3="http://b-ssl.duitang.com/uploads/item/201703/13/20170313012900_VYSJT.thumb.700_0.jpeg";
        String p4="http://s1.sinaimg.cn/middle/93f1e65egb38f437a37f0&690";
        String p5="http://img01.tooopen.com/Downs/images/2010/12/30/sy_20101230005631984036.jpg";
        urlList=new ArrayList<>();
        urlList.add(p1);
        urlList.add(p2);
        urlList.add(p3);
        urlList.add(p4);
        urlList.add(p5);

        idx=0;

        File tmpFile=new File(getFilesDir(),picName);
        if(tmpFile.exists())
            isUpdate=false;
        else
            isUpdate=true;

        changePic(urlList.get(idx),isUpdate);



    }

    private void changePic(String url,boolean isUpdate)
    {
        if(isUpdate)
        try
        {
            loadBingPic(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        File file=new File(getFilesDir(),picName);
        Log.d("Haha", "本地图片大小="+file.length());
        Glide.with(MainActivity.this).load(file).into(bingPicImg);
    }




    public  void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 估计是下载逻辑有问题，导致文件下载不完整
     */
    private void loadBingPic(String picUrl) throws IOException{

        sendOkHttpRequest(picUrl, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null)
                {
                    InputStream is = response.body().byteStream();
                    byte[] arr = new byte[1024];

                    File f=new File(getFilesDir(),picName);
                    FileOutputStream out=new FileOutputStream(f);

                    int len;
                    while ((len = is.read(arr)) != -1)
                    {
                        out.write(arr, 0, len);
                    }
                    out.close();
                    response.body().close();

                    is.close();
                }
            }
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }
        });
    }

}
