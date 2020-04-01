package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.entity.Article;
import com.zb.entity.Problem;
import com.zb.service.ArticleService;
import com.zb.service.ProblemService;
import com.zb.util.Page;
import com.zb.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping("show")
    public Dto<Page> Show(@RequestParam("seeType") String seeType,
                    @RequestParam(name = "type",required = false,defaultValue = "null") String type,
                    @RequestParam("index") int index,
                    @RequestParam("show") int show){
        Page page = null;
        System.out.println(type);
        if(seeType.equals("1")){
            page = problemService.show(type, index, show);
        }else{
            page = problemService.seeHot(type, index, show);
        }
        Dto<Page> dto = new Dto<>();
        if(page != null){
            dto.setSuccess("4001");
            dto.setMsg("成功");
            dto.setData(page);
        }else{
            dto.setErrorCode("00000");
            dto.setMsg("没有内容");
        }
        return dto;
    }

    @RequestMapping("showByYes")
    public Dto<Page> showByYes(@RequestParam(name = "type",required = false,defaultValue = "null") String type,
                          @RequestParam("index") int index,
                          @RequestParam("show") int show){
        Page page = null;
        System.out.println(type);
        page = problemService.showByYes(type, index, show);

        Dto<Page> dto = new Dto<>();
        if(page != null){
            dto.setSuccess("4001");
            dto.setMsg("成功");
            dto.setData(page);
        }else{
            dto.setErrorCode("00000");
            dto.setMsg("没有内容");
        }
        return dto;
    }

    @RequestMapping("showByNo")
    public Dto<Page> showByNo(@RequestParam(name = "type",required = false,defaultValue = "null") String type,
                               @RequestParam("index") int index,
                               @RequestParam("show") int show){
        Page page = null;
        System.out.println(type);
        page = problemService.showByNo(type, index, show);
        Dto<Page> dto = new Dto<>();
        if(page != null){
            dto.setSuccess("4001");
            dto.setMsg("成功");
            dto.setData(page);
        }else{
            dto.setErrorCode("00000");
            dto.setMsg("没有内容");
        }
        return dto;
    }


    @RequestMapping("showById")
    public Dto<ArticleVo> showById(@RequestParam("id") String id){
        ArticleVo articleVo = problemService.findShowById(id);
        Dto<ArticleVo> dto = new Dto<>();
        if(articleVo != null){
            dto.setSuccess("4001");
            dto.setMsg("成功");
            dto.setData(articleVo);
        }else{
            dto.setErrorCode("00000");
            dto.setMsg("没有内容");
        }
        return dto;
    }


    @RequestMapping("insertArticle")
    public Dto<ArticleVo> insertArticle(Problem problem){
        Boolean flag = problemService.insertArticle(problem);
        Dto<ArticleVo> dto = new Dto<>();
        if(flag){
            dto.setSuccess("4001");
            dto.setMsg("成功");
        }else{
            dto.setErrorCode("00000");
            dto.setMsg("没有内容");
        }
        return dto;
    }
}
