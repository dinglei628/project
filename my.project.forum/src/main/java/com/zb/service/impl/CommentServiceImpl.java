package com.zb.service.impl;

import com.mongodb.WriteResult;
import com.zb.config.CommentRabbitConfig;
import com.zb.controller.ArticleController;
import com.zb.dto.Dto;
import com.zb.entity.Article;
import com.zb.entity.Comment;
import com.zb.entity.CommentMessage;
import com.zb.service.CommentService;
import com.zb.util.Page;
import com.zb.util.RedisUtil;
import com.zb.util.Util;
import com.zb.vo.ArticleVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Dto<Comment> insertComment(Comment comment) {
        Dto<Comment> dto = new Dto<>();
        comment.setCreateDateTime(new Date());
        comment.setState("0");
        CommentMessage commentMessage = new CommentMessage();
        if(comment.getParentId().equals("0")){
            comment.setLikeNum(0);
            comment.setReplyNum(0);
            String toUserId = comment.getArticleUserId();
            String fromUserId = comment.getUserId();
            commentMessage.setFromUserId(fromUserId);
            commentMessage.setToUserId(toUserId);
            commentMessage.setFromUserName(comment.getNickName());
            commentMessage.setToUserName(comment.getCreateArticleName());
            commentMessage.setArticleId(comment.getArticleId());
            commentMessage.setMessage("评论了你的帖子，快去看看把");
            commentMessage.setState("0");
        }else{
            String fromUserId = comment.getUserId();
            Query query = new Query(Criteria.where("_id").is(comment.getParentId()));
            Comment comment1 = mongoTemplate.findOne(query,Comment.class);
            Update update = new Update().inc("replyNum",1);
            mongoTemplate.updateMulti(query,update,Comment.class);
            commentMessage.setToUserId(comment1.getUserId());
            commentMessage.setFromUserId(fromUserId);
            commentMessage.setFromUserName(comment.getNickName());
            commentMessage.setMessage("回复了你的评论，快去看看把");
            commentMessage.setState("0");
            commentMessage.setArticleId(comment.getArticleId());
        }
        rabbitTemplate.convertAndSend(CommentRabbitConfig.EXCHANGE_TOPIC_COMMENT,
                "inform.comment", commentMessage);

        try {
            mongoTemplate.save(comment);
        }catch (Exception e){
            dto.setErrorCode("40001");
            dto.setMsg("评论失败");
        }
        dto.setMsg("成功");
        return dto;
    }

    @Override
    public List<CommentMessage> findShowMessage(String userId) {
        Query query = new Query(Criteria.where("toUserId").is(userId).and("state").is("0"));
        return mongoTemplate.find(query,CommentMessage.class);
    }

    @Override
    public void updateCommentByState(CommentMessage commentMessage) {
        Query query = new Query(Criteria.where("_id").is(commentMessage.getId()));
        Update update = new Update().set("state","1");
        mongoTemplate.updateMulti(query,update,CommentMessage.class);
    }

    @RabbitListener(queues = CommentRabbitConfig.QUEUE_COMMENT)
    public void show(CommentMessage commentMessage){
        mongoTemplate.save(commentMessage);
    }


    @Override
    public Page<Comment> findCommentShow(int index,String articleId) {
        Page<Comment> page = new Page<>();
        Query query = new Query(Criteria.where("parentId").is("0")
                .and("articleId").is(articleId));
        int count = (int)mongoTemplate.count(query,Comment.class);
        if(count==0){
            return page;
        }
        System.out.println(count);
        page.setPageSize(Util.COMMENT_PAGE_SIZE);
        page.setTotalCount(count);
        page.setCurrSize(index);
        query.limit(page.getPageSize());
        query.skip(page.getStart());
        query.with(new Sort(Sort.Direction.DESC, "createDateTime"));
        List<Comment> list = mongoTemplate.find(query,Comment.class);
        page.setList(list);
        return page;
    }

    @Override
    public WriteResult updateCommentByStats(String id,String state) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("state",state);
        WriteResult flag = mongoTemplate.updateMulti(query,update,Comment.class);
        return flag;
    }

    @Override
    public WriteResult updateCommentByLikeNum(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().inc("likeNum",1);
        WriteResult writeResult = mongoTemplate.updateMulti(query,update,Comment.class);
        return writeResult;
    }


    public WriteResult updateCommentByReplyNum(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().inc("replyNum",1);
        WriteResult writeResult = mongoTemplate.updateMulti(query,update,Comment.class);
        return writeResult;
    }
}
