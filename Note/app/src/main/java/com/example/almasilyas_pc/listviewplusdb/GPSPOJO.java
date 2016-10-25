package com.example.almasilyas_pc.listviewplusdb;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Almas Ilyas-PC on 2/16/2016.
 */
public class GPSPOJO
{

    ArrayList longitude;
    ArrayList latitude;
    ArrayList Gps_date;
    public GPSPOJO()
    {

    }
    public GPSPOJO(Cursor cursor)
    {
        longitude = new ArrayList<String>();
        latitude = new ArrayList<String>();
        Gps_date = new ArrayList<String>();
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            longitude.add(i,cursor.getString(0));
            latitude.add(i,cursor.getString(1));
            Gps_date.add(i,cursor.getString(2));
            cursor.moveToNext();
        }
    }

    public ArrayList<String> getGps_date() {
        return Gps_date;
    }
    public ArrayList<String> getlatitude()
    {
        return latitude;
    }

    public ArrayList<String> getlongitude()
    {
        return longitude;
    }


}
