package com.example.camera_album_8_3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO=1;//作为intent的请求码
    private ImageView picture;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button takePhoto=(Button)findViewById(R.id.take_photo);
        picture=(ImageView)findViewById(R.id.imageView);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //file对象用来存储拍照后的图片
                //放在SD卡的应用关联缓存目录下
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try
                {
                    if(outputImage.exists())
                        outputImage.delete();
                    outputImage.createNewFile();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24)
                {
                    imageUri= FileProvider.getUriForFile(MainActivity.this,
                            "com.example.camera_album_8_3.file_provider",outputImage);
                }
                else
                    imageUri=Uri.fromFile(outputImage);
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                //指定图片输出地址
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                Log.d("拍照？", "打开拍照程序");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                //这里为何不返回RESULT_OK ？
                if(requestCode==RESULT_FIRST_USER)
                {
                    try
                    {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                        Log.d("拍照？", "返回码= "+requestCode+"，RESULT_FIRST_USER= "+RESULT_FIRST_USER);
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.d("拍照？", "有问题");
                }
                break;
            default:
                break;
        }
    }
}






















