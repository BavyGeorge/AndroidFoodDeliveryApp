package com.bavy.android_assignment_1.DataModel;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Userinfo",

indices ={
        @Index(name="UID",value = "UID"),
        @Index(name= "EmailID",value="EmailID",unique = true),
            @Index(name="Mobilenumber",value = "Mobilenumber", unique = true)})

public class Userinfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UID")
    public int uID;

    @ColumnInfo(name = "EmailID")
    public String emailID;

    @ColumnInfo(name = "FirstName")
    public String firstName;

    @ColumnInfo(name = "LastName")
    public String lastName;

    @ColumnInfo(name="Mobilenumber")
    public String mobilenumber;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "Password")
    public String password;

    public void setuID(int uID) {
        this.uID = uID;
    }



    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getuID() {
        return uID;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    //    public void setUID(int UID) {
//        this.UID = UID;
//    }
//
//    public void setFirstName(String firstName) {
//        FirstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        LastName = lastName;
//    }
//
//    public void setEmailID(String emailID) {
//        EmailID = emailID;
//    }
//
//    public void setPassword(String password) {
//        Password = password;
//    }
//
//    public void setMobilenumber(String mobilenumber) {
//        Mobilenumber = mobilenumber;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public int getUID() {
//        return UID;
//    }
//
//    public String getEmailID() {
//        return EmailID;
//    }
//
//    public String getFirstName() {
//        return FirstName;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getLastName() {
//        return LastName;
//    }
//
//    public String getMobilenumber() {
//        return Mobilenumber;
//    }
//
//    public String getPassword() {
//        return Password;
//    }
}
