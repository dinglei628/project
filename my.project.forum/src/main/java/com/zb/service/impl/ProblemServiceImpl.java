package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Article;
import com.zb.entity.Problem;
import com.zb.mapper.ArticleMapper;
import com.zb.mapper.ArticleSearchRepository;
import com.zb.mapper.ProblemMapper;
import com.zb.service.ProblemService;
import com.zb.util.IdWorker;
import com.zb.util.Page;
import com.zb.util.RedisUtil;
import com.zb.util.Util;
import com.zb.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired(required = false)
    private ProblemMapper problemMapper;

    @Override
    public Page show(String type, int index, int show) {
        Page p = new Page();
        p.setPageSize(Util.PAGE_SIZE);
        if(type.equals("null")){
            type="";
        }
        p.setTotalCount(problemMapper.getArticleCount(type));
        p.setCurrSize(index);
        Map<String,Object> map = new HashMap<>();
        map.put("page",p);
        map.put("type",type);
        map.put("show",show);
        List<Problem> problems = problemMapper.getShow(map);
        List<ArticleVo> list = new ArrayList<>();
        for (Problem problem : problems) {
            ArticleVo articleVo = null;
            if(!redisUtil.hHasKey("problemId:"+problem.getId(),"browse_number")){
                articleVo = new ArticleVo();
                articleVo.setBrowse_number("0");
                articleVo.setData(problem);
                list.add(articleVo);
            }else{
                String json =(String) redisUtil.hget("problemId:"+problem.getId(),"browse_number");
                articleVo = new ArticleVo();
                articleVo.setBrowse_number(json);
                if(articleVo.getBrowse_number()==null){
                    articleVo.setBrowse_number("0");
                    articleVo.setData(problem);
                }else{
                    articleVo.setData(problem);
                }
                list.add(articleVo);
            }
        }
        p.setList(list);
        return p;
    }

    @Override
    public Page seeHot(String type, int index, int show) {
        List<ArticleVo> articleList = new ArrayList<>();
        Page p = new Page();
        System.out.println(type);
        if (type==null || type=="" || type.equals("null")){
            System.out.println("type");
            p.setPageSize(Util.PAGE_SIZE);
            Long length = redisUtil.lGetListSize("ids:problemId");
            Integer length1 = length.intValue();
            p.setTotalCount(length1);
            p.setCurrSize(index);
            List<Object>  list = redisUtil.
                    sortPageList("ids:problemId","problemId:","browse_number"
                            ,true,false,p.getStart(),p.getPageSize());
            for (Object o : list) {
                ArticleVo<Problem> articleVo = new ArticleVo();
                if(redisUtil.hasKey("problemEntity:"+o.toString())){
                    System.out.println("redis");
                    String json = redisUtil.get("problemEntity:"+o.toString()).toString();
                    Problem problem = JSON.parseObject(json,Problem.class);
                    articleVo.setData(problem);
                    String browse_number = (String)redisUtil.hget("problemId:"+o.toString(),"browse_number");
                    articleVo.setBrowse_number(browse_number);
                    articleList.add(articleVo);

                }else{
                    articleVo.setData(problemMapper.getShowById(o.toString()));
                    String json =(String) redisUtil.hget("problemId:"+articleVo.getData().getId(),"browse_number");
                    articleVo.setBrowse_number(json);
                    articleList.add(articleVo);
                }
            }

            p.setList(articleList);
        } else {
            System.out.println("notype");
            p.setPageSize(Util.PAGE_SIZE);
            Long length = redisUtil.lGetListSize("ids:problemId:"+type);
            Integer length1 = length.intValue();
            p.setTotalCount(length1);
            p.setCurrSize(index);
            System.out.println(length1);
            List<Object>  list = redisUtil.
                    sortPageList("ids:problemId:"+type,"problemId:","browse_number",true,false,p.getStart(),p.getPageSize());
            for (Object o : list) {
                ArticleVo<Problem> articleVo = new ArticleVo();
                if(redisUtil.hasKey("problemEntity:"+o.toString())){
                    System.out.println("redis");
                    String json = redisUtil.get("problemEntity:"+o.toString()).toString();
                    Problem problem = JSON.parseObject(json,Problem.class);
                    articleVo.setData(problem);
                    String browse_number = (String)redisUtil.hget("problemId:"+o.toString(),"browse_number");
                    articleVo.setBrowse_number(browse_number);
                    articleList.add(articleVo);

                }else{
                    articleVo.setData(problemMapper.getShowById(o.toString()));
                    String json =(String) redisUtil.hget("problemId:"+articleVo.getData().getId(),"browse_number");
                    articleVo.setBrowse_number(json);
                    articleList.add(articleVo);
                }
            }

            p.setList(articleList);
        }
        return p;
    }

    @Override
    public Page showByYes(String type, int index, int show) {
        Page p = new Page();
        p.setPageSize(Util.PAGE_SIZE);
        if(type.equals("null")){
            type="";
        }
        p.setTotalCount(problemMapper.getProblemCountByYes(type));
        if(p.getTotalCount()==null || p.getTotalCount()==0){
            return null;
        }
        p.setCurrSize(index);
        Map<String,Object> map = new HashMap<>();
        map.put("page",p);
        map.put("type",type);
        map.put("show",show);
        List<Problem> problems = problemMapper.getProblemCountByYes(map);
        List<ArticleVo> list = new ArrayList<>();
        for (Problem problem : problems) {
            ArticleVo articleVo = null;
            if(!redisUtil.hHasKey("problemId:"+problem.getId(),"browse_number")){
                articleVo = new ArticleVo();
                articleVo.setBrowse_number("0");
                articleVo.setData(problem);
                list.add(articleVo);
            }else{
                String json =(String) redisUtil.hget("problemId:"+problem.getId(),"browse_number");
                articleVo = new ArticleVo();
                articleVo.setBrowse_number(json);
                if(articleVo.getBrowse_number()==null){
                    articleVo.setBrowse_number("0");
                    articleVo.setData(problem);
                }else{
                    articleVo.setData(problem);
                }
                list.add(articleVo);
            }
        }
        p.setList(list);
        return p;
    }

    @Override
    public Page showByNo(String type, int index, int show) {
        Page p = new Page();
        p.setPageSize(Util.PAGE_SIZE);
        if(type.equals("null")){
            type="";
        }
        p.setTotalCount(problemMapper.getProblemCountByNo(type));
        p.setCurrSize(index);
        Map<String,Object> map = new HashMap<>();
        map.put("page",p);
        map.put("type",type);
        map.put("show",show);
        List<Problem> problems = problemMapper.getShowByNo(map);
        List<ArticleVo> list = new ArrayList<>();
        for (Problem problem : problems) {
            ArticleVo articleVo = null;
            if(!redisUtil.hHasKey("problemId:"+problem.getId(),"browse_number")){
                articleVo = new ArticleVo();
                articleVo.setBrowse_number("0");
                articleVo.setData(problem);
                list.add(articleVo);
            }else{
                String json =(String) redisUtil.hget("problemId:"+problem.getId(),"browse_number");
                articleVo = new ArticleVo();
                articleVo.setBrowse_number(json);
                if(articleVo.getBrowse_number()==null){
                    articleVo.setBrowse_number("0");
                    articleVo.setData(problem);
                }else{
                    articleVo.setData(problem);
                }
                list.add(articleVo);
            }
        }
        p.setList(list);
        return p;
    }


    @Override
    public ArticleVo findShowById(String id) {
        Problem problem =null;
        if(!redisUtil.hasKey("problemEntity:"+id)){
            problem = problemMapper.getShowById(id);
            Random random = new Random();
            Long ram = Long.parseLong(random.nextInt(1000)+"");
            String jsonEntity = JSON.toJSONString(problem);
            redisUtil.set("problemEntity:"+id,jsonEntity,ram);
            redisUtil.hincr("problemId:"+id,"browse_number",1F);
        }else{
            String json = redisUtil.get("problemEntity:"+id).toString();
            redisUtil.hincr("problemId:"+id,"browse_number",1F);
            problem = JSON.parseObject(json,Problem.class);
        }
        String setBrowse_number = (String)redisUtil.hget("problemId:"+id,"browse_number");

        ArticleVo articleVo = new ArticleVo();
        articleVo.setData(problem);
        articleVo.setBrowse_number(setBrowse_number);
        return articleVo;
    }

    @Override
    public Boolean insertArticle(Problem problem) {
        problem.setId(IdWorker.getId());
        problem.setStats("1");
        int cnt = problemMapper.insertArticle(problem);
        LocalDateTime local = LocalDateTime.now();
        problem.setCreate_time(new Date());
        if(cnt>0){
            redisUtil.lSet("ids:problemId",problem.getId());
            redisUtil.lSet("ids:problemId:"+problem.getType(),problem.getId());
            redisUtil.hset("problemId:"+problem.getId(),"browse_number","0");
            return true;
        }
        return false;
    }
}
