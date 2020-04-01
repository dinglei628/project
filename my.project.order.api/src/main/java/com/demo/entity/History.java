package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private String id;
    private String orderId;
    private String orderName;
    private String userId;
    private String userName;
    private String goodId;
    private String goodName;
    private Integer ispay;
    private Float orderPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private Date orderCreateDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private Date payDate;


}
