package com.demo.entity;
import java.io.Serializable;
import java.util.Date;

/**
*   pojo of product
*/
public class Product implements Serializable {
    //
    private Integer id;
    //
    private String productName;
    //
    private Integer quantity;
    //getter setter
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setProductName (String  productName){
        this.productName=productName;
    }
    public  String getProductName(){
        return this.productName;
    }
    public void setQuantity (Integer  quantity){
        this.quantity=quantity;
    }
    public  Integer getQuantity(){
        return this.quantity;
    }
}
