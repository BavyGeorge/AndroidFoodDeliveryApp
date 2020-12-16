package com.bavy.android_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;

public class address_listactivity extends AppCompatActivity {
    RecyclerView addresslist;
    Userinfo userinfo;
    Creditcardinfo creditcardinfo;
    Address address;
    Orderinfo orderinfo;
    public static final String ADDRESSID = "ADDRESSID";
    String selectedcard,selectedaddress,s2,addressid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_listactivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addresslist=findViewById(R.id.recyclerview2);
        addresslist.setLayoutManager(new LinearLayoutManager(this));
        addresslist.setAdapter(new address_recyclerviewadapter());
        selectedaddress= (String) getIntent().getSerializableExtra(login_screen.ADDRESSSELECTED);
        addressid = (String) getIntent().getSerializableExtra(addressinformation.ADDRESSIDOBJECT);
        s2="nukkk";
        runtrycatch();
    }

    public void runtrycatch(){
        try {
            if(selectedaddress.length()==0){
                selectedaddress="nothing";
            }
            else
            {
                selectedaddress=selectedaddress;
            }
        }catch (Exception e){
            selectedaddress="nothing";
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_addressmenu,menu);
        return true;

    }

    public void savedata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(login_screen.ADDRESSSELECTED,selectedaddress);
        editor.putString(ADDRESSID,addressid);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Address successfully saved and selected",Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.item61:{
                Intent intent1 = new Intent(this,Primaryscreen.class);
                startActivity(intent1);
                break;
            }
            case R.id.item62:{
                Intent intent1 = new Intent(this,order_confirmation.class);
                if(selectedaddress.equals("nothing")){
                    Toast.makeText(getApplicationContext(),"Please select address by clicking the appropriate card and pressing select",Toast.LENGTH_LONG).show();
                }
                else{
                    savedata();
                    startActivity(intent1);}
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void addnewaddress(View view) {
        Intent intent= new Intent(this,address_registration.class);
        startActivity(intent);
    }

    public void gotonextactcivity5(View view) {
        Intent intent1 = new Intent(this,order_confirmation.class);
        if(selectedaddress.equals("nothing")){
            Toast.makeText(getApplicationContext(),"Please select address by clicking the appropriate card and pressing select",Toast.LENGTH_LONG).show();
        }
        else{
            savedata();
        startActivity(intent1);}
    }
}
