package com.zb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@Document(collection = "comment")
@CompoundIndex(def = "{'userId':1,'createDateTime':1}")
public class Comment implements Serializable {

    /**
     * 评论id
     */
    @Id
    private String id;
    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章用户id
     */
    private String articleUserId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人id
     */
    private String userId;

    /**
     * 评论人昵称
     */
    private String nickName;

    /**
     * 评论时间
     */
    private Date createDateTime;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 回复数
     */
    private Integer replyNum;

    /**
     * 状态id
     */
    private String state;

    /**
     * 上级id，为0表示文章的顶级id
     */
    private String parentId;

    /**
     * 文章用户名称
     */
    private String createArticleName;
}
