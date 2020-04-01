package com.zb.mapper;

import com.zb.pojo.qg_good_infrom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QgGoodsMapper {

    List<qg_good_infrom> getQgGoodBy();

    public qg_good_infrom getQgGoodsById(@Param(value = "qg_id") Integer qg_id)throws Exception;
}
