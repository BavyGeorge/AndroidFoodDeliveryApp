package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class addressinformation extends AppCompatActivity {

    Address address;
    EditText et1,et2,et3,et4,et5;
    TextView tv1;
    int a,b,c,d,theuserid;
    public static final String ADDRESSIDOBJECT = "ADDRESSIDOBJECT";
    String st1,st2,st3,st4,st5,selectedaddress,selectedcard;
    long updationresult=-1,assignresult,addresssize,position;
    String addresssid;
    Userinfo userinfo;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressinformation);

        address =(Address) getIntent().getSerializableExtra(login_screen.ADDRESSOBJECT);
        selectedcard=(String) getIntent().getSerializableExtra(login_screen.CARDSELECT);
        viewModel = new UserViewModel(getApplicationContext());
        loadprefdata();
        intialise();
        SetUIbehavior();
    }

    public void intialise(){
        et2= findViewById(R.id.ainfodedittext2);
        et3= findViewById(R.id.ainfodedittext3);
        et4= findViewById(R.id.ainfodedittext4);
        et5=findViewById(R.id.ainfodedittext5);
        tv1=findViewById(R.id.addressinfotextview1);

        tv1.setText("The address ID is : "+String.valueOf(address.getAddress_id()));
        et2.setText(String.valueOf(address.getAddress_sa_no()));
        et3.setText(address.getAddress_streetname());
        et4.setText(address.getAddress_citytown());
        et5.setText(String.valueOf(address.getAddress_postcode()));

    }

    public void SetUIbehavior(){

        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                st2=et2.getText().toString().trim();
                if(!st2.equals(address.getAddress_sa_no())){
                    address.setAddress_sa_no(st2);
                }
                else
                {
                    et2.selectAll();
                }
            }
        });

        et3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                st3=et3.getText().toString().trim();
                if(!st3.equals(address.getAddress_streetname())){
                    address.setAddress_streetname(st3);
                }
                else
                {
                    et2.selectAll();
                }
            }
        });

        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                st4=et4.getText().toString().trim();
                if(!st4.equals(address.getAddress_citytown())){
                    address.setAddress_citytown(st4);
                }
                else
                {
                    et4.selectAll();
                }
            }
        });

        et5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                st5=et5.getText().toString().trim();
                if(!st5.equals(address.getAddress_postcode())){
                    address.setAddress_postcode(st5);
                }
                else
                {
                    et5.selectAll();
                }
            }
        });
    }

    public void selectaddress(View view) {
        selectedaddress=address.getAddress_sa_no()+", "+address.getAddress_streetname();
        completed();
        Toast.makeText(getApplicationContext(),"Address successfully selected",Toast.LENGTH_SHORT).show();
    }

    public void loadprefdata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        theuserid = (int) DatabaseFunctionality.USID;
    }

    public void completed(){
        String addressid = String.valueOf(address.getAddress_id());
        Intent intent=new Intent(getApplicationContext(),address_listactivity.class);
        intent.putExtra(login_screen.ADDRESSSELECTED,selectedaddress);
        intent.putExtra(ADDRESSIDOBJECT,addressid);
        startActivity(intent);
    }

    public void updateaddressbutton(View view){
        List<Address> addressdata= login_screen.myDao.readalladdresses();;
        addresssize = addressdata.size();
        position=address.getAddress_id();
        viewModel.updateaddress(address,addresssize,position);
        //new updateaddressinformation().execute(address);
        completed();
    }

    public class updateaddressinformation extends AsyncTask<Address,Void,Void>{
        Address address;

        @Override
        protected Void doInBackground(Address... addresses) {
            updationresult=login_screen.myDao.UpdateAddress(addresses[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(updationresult==-1){
                Toast.makeText(getApplicationContext(),"Updation failed",Toast.LENGTH_LONG).show();
                completed();
            }
            else{
                Toast.makeText(getApplicationContext(),"Address info has been successfully updated",Toast.LENGTH_LONG).show();
                completed();
            }
        }
    }

}
