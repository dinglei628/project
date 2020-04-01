package com.zb.mapper;

import com.zb.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Mapper
@Component
public interface ArticleMapper {
    public Integer getArticleCount(@Param("type") String type);

    public List<Article> getShow(Map<String,Object> map);

    public Article getShowById(@Param("id") String id);

    public Integer insertArticle(Article article);

}
