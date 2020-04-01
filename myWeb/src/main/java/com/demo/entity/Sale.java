package com.demo.entity;
import java.io.Serializable;
import java.util.Date;

/**
*   pojo of sale
*/
public class Sale implements Serializable {
    //
    private Integer id;
    //
    private Float price;
    //
    private Integer quantity;
    //
    private Float totalPrice;
    //
    private Date saleDate;
    //
    private Integer userId;
    //
    private Integer productId;
    private String productName;
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    //getter setter
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setPrice (Float  price){
        this.price=price;
    }
    public  Float getPrice(){
        return this.price;
    }
    public void setQuantity (Integer  quantity){
        this.quantity=quantity;
    }
    public  Integer getQuantity(){
        return this.quantity;
    }
    public void setTotalPrice (Float  totalPrice){
        this.totalPrice=totalPrice;
    }
    public  Float getTotalPrice(){
        return this.totalPrice;
    }
    public void setSaleDate (Date  saleDate){
        this.saleDate=saleDate;
    }
    public  Date getSaleDate(){
        return this.saleDate;
    }
    public void setUserId (Integer  userId){
        this.userId=userId;
    }
    public  Integer getUserId(){
        return this.userId;
    }
    public void setProductId (Integer  productId){
        this.productId=productId;
    }
    public  Integer getProductId(){
        return this.productId;
    }
}
