package com.zb.service;

import com.zb.entity.Article;
import com.zb.entity.Problem;
import com.zb.util.Page;
import com.zb.vo.ArticleVo;

/**
 * @author Administrator
 */
public interface ProblemService {
    public Page show(String type, int index, int show);
    public Page seeHot(String type, int index, int  show);
    public Page showByYes(String type, int index, int show);
    public Page showByNo(String type, int index, int show);
    public ArticleVo findShowById(String id);
    public Boolean insertArticle(Problem problem);
}
