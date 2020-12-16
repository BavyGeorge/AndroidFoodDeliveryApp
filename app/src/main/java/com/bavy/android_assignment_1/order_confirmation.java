package com.bavy.android_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;


public class order_confirmation extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    Userinfo userinfo;
    UserViewModel viewModel;
    String  bavy;
    String locationn, orderss, dressingss, quantities, notess,timings;
    String  selectedaddress,addressidd,cardidd,requiredcard,requiredaddress;
    int error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selectedaddress = (String) getIntent().getSerializableExtra(login_screen.ADDRESSSELECTED);

        tv1 = findViewById(R.id.textviewconfirm2);
        tv2 = findViewById(R.id.textviewconfirm4);
        tv3 = findViewById(R.id.textviewconfirm5);
        tv4 = findViewById(R.id.textviewconfirm6);
        tv5 = findViewById(R.id.textviewconfirm7);
        tv6 = findViewById(R.id.textviewconfirm8);
        viewModel= new UserViewModel(getApplicationContext());
        error = 1;
        loaddatafororder();
        displaydata();
    }

    public void loaddata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES,MODE_PRIVATE);
        requiredcard=sharedPreferences.getString(login_screen.CARDSELECT,"");

        cardidd = sharedPreferences.getString(creditcard_list.CARDID,"");
    }

    public void loaddata1(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        requiredaddress=sharedPreferences.getString(login_screen.ADDRESSSELECTED,"");
        addressidd = sharedPreferences.getString(address_listactivity.ADDRESSID,"");
    }

    public void loaddatafororder(){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.LOCATIONSHAREDPREF,MODE_PRIVATE);
        SharedPreferences sharedPreferences1= getSharedPreferences(OrderScreen.SHAREDPREFDATAORDERSCREEN,MODE_PRIVATE);
        SharedPreferences sharedPreferences3= getSharedPreferences(deliverytime.SHAREDPREFDATADELIVERYTIME,MODE_PRIVATE);
        locationn=sharedPreferences.getString(MainActivity.LOCATIONDATA,"");
        orderss=sharedPreferences1.getString(OrderScreen.ORDERSCREENSAVEOBJECT,"");
        dressingss=sharedPreferences1.getString(OrderScreen.DRESSINGNEEDED,"");
        quantities=sharedPreferences1.getString(OrderScreen.QUANTITYNEEDED,"");
        notess=sharedPreferences1.getString(OrderScreen.ADDNOTEOBJECT,"");
        timings=sharedPreferences3.getString(deliverytime.DELIVERYTIMESAVEOBJECT,"");
    }

        public void displaydata(){
        String str ="null";
            locationn=locationn.replaceAll(str,"");
            orderss = orderss.replaceAll(str,"");
            dressingss = dressingss.replaceAll(str,"");
            quantities = quantities.replaceAll(str,"");
            notess = notess.replaceAll(str,"");
            timings=timings.replaceAll(str,"");
            loaddata();
            loaddata1();

        tv1.setText("Welcome "+"\n");
        tv2.setText("Delivery Location : "+locationn);
        tv3.setText("The quantity needed is: "+quantities+"\n Your orders are: "+orderss+"\n Dressing type: "+dressingss+"\n Your note: "+notess+"\n");
        tv4.setText("The card number is : "+requiredcard+"\n");
        tv5.setText("The delivery time is:  "+timings+"\n");
        tv6.setText("The selected address is :  "+requiredaddress);
        validation();
    }

    public void validation(){
        try{
            if(notess.isEmpty()){
                notess="notess";
            }
            else{
                notess=notess;
            }
        }catch (Exception e){
            notess ="notess";
        }
    }

    public void saveindatabase(View view){
        Orderinfo orderinfo = new Orderinfo();
        SharedPreferences sharedPreferences5=getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        int s1;
        s1=sharedPreferences5.getInt(login_screen.GETUSERID,0);
        orderinfo.setuID(s1);
        DatabaseFunctionality.ORDERID++;
        orderinfo.setOid((int) DatabaseFunctionality.ORDERID);
        orderinfo.setOrder(orderss);
        orderinfo.setDressing(dressingss);
        orderinfo.setDlocations(locationn);
        orderinfo.setQuantityneed(quantities);
        orderinfo.setSelectedcard(requiredcard);
        orderinfo.setcID(Integer.valueOf(cardidd));
        orderinfo.setAddress_id_(Integer.valueOf(addressidd));
        orderinfo.setOrdernote(notess);
        orderinfo.setTiming(timings);
        orderinfo.setSelectedaddress(requiredaddress);
        viewModel.addorders(orderinfo);
        completed();
    }

    public void cancelsave(View view) {
        Intent intent= new Intent(getApplicationContext(),Primaryscreen.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Order discarded",Toast.LENGTH_LONG).show();
    }

    public void completed(){
        Intent intent = new Intent(getApplicationContext(),Primaryscreen.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Order successfully saved in Database",Toast.LENGTH_LONG).show();
    }

//    public class Insertdatainda extends AsyncTask<Orderinfo,Void,Void>{
//
//        @Override
//        protected Void doInBackground(Orderinfo... orderinfos) {
//            link = login_screen.myDao.InsertOther(orderinfos[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            if(link==-1)
//            {
//            Toast.makeText(getApplicationContext(),"Insertion failed",Toast.LENGTH_LONG).show();}
//            else
//                {
//            Toast.makeText(getApplicationContext(),"Insertion successful ID is :"+link,Toast.LENGTH_LONG).show();
//                }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_orderconfirmation,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.item51:{
                Intent intent1 = new Intent(this,Primaryscreen.class);
                startActivity(intent1);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
