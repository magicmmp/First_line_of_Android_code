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
    List<String> urlList;
    List<File>   fileList;
    int fileNum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    changePic();
            }
        });
        Button delete_picture=(Button)findViewById(R.id.delete_picture);
        delete_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(fileList.size()>0)
                {
                    File file=fileList.get(fileNum);
                    if(file.exists())
                        file.delete();
                    fileList.remove(fileNum);
                    changePic();
                }
            }
        });




        String tmpUrl;
        String p1;
        String p2;
        String p3;
        String p4;
        String p5;
        String p6;
        String p7;
        String p8;

        boolean isTest=true;//是否从本地服务器下载
        if(isTest)
        {
            tmpUrl="http://10.0.2.2/last.jpg";
            p1="http://10.0.2.2/p1.jpg";
            p2="http://10.0.2.2/p2.jpg";
            p3="http://10.0.2.2/p3.jpg";
            p4="http://10.0.2.2/p4.jpg";
            p5="http://10.0.2.2/p5.jpg";
            p6="http://10.0.2.2/p6.jpg";
            p7="http://10.0.2.2/p7.jpg";
            p8="http://10.0.2.2/p8.jpg";

        }
        else
        {
            tmpUrl="https://tp.85814.com/d/file/shutu/2017-10/mwsscdyqvq4.jpg!800";
            p1="http://pic30.nipic.com/20130605/7447430_163105479000_2.jpg";
            p2="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2600834561,590596787&fm=26&gp=0.jpg";
            p3="http://img1.ph.126.net/rkc8aZrh8sk_hgTwtZoozw==/643451796778087126.jpg";
            p4="http://s1.sinaimg.cn/middle/93f1e65egb38f437a37f0&690";
            p5="http://img01.tooopen.com/Downs/images/2010/12/30/sy_20101230005631984036.jpg";
            p6="http://img3.imgtn.bdimg.com/it/u=4192583303,404022542&fm=26&gp=0.jpg";
            p7="https://tp.85814.com/d/file/shutu/2017-10/krtv00dbfiw.jpg!800";
            p8="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1443687308,2680039915&fm=26&gp=0.jpg";
        }

        urlList=new ArrayList<>();
        urlList.add(p1);
        urlList.add(p2);
        urlList.add(p3);
        urlList.add(p4);
        urlList.add(p5);
        urlList.add(p6);
        urlList.add(p7);
        urlList.add(p8);



        try
        {
            downloadPictureAndSave(tmpUrl,"last.jpg");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        fileList=new ArrayList<>();
        for(int i=0;i<urlList.size();i++)
        {
            String tmpName=i+".jpg";
            File tmpFile=new File(getFilesDir(),tmpName);
            try
            {
                downloadPictureAndSave(urlList.get(i),tmpName);
                fileList.add(tmpFile);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

       changePic();

    }

    private void changePic()
    {
        if(fileList.size()>0)
        {
            fileNum=(fileNum+1)%fileList.size();
            File file=fileList.get(fileNum);
            Log.d("Haha", "本地图片"+file+"大小="+file.length());
            Glide.with(MainActivity.this).load(file).into(bingPicImg);
        }
        else
        {
            File tmpFile=new File(getFilesDir(),"last.jpg");
            if(tmpFile.exists())
                Glide.with(MainActivity.this).load(tmpFile).into(bingPicImg);
            else
                finish();
        }
    }




    public  void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 估计是下载逻辑有问题，导致文件下载不完整
     */
    private void downloadPictureAndSave(String picUrl, final String SavedAsPicName) throws IOException{

        sendOkHttpRequest(picUrl, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null)
                {
                    InputStream is = response.body().byteStream();
                    byte[] arr = new byte[1024];

                    File f=new File(getFilesDir(),SavedAsPicName);
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

    @Override
    protected void onDestroy() {
        for(int i=0;i<urlList.size();i++)
        {
            String tmpName=i+".jpg";
            File tmpFile=new File(getFilesDir(),tmpName);
            if(tmpFile.exists())
                tmpFile.delete();
        }
        File lastFile=new File(getFilesDir(),"last.jpg");
        if(lastFile.exists())
            lastFile.delete();
        super.onDestroy();

    }
}
