package com.bavy.android_assignment_1.DataModel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    RemoteDBHandler remoteDBHandler;
    RoomDBHandler roomDBHandler;
     public Repository(Context context){
         remoteDBHandler = new RemoteDBHandler(this);
         roomDBHandler = new RoomDBHandler(context,this);
     }

     public void notifyfirebasedatachangedforuser(){
            roomDBHandler.addUsers(remoteDBHandler.getAllusers());
     }

     public void notifyfirebasedatachangedforcard(){
            roomDBHandler.AddCards(remoteDBHandler.getAllcards());
     }

     public void notifyfirebasedatachangedforaddress(){
         roomDBHandler.AddAddress(remoteDBHandler.getAlladdresses());
     }

     public void notifyfirebasedatachangedfororder(){
         roomDBHandler.AddOrder(remoteDBHandler.getAllorders());
     }

     public void deleteduserarg(Userinfo userinfo){
         roomDBHandler.DeleteUser(userinfo);
     }

     public LiveData<List<Userinfo>> getallusers(){
            return roomDBHandler.getAllusers();
     }

     //public LiveData<List<Userinfo>> getdeleteduser(){return roomDBHandler.deleteduser;}

     public LiveData<List<Creditcardinfo>> getallcards(){return roomDBHandler.getAllcards();}

     public LiveData<List<Address>> getalladdresses(){return roomDBHandler.getAlladdresses();}

     public LiveData<List<Orderinfo>> getallorders(){return roomDBHandler.getAllorders();}

     public void addusers(Userinfo userinfo){
            remoteDBHandler.addUser(userinfo);
     }

     public void updateuser(Userinfo userinfo,long s, long p){
         remoteDBHandler.updateuser(userinfo,s,p);
     }

     public void deleteuser(Userinfo userinfo, long s,long p){
         remoteDBHandler.deleteuser(userinfo,s,p);
     }

    public void updatecard(Creditcardinfo creditcardinfo,long s, long p){
        remoteDBHandler.updatecard(creditcardinfo,s,p);
    }

    public void updateaddress(Address address,long s, long p){
        remoteDBHandler.updateaddress(address,s,p);
    }

     public void addcards(Creditcardinfo creditcardinfo){
         remoteDBHandler.addCard(creditcardinfo);
     }

     public void addaddress(Address address){
         remoteDBHandler.addAddress(address);
     }
     public void addorders(Orderinfo orderinfo){
         remoteDBHandler.addOrders(orderinfo);
     }
}
