package com.zb.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class Problem implements Serializable {
    private String id;
    private String title;
    private String user_id;
    private String content;
    private String type;
    private Date create_time;
    private String stats;
}
