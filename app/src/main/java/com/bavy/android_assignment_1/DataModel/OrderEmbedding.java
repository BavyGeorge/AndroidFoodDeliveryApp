package com.bavy.android_assignment_1.DataModel;

import androidx.room.Embedded;

import java.io.Serializable;

public class OrderEmbedding implements Serializable {

    @Embedded
    Address address;

    @Embedded
    Creditcardinfo creditcardinfo;

    @Embedded
    Userinfo userinfo;

    @Embedded
    Orderinfo orderinfo;

    public Address getAddress() {
        return address;
    }

    public Creditcardinfo getCreditcardinfo() {
        return creditcardinfo;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public Orderinfo getOrderinfo() {
        return orderinfo;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void setCreditcardinfo(Creditcardinfo creditcardinfo) {
        this.creditcardinfo = creditcardinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public void setOrderinfo(Orderinfo orderinfo) {
        this.orderinfo = orderinfo;
    }
}
