package com.example.pss.definelistview;

/**
 * Created by pss on 2019/6/9.
 */

public class Fruit
{
    private  String name;
    private int imageId;

    public  Fruit(String name,int imageId)
    {
        this.name=name;
        this.imageId=imageId;
    }
    public  String getName()
    {
        return  name;
    }
    public int getImageId()
    {
        return  imageId;
    }

}

