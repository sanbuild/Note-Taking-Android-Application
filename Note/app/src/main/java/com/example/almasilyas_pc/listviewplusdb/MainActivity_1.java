package com.example.almasilyas_pc.listviewplusdb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity_1 extends AppCompatActivity implements View.OnClickListener {

    Button bp_btn,shopping_btn,gps_btn,borrow_money_btn;
    private Toolbar toolbar;
    TextView head;
    SharedPreferences sp;
    Firebase myFirebaseRef;
    Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_1);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        db=new Db(getApplicationContext(),"abc",null,1);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://logbook-enigma.firebaseio.com/");
        sp = getSharedPreferences("id",0);
        head = (TextView)findViewById(R.id.none);
        if(sp.getString("id","") != "")
        {
            head.setText("Logged In");
        }
        bp_btn=(Button)findViewById(R.id.bp);
        shopping_btn=(Button)findViewById(R.id.shopping);
        gps_btn=(Button)findViewById(R.id.gps);
        borrow_money_btn=(Button)findViewById(R.id.borrow_money);

        bp_btn.setOnClickListener(this);
        shopping_btn.setOnClickListener(this);
        gps_btn.setOnClickListener(this);
        borrow_money_btn.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String uid = sp.getString("id", "");
        //noinspection SimplifiableIfStatement
        int count = 3;
        if (id == R.id.action_login) {
            startActivityForResult(new Intent(getApplicationContext(), Login.class), 1);
            return true;
        }
        if(id == R.id.action_retrieve)
        {
            final String iid = sp.getString("id", "");
            myFirebaseRef.child("user").child(uid).child("count").setValue(count);
            count = 1;
            myFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    ArrayList<String> gpslongitude = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("gps").child("longitude").getValue();
                    ArrayList<String> gpslatitude = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("gps").child("latitude").getValue();
                    ArrayList<String> gpsGps_date = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("gps").child("gps_date").getValue();

                    ArrayList<String> BorrowMoney = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("borrow").child("money").getValue();
                    ArrayList<String> Borrowname = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("borrow").child("name").getValue();
                    ArrayList<String> BorrowDate = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("borrow").child("borrow_date").getValue();

                    ArrayList<String> Shopping_list = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("shopping").child("shopping_list").getValue();
                    ArrayList<String> Shopping_date = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("shopping").child("shopping_date").getValue();

                    ArrayList<String> Bp_reading = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("bp").child("bp_reading").getValue();
                    ArrayList<String> Bp_time = (ArrayList<String>) dataSnapshot.child("user").child(iid).child("bp").child("bp_time").getValue();

                    for(int i=0; i<gpslongitude.size(); i++)
                    {
                        db.GPS_insert(gpslongitude.get(i),gpslatitude.get(i),gpsGps_date.get(i));
                    }
                    for(int j=0; j<Shopping_list.size(); j++)
                    {
                        db.Shopping_insert(Shopping_list.get(j),Shopping_date.get(j));
                    }
                    for(int k=0; k<Bp_reading.size(); k++)
                    {
                        db.Bp_insert(Bp_reading.get(k),Bp_time.get(k));
                    }
                    for(int l=0; l<BorrowMoney.size(); l++)
                    {
                        db.Borrow_Money_insert(BorrowMoney.get(l),Borrowname.get(l),BorrowDate.get(l));
                    }


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            return true;
        }
        if(id == R.id.action_sync) {
            uid = sp.getString("id", "");
            if (uid != "") {
                myFirebaseRef.child("user").child(uid);
                Cursor c = db.retrieve_Shopping();
                Cursor c1 = db.retrieve_Borrow();
                Cursor c2 = db.retrieve_Bp();
                Cursor c3 = db.retrieve_Gps();

                GPSPOJO pojog = new GPSPOJO(c3);
                ShoppingPOJO pojos = new ShoppingPOJO(c);
                BorrowPOJO pojob = new BorrowPOJO(c1);
                BPPOJO pojop = new BPPOJO(c2);

                myFirebaseRef.child("user").child(uid).child("gps").setValue(pojog);
                myFirebaseRef.child("user").child(uid).child("shopping").setValue(pojos);
                myFirebaseRef.child("user").child(uid).child("borrow").setValue(pojob);
                myFirebaseRef.child("user").child(uid).child("bp").setValue(pojop);

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onClick(View v)
    {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("button_id",v.getId());
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == resultCode)
        {
            if(sp.getString("id","") != "")
            {
                head.setText("Logged In");
            }
        }
    }
}
