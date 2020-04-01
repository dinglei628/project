package com.zb.pojo;

import java.io.Serializable;

public class Job implements Serializable {
    private Integer id;
    private  Integer varsion;
    private   Integer   scoreid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVarsion() {
        return varsion;
    }

    public void setVarsion(Integer varsion) {
        this.varsion = varsion;
    }

    public Integer getScoreid() {
        return scoreid;
    }

    public void setScoreid(Integer scoreid) {
        this.scoreid = scoreid;
    }
}
