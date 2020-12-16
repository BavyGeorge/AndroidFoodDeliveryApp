package com.bavy.android_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bavy.android_assignment_1.DataModel.Orderinfo;

public class deliverytime extends AppCompatActivity {

    int getid,theuserid;
    String time1,time2,time3,time4,times;
    long timing;
    String selectedcard,number2,CARDSELECTED;
    ImageView imageView1;
    public static final String SHAREDPREFDATADELIVERYTIME = "SHAREDPREFDATADELIVERYTIME";
    public static final String DELIVERYTIMESAVEOBJECT = "DELIVERYTIMESAVEOBJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverytime);
        Toolbar toolbar = findViewById(R.id.toolbar);
        imageView1 = findViewById(R.id.imageviewdeliverytiming);
        setSupportActionBar(toolbar);
//        loadprefdata();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_deliverytiming,menu);
        return true;
    }

    public void selectordertime(View view){
        Orderinfo orderinfo = new Orderinfo();
        getid=view.getId();

        switch (getid){
            case R.id.radiotime1:
                time1 = "11:45 - 12:15";
                orderinfo.setTiming(time1);
                imageView1.setImageResource(R.drawable.time1145);
                break;
            case R.id.radiotime2:
                time2 = "12:15 - 12:45";
                orderinfo.setTiming(time2);
                imageView1.setImageResource(R.drawable.time1215);
                break;
            case R.id.radiotime3:
                time3 = "12:45 - 13:15";
                orderinfo.setTiming(time3);
                imageView1.setImageResource(R.drawable.time1245);
                break;
            case R.id.radiotime4:
                time4 = "13:15 - 13:45";
                orderinfo.setTiming(time4);
                imageView1.setImageResource(R.drawable.time115);
                break;
        }
        times=time1+time2+time3+time4;
        savedeliverytimedata();

    }

    public void savedeliverytimedata(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFDATADELIVERYTIME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DELIVERYTIMESAVEOBJECT,times);
        editor.apply();
    }

    public void gotonextactivity3(View view) {
        Intent intent1 = new Intent(this,address_listactivity.class);
        startActivity(intent1);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){

//            case R.id.item41:{
//                Intent intent1 = new Intent(this,creditcard_list.class);
//                startActivity(intent1);
//                break;
//            }

            case R.id.item42:{
                Intent intent1 = new Intent(this,Primaryscreen.class);
                startActivity(intent1);
                break;
            }

            case R.id.item43:{
                Intent intent1 = new Intent(this,address_listactivity.class);
                startActivity(intent1);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
