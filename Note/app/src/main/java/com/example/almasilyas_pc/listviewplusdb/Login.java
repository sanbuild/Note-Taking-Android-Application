package com.example.almasilyas_pc.listviewplusdb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button login;
    Button create;
    Firebase myFirebaseRef;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        sp = getSharedPreferences("id",0);
        myFirebaseRef = new Firebase("https://logbook-enigma.firebaseio.com/");
        username = (EditText) findViewById(R.id.loginusername);
        spe = sp.edit();
        password = (EditText) findViewById(R.id.loginpassword);
        login = (Button) findViewById(R.id.login);
        create = (Button)findViewById(R.id.createaccount);
        login.setOnClickListener(this);
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.createaccount)
        {
            startActivity(new Intent(getApplicationContext(),CreateAccount.class));
        }
        else {
            String name = username.getText().toString();
            String pass = password.getText().toString();
            if (!name.isEmpty() && !pass.isEmpty()) {
                myFirebaseRef.authWithPassword(name, pass, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData)
                    {
                        spe.putString("id", authData.getUid());
                        spe.commit();
                        Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        setResult(1,i);
                        finish();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                    }
                });
            }
        }
    }
}

