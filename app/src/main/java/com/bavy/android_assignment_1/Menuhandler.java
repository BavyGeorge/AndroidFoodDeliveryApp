package com.bavy.android_assignment_1;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;



public class Menuhandler extends AppCompatActivity {
    Userinfo userinfo;
    Orderinfo orderinfo;
    Creditcardinfo creditcardinfo;

    public class Personalinfo{
        public void putdata(){
            userinfo=(Userinfo) getIntent().getSerializableExtra(login_screen.USEROBJECT);
        }
    }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            int id=item.getItemId();
            switch (id){

                case R.id.item10:{
                    Intent intent5= new Intent(this,Primaryscreen.class);
                    startActivity(intent5);
                    break;
                }

                case R.id.item11:{
                    Intent intent2= new Intent(this, Primaryscreen.class);
                    startActivity(intent2);
                    break;
                }

                case R.id.item12:{
                    Intent intent1 = new Intent(this,UserInfoUpdate.class);
                    Personalinfo personalinfo = new Personalinfo();
                    personalinfo.putdata();
                    intent1.putExtra(login_screen.USEROBJECT,userinfo);
                    startActivity(intent1);
                    break;
                }

                case R.id.item13:{
                    Intent intent2= new Intent(this,OrderScreen.class);
                    intent2.putExtra(login_screen.OTHEROBJECT, orderinfo);
                    startActivity(intent2);
                    break;
                }

            }
            return super.onOptionsItemSelected(item);
        }




}
