package com.example.almasilyas_pc.listviewplusdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button Create;
    Firebase myFirebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://logbook-enigma.firebaseio.com/");
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Create = (Button)findViewById(R.id.create);
        Create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = username.getText().toString();
        String pass = password.getText().toString();
        if(!name.isEmpty() && !pass.isEmpty())
        {
            myFirebaseRef.createUser(name, pass, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(),"Created",Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(FirebaseError firebaseError) {

                }
            });
        }
    }
}
