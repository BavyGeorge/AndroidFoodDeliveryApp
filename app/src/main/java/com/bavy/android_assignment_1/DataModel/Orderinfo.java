package com.bavy.android_assignment_1.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

@Entity(tableName = "Orderinfo", foreignKeys = {
        @ForeignKey(
                entity = Userinfo.class,
                parentColumns = "UID",
                childColumns = "U_ID",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Creditcardinfo.class,
                parentColumns = "CID",
                childColumns = "C_ID",
                onDelete = ForeignKey.CASCADE

        ),
        @ForeignKey(
                entity = Address.class,
                parentColumns = "ADDRESS_ID",
                childColumns = "ADDRESS_ID_",
                onDelete = ForeignKey.CASCADE
        )
}
)
public class Orderinfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "OID")
    public int oid;

    @ColumnInfo(name = "C_ID")
    public int cID;

    @ColumnInfo(name = "ADDRESS_ID_")
    public int address_id_;

    @ColumnInfo(name = "U_ID")
    public int uID;

    @ColumnInfo(name = "selectedcard")
    public String selectedcard;

    @ColumnInfo(name = "Order1")
    public String order;

    @ColumnInfo(name= "Dressing1")
    public String dressing;

    @ColumnInfo(name = "Delivery_location")
    public String dlocations;

    @ColumnInfo(name = "Timings")
    public String timing;

    @ColumnInfo(name = "Quantity needed1")
    public String quantityneed;

    @ColumnInfo(name = "order1note")
    public String ordernote;

    @ColumnInfo(name = "selectedaddress")
    public String selectedaddress;

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public void setAddress_id_(int address_id_) {
        this.address_id_ = address_id_;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setSelectedcard(String selectedcard) {
        this.selectedcard = selectedcard;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }

    public void setQuantityneed(String quantityneed) {
        this.quantityneed = quantityneed;
    }

    public void setOrdernote(String ordernote) {
        this.ordernote = ordernote;
    }

    public void setDlocations(String dlocations) {
        this.dlocations = dlocations;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public void setSelectedaddress(String selectedaddress) {
        this.selectedaddress = selectedaddress;
    }
}
