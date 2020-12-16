package com.bavy.android_assignment_1;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.RemoteDBHandler;
import com.bavy.android_assignment_1.DataModel.RoomDBHandler;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

import static com.bavy.android_assignment_1.login_screen.USEROBJECT;

//Extending from Menuhandler.java class
public class MainActivity extends Menuhandler {

//    public static UserRecyclerViewAdapter adapter;
    RecyclerView userRecyclerView;
    TextView tv;
    String st,locations,location1,location2,location3,location4,location;
    int location_id,theuserid;
    RadioButton rd1,rd2,rd3,rd4;
    long savestuff=-1;
    Userinfo userinfo;
    ImageView imageView;


    public static final String LOCATIONSHAREDPREF = "LOCATIONSHAREDPREF";
    public static final String LOCATIONDATA ="LOCATIONDATA";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview1main);
        rd1 = findViewById(R.id.radio1);
        rd2 = findViewById(R.id.radio2);
        rd3 = findViewById(R.id.radio3);
        rd4 = findViewById(R.id.radio4);
        imageView=findViewById(R.id.imageViewmainactivity);
        userinfo=(Userinfo) getIntent().getSerializableExtra(USEROBJECT);
        tv.setText("WELCOME TO THE TUCKBOX HOMEPAGEEEE");
      Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        loadprefdata();
    }

    public void setlocations(View view){
        location_id = view.getId();
//        Orderinfo orderinfo = new Orderinfo();

        switch (location_id){
            case R.id.radio1:
                location = "Palmerston North";
//                orderinfo.setDlocations(locations);
                imageView.setImageResource(R.drawable.palmerstonnorth);
                break;
            case R.id.radio2:
                location = "Fielding";
//                orderinfo.setDlocations(locations);
                imageView.setImageResource(R.drawable.fielding);
                break;
            case R.id.radio3:
                location ="Ashhurst";
//                orderinfo.setDlocations(locations);
                imageView.setImageResource(R.drawable.ashurst);
                break;
            case R.id.radio4:
                location ="Longburn";
//                orderinfo.setDlocations(locations);
                imageView.setImageResource(R.drawable.longburn);
                break;
        }
//        orderinfo.setUID(theuserid);
//        Savelocation savelocation= new Savelocation();
//        savelocation.execute(orderinfo);
        locations=location;
        savelocationdata();
    }


    public void savelocationdata(){
        SharedPreferences sharedPreferences = getSharedPreferences(LOCATIONSHAREDPREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOCATIONDATA,locations);
        editor.apply();
    }

    public void gotonextactivity(View view) {
        Intent intent2= new Intent(this,OrderScreen.class);
        startActivity(intent2);
    }

//    public class Savelocation extends AsyncTask <Orderinfo,Void,Void>{
//
//    @Override
//    protected Void doInBackground(Orderinfo... otherinfos) {
//        savestuff = login_screen.myDao.InsertOther(otherinfos[0]);
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//
//        super.onPostExecute(aVoid);
////        Toast.makeText(getApplicationContext(),"The location ID is :"+savestuff,Toast.LENGTH_LONG).show();
//    }
//}

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id=item.getItemId();
//        switch (id){
//
//            case R.id.item12:{
//                Intent intent1 = new Intent(this,UserInformationActivity.class);
//                intent1.putExtra(login_screen.USEROBJECT,userinfo);
//                startActivity(intent1);
//                break;
//            }
//
//            case R.id.item13:{
//                Intent intent2= new Intent(this,OrderScreen.class);
//                intent2.putExtra(login_screen.OTHEROBJECT,orderinfo);
//                startActivity(intent2);
//                break;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_activitymain,menu);
        return true;
    }

//    public void loadprefdata(){
//        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
//        theuserid = sharedPreferences.getInt(login_screen.GETUSERID,0);
//    }

}

