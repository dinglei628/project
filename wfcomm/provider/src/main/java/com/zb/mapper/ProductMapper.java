package com.zb.mapper;

import com.zb.entity.product;
import com.zb.entity.takeout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<product> getSel();

    int insert(takeout t);

    int update(@Param("quantity")int quantity,@Param("id")int id);
}
