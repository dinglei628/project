package com.zb.mapper;

import com.zb.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Administrator
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, String> {

}