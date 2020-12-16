package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.bavy.android_assignment_1.DataModel.Userinfo;

public class UserInfoUpdate extends Menuhandler {
    Userinfo userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userinfo= (Userinfo) getIntent().getSerializableExtra(login_screen.USEROBJECT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_userinfoupdate,menu);
        return true;
    }

    public void userinformation(View view) {
        Intent intent = new Intent(getApplicationContext(),UserInformationActivity.class);
        intent.putExtra(login_screen.USEROBJECT,userinfo);
        startActivity(intent);
    }

    public void ceditcardinformation(View view) {
        Intent intent = new Intent(getApplicationContext(),creditcard_list.class);
        startActivity(intent);
    }

    public void deliveryinformation(View view) {
        Intent intent = new Intent(getApplicationContext(),address_listactivity.class);
        startActivity(intent);
    }

    public void cancelbuttonupdatescreen(View view) {
        finish();
    }
}
