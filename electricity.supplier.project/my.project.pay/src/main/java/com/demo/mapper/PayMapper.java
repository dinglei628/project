package com.demo.mapper;

import com.demo.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayMapper {

    Integer getPayById(@Param("id") String id);

    Integer getPayByIdAndStatus(@Param("id")String id,@Param("status") String status);

    Integer addPay(Pay pay);

    Integer updatePayInfo(@Param("status") String status,@Param("mentDate") String mentDate,@Param("id") String id);

    //根据订单编号查询支付记录
    Integer getPayByOrderId(@Param("oId")String oId);
    //根据订单编号查询是否支付
    Pay isPay(@Param("oId")String oId);

}
