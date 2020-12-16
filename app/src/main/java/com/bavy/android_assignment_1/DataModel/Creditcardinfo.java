package com.bavy.android_assignment_1.DataModel;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Creditcardinfo", foreignKeys = {
        @ForeignKey(
                entity = Userinfo.class,
                parentColumns = "UID",
                childColumns = "U_ID",
                onDelete = ForeignKey.CASCADE
        )})

public class Creditcardinfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CID")
    public int cID;

    @ColumnInfo(name = "Creditcardno")
    public String cno;

    @ColumnInfo(name = "Nameoncard")
    public String cname;

    @ColumnInfo(name = "Expiry Date")
    public String expdate;

    @ColumnInfo(name = "CVVno")
    public String cvvno;

    @ColumnInfo(name = "U_ID")
    public int uID;

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getcID() {
        return cID;
    }

    public int getuID() {
        return uID;
    }

    public String getCno() {
        return cno;
    }

    public String getCname() {
        return cname;
    }

    public String getCvvno() {
        return cvvno;
    }

    public String getExpdate() {return expdate;}

    public void setCno(String cno) {
        this.cno = cno;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCvvno(String cvvno) {
        this.cvvno = cvvno;
    }

    public void setExpdate(String expdate) {this.expdate = expdate;}
}

