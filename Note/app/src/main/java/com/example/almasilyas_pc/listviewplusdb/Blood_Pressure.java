package com.example.almasilyas_pc.listviewplusdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Blood_Pressure extends AppCompatActivity implements View.OnClickListener {

    EditText bp,time;
    Button save;
    Db db;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood__pressure);
        bp=(EditText)findViewById(R.id.bp_reading);
        time=(EditText)findViewById(R.id.BP_time);
        save=(Button)findViewById(R.id.BP_save_btn);
        save.setOnClickListener(this);
        db=new Db(getApplicationContext(),"abc",null,1);
        i = getIntent();
        if(i.getExtras() != null) { // check if ye activity indert ky liye to nai call ho rhi, cuz wahan sy to hum intent ky ziriye kuch nai bhej rhy
            boolean flag = i.getBooleanExtra("edit", true);
            if (!flag) // Yar yr ye to new activity bn rhi hy. Wo fragment to back end py chla gya hy us y ap access nai kr skti.
            {
                bp.setText(i.getStringExtra("bp"));
                time.setText(i.getStringExtra("time"));
            }
        }

    }

    @Override
    public void onClick(View v) {

        String data_1=bp.getText().toString();
        String data_2=time.getText().toString();
        if(i.getExtras() != null) { // check if ye activity indert ky liye to nai call ho rhi, cuz wahan sy to hum intent ky ziriye kuch nai bhej rhy
            boolean flag = i.getBooleanExtra("edit", true);
            if (!flag) // agar value true mily to if main true ajae ga or if condition true ho jae gi or false ae ga to if nai chly gi
            {
                db.Edit_Bp(data_1,data_2,i.getIntExtra("position",0));
                Intent i = new Intent();
                setResult(1,i);
                finish();
                // Edit Querry
            }
        }
        else
        db.Bp_insert(data_1,data_2);
        Toast.makeText(getApplicationContext(),"saved sucessfully",Toast.LENGTH_SHORT).show();

    }
}
