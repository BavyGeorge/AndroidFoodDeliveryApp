package com.bavy.android_assignment_1.DataModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Userinfo.class, Creditcardinfo.class, Orderinfo.class, Address.class},
        version = 2, exportSchema = false)

public abstract class DatabaseFunctionality extends RoomDatabase {

    public static long USID = 0;

    public static long CID = 0;

    public static long ADDRESSID = 0;

    public static long ORDERID = 0;

    private static DatabaseFunctionality INSTANCE;


    private static DatabaseFunctionality myDemoDB = null;

    public abstract DatabaseDao getDao();

    public static  DatabaseFunctionality createDemoDBInstance(Context context){
        if(myDemoDB==null){
            myDemoDB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DatabaseFunctionality.class,"DBFunctionality"
            )
                    .allowMainThreadQueries()
                    .build();
        }
        return myDemoDB;
    }

}

