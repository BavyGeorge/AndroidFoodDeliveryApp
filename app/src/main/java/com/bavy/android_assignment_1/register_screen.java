package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.DatabaseDao;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class register_screen extends AppCompatActivity {

    public static DatabaseDao myDao;

    EditText et1,et2,et3,et4,et5,et6,et7;
    Spinner sp1;
    int userid;
    String st1,st2,st3,st4,st5,st6,st7;
    long insertionresult = -1;
    Userinfo userinfo;
    View view;
    UserViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        myDao = DatabaseFunctionality.createDemoDBInstance(getApplicationContext()).getDao();
        et1 = findViewById(R.id.edittext1);
        et2 = findViewById(R.id.edittext2);
        et3 = findViewById(R.id.edittext3);
        et4 = findViewById(R.id.edittext4);
        et5 = findViewById(R.id.edittext5);
        sp1 = findViewById(R.id.spinnergender);
        et7 = findViewById(R.id.edittext7);
        viewModel = new UserViewModel(getApplicationContext());
    }

    public void completed(){
        finish();
    }

    public void register(View view){
        st1 = et1.getText().toString().trim();
        st2 = et2.getText().toString().trim();
        st3 = et3.getText().toString().trim();
        st4 = et4.getText().toString().trim();
        st5 = et5.getText().toString().trim();
        st7 = et7.getText().toString().trim();


        if(st1.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter your First Name",Toast.LENGTH_LONG).show();
        }
        else if(st2.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter your Last Name",Toast.LENGTH_LONG).show();
        }
        else if(st3.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter your E-mail ID",Toast.LENGTH_LONG).show();
        }
        else if(st4.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
        }
        else if(st5.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter your Password ",Toast.LENGTH_LONG).show();
        }
        else if(st7.length()!=10){
            Toast.makeText(getApplicationContext(),"Please enter a valid 10 digit your Mobile number",Toast.LENGTH_LONG).show();
        }
        else{
            if(!st4.equals(st5)){
                Toast.makeText(getApplicationContext(),"Both the passwords should match",Toast.LENGTH_LONG).show();
            }
            else{
                Userinfo userinfo= new Userinfo();
                DatabaseFunctionality.USID++;
                int size1;
                List<Userinfo> data = null;
                data=login_screen.myDao.readAllUsers();
                size1=data.size();
                size1++;
                userinfo.setuID(size1);
                userinfo.setFirstName(st1);
                userinfo.setLastName(st2);
                userinfo.setEmailID(st3);
                userinfo.setPassword(st4);
                userinfo.setMobilenumber(st7);
                userinfo.setGender(sp1.getSelectedItem().toString());
                viewModel.addusers(userinfo);
                savedatapref();
                creditcardscreen();
//                Insertuser insertuser= new Insertuser();
//                insertuser.execute(userinfo);

            }
        }
    }

    public void creditcardscreen() {
        Intent intent = new Intent(this,creditcard_registration.class);
        startActivity(intent);
    }

    public void savedatapref(){
        int numb = (int)DatabaseFunctionality.USID;
        userid=numb;
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(login_screen.GETUSERID,userid);
        editor.apply();
    }


//public class Insertuser extends AsyncTask<Userinfo,Void,Void>{
//        List<Userinfo> checkifexists=null;
//
//
//    @Override
//    protected Void doInBackground(Userinfo... userinfos) {
//        insertionresult = login_screen.myDao.insertUser(userinfos[0]);
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        if(insertionresult==-1){
//            Toast.makeText(getApplicationContext(),"Insertion failed",Toast.LENGTH_LONG).show();
//        }
//        else{
//            Toast.makeText(getApplicationContext(),"Insertion successful ID is :"+insertionresult,Toast.LENGTH_LONG).show();
////            savedatapref();
//            creditcardscreen();
//        }
//    }
//
//
//
//}
}
