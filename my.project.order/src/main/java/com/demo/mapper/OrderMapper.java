package com.demo.mapper;

import com.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    Integer addOrder(Order order);


    List<Order> getOrderbyCondition(@Param("uid") String uid, @Param("gid")String gid,
                                    @Param("startDate") String startDate, @Param("endDate")String endDate,
                                    @Param("from") Integer from,@Param("pageSize") Integer pageSize);

    Integer getOrderbyConditionCount(@Param("uid") String uid, @Param("gid")String gid,
                                     @Param("startDate") String startDate, @Param("endDate")String endDate);

    List<Order> getOrderByUserId(@Param("uid") String uid,@Param("from") Integer from,@Param("pageSize") Integer pageSize);

    Integer getOrderByUserIdCount(@Param("uid") String uid);

    Integer updateOrderstatus(@Param("id") String id,@Param("status") Integer status,@Param("payTime") String payTime);

    Order getById(@Param("id") String id);

    Integer delOrder(@Param("id") String id);

    List<Order> getAwaitOrder();
}
