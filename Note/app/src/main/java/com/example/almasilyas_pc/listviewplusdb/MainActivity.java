package com.example.almasilyas_pc.listviewplusdb;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // this class only display fragments
    // make_note button just calling new activity
    // list button switches b/w list view and grid view

    Button list,make_note;
    FragmentManager fm=getFragmentManager();
    FragmentTransaction ft;
    GridF g=new GridF();
    ListF l=new ListF();
    int count=0;
    int Button_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(Button)findViewById(R.id.list_btn);
        make_note=(Button)findViewById(R.id.make_note_btn);
        list.setOnClickListener(this);
        make_note.setOnClickListener(this);

        Intent i= getIntent();
         Button_id= i.getIntExtra("button_id",0);// getting btn id


       g=new GridF();
        g.setid(Button_id);
        l=new ListF();
        l.setId(Button_id);

        ft=fm.beginTransaction();
        ft.replace(R.id.frame,l);
        ft.commit();

    }

    @Override
    public void onClick(View v) {


        if(v.getId()==R.id.list_btn && count==0)
        {
            ft=fm.beginTransaction();
            ft.replace(R.id.frame,l);

            ft.commit();
            list.setText("=");
            count=1;


        }
        else if(v.getId()==R.id.list_btn && count==1)
        {

                ft = fm.beginTransaction();
                ft.replace(R.id.frame, g);
                ft.commit();
                 list.setText("||");
                count=0;


        }
        // Make note for gps,borrow money etc
        else if(v.getId()==R.id.make_note_btn)
        {
            if(Button_id == R.id.gps) {
                Intent intent = new Intent(getApplicationContext(), GPS.class);
                startActivity(intent);
            }
            else if(Button_id==R.id.borrow_money)
            {
                Intent intent = new Intent(getApplicationContext(), Borrow_Money.class);
                startActivity(intent);

            }
            else if(Button_id==R.id.bp)
            {
                Intent intent = new Intent(getApplicationContext(),Blood_Pressure.class);
                startActivity(intent);
            }
            else if(Button_id==R.id.shopping)
            {
                Intent intent = new Intent(getApplicationContext(), Shopping.class); // yahan sy to koi put extra nai ho rha . so is lye waha nwo condtion lga ihy
                startActivity(intent);
            }
        }


    }
}
