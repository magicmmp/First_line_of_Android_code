package com.example.filesavedandread_6_2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    //Git测试，怎么提交不了？
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);
        //新建数据库
        Button createDatabase=(Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        //添加2条信息
        Button addData=(Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SQLiteDatabase db= dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //插入第1本书的信息
                values.put("name","达芬奇密码");
                values.put("author","吴承恩");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                //插入第2本书的信息
                values.put("name","三国演义");
                values.put("author","吴冠中");
                values.put("pages",600);
                values.put("price",30.02);
                db.insert("Book",null,values);
            }
        });
        //更新数据
        Button updateData=(Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //更新价格
                values.put("price",50);
                db.update("Book",values,"name=?",new String[]{"达芬奇密码"});
            }
        });

        //删除数据
        Button deleteData=(Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                db.delete("Book",null,null);
            }
        });
        //查询数据
        Button queryData=(Button)findViewById(R.id.query_data);
       queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                //查询Book的所有数据
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {
                    do
                    {
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "书名: "+name);
                        Log.d("MainActivity", "作者: "+author);
                        Log.d("MainActivity", "页数: "+pages);
                        Log.d("MainActivity", "价格: "+price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
















