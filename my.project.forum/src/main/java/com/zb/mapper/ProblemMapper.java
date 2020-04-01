package com.zb.mapper;

import com.zb.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Mapper
public interface ProblemMapper {
    public Integer getArticleCount(@Param("type") String type);

    public List<Problem> getShow(Map<String,Object> map);

    public Integer getProblemCountByYes(@Param("type") String type);

    public List<Problem> getProblemCountByYes(Map<String,Object> map);

    public List<Problem> getShowByNo(Map<String,Object> map);

    public Integer getProblemCountByNo(@Param("type") String type);

    public Problem getShowById(@Param("id") String id);

    public Integer insertArticle(Problem problem);
}
