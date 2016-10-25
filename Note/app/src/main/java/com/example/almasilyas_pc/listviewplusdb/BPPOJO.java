package com.example.almasilyas_pc.listviewplusdb;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Almas Ilyas-PC on 2/16/2016.
 */
public class BPPOJO
{
    ArrayList Bp_reading;
    ArrayList Bp_time;

    public BPPOJO()
    {

    }
    public BPPOJO(Cursor cursor)
    {
        Bp_reading = new ArrayList<String>();
        Bp_time = new ArrayList<String>();
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            Bp_reading.add(i,cursor.getString(0));
            Bp_time.add(i,cursor.getString(1));
            cursor.moveToNext();
        }
    }

    public ArrayList<String> getBp_reading() {
        return Bp_reading;
    }
    public ArrayList<String> getBp_time()
    {
        return Bp_time;
    }

}
