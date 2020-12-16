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

public class creditcard_list extends AppCompatActivity {

    RecyclerView cardlist;
    String selectedcard,cardid;
    public static final String CARDID = "CARDID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cardlist=findViewById(R.id.recyclerview1);
        cardlist.setLayoutManager(new LinearLayoutManager(this));
        cardlist.setAdapter(new creditcardRecyclerviewadapter());
        selectedcard=(String) getIntent().getSerializableExtra(login_screen.CARDSELECT);
        cardid = (String) getIntent().getSerializableExtra(Creditcardinformation.CARDIDOBJECT);
        runtrycatch();
    }

    public void runtrycatch(){
        try {
            if(selectedcard.length()==0){
                selectedcard="nothing";
            }
            else
            {
                selectedcard=selectedcard;
            }
        }catch (Exception e){
            selectedcard="nothing";
        }
    }

    public void savedata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(login_screen.CARDSELECT,selectedcard);
        editor.putString(CARDID,cardid);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Card successfully saved and selected",Toast.LENGTH_LONG).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_creditcardmenu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
//            case R.id.item31:{
//                Intent intent1 = new Intent(this,OrderScreen.class);
//                startActivity(intent1);
//                break;
//            }
            case R.id.item32:{
                Intent intent1 = new Intent(this,Primaryscreen.class);
                startActivity(intent1);
                break;
            }
            case R.id.item33:{
                Intent intent1 = new Intent(this,deliverytime.class);
                if(selectedcard.equals("nothing")){
                    Toast.makeText(getApplicationContext(),"Please select card by clicking the appropriate card and pressing select",Toast.LENGTH_LONG).show();
                }
                else{
                    savedata();
                startActivity(intent1);
                break;}
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void addnewcardinfo(View view) {
        Intent intent= new Intent(this,creditcard_registration.class);
        startActivity(intent);
    }

//    public void gotoaddresslist(View view) {
//        Intent intent12 = new Intent(this,address_listactivity.class);
//        startActivity(intent12);
//    }

    public void gotonextactivity2(View view) {
        Intent intent1 = new Intent(this,deliverytime.class);
        if(selectedcard.equals("nothing"))
        {
            Toast.makeText(getApplicationContext(),"Please select card by clicking the appropriate card and pressing select",Toast.LENGTH_LONG).show();
        }
        else{
            savedata();
            startActivity(intent1);
        }
    }
}
