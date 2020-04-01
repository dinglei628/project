package com.zb.service;

import com.mongodb.WriteResult;
import com.zb.dto.Dto;
import com.zb.entity.Article;
import com.zb.entity.Comment;
import com.zb.entity.CommentMessage;
import com.zb.util.Page;
import com.zb.vo.ArticleVo;

import java.util.List;

public interface CommentService {

    public Dto<Comment> insertComment(Comment comment);

    public Page<Comment> findCommentShow(int index,String articleId);

    public WriteResult updateCommentByStats(String id,String state);

    public WriteResult updateCommentByLikeNum(String id);

    public List<CommentMessage> findShowMessage(String userId);

    public void updateCommentByState(CommentMessage commentMessage);


}
