package com.zb.mapper;

import com.zb.pojo.qg_good_end;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QgGoodInendMapper {

    public Integer getQgGoodEndCountByMap(Map<String,Object> param)throws Exception;
    //2
    public Integer insertQgGoodEnd(qg_good_end qg_good_end)throws Exception;

}
