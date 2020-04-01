package com.zb.entity;

import java.io.Serializable;

public class order{
    private Integer id;
    private String info;
    private Integer uid;

    public order() {
    }

    public order(Integer id, String info, Integer uid) {
        this.id = id;
        this.info = info;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
