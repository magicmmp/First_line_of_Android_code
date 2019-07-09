package com.example.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager=new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }





    public  void initFruits()
    {
        for(int i=0;i<100;i++)
        {
            Fruit apple=new Fruit(getRamdonLengthName("苹果"), R.drawable.apple);
            fruitList.add(apple);
            Fruit grape=new Fruit(getRamdonLengthName("葡萄"), R.drawable.grape);
            fruitList.add(grape);
            Fruit huangpi=new Fruit(getRamdonLengthName("黄皮"), R.drawable.huangpi);
            fruitList.add(huangpi);
            Fruit orange=new Fruit(getRamdonLengthName("橘子"), R.drawable.orange);
            fruitList.add(orange);
            Fruit strawberry=new Fruit(getRamdonLengthName("草莓"), R.drawable.strawberry);
            fruitList.add(strawberry);
            Fruit tomato=new Fruit(getRamdonLengthName("番茄"), R.drawable.tomato);
            fruitList.add(tomato);
        }
    }

    String getRamdonLengthName(String name)
    {
        Random random=new Random();
        int length=random.nextInt(50)+1;
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<length;i++)
            builder.append(name);
        return  builder.toString();
    }
}













