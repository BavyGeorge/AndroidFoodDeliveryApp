package com.bavy.android_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;

public class OrderScreen extends AppCompatActivity {
    Creditcardinfo creditcardinfo;
    CheckBox cb1,cb2,cb3,cb4;
    EditText et1,et2,et3,et4,savenoteet1,savenoteet2,savenoteet3,savenoteet4;
    Spinner sp1,sp2,sp3,sp4;
    String order1,order2,order3,order4,st1,st2,st3,st4,note1,note2,note3,note4,notess,quantity1,quantity2,quantity3,quantity4,orders;
    String dressings,dressing1,dressing2,dressing3,dressing4;
    String quantities;
    public static final String SHAREDPREFDATAORDERSCREEN = "SHAREDPREFDATAORDERSCREEN";
    public static final String ORDERSCREENSAVEOBJECT = "ORDERSCREENSAVEOBJECT";
    public static final String QUANTITYNEEDED = "QUANTITYNEEDED";
    public static final String DRESSINGNEEDED = "DRESSINGNEEDED";
    public static final String ADDNOTEOBJECT = "ADDNOTEOBJECT";

    Orderinfo orderinfo;
    Userinfo userinfo;
//    int theuserid;
//    long savingorder=-1,savequantityneed=-1,savedressing=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orderinfo =(Orderinfo) getIntent().getSerializableExtra(login_screen.OTHEROBJECT);
        cb1=findViewById(R.id.checkbox1);
        cb2=findViewById(R.id.checkbox2);
        cb3=findViewById(R.id.checkbox3);
        cb4=findViewById(R.id.checkbox4);

        et1= findViewById(R.id.quantityedittext1);
        et2= findViewById(R.id.quantityedittext2);
        et3= findViewById(R.id.quantityedittext3);
        et4= findViewById(R.id.quantityedittext4);
        savenoteet1=findViewById(R.id.edittextnote1);
        savenoteet2=findViewById(R.id.edittextnote2);
        savenoteet3=findViewById(R.id.edittextnote3);
        savenoteet4=findViewById(R.id.edittextnote4);


        savequantityneed();
        savenote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_orderscreen,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id8=item.getItemId();
        switch (id8){


            case R.id.item22:{
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
            }

            case R.id.item23:{
                Intent intent1 = new Intent(this,creditcard_list.class);
                startActivity(intent1);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }



    public void saveorderitems(View view){


        int id = view.getId();

        switch (id){
            case R.id.checkbox1:{
                order1="Green Salad Lunch";

                break;
            }
            case R.id.checkbox2:{
                order2="Lamb Korma ";

                break;
            }
            case R.id.checkbox3:{
                order3="Open Chicken Sandwich ";

                break;
            }
            case R.id.checkbox4:{
                order4="Beef Noodle Salad ";

                break;
            }
        }
        orders=order1+" "+order2+" "+order3+" "+order4;
        saveorderscreendata();
    }

    public void gotocreditcardactivity(View view) {
        Intent intent1 = new Intent(this,creditcard_list.class);
        startActivity(intent1);
    }

    public void saveorderscreendata(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFDATAORDERSCREEN,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ORDERSCREENSAVEOBJECT,orders);
        editor.putString(QUANTITYNEEDED,quantities);
        editor.putString(DRESSINGNEEDED,dressings);
        editor.putString(ADDNOTEOBJECT,notess);
        editor.apply();
    }


    public void savequantityneed(){
        st1=et1.getText().toString();
        st2=et2.getText().toString();
        st2=et2.getText().toString();
        st4=et4.getText().toString();

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                quantity1=et1.getText().toString();
                quantities=quantity1+" "+quantity2+" "+quantity3+" "+quantity4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                quantity2=et2.getText().toString();
                quantities=quantity1+" "+quantity2+" "+quantity3+" "+quantity4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                quantity3=et3.getText().toString();
                quantities=quantity1+" "+quantity2+" "+quantity3+" "+quantity4;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                quantity4=et4.getText().toString();
                quantities=quantity1+" "+quantity2+" "+quantity3+" "+quantity4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        saveorderscreendata();
    }

    public void savenote(){

        savenoteet1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note1=savenoteet1.getText().toString();
                notess=note1+" "+note2+" "+note3+" "+note4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        savenoteet2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note2=savenoteet2.getText().toString();
                notess=note1+" "+note2+" "+note3+" "+note4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        savenoteet3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note3=savenoteet3.getText().toString();
                notess=note1+" "+note2+" "+note3+" "+note4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        savenoteet4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note4=savenoteet4.getText().toString();
                notess=note1+" "+note2+" "+note3+" "+note4;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        saveorderscreendata();
    }



//    public void addnotebutton(View view) {
//        int id5=view.getId();
//
//        switch (id5){
//            case R.id.savenotebutton1:
//                button1 = "button1";
//                break;
//            case R.id.savenotebutton2:
//                button2 = "button2";
//                break;
//            case R.id.savenotebutton3:
//                button3 = "button3";
//                break;
//            case R.id.savenotebutton4:
//                button4 = "button4";
//                break;
//        }
//        saveorderscreendata();
//        Intent intent = new Intent(this,addnote.class);
//        intent.putExtra(login_screen.BUTTONOBJECT1,button1);
//        intent.putExtra(login_screen.BUTTONOBJECT2,button2);
//        intent.putExtra(login_screen.BUTTONOBJECT3,button3);
//        intent.putExtra(login_screen.BUTTONOBJECT4,button4);
//        startActivity(intent);
//    }

    public void radiobuttonhandling(View view) {

        int id4=view.getId();

        switch (id4){
            case R.id.orderrb1:
                dressing1="none";

                break;
            case R.id.orderrb2:
                dressing1="ranch";

                break;
            case R.id.orderrb3:
                dressing1="vinaigrette";

                break;
            case R.id.orderrb4:
                dressing2="mild";

                break;
            case R.id.orderrb5:
                dressing2="med";

                break;
            case R.id.orderrb6:
                dressing2="hot";

                break;
            case R.id.orderrb7:
                dressing3="white";

                break;
            case R.id.orderrb8:
                dressing3="rye";
                break;
            case R.id.orderrb9:
                dressing3="wholemeal";
                break;
            case R.id.orderrb10:
                dressing4="no chilli flakes";
                break;
            case R.id.orderrb11:
                dressing4="regular chilli flakes";
                break;
            case R.id.orderrb12:
                dressing4="extra chilli flakes";
                break;
        }
        dressings=dressing1+" "+dressing2+" "+dressing3+" "+dressing4;
        saveorderscreendata();
    }

}
