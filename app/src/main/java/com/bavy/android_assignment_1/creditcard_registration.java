package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class creditcard_registration extends AppCompatActivity {
    long insertionresult = -1;
    EditText et1,et2,et3,et4;
    String st1,st2,st3,st4;
    int a,b,c,theuserid;
    Userinfo userinfo;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard_registration);

        et1 = findViewById(R.id.credittext1);
        et2 = findViewById(R.id.credittext2);
        et3 = findViewById(R.id.credittext3);
        et4 = findViewById(R.id.credittext4);
        viewModel = new UserViewModel(getApplicationContext());

        loadprefdata();
    }

    public void cancelRegistration(View view){
        completed();
    }

    public void completed(){
        finish();
    }

    public void creditregistration(View view){

        st1= et1.getText().toString().trim();
        st2= et2.getText().toString().trim();
        st3= et3.getText().toString().trim();
        st4= et4.getText().toString().trim();

        if(st1.length()!= 16){
            Toast.makeText(getApplicationContext(),"Enter a valid 16 digit credit card number",Toast.LENGTH_LONG).show();
        }
        else if(st2.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter name on credit card",Toast.LENGTH_LONG).show();
        }
        else if(st3.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter credit card expiry date",Toast.LENGTH_LONG).show();
        }
        else if(st4.length()!= 3){
            Toast.makeText(getApplicationContext(),"Enter a valid 3 digit credit card CVV no",Toast.LENGTH_LONG).show();
        }
        else{
            Creditcardinfo creditcardinfo = new Creditcardinfo();
            DatabaseFunctionality.CID++;
            creditcardinfo.setcID((int) DatabaseFunctionality.CID);
            creditcardinfo.setCno(st1);
            creditcardinfo.setCname(st2);
            creditcardinfo.setExpdate(st3);
            creditcardinfo.setCvvno(st4);
            creditcardinfo.setuID(theuserid);
            viewModel.addcards(creditcardinfo);
            registeraddress();
//            Insertcreditcard insertcreditcard= new Insertcreditcard();
//            insertcreditcard.execute(creditcardinfo);
        }
    }

    public void loadprefdata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        theuserid = sharedPreferences.getInt(login_screen.GETUSERID,0);
    }

    public void registeraddress() {
            Intent intent = new Intent(this,address_registration.class);
            startActivity(intent);
    }

    public class Insertcreditcard extends AsyncTask<Creditcardinfo,Void,Void>{

        @Override
        protected Void doInBackground(Creditcardinfo... creditcardinfos) {
            insertionresult=login_screen.myDao.Insertcard(creditcardinfos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(insertionresult==-1){
                Toast.makeText(getApplicationContext(),"Insertion Failed",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Insertion successful. Card ID is : "+insertionresult,Toast.LENGTH_LONG).show();
                registeraddress();
            }
        }
    }
}
