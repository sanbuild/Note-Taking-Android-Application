package com.example.almasilyas_pc.listviewplusdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Almas Ilyas-PC on 1/28/2016.
 */
public class Db extends SQLiteOpenHelper {

    final static String Db_name="NOTE_BOOK";
    final static String Table_name="Courses";
    final static int VERSION=1;
    final static String Table_field_1="Teacher_names";
    final static String Table_field_2="Courses_names";
    final static String Table_name_1="GPS";
    final static String Table_name_2="BORROW_MONEY";
    final static String Table_name_3="BP";
    final static String Table_name_4="SHOPPING";
    final static String GPS_COL_1="Longitude";
    final static String GPS_COL_2="Latitude";
    final static String GPS_COL_3="GPS_Date";
    final static String BORROW_COL_1="Money";
    final static String BORROW_COL_2="Name";
    final static String BORROW_COL_3="BORROW_Date";
    final static String BP_COL_1="Bp_reading";
    final static String BP_COL_2="BP_Time";
    final static String SHOPPING_COL_1="List";
    final static String SHOPPING_COL_2="SHOPPING_Date";




    public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Db_name,null, VERSION);
    }

    @Override
    // this method is calling only 4 once untill the version is upgraded.. query is run and table is created
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Courses(Teacher_names text,Courses_names text,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);");
        db.execSQL("create table GPS(Longitude text,Latitude text,GPS_Date text,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);");
        db.execSQL("create table BORROW_MONEY(Money text,Name text,BORROW_Date text,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);");
        db.execSQL("create table Shopping(List text,SHOPPING_Date text,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);");
        db.execSQL("create table Bp(Bp_reading text,BP_Time text,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);");




    }

    // our own method
    public void insert(String T_names,String C_names)
    {
        SQLiteDatabase db = getWritableDatabase();// getting write permission
        ContentValues values = new ContentValues();// a container use to store set of values
        values.put(Table_field_1, T_names);
        values.put(Table_field_2, C_names);
        db.insert(Table_name, null, values);//to put values in db
    }


    public void Bp_insert(String reading,String Time)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BP_COL_1,reading);
        values.put(BP_COL_2, Time);
        db.insert(Table_name_3,null,values);

    }
    public void Bp_delete(int position)
    {
        String ids = Integer.toString(position);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor temp = retrieve_Bp();
        temp.move(position);
        String tempid = temp.getString(2);
        db.execSQL("DELETE FROM " + Table_name_3 + " WHERE " + "id" + "='" + ids + "'");
        db.close();


    }

    public  void GPS_insert(String longitude,String latitude,String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GPS_COL_1,longitude);
        values.put(GPS_COL_2, latitude);
        values.put(GPS_COL_3, date);
        db.insert(Table_name_1, null, values);

    }

    public  void Borrow_Money_insert(String money, String name,String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BORROW_COL_1,money);
        values.put(BORROW_COL_2, name);
        values.put(BORROW_COL_3, date);
        db.insert(Table_name_2,null,values);
    }
    public  void Shopping_insert(String list,String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SHOPPING_COL_1,list);
        values.put(SHOPPING_COL_2, date);

        db.insert(Table_name_4,null,values);
    }



    public Cursor retrieve(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Courses;",null);///This interface provides random read-write access to the result set returned by a database query.
        return cursor;
    }
    public Cursor retrieve_Gps()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from GPS;",null);
        return  c;
    }
    public Cursor retrieve_Borrow()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from BORROW_MONEY;",null);
        return  c;
    }
    public Cursor retrieve_Bp()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from BP;",null);
        return  c;
    }
    public Cursor retrieve_Shopping()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from SHOPPING;",null);
        return  c;
    }



    public void Edit_Shopping(String list,String date,int position)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SHOPPING_COL_1,list);
        values.put(SHOPPING_COL_2, date);
        db.update(Table_name_4,values,"id ="+position,null);

    }
    public void Edit_Gps(String longitude,String latitude,String date, int position)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GPS_COL_1,longitude);
        values.put(GPS_COL_2, latitude);
        values.put(GPS_COL_3, date);
        db.update(Table_name_1,values,"id ="+position,null);
    }
    public void Edit_Borrow(String name,String money,String date, int position)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BORROW_COL_1,money);
        values.put(BORROW_COL_2, name);
        values.put(BORROW_COL_3,date);
        db.update(Table_name_2,values,"id ="+position,null);
    }
    public void Edit_Bp(String reading,String date, int position)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BP_COL_1,reading);
        values.put(BP_COL_2,date);
        db.update(Table_name_3,values,"id ="+position,null);
    }

    public void Shopping_delete(int position)
    {
  /*      String ids = Integer.toString(position);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor temp = retrieve_Bp();
        temp.move(position);
        String tempid = temp.getString(2);
        db.execSQL("DELETE FROM " + Table_name_3 + " WHERE " + "id" + "='" + ids + "'");
        db.close();
        */
    }

    public void Gps_delete(int position)
    {
        /*
        String ids = Integer.toString(position);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor temp = retrieve_Bp();
        temp.move(position);
        String tempid = temp.getString(2);
        db.execSQL("DELETE FROM " + Table_name_3 + " WHERE " + "id" + "='" + ids + "'");
        db.close();
        */


    }
    public void Borrow_delete(int position)
    {
        /*
        String ids = Integer.toString(position);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor temp = retrieve_Bp();
        temp.move(position);
        String tempid = temp.getString(2);
        db.execSQL("DELETE FROM " + Table_name_3 + " WHERE " + "id" + "='" + ids + "'");
        db.close();
        */


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
