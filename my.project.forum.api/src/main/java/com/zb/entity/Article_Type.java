package com.zb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class Article_Type implements Serializable {
    private String id;
    private String type_name;
}
