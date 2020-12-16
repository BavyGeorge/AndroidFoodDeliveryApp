package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class UserInformationActivity extends AppCompatActivity {

    Userinfo userinfo;
    Creditcardinfo creditcardinfo;
    EditText et1,et2,et3,et4,et5,et6,et7;
    Spinner sp1;
    int theuserid,error=0;
    long size,position;
    long updationresult=-1;
    long deletionresult=-1;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        viewModel = new UserViewModel(getApplicationContext());
        readdata();
        //userinfo = (Userinfo) getIntent().getSerializableExtra(login_screen.USEROBJECT);
        Intialise();
        setUIbehavior();
    }

    public void readdata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        theuserid = sharedPreferences.getInt(login_screen.GETUSERID,0);
        position=theuserid;
    }

    public void Intialise(){
        et1 = findViewById(R.id.iedittext1);
        et2 = findViewById(R.id.iedittext2);
        et3 = findViewById(R.id.iedittext3);
        et4 = findViewById(R.id.iedittext4);
        sp1 = findViewById(R.id.spinnergenderinfo1);
        et6 = findViewById(R.id.iedittext6);
        et7 = findViewById(R.id.edittextid);

        List<Userinfo> data;
       data =login_screen.myDao.readAllUsers();
       size=data.size();
       theuserid--;
        userinfo=data.get(theuserid);
        position=userinfo.getuID();
        et1.setText(userinfo.getFirstName());
        et2.setText(userinfo.getLastName());
        et3.setText(userinfo.getEmailID());
        et4.setText(userinfo.getPassword());
        et6.setText(userinfo.getMobilenumber());
        et7.setText(String.valueOf(userinfo.getuID()));
        et7.setEnabled(false);
        if(!sp1.getSelectedItem().toString().equals(userinfo.getGender())){
            sp1.setSelection(1);
        }
    }

    public void setUIbehavior(){
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!et1.getText().toString().equals(userinfo.getFirstName())){
                    userinfo.setFirstName(et1.getText().toString());
                }
                else{
                    et1.selectAll();
                }
            }
        });

        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!et2.getText().toString().equals(userinfo.getLastName())){
                    userinfo.setLastName(et2.getText().toString());
                }
                else{
                    et2.selectAll();
                }
            }
        });
        et3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!et3.getText().toString().equals(userinfo.getEmailID())){
                    userinfo.setEmailID(et3.getText().toString());
                }
                else{
                    et3.selectAll();
                }
            }
        });
        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!et4.getText().toString().equals(userinfo.getPassword())){
                    userinfo.setPassword(et4.getText().toString());
                }
                else{
                    et4.selectAll();
                }
            }
        });
//        et5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!et5.getText().toString().equals(userinfo.getAddress())){
//                    userinfo.setAddress(et5.getText().toString());
//                }
//                else{
//                    et5.selectAll();
//                }
//            }
//        });
        et6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!et6.getText().toString().equals(userinfo.getMobilenumber())){
                    userinfo.setMobilenumber(et6.getText().toString());
                }
                else{
                    et6.selectAll();
                }
            }
        });

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!sp1.getSelectedItem().toString().equals(userinfo.getGender())){
                    userinfo.setGender(sp1.getSelectedItem().toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void updatebutton(View view){
        viewModel.updatesuser(userinfo,size,position);
        completed();
    }

    public void cancelbutton(View view){
        completed();
    }

    public void completed(){
//        Intent intent = new Intent(getApplicationContext(),UserInfoUpdate.class);
//        startActivity(intent);
        finish();
    }

    public void update_addressbutton(View view) {
        Intent intent = new Intent(this,creditcard_list.class);
        startActivity(intent);
    }

    public class updateuserinfo extends AsyncTask<Userinfo,Void,Void>{

        @Override
        protected Void doInBackground(Userinfo... userinfos) {
            updationresult=login_screen.myDao.UpdateUser(userinfos[0]);
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
                Toast.makeText(getApplicationContext(),"Information has been successfully updated",Toast.LENGTH_LONG).show();
                completed();
            }
        }
    }

    public void deletebutton(View view){
        //new deleteuserinfo().execute(userinfo);
        viewModel.deleteuser(userinfo,size,position);
        Intent intent = new Intent(getApplicationContext(),login_screen.class);
        startActivity(intent);
    }

    public class deleteuserinfo extends AsyncTask<Userinfo,Void,Void>{

        @Override
        protected Void doInBackground(Userinfo... userinfos) {
            deletionresult=login_screen.myDao.DeleteUser(userinfos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(deletionresult==-1){
                Toast.makeText(getApplicationContext(),"Deletion failed",Toast.LENGTH_LONG).show();
                completed();
            }
            else{
                Toast.makeText(getApplicationContext(),"User has been successfully deleted",Toast.LENGTH_LONG).show();
                completed();
            }
        }
    }
}
