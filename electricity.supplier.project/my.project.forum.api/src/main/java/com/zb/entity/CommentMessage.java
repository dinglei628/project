package com.zb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@Document(collection = "message")
public class CommentMessage implements Serializable {
    @Id
    private String id;

    private String fromUserId;

    private String toUserId;

    private String toUserName;

    private String fromUserName;

    private String message;

    private String state;

    private String articleId;
}
