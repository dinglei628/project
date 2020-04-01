package com.demo.service;

import com.demo.entity.Order;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

public interface OrderService {
    //成功
    String SUCCESS = "666";
    //系统异常
    String ERROR = "222";
    //用户不存在
    String ERROR_USER_NOT = "10001";
    //商品不存在
    String ERROR_GOOD_NOT = "10002";
    //订单出错
    String ERROR_ORDER_NOT = "10003";
    //订单重复
    String ERROR_ORDER_REPEAT = "10004";
    //订单不存在
    String ERROR_ORDER_NON_EXISTENT = "10006";

    @PostMapping("/createOrder")
    Dto createOrder(@RequestParam(value = "token") String token,
                    @RequestParam(value = "goodsId") String goodsId);


    @GetMapping("/getOrderById/{id}")
    Dto getOrderById(@PathVariable("id")String id);

    @GetMapping("/searchOrder/{token}")
    Dto searchOrder(@PathVariable("token")String token,
                    @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                    @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);

    @GetMapping("/searchOrderCondition")
    Dto searchOrderCondition(@RequestParam(value = "uid",required = false) String uid,
                             @RequestParam(value = "gid",required = false)String gid,
                             @RequestParam(value = "startDate",required = false)String startDate,
                             @RequestParam(value = "endDate",required = false)String endDate,
                             @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);


    @PostMapping("/changeOrder")
    void changeOrder(@RequestParam(value = "id") String id,@RequestParam(value = "status") Integer status,@RequestParam(value = "payTime") String payTime);
}
