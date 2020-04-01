package com.zb.service;

import com.zb.entity.Article;
import com.zb.vo.ArticleVo;
import com.zb.util.Page;

/**
 * @author Administrator
 */
public interface ArticleService {
    public Page show(String type, int index, int show);
    public Page seeHot(String type, int index, int show);

    public ArticleVo findShowById(String id);

    public Boolean insertArticle(Article article);
}
