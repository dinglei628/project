package com.zb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 14914
 * @date 2020-03-20
 */
@Data
public class Message implements Serializable {

    String id;
    String fromId;
    String toId;
    String conversationId;
    String status;
    Date createTime;
    String content;
}
