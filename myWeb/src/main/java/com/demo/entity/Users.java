package com.demo.entity;
import java.io.Serializable;
import java.util.Date;

/**
*   pojo of users
*/

public class Users implements Serializable {
    //
    private Integer id;
    //
    private String userName;
    //
    private String userPwd;
    //
    private String realName;
    //getter setter
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setUserName (String  userName){
        this.userName=userName;
    }
    public  String getUserName(){
        return this.userName;
    }
    public void setUserPwd (String  userPwd){
        this.userPwd=userPwd;
    }
    public  String getUserPwd(){
        return this.userPwd;
    }
    public void setRealName (String  realName){
        this.realName=realName;
    }
    public  String getRealName(){
        return this.realName;
    }
}
