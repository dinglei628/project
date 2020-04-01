package com.zb.mapper;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseinfoMapper {

    List<Houseinfo> getListHou();

    List<Housetype> getListType();

    int insert(Houseinfo h);
}
