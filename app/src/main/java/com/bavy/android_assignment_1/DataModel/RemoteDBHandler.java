package com.bavy.android_assignment_1.DataModel;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.widgets.Snapshot;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RemoteDBHandler {

    List<Userinfo> allusers;
    List<Userinfo> deleteusers;
    List<Creditcardinfo> allcards;
    List<Address> alladdresses;
    List<Orderinfo> allorders;
    Repository repository;

    int completed=0,updation=0,completed1=0,val=0;

    RemoteDBHandler(Repository repos){
        allusers = new ArrayList<>();
        deleteusers = new ArrayList<>();
        allcards = new ArrayList<>();
        alladdresses = new ArrayList<>();
        allorders = new ArrayList<>();
        repository= repos;
        readUserRecords();
    }

    public List<Userinfo> getAllusers(){
        int p=0;
        return allusers;
    }

    public List<Userinfo> getDeleteusers(){
        return deleteusers;
    }

    public List<Creditcardinfo> getAllcards(){
        return allcards;
    }

    public List<Address> getAlladdresses(){
        return alladdresses;
    }

    public List<Orderinfo> getAllorders(){
        return allorders;
    }

    public void addUser(Userinfo userinfo){
        FirebaseDatabase FbDb = FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_user");
        String recordId = DbRef.push().getKey();
        DbRef.child(recordId).setValue(userinfo);
        setusid(DatabaseFunctionality.USID);
    }

    public void updateuser(final Userinfo userinfo,final long size, final long position){
        FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
        final DatabaseReference DbRef =FbDb.getReference("Android_Assignment_user");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if(completed!=1) {
                            if (i == position) {
                                String key = snapshot.getKey();
                                DbRef.child(key).setValue(userinfo);
                                completed=1;
                                //repository.notifyfirebasedatachangedforuser();
                            } else {
                                i++;
                            }
                        }
                        else {
                            break;
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteuser(final Userinfo userinfo,final long size, final long position){
        FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
        final DatabaseReference DbRef =FbDb.getReference("Android_Assignment_user");
        DbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(completed!=1) {
                        if (i == position) {
                            String key = snapshot.getKey();
                            DbRef.child(key).setValue(null);
                            repository.deleteduserarg(userinfo);
                            completed=1;
                            updation=1;
                            val=(int) position;
                        } else {
                            i++;
                        }
                    }
                    else {
                        Userinfo userinfo=snapshot.getValue(Userinfo.class);
                        if(updation==1&&completed1!=1){
                            if (val==size) {
                                completed1 = 1;
                            }
                            else {
                            userinfo.setuID(val);
                            val++;
                            String key = snapshot.getKey();
                            DbRef.child(key).setValue(userinfo);
                            repository.notifyfirebasedatachangedforuser();
                            }
                            }
                    }
                }
            }

//            public void updateuvalues(final Userinfo userinfo1,final long s,final long p){
//                FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
//                final DatabaseReference DbRef =FbDb.getReference("Android_Assignment_user");
//                DbRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        int i=1,val=1,completed1=0;
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            if (completed1 != 1){
//                                userinfo1.setuID(val);
//                            val++;
//                            String key = snapshot.getKey();
//                            DbRef.child(key).setValue(userinfo1);
//                            if (val > s - 1) {
//                                completed1 = 1;
//                            }
//                        }
//                            else {
//                                break;
//                            }
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
//            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updatecard(final Creditcardinfo creditcardinfo,final long size, final long position){
        FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
        final DatabaseReference DbRef =FbDb.getReference("Android_Assignment_card");

        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(completed!=1) {
                        if (i == position) {
                            String key = snapshot.getKey();
                            DbRef.child(key).setValue(creditcardinfo);
                            completed=1;
                            repository.notifyfirebasedatachangedforcard();
                        } else {
                            i++;
                        }
                    }
                    else {
                        //break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateaddress(final Address address,final long size, final long position){
        FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
        final DatabaseReference DbRef =FbDb.getReference("Android_Assignment_address");

        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(completed!=1) {
                        if (i == position) {
                            String key = snapshot.getKey();
                            DbRef.child(key).setValue(address);
                            completed=1;
                                repository.notifyfirebasedatachangedforaddress();
                        } else {
                            i++;
                        }
                    }
                    else {break;}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addCard(Creditcardinfo creditcardinfo){
        FirebaseDatabase FbDb = FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_card");
        String cardRecordID = DbRef.push().getKey();
        DbRef.child(cardRecordID).setValue(creditcardinfo);
        setCid(DatabaseFunctionality.CID);
    }

    public void addAddress(Address address){
        FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_address");
        String addressRecordID =DbRef.push().getKey();
        DbRef.child(addressRecordID).setValue(address);
        setAddressid(DatabaseFunctionality.ADDRESSID);
    }

    public void addOrders(Orderinfo orderinfo){
        FirebaseDatabase FbDb =FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_order");
        String orderrecordid = DbRef.push().getKey();
        DbRef.child(orderrecordid).setValue(orderinfo);
        setOrderid(DatabaseFunctionality.ORDERID);
    }

    public void setOrderid(long value){
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference();
        DbRef.child("ORDERID").setValue(value);
        readOrderinfoRecords();
    }

    public void setAddressid(long value){
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference();
        DbRef.child("ADDRESSID").setValue(value);
        readAddressRecords();
    }

    public void setCid(long value){
        DatabaseReference DbRef = FirebaseDatabase  .getInstance().getReference();
        DbRef.child("CID").setValue(value);
        readCardRecords();
    }

    public void setusid(long value){
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference();
        DbRef.child("USID").setValue(value);
        readUserRecords();
    }



//    public int getAllusers(){
//    public int getAllusers(){
//    public int getAllusers(){
//    public int getAllusers(){
//    public int getAllusers(){
//        int p=0;
//        return p;
//    }

    public void readOrderinfoRecords(){
        FirebaseDatabase FbDb = FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_order");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allorders=new ArrayList<Orderinfo>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Orderinfo orderinfo = snapshot.getValue(Orderinfo.class);
                    allorders.add(orderinfo);
                }
                repository.notifyfirebasedatachangedfororder();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DbRef =FbDb.getReference().child("ORDERID");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long orderID = (long)dataSnapshot.getValue();
                DatabaseFunctionality.ORDERID=orderID;
                Log.e("ORDERIDVAL","value of ORDERID is :"+DatabaseFunctionality.ORDERID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readAddressRecords(){
        FirebaseDatabase FbDb = FirebaseDatabase.getInstance();
        DatabaseReference DbRef =FbDb.getReference("Android_Assignment_address");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alladdresses=new ArrayList<Address>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Address address = snapshot.getValue(Address.class);
                    alladdresses.add(address);
                }
                repository.notifyfirebasedatachangedforaddress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DbRef=FbDb.getReference().child("ADDRESSID");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long addressID = (long)dataSnapshot.getValue();
                DatabaseFunctionality.ADDRESSID=addressID;
                Log.e("ADDRESSIDVAL","value of ADDRESSID is :"+DatabaseFunctionality.ADDRESSID);
                readOrderinfoRecords();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readCardRecords(){
        FirebaseDatabase FbDb = FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_card");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allcards= new ArrayList<Creditcardinfo>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Creditcardinfo creditcardinfo = snapshot.getValue(Creditcardinfo.class);
                    allcards.add(creditcardinfo);
                }
                repository.notifyfirebasedatachangedforcard();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("REMOTE_DB_ERROR","Error reading from Firebase");
            }
        });
        DbRef =FbDb.getReference().child("CID");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long cID = (long)dataSnapshot.getValue();
                DatabaseFunctionality.CID=cID;
                Log.e("CIDVAL","value of CID is :"+DatabaseFunctionality.CID);
                readAddressRecords();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void readUserRecords(){
        FirebaseDatabase FbDb = FirebaseDatabase.getInstance();
        DatabaseReference DbRef = FbDb.getReference("Android_Assignment_user");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allusers = new ArrayList<Userinfo>();
//                List<Userinfo> userinfos = new ArrayList<Userinfo>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Userinfo userinfo = snapshot.getValue(Userinfo.class);
//                    userinfos.add(userinfo);
                    allusers.add(userinfo);
                }
                repository.notifyfirebasedatachangedforuser();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("REMOTE_DB_ERROR","Error reading from Firebase");
            }
        });
        DbRef=FbDb.getReference().child("USID");
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long usID = (long) dataSnapshot.getValue();
                DatabaseFunctionality.USID=usID;
                Log.e("USIDVAL","value of USID is :"+DatabaseFunctionality.USID);
                readCardRecords();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
