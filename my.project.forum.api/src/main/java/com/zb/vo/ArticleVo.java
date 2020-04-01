package com.zb.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class ArticleVo<T> implements Serializable {
    private T Data;
    private String browse_number;
}
