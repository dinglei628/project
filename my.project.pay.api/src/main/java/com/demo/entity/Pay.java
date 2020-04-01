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
public class Pay implements Serializable {
    //id orderId isPay userId goodId way createDate updateDate
    private String id;
    private String orderId;
    private String subject;
    private Float amount;
    private String isPay;
    private String way;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private String createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private String mentDate;
}
