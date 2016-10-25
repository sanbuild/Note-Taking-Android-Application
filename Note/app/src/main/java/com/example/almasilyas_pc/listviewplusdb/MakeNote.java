package com.example.almasilyas_pc.listviewplusdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MakeNote extends AppCompatActivity implements View.OnClickListener {
    // this class just inserting data into db

    EditText title_edit_text,note_edit_text;
    Button save_btn,back_btn;
    Db db;// making instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_note);
        title_edit_text = (EditText)findViewById(R.id.title);
        note_edit_text=(EditText)findViewById(R.id.note);
        save_btn=(Button)findViewById(R.id.save);
        back_btn=(Button)findViewById(R.id.back);
        db=new Db(getApplicationContext(),"abc",null,1);// db constructor
        save_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // put data into db
        if(v.getId()==R.id.save) {
            String data1 = title_edit_text.getText().toString();
            String data2 = note_edit_text.getText().toString();
            db.insert(data1, data2); // calling function 4rm db class
        }

        // back to main activity
        else if(v.getId()==R.id.back)
        {
            finish();
        }

    }
}
