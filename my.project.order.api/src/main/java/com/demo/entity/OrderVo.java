package com.demo.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class OrderVo implements Serializable {
    private String oid;
    private String uid;
    private String gid;
    private Float price;
    private String OrderName;

}
