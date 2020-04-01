package com.zb.controller;

import com.mongodb.WriteResult;
import com.zb.dto.Dto;
import com.zb.entity.Comment;
import com.zb.entity.CommentMessage;
import com.zb.service.CommentService;
import com.zb.util.Page;
import com.zb.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 */
@RestController()
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("insert")
    public Dto<Comment> insertComment(Comment comment){
        return commentService.insertComment(comment);
    }

    @RequestMapping("findCommentShow")
    public Dto<Page> findCommentShow(@RequestParam("index") int index,
                                     @RequestParam("articleId") String articleId){
        Dto<Page> dto = new Dto<>();
        Page<Comment> page = commentService.findCommentShow(index,articleId);
        dto.setData(page);
        return dto;
    }

    @RequestMapping("updateCommentByStats")
    public Dto updateCommentByStats(@RequestParam("id") String id,
                                    @RequestParam("state") String state){
        Dto dto = new Dto<>();
        WriteResult writeResult = commentService.updateCommentByStats(id,state);
        if(writeResult.isUpdateOfExisting()){
            dto.setSuccess("40000");
            dto.setMsg("成功");
        }else{
            dto.setSuccess("40001");
            dto.setMsg("失败");
        }
        return dto;
    }


    @RequestMapping("updateCommentByLikeNum")
    public Dto updateCommentByLikeNum(@RequestParam("id") String id){
        Dto dto = new Dto<>();
        WriteResult writeResult = commentService.updateCommentByLikeNum(id);
        if(writeResult.isUpdateOfExisting()){
            dto.setSuccess("40000");
            dto.setMsg("成功");
        }else{
            dto.setSuccess("40001");
            dto.setMsg("失败");
        }
        return dto;
    }

    @RequestMapping("findShowMessage")
    public List<CommentMessage> findShowMessage(@RequestParam("userId") String userId){
        return commentService.findShowMessage(userId);
    }

    @RequestMapping("updateCommentByState")
    public String updateCommentByState(CommentMessage commentMessage){
        commentService.updateCommentByState(commentMessage);
        return "成功";
    }
}
