package com.zb.mapper;

import com.zb.pojo.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobMapper {
    public List<Job> getJobAll();
    public Integer add(@Param("varsion") Integer varsion, @Param("scoreid") Integer scoreid);
}
