package com.bavy.android_assignment_1.DataModel;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Database;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RoomDBHandler {
    Repository repository;
    DatabaseDao databaseDao;
    RemoteDBHandler remoteDBHandler;
    LiveData<List<Userinfo>> allusers;
    List<Userinfo> deleteduser;
    LiveData<List<Creditcardinfo>> allcards;
    LiveData<List<Address>> alladdresses;
    LiveData<List<Orderinfo>> allorders;

    public RoomDBHandler(Context context,Repository repos){
        repository = repos;
        databaseDao = DatabaseFunctionality.createDemoDBInstance(context).getDao();
        allusers = databaseDao.readalluserslive();
        allcards =databaseDao.readallcardslive();
        alladdresses=databaseDao.readalladdresseslive();
        allorders = databaseDao.readallorderslive();
    }

    public LiveData<List<Userinfo>> getAllusers(){
        return allusers;
    }

    public LiveData<List<Creditcardinfo>> getAllcards(){
        return allcards;
    }

    public LiveData<List<Address>> getAlladdresses(){
        return alladdresses;
    }

    public LiveData<List<Orderinfo>> getAllorders(){
        return allorders;
    }

    public void addUser(Userinfo userinfo){
        new AddUserTask(databaseDao).execute(userinfo);
    }

    public void addUsers(List<Userinfo> userinfo){
        new AddUsersTask(databaseDao).execute(userinfo);
    }

    public void AddCards(List<Creditcardinfo> creditcardinfo){
        new AddCardsTask(databaseDao).execute(creditcardinfo);
    }

    public void AddAddress(List<Address> address){
        new AddAddressesTask(databaseDao).execute(address);
    }

    public void  AddOrder(List<Orderinfo> orderinfos){
        new AddOrderTask(databaseDao).execute(orderinfos);
    }

    public void DeleteUser(Userinfo userinfo){
        new DeleteUserTask(databaseDao).execute(userinfo);
    }

    public class AddOrderTask extends AsyncTask<List<Orderinfo>,Void,Void>{

        public final DatabaseDao DAO;

        public AddOrderTask(DatabaseDao dao){
            DAO = dao;
        }

        @Override
        protected Void doInBackground(List<Orderinfo>... lists) {
            try {
                DAO.insertOrderslive(lists[0]);
            }catch (SQLiteException e){
                Log.e("SQLEXCEPTION","CANNOT ADD ORDERS"+e.getMessage());
            }
            return null;
        }
    }

    public class AddAddressesTask extends AsyncTask<List<Address>,Void,Void>{

        public final DatabaseDao DAO;

        public AddAddressesTask(DatabaseDao dao){
            DAO=dao;
        }

        @Override
        protected Void doInBackground(List<Address>... lists) {
            try{
                DAO.insertaddresseslive(lists[0]);
            }catch (SQLiteException e){
                Log.e("SQLEXCEPTION","CANNOT ADD ADDRESSES"+e.getMessage());
            }
            return null;
        }
    }

    public class AddCardsTask extends AsyncTask<List<Creditcardinfo>,Void,Void>{

        public final DatabaseDao DAO;

        public AddCardsTask(DatabaseDao dao){
            DAO=dao;
        }

        @Override
        protected Void doInBackground(List<Creditcardinfo>... lists) {
            try{
                DAO.insertcardslive(lists[0]);
            }catch (SQLiteException e){
                Log.e("SQLEXCEPTION","CANNOT ADD CARDS"+e.getMessage());
            }
            return null;
        }
    }

    public class AddUsersTask extends AsyncTask<List<Userinfo>,Void,Void>{
        public final DatabaseDao DAO;

        public AddUsersTask(DatabaseDao dao){
            DAO=dao;
        }

        @Override
        protected Void doInBackground(List<Userinfo>... lists) {
            try{
                DAO.insertUsers(lists[0]);
            }catch (SQLiteException e){
                Log.e("SQLEXCEPTION","CANNOT ADD USERS"+e.getMessage());
            }
            return null;
        }
    }

    public class AddUserTask extends AsyncTask<Userinfo,Void,Void>{
        public final DatabaseDao DAO;

        public AddUserTask(DatabaseDao dao){
            DAO=dao;
        }

        @Override
        protected Void doInBackground(Userinfo... userinfos) {
            try{
                DAO.insertUser(userinfos[0]);
            }catch (SQLiteException e){
                Log.e("SQLEXCEPTION","CANNOT ADD USERS"+e.getMessage());
            }
            return null;
        }
    }

    public class DeleteUserTask extends AsyncTask<Userinfo,Void,Void>{
        private final DatabaseDao DAO;

        public DeleteUserTask(DatabaseDao dao){
            DAO = dao;
        }

        @Override
        protected Void doInBackground(Userinfo... userinfos) {
            DAO.DeleteUser(userinfos[0]);
            return null;
        }
    }
}
