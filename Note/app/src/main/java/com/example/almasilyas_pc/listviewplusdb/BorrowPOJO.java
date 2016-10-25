package com.example.almasilyas_pc.listviewplusdb;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Almas Ilyas-PC on 2/16/2016.
 */
public class BorrowPOJO
{
    ArrayList money;
    ArrayList name;
    ArrayList Borrow_date;

    public BorrowPOJO()
    {

    }
    public BorrowPOJO(Cursor cursor)
    {
        money = new ArrayList<String>();
        name = new ArrayList<String>();
        Borrow_date = new ArrayList<String>();
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            money.add(i,cursor.getString(0));
            name.add(i,cursor.getString(1));
            Borrow_date.add(i,cursor.getString(2));
            cursor.moveToNext();
        }
    }

    public ArrayList<String> getmoney()
    {
        return money;
    }
    public ArrayList<String> getname()
    {
    return name;
    }
    public ArrayList<String> getBorrow_date()
    {
        return Borrow_date;
    }
}
