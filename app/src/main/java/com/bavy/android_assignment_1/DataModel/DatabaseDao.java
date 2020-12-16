package com.bavy.android_assignment_1.DataModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DatabaseDao {
    @Query("select * from Userinfo")
    List<Userinfo> readAllUsers();

    @Query("select * from Creditcardinfo")
    List<Creditcardinfo> readAllCards();

    @Query("select * from Address")
    List<Address> readalladdresses();

    @Query("select * from Userinfo where EmailID like :Email")
    List<Userinfo> SearchUserByEmail(String Email);

    @Query("select * from Userinfo")
    LiveData<List<Userinfo>> readalluserslive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertUsers(List<Userinfo> userinfos);

    @Query("select * from Creditcardinfo")
    LiveData<List<Creditcardinfo>> readallcardslive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertcardslive(List<Creditcardinfo> creditcardinfos);

    @Query("select * from Address")
    LiveData<List<Address>> readalladdresseslive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertaddresseslive(List<Address> addresses);

    @Query("select * from Orderinfo")
    LiveData<List<Orderinfo>> readallorderslive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertOrderslive(List<Orderinfo> orderinfos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(Userinfo userinfo);

    @Update
    int UpdateUser(Userinfo userinfo);

    @Delete
    int DeleteUser(Userinfo userinfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long Insertcard(Creditcardinfo creditcardinfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int Updatecard(Creditcardinfo creditcardinfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long InsertAddress(Address address);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int UpdateAddress(Address address);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long InsertOther(Orderinfo orderinfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int UpdateOther(Orderinfo orderinfo);



}
