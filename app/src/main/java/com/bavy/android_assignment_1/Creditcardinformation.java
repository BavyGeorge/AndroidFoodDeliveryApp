package com.bavy.android_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class Creditcardinformation extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5;
    Creditcardinfo creditcardinfo;
    String selectedcard;
    public static final String CARDIDOBJECT ="CARDIDOBJECT";
    long updationresult=-1,selectcardd=-1;
    int cardid,carddatasize,sendnumber,theuserid;
    Userinfo userinfo;
    UserViewModel viewModel;
    List<Creditcardinfo> carddata = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcardinformation);

        creditcardinfo = (Creditcardinfo)getIntent().getSerializableExtra(login_screen.CARDOBJECT);
        viewModel = new UserViewModel(getApplicationContext());
        loadprefdata();
        Intialise();
        setUIbehavior();
    }

    public void Intialise(){
        et1 = findViewById(R.id.ciedittext1);
        et2 = findViewById(R.id.ciedittext2);
        et3 = findViewById(R.id.ciedittext3);
        et4 = findViewById(R.id.ciedittext4);
        et5 = findViewById(R.id.ciedittext5);

        List<Creditcardinfo> carddata;
        et1.setText(String.valueOf(creditcardinfo.getcID()));
        et1.setEnabled(false);
        cardid = Integer.valueOf(creditcardinfo.getcID());
        et2.setText(String.valueOf(creditcardinfo.getCno()));
        et3.setText(creditcardinfo.getCname());
        et4.setText(String.valueOf(creditcardinfo.getExpdate()));
        et5.setText(String.valueOf(creditcardinfo.getCvvno()));
    }

    public void setUIbehavior(){
        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String st2 =et2.getText().toString();
                if(!st2.equals(creditcardinfo.getCno())){
                    creditcardinfo.setCno(st2);
                }
                else{
                    et2.selectAll();
                }
            }
        });

        et3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String st3 =et3.getText().toString();
                if(!st3.equals(creditcardinfo.cname)){
                    creditcardinfo.setCname(st3);
                }
                else{
                    et3.selectAll();
                }
            }
        });

        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String st4 =et4.getText().toString();
                if(!st4.equals(creditcardinfo.getExpdate())){
                    creditcardinfo.setExpdate(st4);
                }
                else{
                    et4.selectAll();
                }
            }
        });

        et5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String st5 =et5.getText().toString();
                if(!st5.equals(creditcardinfo.getCvvno())){
                    creditcardinfo.setCvvno(st5);
                }
                else{
                    et5.selectAll();
                }
            }
        });
    }



    public void completed(){
        String cardID = String.valueOf(cardid);
        Intent intent = new Intent(getApplicationContext(),creditcard_list.class);
        intent.putExtra(login_screen.CARDSELECT,selectedcard);
        intent.putExtra(CARDIDOBJECT,cardID);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Operation completed successfully",Toast.LENGTH_SHORT).show();
    }

    public void updatebuttonc(View view){
        carddata = login_screen.myDao.readAllCards();
        carddatasize=carddata.size();
        viewModel.updatecard(creditcardinfo,carddatasize,cardid);
        //new Updatecard().execute(creditcardinfo);
        completed();
    }

    public void savecard(View view) {
        carddata = login_screen.myDao.readAllCards();
        carddatasize=carddata.size();
        selectedcard=creditcardinfo.getCno();
        completed();
    }

    public void loadprefdata(){
        SharedPreferences sharedPreferences = getSharedPreferences(login_screen.SHAREDPREFERENCES1,MODE_PRIVATE);
        theuserid = sharedPreferences.getInt(login_screen.GETUSERID,0);
    }

    public void updateaddress_button(View view) {
        Intent intent = new Intent(this,address_listactivity.class);
        startActivity(intent);
    }


    public class Updatecard extends AsyncTask<Creditcardinfo,Void,Void>{

        @Override
        protected Void doInBackground(Creditcardinfo... creditcardinfos) {
            updationresult= login_screen.myDao.Updatecard(creditcardinfos[0]);
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
                Toast.makeText(getApplicationContext(),"Card info has been successfully updated",Toast.LENGTH_LONG).show();
                completed();
            }
        }
    }
}
