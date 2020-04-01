package com.zb.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zb.entity.order;
import com.zb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @SentinelResource(fallback = "show_sentinel",blockHandler = "show_blockhandler")
    @GetMapping("show")
    public List<order> show(Integer uid){
        if(uid==1){
            throw new RuntimeException("异常");
        }
        return orderService.getListOreder(uid);
    }
    public List<order> show_sentinel(Integer uid){
        List<order> list = new ArrayList<>();
        order o = new order();
        o.setId(1);
        o.setInfo("fallback服务熔断降级");
        o.setUid(uid);
        list.add(o);
        return list;
    }
    public List<order> show_blockhandler(Integer uid){
        List<order> list = new ArrayList<>();
        order o = new order();
        o.setId(1);
        o.setInfo("blockHandler服务熔断降级");
        o.setUid(uid);
        list.add(o);
        return list;
    }
    @PostMapping(value = "add",produces = "application/json;charset=UTF-8")
    public String add(@RequestBody order order){
        return orderService.addOrderByUser(order);
    }

}
