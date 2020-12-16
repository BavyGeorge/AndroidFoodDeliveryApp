package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class address_registration extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    String st1,st2,st3,st4;
    int theuserid;
    long insertionresult=-1;
    Userinfo userinfo;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_registration);

        et1=findViewById(R.id.adedittext1);
        et2=findViewById(R.id.adedittext2);
        et3=findViewById(R.id.adedittext3);
        et4=findViewById(R.id.adedittext4);
        loadprefdata();
        viewModel= new UserViewModel(getApplicationContext());
    }

    public void completed(){
            Intent intent = new Intent(this,login_screen.class);
            startActivity(intent);
    }

    public void registernewaddress(View view){
        if(et1.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter your Apartment number or House number ",Toast.LENGTH_LONG).show();
        }
        else if(et2.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter your Street Name ",Toast.LENGTH_LONG).show();
        }
        else if(et3.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter your City or Town ",Toast.LENGTH_LONG).show();
        }
        else if(et4.getText().toString().trim().length()!= 4){
            Toast.makeText(getApplicationContext(),"Please Enter a valid 4 digit Postcode ",Toast.LENGTH_LONG).show();
        }
        else{
            st1=et1.getText().toString().trim();
            st2=et2.getText().toString().trim();
            st3=et3.getText().toString().trim();
            st4=et4.getText().toString().trim();
//            a=Integer.parseInt(st1);
//            b=Integer.parseInt(st4);


            Address address= new Address();
            DatabaseFunctionality.ADDRESSID++;
            address.setAddress_id((int) DatabaseFunctionality.ADDRESSID);
            address.setAddress_sa_no(st1);
            address.setAddress_streetname(st2);
            address.setAddress_citytown(st3);
            address.setAddress_postcode(st4);
            address.setuID(theuserid);
            viewModel.addaddresses(address);
            completed();
//            Insertaddress insertaddress = new Insertaddress();
//            insertaddress.execute(address);
        }
    }

    public void loadprefdata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        theuserid = sharedPreferences.getInt(login_screen.GETUSERID,0);
    }


    public class Insertaddress extends AsyncTask<Address,Void,Void>{


        @Override
        protected Void doInBackground(Address... addresses) {
            insertionresult=login_screen.myDao.InsertAddress(addresses[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(insertionresult==-1){
                Toast.makeText(getApplicationContext(),"Insertion Failed",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Insertion successful. Address ID is :"+insertionresult,Toast.LENGTH_LONG).show();
                completed();
            }
        }
    }
}
