package com.example.almasilyas_pc.listviewplusdb;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Almas Ilyas-PC on 2/16/2016.
 */
public class ShoppingPOJO
{
    ArrayList Shopping_list;
    ArrayList Shopping_date;

    public ShoppingPOJO()
    {

    }
    public ShoppingPOJO(Cursor cursor)
    {
        Shopping_list = new ArrayList<String>();
        Shopping_date = new ArrayList<String>();
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            Shopping_list.add(i,cursor.getString(0));
            Shopping_date.add(i,cursor.getString(1));
            cursor.moveToNext();
        }
    }

    public ArrayList<String> getShopping_list() {
        return Shopping_list;
    }
    public ArrayList<String> getShopping_date()
    {
        return Shopping_date;
    }
}
