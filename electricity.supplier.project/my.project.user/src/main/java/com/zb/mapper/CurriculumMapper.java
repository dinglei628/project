package com.zb.mapper;

import com.zb.entity.Curriculum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CurriculumMapper {

    List<Curriculum> getCurriculumsByUid(@Param("userId") String userId, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    Integer getCurriculumCountByUid(@Param("userId") String userId);


    //添加用户课程   类型map  需要 课程对象  和 用户id
    //Integer addCurriculum(@Param("cid") String cid, @Param("uid") String uid);


    //添加用户收藏   类型map  需要 帖子对象  和 用户id
    //Integer addCurriculum(@Param("cid") String cid, @Param("uid") String uid);
}
