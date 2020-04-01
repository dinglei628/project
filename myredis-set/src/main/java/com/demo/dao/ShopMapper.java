package com.demo.dao;

import com.demo.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<Shop> getList();

    Shop getSel(@Param("id")int id);

}
