package com.demo.mapper;

import com.demo.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper {
    //添加已处理订单
    Integer addHistory(History history);


    //根据用户ID分页查询His信息
    List<History> getHisOrderByUserId(@Param("uId")String uId,@Param("from") Integer from,@Param("pageSize") Integer pageSize);
    Integer getHisOrderByUserIdCount(@Param("uId")String uId);


    //组合条件分页查询His信息
    List<History> getHisOrderbyCondition(@Param("uid") String uid, @Param("gid")String gid,
                                      @Param("startDate") String startDate, @Param("endDate")String endDate,
                                      @Param("from") Integer from,@Param("pageSize") Integer pageSize);
    Integer getHisOrderbyConditionCount(@Param("uid") String uid, @Param("gid")String gid,
                                     @Param("startDate") String startDate, @Param("endDate")String endDate);

    //根据HisID查询已处理订单详情
    History getById(@Param("id") String id);
}
