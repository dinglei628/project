package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Article;
import com.zb.mapper.ArticleSearchRepository;
import com.zb.vo.ArticleVo;
import com.zb.util.Page;
import com.zb.mapper.ArticleMapper;
import com.zb.service.ArticleService;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtil;
import com.zb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @Override
    public Page show(String type,int index,int show) {
        Page p = new Page();
        p.setPageSize(Util.PAGE_SIZE);
        if(type.equals("null")){
            type="";
        }
        p.setTotalCount(articleMapper.getArticleCount(type));
        p.setCurrSize(index);
        Map<String,Object> map = new HashMap<>();
        map.put("page",p);
        map.put("type",type);
        map.put("show",show);
        List<Article> articles = articleMapper.getShow(map);
        List<ArticleVo> list = new ArrayList<>();
        for (Article article : articles) {
            ArticleVo articleVo = null;
            if(!redisUtil.hHasKey("articleId:"+article.getId(),"browse_number")){
                articleVo = new ArticleVo();
                articleVo.setBrowse_number("0");
                articleVo.setData(article);
                list.add(articleVo);
            }else{
                String json =(String) redisUtil.hget("articleId:"+article.getId(),"browse_number");
                System.out.println(json);
                articleVo = new ArticleVo();
                articleVo.setBrowse_number(json);
                if(articleVo.getBrowse_number()==null){
                    articleVo.setBrowse_number("0");
                    articleVo.setData(article);
                }else{
                    articleVo.setData(article);
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
        if (type==null || type=="" || type.equals("null")){
            System.out.println("type");
            p.setPageSize(Util.PAGE_SIZE);
            Long length = redisUtil.lGetListSize("ids:articleId");
            Integer length1 = length.intValue();
            p.setTotalCount(length1);
            p.setCurrSize(index);
            List<Object>  list = redisUtil.
                    sortPageList("ids:articleId","articleId:","browse_number",true,false,p.getStart(),p.getPageSize());
            for (Object o : list) {
                ArticleVo<Article> articleVo = new ArticleVo();
                if(redisUtil.hasKey("articleEntity:"+o.toString())){
                    System.out.println("redis");
                    String json = redisUtil.get("articleEntity:"+o.toString()).toString();
                    Article article = JSON.parseObject(json,Article.class);
                    articleVo.setData(article);
                    String browse_number = (String)redisUtil.hget("articleId:"+o.toString(),"browse_number");
                    articleVo.setBrowse_number(browse_number);
                    articleList.add(articleVo);

                }else{
                    articleVo.setData(articleMapper.getShowById(o.toString()));
                    String json =(String) redisUtil.hget("articleId:"+articleVo.getData().getId(),"browse_number");
                    articleVo.setBrowse_number(json);
                    articleList.add(articleVo);
                }
            }

            p.setList(articleList);
        } else {
            System.out.println("notype");
            p.setPageSize(Util.PAGE_SIZE);
            Long length = redisUtil.lGetListSize("ids:articleId:"+type);
            Integer length1 = length.intValue();
            p.setTotalCount(length1);
            p.setCurrSize(index);
            System.out.println(length1);
            List<Object>  list = redisUtil.
                    sortPageList("ids:articleId:"+type,"articleId:","browse_number",true,false,p.getStart(),p.getPageSize());
            for (Object o : list) {
                ArticleVo<Article> articleVo = new ArticleVo();
                if(redisUtil.hasKey("articleEntity:"+o.toString())){
                    System.out.println("redis");
                    String json = redisUtil.get("articleEntity:"+o.toString()).toString();
                    Article article = JSON.parseObject(json,Article.class);
                    articleVo.setData(article);
                    String browse_number = (String)redisUtil.hget("articleId:"+o.toString(),"browse_number");
                    articleVo.setBrowse_number(browse_number);
                    articleList.add(articleVo);

                }else{
                    articleVo.setData(articleMapper.getShowById(o.toString()));
                    String json =(String) redisUtil.hget("articleId:"+articleVo.getData().getId(),"browse_number");
                    articleVo.setBrowse_number(json);
                    articleList.add(articleVo);
                }
            }

            p.setList(articleList);
        }
        return p;
    }


    @Override
    public ArticleVo findShowById(String id) {
        Article article =null;
        if(!redisUtil.hasKey("articleEntity:"+id)){
            article = articleMapper.getShowById(id);
            Random random = new Random();
            Long ram = Long.parseLong(random.nextInt(1000)+"");
            String jsonEntity = JSON.toJSONString(article);
            redisUtil.set("articleEntity:"+id,jsonEntity,ram);
            redisUtil.hincr("articleId:"+id,"browse_number",1F);
        }else{
            String json = redisUtil.get("articleEntity:"+id).toString();
            redisUtil.hincr("articleId:"+id,"browse_number",1F);
            article = JSON.parseObject(json,Article.class);
        }
        String setBrowse_number = (String)redisUtil.hget("articleId:"+id,"browse_number");

        ArticleVo articleVo = new ArticleVo();
        articleVo.setData(article);
        articleVo.setBrowse_number(setBrowse_number);
        return articleVo;
    }

    @Override
    public Boolean insertArticle(Article article) {
        article.setId(IdWorker.getId());
        article.setStats("1");
        int cnt = articleMapper.insertArticle(article);
        LocalDateTime local = LocalDateTime.now();
        article.setCreate_time(new Date());
        articleSearchRepository.save(article);
        if(cnt>0){
            redisUtil.lSet("ids:articleId",article.getId());
            redisUtil.lSet("ids:articleId:"+article.getType(),article.getId());
            redisUtil.hset("articleId:"+article.getId(),"browse_number","0");
            return true;
        }
        return false;
    }
}
