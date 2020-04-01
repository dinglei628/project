package com.demo.dao;

import com.demo.pojo.Bug_detail;
import com.demo.pojo.Bug_pro;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface bug_detailMapper {

    List<Bug_detail> getListBug(@Param("projectId") int projectId);

    List<Bug_pro> getListBug_Por();

    int insert(Bug_detail b);
}
