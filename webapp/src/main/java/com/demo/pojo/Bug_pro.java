package com.demo.pojo;
import java.io.Serializable;

/**
*   pojo of bug_pro
*/
public class Bug_pro implements Serializable {
    //
    private Integer id;
    //
    private String name;
    //getter setter
    public void setId (Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setName (String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
