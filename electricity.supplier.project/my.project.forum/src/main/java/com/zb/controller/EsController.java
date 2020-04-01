package com.zb.controller;

import com.zb.entity.Article;
import com.zb.mapper.ArticleSearchRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class EsController {
    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/query")
    public List<Article> testSearch(@RequestParam("queryString") String queryString,
                                    @RequestParam("page") int page,
                                    @RequestParam("size") int size) {
        // 分页参数
        Pageable pageable = new PageRequest(page-1, size);
        //排序字段
        SortBuilder sortBuilder = new FieldSortBuilder("create_time")
                .order(SortOrder.DESC);
        //查询条件
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);

        //高亮字段
        HighlightBuilder.Field field = new HighlightBuilder.Field("*")
                //关闭检索字段匹配
                .requireFieldMatch(false)
                .preTags("<span style = 'color:red'>")
                .postTags("</span>");
        //组合查询条件
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("ant_community")
                .withTypes("article")
                .withQuery(builder)
                //.withFilter(QueryBuilders.rangeQuery("stats"))
                .withSort(sortBuilder)
                .withPageable(pageable)
                .withHighlightFields(field)
                .build();
        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(searchQuery, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<Article> list = new ArrayList<>();

                SearchHits hits = searchResponse.getHits();
                SearchHit[] searchHits = hits.getHits();
                for (SearchHit searchHit : searchHits) {
                    Article article = new Article();
                    //原始记录
                    Map<String, Object> source = searchHit.getSource();
                    //高亮记录
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();

                    article.setId(source.get("id").toString());
                    article.setTitle(source.get("title").toString());
                    if (highlightFields.containsKey("title")) {
                        article.setTitle(highlightFields.get("title").fragments()[0].toString());
                    }

                    article.setUser_id(source.get("user_id").toString());
                    article.setType(source.get("type").toString());
                    if (highlightFields.containsKey("type")) {
                        article.setTitle(highlightFields.get("type").fragments()[0].toString());
                    }

                    article.setContent(source.get("content").toString());
                    if (highlightFields.containsKey("content")) {
                        article.setTitle(highlightFields.get("content").fragments()[0].toString());
                    }
                    article.setStats(source.get("stats").toString());
                    article.setCreate_time(new Date(Long.valueOf(source.get("create_time").toString())));

                    list.add(article);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }
        });
        return articles.getContent();
    }
}
