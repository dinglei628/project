package com.zb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@Document(indexName = "ant_community",type="article")
public class Article implements Serializable {
    @Id
    private String id;
    @Field(type = FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;
    private String user_id;
    @Field(type = FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content;
    @Field(type = FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String type;
    @Field(type = FieldType.Date)
    private Date create_time;
    private String stats;


}
