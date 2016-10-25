package com.example.almasilyas_pc.listviewplusdb;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Almas Ilyas-PC on 1/27/2016.
 */


public class custom_adapter extends BaseAdapter {
    // retrieving data from db
    //putting it into array list
    // then array list to text views(my layout)
    // populate data into mylaout( list and grid view in main activity)


   // Variables bs bahir bnty hain unka object constructor main ya kisi method main bnana chahiye. kr ap thk  rhi thi lekin not good practice
    TextView tv1,tv2,tv3,tv4,tv5;
    Context context;
    Db db;
    Cursor cursor;
    ArrayList subjects;
    ArrayList teachers;
    ArrayList Bp_reading;
    ArrayList Bp_time;
    ArrayList Shopping_list;
    ArrayList Shopping_date;
    ArrayList longitude;
    ArrayList latitude;
    ArrayList Gps_date;
    ArrayList money;
    ArrayList name;
    ArrayList Borrow_date;

    public void populateDatatoArrayShopping()
    {
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            Shopping_list.add(i,cursor.getString(0));
            Shopping_date.add(i,cursor.getString(1));
            cursor.moveToNext();
        }

    }
    public void populateDatatoArrayBp()
    {
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            Bp_reading.add(i,cursor.getString(0));
            Bp_time.add(i,cursor.getString(1));
            cursor.moveToNext();
        }
    }

    public  void  populateDatatoArrayGPS()
    {
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            longitude.add(i,cursor.getString(0));
            latitude.add(i,cursor.getString(1));
            Gps_date.add(i,cursor.getString(2));
            cursor.moveToNext();
        }
    }

    public  void  populateDatatoArrayBorrow()
    {
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++) {

            money.add(i,cursor.getString(0));
            name.add(i,cursor.getString(1));
           Borrow_date.add(i,cursor.getString(2));
            cursor.moveToNext();
        }
    }




    public void populateDatatoArray() {
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++)
        {
            subjects.add(i,cursor.getString(0));// 0 is for (first column of table and 1 for second column of the table
            teachers.add(i,cursor.getString(1));
            cursor.moveToNext(); // good. humien bs data chahiye array main phenkny ky liye. CURSOR return kiuho rha hy? ya method khudi kr raha ha ma nai kya
        }

    }

   // String []subjects={"Programming","OOP","DS","OOAD","Numerical Analysis","Calculs","Linear ALgebra"};
    //String []teachers={"Aftab Alam","Dr Fakhar Lodhi","Dr Fakhar Lodhi","Zeyad Amin","Zulfiqar Ahmed","Anjum Iqbal","Irfan Iqbal"};
    int id;
    public custom_adapter(Context c,int i)
    {
        context=c;// db needs context
        db=new Db(context,"abc",null,1); // ye kam constructor main krna hy
        subjects =new ArrayList<String>(); // object creation bahir nai krty.
        teachers= new ArrayList<String>();
        Bp_reading = new ArrayList<String>();
        Bp_time = new ArrayList<String>();
        Shopping_list = new ArrayList<String>();
        Shopping_date = new ArrayList<String>();
        longitude = new ArrayList<String>();
        latitude = new ArrayList<String>();
        Gps_date = new ArrayList<String>();
        money = new ArrayList<String>();
        name = new ArrayList<String>();
        Borrow_date = new ArrayList<String>();
        id =i;///


        if(id == R.id.bp) {
            cursor = db.retrieve_Bp(); // koi bhi function call method sy bahir nai krty. Just Variable bahir bnaty hain
            populateDatatoArrayBp();
        }
        else if(id == R.id.shopping)
        {
            cursor = db.retrieve_Shopping();
            populateDatatoArrayShopping();
        }

        else if( id == R.id.gps)
        {
            cursor = db.retrieve_Gps();
            populateDatatoArrayGPS();
        }
        else if(id == R.id.borrow_money)
        {
            cursor = db.retrieve_Borrow();
            populateDatatoArrayBorrow();
        }

    }
    @Override
    public int getCount() {
        return cursor.getCount();
    }

    // Array list humien Object return krwa rhi hy. An get Iteam ki return type bhi Object hy. Jbky getTeacher ki return type Strng hy.So humei ntype caste krna par rha hy
    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }
    public String getTeachers(int position) {
        return (String)teachers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int tv1_text_size = 0;
        int tv2_text_size=0;
        int tv3_text_size=0;
        int tv4_text_size=0;
        int tv5_text_size=0;


        if (id == R.id.bp)
        {
            String tv1_text=Bp_reading.get(position).toString();//getting text from an array
            String tv2_text=Bp_time.get(position).toString();
             tv1_text_size=tv1_text.length();// getting length
             tv2_text_size=tv2_text.length();

        }
        else if (id == R.id.shopping)
        {
            String tv1_text= Shopping_list.get(position).toString();//getting text from an array
            String tv2_text=Shopping_date.get(position).toString();
            tv1_text_size=tv1_text.length();// getting length
            tv2_text_size=tv2_text.length();

        }

        else if( id == R.id.gps)
        {
            String tv3_text = longitude.get(position).toString();
            String tv4_text = latitude.get(position).toString();
            String tv5_text = Gps_date.get(position).toString();
            tv3_text_size = tv3_text.length();
            tv4_text_size = tv4_text.length();
            tv5_text_size = tv5_text.length();

        }

        else  if (id == R.id.borrow_money)
        {
            String tv3_text = money.get(position).toString();
            String tv4_text = name.get(position).toString();
            String tv5_text = Borrow_date.get(position).toString();
            tv3_text_size = tv3_text.length();
            tv4_text_size = tv4_text.length();
            tv5_text_size = tv5_text.length();

        }

        if(convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            if(id == R.id.bp || id == R.id.shopping) {
                convertView = (LinearLayout) layoutInflater.inflate(R.layout.mylayout, null);
                tv1 = (TextView) convertView.findViewById(R.id.textView);
                tv2 = (TextView) convertView.findViewById(R.id.textView2);
                ViewGroup.LayoutParams parms=tv1.getLayoutParams();
                ViewGroup.LayoutParams params2=tv2.getLayoutParams();
                parms.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tv1.setLayoutParams(parms);
                params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tv2.setLayoutParams(params2);
            }else {
                // convertvi ki value change ho jati hy // agar else run hoti tb bhi wo tv1 and tv2 wala code chalna tha jb ky unka findviewbyid if waly ky andar ho rha hy
                convertView = (LinearLayout) layoutInflater.inflate(R.layout.my_layout_2, null);
                tv3 = (TextView) convertView.findViewById(R.id.tv1_layout2);
                tv4 = (TextView) convertView.findViewById(R.id.tv2_layout2);
                tv5 = (TextView) convertView.findViewById(R.id.tv3_layout2);

            }
            // ik hi convert view main 5 text view to nai hain na jo apny nichy kiye hove hain
            //Now we are using if statemetns of two and tree views

              // bas ya kya ha

            if (id == R.id.bp)
            {
                if (tv1_text_size < 5 || tv2_text_size < 5) {
                    tv1.setTextSize(50);
                    tv2.setTextSize(50);


                    tv1.setText(Bp_reading.get(position).toString());
                    tv2.setText(Bp_time.get(position).toString());
                }
                else
                {


                    tv1.setText(Bp_reading.get(position).toString());
                    tv2.setText(Bp_time.get(position).toString()); // jb text bht zyada ho jae ga tb humien jaga bhi zyada cchahiye

                }

            }

            else if (id == R.id.shopping)
            {
                if (tv1_text_size < 5 || tv2_text_size < 5) {
                    tv1.setTextSize(50);
                    tv2.setTextSize(50);


                    tv1.setText(Shopping_list.get(position).toString());
                    tv2.setText(Shopping_date.get(position).toString());
                }
                else
                {


                    tv1.setText(Shopping_list.get(position).toString());
                    tv2.setText(Shopping_date.get(position).toString()); // jb text bht zyada ho jae ga tb humien jaga bhi zyada cchahiye

                }

            }

            else if( id == R.id.gps)
            {
                if (tv3_text_size < 5 || tv4_text_size <5 || tv5_text_size < 5)
                {
                    tv3.setTextSize(50);
                    tv4.setTextSize(50);
                    tv5.setTextSize(50);

                    tv3.setText(longitude.get(position).toString());
                    tv4.setText(latitude.get(position).toString());
                    tv5.setText(Gps_date.get(position).toString());

                }
                else
                {
                    tv3.setText(longitude.get(position).toString());
                    tv4.setText(latitude.get(position).toString());
                    tv5.setText(Gps_date.get(position).toString());

                }
            }
            else if (id == R.id.borrow_money)
            {
                if (tv3_text_size < 5 || tv4_text_size <5 || tv5_text_size < 5)
                {
                    tv3.setTextSize(50);
                    tv4.setTextSize(50);
                    tv5.setTextSize(50);

                    tv3.setText(money.get(position).toString());
                    tv4.setText(name.get(position).toString());
                    tv5.setText(Borrow_date.get(position).toString());

                }
                else
                {
                    tv3.setText(money.get(position).toString());
                    tv4.setText(name.get(position).toString());
                    tv5.setText(Borrow_date.get(position).toString());

                }
            }

        }
        else
        {




            if(id == R.id.bp || id == R.id.shopping) {
                tv1 = (TextView) convertView.findViewById(R.id.textView);
                tv2 = (TextView) convertView.findViewById(R.id.textView2);
                ViewGroup.LayoutParams parms=tv1.getLayoutParams();
                ViewGroup.LayoutParams params2=tv2.getLayoutParams();
                parms.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tv1.setLayoutParams(parms);
                params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tv2.setLayoutParams(params2);
            }
            else {
                tv3 = (TextView) convertView.findViewById(R.id.tv1_layout2);
                tv4 = (TextView) convertView.findViewById(R.id.tv2_layout2);
                tv5 = (TextView) convertView.findViewById(R.id.tv3_layout2);
            }
            // aisay ? yeah:-p// Cuz ik convert view main 5 textviewn nai ho skty ik time py

            if (id == R.id.bp)
            {
                if (tv1_text_size < 5 || tv2_text_size < 5) {
                    tv1.setTextSize(50);
                    tv2.setTextSize(50);


                    tv1.setText(Bp_reading.get(position).toString());
                    tv2.setText(Bp_time.get(position).toString());
                }
                else
                {


                    tv1.setText(Bp_reading.get(position).toString());
                    tv2.setText(Bp_time.get(position).toString()); // jb text bht zyada ho jae ga tb humien jaga bhi zyada cchahiye

                }

            }

           else if (id == R.id.shopping)
            {
                if (tv1_text_size < 5 || tv2_text_size < 5) {
                    tv1.setTextSize(50);
                    tv2.setTextSize(50);


                    tv1.setText(Shopping_list.get(position).toString());
                    tv2.setText(Shopping_date.get(position).toString());
                }
                else
                {


                    tv1.setText(Shopping_list.get(position).toString());
                    tv2.setText(Shopping_date.get(position).toString()); // jb text bht zyada ho jae ga tb humien jaga bhi zyada cchahiye

                }

            }

            else if( id == R.id.gps)
            {
                if (tv3_text_size < 5 || tv4_text_size <5 || tv5_text_size < 5)
                {
                    tv3.setTextSize(50);
                    tv4.setTextSize(50);
                    tv5.setTextSize(50);

                    tv3.setText(longitude.get(position).toString());
                    tv4.setText(latitude.get(position).toString());
                    tv5.setText(Gps_date.get(position).toString());

                }
                else
                {
                    tv3.setText(longitude.get(position).toString());
                    tv4.setText(latitude.get(position).toString());
                    tv5.setText(Gps_date.get(position).toString());

                }
            }
            else if (id == R.id.borrow_money)
            {
                if (tv3_text_size < 5 || tv4_text_size <5 || tv5_text_size < 5)
                {
                    tv3.setTextSize(50);
                    tv4.setTextSize(50);
                    tv5.setTextSize(50);

                    tv3.setText(money.get(position).toString());
                    tv4.setText(name.get(position).toString());
                    tv5.setText(Borrow_date.get(position).toString());

                }
                else
                {
                    tv3.setText(money.get(position).toString());
                    tv4.setText(name.get(position).toString());
                    tv5.setText(Borrow_date.get(position).toString());

                }
            }







        }


        return convertView;    }
}
