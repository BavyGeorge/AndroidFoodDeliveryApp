package com.bavy.android_assignment_1;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;

public class Primaryscreen extends Menuhandler {
    Userinfo userinfo;
    Orderinfo orderinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primaryscreen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userinfo =(Userinfo) getIntent().getSerializableExtra(login_screen.USEROBJECT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_primaryscreen,menu);
        return true;
    }

    public void updateuserinfo(View view) {
        Intent intent1 = new Intent(this,UserInfoUpdate.class);
        intent1.putExtra(login_screen.USEROBJECT,userinfo);
        startActivity(intent1);
    }

    public void placeorder(View view) {
        Intent intent2= new Intent(this,MainActivity.class);
//        intent2.putExtra(login_screen.OTHEROBJECT,orderinfo);
        startActivity(intent2);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this,login_screen.class);
        startActivity(intent);
    }
}
