package com.zb.mapper;

import com.zb.entity.TbContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKey(TbContent record);
}