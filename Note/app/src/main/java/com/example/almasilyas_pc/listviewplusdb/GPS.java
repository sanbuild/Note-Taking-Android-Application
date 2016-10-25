package com.example.almasilyas_pc.listviewplusdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GPS extends AppCompatActivity implements View.OnClickListener {
    EditText longitude,latitude,date;
    Button save_btn;
    Db db;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        latitude=(EditText)findViewById(R.id.gps_latitude);
        longitude=(EditText)findViewById(R.id.gps_longitude);
        date=(EditText)findViewById(R.id.gps_date);
        save_btn=(Button)findViewById(R.id.gps_save_btn);
        save_btn.setOnClickListener(this);
        db=new Db(getApplicationContext(),"abc",null,1);
        i = getIntent();
        if(i.getExtras() != null) { // check if ye activity indert ky liye to nai call ho rhi, cuz wahan sy to hum intent ky ziriye kuch nai bhej rhy
            boolean flag = i.getBooleanExtra("edit", true);
            if (!flag) // Yar yr ye to new activity bn rhi hy. Wo fragment to back end py chla gya hy us y ap access nai kr skti.
            {
                latitude.setText(i.getStringExtra("latitude"));
                longitude.setText(i.getStringExtra("longitude"));
                date.setText(i.getStringExtra("date"));
            }
        }

    }

    @Override
    public void onClick(View v) {
        String data_1 = longitude.getText().toString();
        String data_2 =  latitude.getText().toString();
        String data_3 = date.getText().toString();
        if(i.getExtras() != null) { // check if ye activity indert ky liye to nai call ho rhi, cuz wahan sy to hum intent ky ziriye kuch nai bhej rhy
            boolean flag = i.getBooleanExtra("edit", true);
            if (!flag) // agar value true mily to if main true ajae ga or if condition true ho jae gi or false ae ga to if nai chly gi
            {
                db.Edit_Gps(data_1,data_2,data_3,i.getIntExtra("position",0));
                Intent i = new Intent();
                setResult(1,i);
                finish();
                // Edit Querry
            }
        }
        else
        db.GPS_insert(data_1,data_2,data_3);
        Toast.makeText(getApplicationContext(), "saved sucessfully", Toast.LENGTH_SHORT).show();

    }
}
