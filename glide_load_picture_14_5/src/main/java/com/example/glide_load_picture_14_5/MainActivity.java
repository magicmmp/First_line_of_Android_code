package com.example.glide_load_picture_14_5;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView bingPicImg;
    private String picName="meinv5"; // pic.bmp
    String TAG="Haha";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);

      String picAddr="http://10.0.2.2/xiaomeinv.jpg"; //xiaomeinv.jpg  p1,jpg
     //  String picAddr="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2600834561,590596787&fm=26&gp=0.jpg";
/**/
        try
        {
            loadBingPic(picAddr);
        }
        catch (IOException e)
        {
            Log.d(TAG, "下载图片时发生异常");
            e.printStackTrace();
        }
       // picName="meiNv.bmp";
        File picture=new File(getFilesDir().getPath()+"/"+picName);
        Log.d(TAG, "文件路径为："+picture.getPath());
        Log.d(TAG, "本地文件大小3= "+picture.length());
 /**/

        Glide.with(MainActivity.this).load(picture).into(bingPicImg);

    }








    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic(String picUrl) throws IOException{

        sendOkHttpRequest(picUrl, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null)
                {
                    InputStream is = response.body().byteStream();
                    byte[] arr = new byte[1024];


                    FileOutputStream out=openFileOutput(picName, Context.MODE_PRIVATE);

                    int len;
                    while ((len = is.read(arr)) != -1)
                    {
                        out.write(arr, 0, len);
                    }
                    response.body().close();
                    out.close();
                    is.close();
                }
            }
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
                Log.d(TAG, e.toString());
            }
        });
    }

}
