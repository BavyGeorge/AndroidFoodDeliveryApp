package com.bavy.android_assignment_1.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "Address", foreignKeys = {
        @ForeignKey(
                entity = Userinfo.class,
                parentColumns = "UID",
                childColumns = "U_ID",
                onDelete = ForeignKey.CASCADE
        )
        }
)

public class Address implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ADDRESS_ID")
    public int address_id;

    @ColumnInfo(name = "Street or Apartment number")
    public String address_sa_no;

    @ColumnInfo(name = "Street name")
    public String address_streetname;

    @ColumnInfo(name = "City or Town")
    public String address_citytown;

    @ColumnInfo(name = "Post Code")
    public String address_postcode;

    @ColumnInfo(name = "U_ID")
    public int uID;

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setAddress_sa_no(String address_sa_no) {
        this.address_sa_no = address_sa_no;
    }

    public void setAddress_citytown(String address_citytown) {
        this.address_citytown = address_citytown;
    }

    public void setAddress_postcode(String address_postcode) {
        this.address_postcode = address_postcode;
    }

    public void setAddress_streetname(String address_streetname) {
        this.address_streetname = address_streetname;
    }

    public int getAddress_id() {
        return address_id;
    }

    public int getuID() {
        return uID;
    }

    public String getAddress_sa_no() {
        return address_sa_no;
    }

    public String getAddress_citytown() {
        return address_citytown;
    }

    public String getAddress_postcode() {
        return address_postcode;
    }

    public String getAddress_streetname() {
        return address_streetname;
    }
}
