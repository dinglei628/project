package com.zb.mapper;

import com.zb.bug_detail;
import com.zb.bug_project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DetailMapper {

    List<bug_detail> getDetailList(@Param("projectId")Integer projectId);

    List<bug_project> getSelect();

    int insert(@Param("projectId")Integer projectId,@Param("severity")Integer severity,@Param("title")String title,@Param("reportUser")String reportUser);

}
