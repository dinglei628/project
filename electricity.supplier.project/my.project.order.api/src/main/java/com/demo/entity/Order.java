package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    //id userId goodId STATUS price createDate updateDate deleteDate
    private String id;
    private String orderName;
    private String userId;
    private String goodId;
    private Integer status;
    private Float price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private Date updateDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-8")
    private Date deleteDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-8")
    private Date payTime;

}
