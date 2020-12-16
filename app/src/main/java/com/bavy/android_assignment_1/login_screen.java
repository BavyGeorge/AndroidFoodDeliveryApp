package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.DatabaseDao;
import com.bavy.android_assignment_1.DataModel.DatabaseFunctionality;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.RemoteDBHandler;
import com.bavy.android_assignment_1.DataModel.RoomDBHandler;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;


public class login_screen extends AppCompatActivity {

    EditText et1, et2;
    TextView tv1;
    String st1, st2, st3;
    int userid;
    Userinfo userinfo;
    UserViewModel viewModel;
    RoomDBHandler roomDBHandler;
    RemoteDBHandler remoteDBHandler;
    public static final String USEROBJECT = "USEROBJECT";
    public static final String CARDOBJECT = "CARDOBJECT";
    public static final String ADDRESSOBJECT = "ADDRESSOBJECT";
    public static final String OTHEROBJECT = "OTHEROBJECT";
    public static final String CONFIRMATIONOBJECT  = "CONFIRMATIONOBJECT";
    public static final String SHAREDPREFERENCES = "SHAREDPREFERENCES";
    public static final String SHAREDPREFERENCES1 = "SHAREDPREFERENCES1";
    public  static final String GETUSERID = "GETUSERID";

    public static final String BUTTONOBJECT1 = "BUTTONOBJECT1";
    public static final String BUTTONOBJECT2 = "BUTTONOBJECT2";
    public static final String BUTTONOBJECT3 = "BUTTONOBJECT3";
    public static final String BUTTONOBJECT4 = "BUTTONOBJECT4";
    public static final  String CARDSELECT ="CARDSELECT";
    public static final String ADDRESSSELECTED="ADDRESSSELECTED";
    public static DatabaseDao myDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        myDao = DatabaseFunctionality.createDemoDBInstance(getApplicationContext()).getDao();
        et1 = findViewById(R.id.edittext1);
        et2 = findViewById(R.id.edittext2);
        tv1 = findViewById(R.id.textviewforregister);
        viewModel= new UserViewModel(getApplicationContext());
    }

    public void logindetails(View view) {
        st1 = et1.getText().toString().trim();
        st2 = et2.getText().toString();
        if (st1.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please enter your E-mail ID", Toast.LENGTH_LONG).show();
        } else if (st2.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please enter your password", Toast.LENGTH_LONG).show();
        } else {
            new Readuserbyemail().execute(st1);
        }
    }

    public void checkpassword(Userinfo userinfo) {
        if (st2.equals(userinfo.getPassword())) {
            userid = userinfo.getuID();
            Intent intent = new Intent(getApplicationContext(),Primaryscreen.class);
            intent.putExtra(USEROBJECT,userinfo);
            savedatapref();
            startActivity(intent);




        } else {
            Toast.makeText(getApplicationContext(),
                    "Password is wrong", Toast.LENGTH_LONG).show();
        }
    }

//    public static void main(String[] args) {
//        outerloopp:
//        for (int i=0; i < 5; i++) {
//            for (int j=0; j < 5; j++) {
//                if (i * j > 6) {
//                    System.out.println("Breaking");
//                    break outerloopp;
//                }
//                System.out.println(i + " " + j);
//            }
//        }
//        System.out.println("Done");
//    }


    public void savedatapref(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFERENCES1,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(GETUSERID,userid);
        editor.apply();
    }

    public void newuserregister(View view) {
        Intent intent1 = new Intent(this, register_screen.class);
        startActivity(intent1);
    }

    private class Readuserbyemail extends AsyncTask<String, Void, Void> {
        List<Userinfo> user;

        @Override
        protected Void doInBackground(String... strings) {
            user = myDao.SearchUserByEmail(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (user == null) {
                Toast.makeText(getApplicationContext(),
                        "Read operation failure", Toast.LENGTH_LONG).show();
            } else if (user.size() == 0) {
                Toast.makeText(getApplicationContext(),
                        "Email ID does not exist", Toast.LENGTH_LONG).show();
            } else {
                checkpassword(user.get(0));
            }
        }
    }
}


