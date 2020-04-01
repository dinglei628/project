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
public class Messages {
    private String id;
    private String describe;
    private String userId;
    private String way;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss", timezone = "GMT-16")
    private Date time;
    private String type;

}
