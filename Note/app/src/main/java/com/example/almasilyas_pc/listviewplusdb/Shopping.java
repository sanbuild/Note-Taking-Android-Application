package com.example.almasilyas_pc.listviewplusdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Shopping extends AppCompatActivity implements View.OnClickListener {

    EditText list,date;
    Button save;
    Db db;
    String get_tag;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        list=(EditText)findViewById(R.id.Shopping_list);
        date=(EditText)findViewById(R.id.Shopping_date);
        save=(Button)findViewById(R.id.shopping_save_btn);

        db=new Db(getApplicationContext(),"abc",null,1);

        Intent i = getIntent();
        if(i.getExtras() != null) { // check if ye activity indert ky liye to nai call ho rhi, cuz wahan sy to hum intent ky ziriye kuch nai bhej rhy
            boolean flag = i.getBooleanExtra("edit", true);
            if (!flag) // Yar yr ye to new activity bn rhi hy. Wo fragment to back end py chla gya hy us y ap access nai kr skti.
            {
                list.setText(i.getStringExtra("shopping_list"));
                date.setText(i.getStringExtra("shopping_date"));
            }
        }
        save.setOnClickListener(this);

    }

   /* public void TAGG(String t)
    {
        get_tag = t;
    }*/
    @Override
    public void onClick(View v) {

        String data_1= list.getText().toString();
        String data_2= date.getText().toString();
        if(i.getExtras() != null) { // check if ye activity indert ky liye to nai call ho rhi, cuz wahan sy to hum intent ky ziriye kuch nai bhej rhy
           boolean flag = i.getBooleanExtra("edit", true);
            if (!flag) // agar value true mily to if main true ajae ga or if condition true ho jae gi or false ae ga to if nai chly gi
            {
                db.Edit_Shopping(data_1,data_2,i.getIntExtra("position",0));
                Intent i = new Intent();
                setResult(1,i);
                finish();
            }
        }
        else
            db.Shopping_insert(data_1,data_2);
        Toast.makeText(getApplicationContext(), "saved sucessfully", Toast.LENGTH_SHORT).show();
    }
}
