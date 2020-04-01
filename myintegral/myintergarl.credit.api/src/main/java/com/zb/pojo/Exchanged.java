package com.zb.pojo;

import java.io.Serializable;

public class Exchanged implements Serializable {
    private Integer id;
    private Integer User_id;
    private Integer exchanged_credit;
    private Integer product_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return User_id;
    }

    public void setUser_id(Integer user_id) {
        User_id = user_id;
    }

    public Integer getExchanged_credit() {
        return exchanged_credit;
    }

    public void setExchanged_credit(Integer exchanged_credit) {
        this.exchanged_credit = exchanged_credit;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
}
