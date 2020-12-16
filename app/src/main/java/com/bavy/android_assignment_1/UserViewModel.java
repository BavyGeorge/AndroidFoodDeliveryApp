package com.bavy.android_assignment_1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bavy.android_assignment_1.DataModel.Address;
import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Orderinfo;
import com.bavy.android_assignment_1.DataModel.Repository;
import com.bavy.android_assignment_1.DataModel.Userinfo;

import java.util.List;

public class UserViewModel extends ViewModel {

    Repository repository;

    public UserViewModel(Context context){
        repository = new Repository(context);
    }

    LiveData<List<Userinfo>> getallusers(){
        return repository.getallusers();
    }

    LiveData<List<Creditcardinfo>> getallcards(){ return repository.getallcards();}

    LiveData<List<Address>> getalladresses(){return repository.getalladdresses();}

    LiveData<List<Orderinfo>> getallaorders(){return repository.getallorders();}

    void addusers(Userinfo userinfo){
        repository.addusers(userinfo);
    }

    void  updatesuser(Userinfo userinfo,long s, long p){
        repository.updateuser(userinfo,s,p);
    }

    void deleteuser(Userinfo userinfo,long s,long p){
        repository.deleteuser(userinfo,s,p);
    }

    void  updatecard(Creditcardinfo creditcardinfo,long s, long p){
        repository.updatecard(creditcardinfo,s,p);
    }

    void  updateaddress(Address address,long s, long p){
        repository.updateaddress(address,s,p);
    }

    void addcards(Creditcardinfo creditcardinfo){
        repository.addcards(creditcardinfo);
    }

    void addaddresses(Address address){
        repository.addaddress(address);
    }

    void addorders(Orderinfo orderinfo){
        repository.addorders(orderinfo);
    }
}
