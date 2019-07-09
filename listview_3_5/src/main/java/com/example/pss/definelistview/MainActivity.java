package com.example.pss.definelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter=new FruitAdapter(MainActivity.this,
                R.layout.fruit_item, fruitList);
        ListView listview =(ListView)findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        //设置listview条目的点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit=fruitList.get(position);
                Toast.makeText(MainActivity.this,"你点了第"+position+"项："+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void initFruits()
    {
        for(int i=0;i<100;i++)
        {
            Fruit apple=new Fruit("苹果", R.drawable.apple);
            fruitList.add(apple);
            Fruit grape=new Fruit("葡萄", R.drawable.grape);
            fruitList.add(grape);
            Fruit huangpi=new Fruit("黄皮", R.drawable.huangpi);
            fruitList.add(huangpi);
            Fruit orange=new Fruit("橘子", R.drawable.orange);
            fruitList.add(orange);
            Fruit strawberry=new Fruit("草莓", R.drawable.strawberry);
            fruitList.add(strawberry);
            Fruit tomato=new Fruit("番茄", R.drawable.tomato);
            fruitList.add(tomato);
        }
    }

}
