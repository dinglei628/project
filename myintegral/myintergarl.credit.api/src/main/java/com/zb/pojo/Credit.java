package com.zb.pojo;

import java.io.Serializable;

public class Credit implements Serializable {
    private Integer id;
    private Integer User_id;
    private Integer Credit;

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

    public Integer getCredit() {
        return Credit;
    }

    public void setCredit(Integer credit) {
        Credit = credit;
    }
}
