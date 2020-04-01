package com.zb.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @GetMapping("show")
    @HystrixCommand(fallbackMethod="show_procc")
    public List<order> show(Integer uid){
        if(uid==1){
            throw new RuntimeException("错误");
        }
        return orderService.getListOreder(uid);
    }


    @PostMapping(value = "add",produces = "application/json;charset=UTF-8")
    public String add(@RequestBody order order){
        return orderService.addOrderByUser(order);
    }


    public List<order> show_procc(Integer uid){
        List<order> list = new ArrayList<>();
        order o = new order();
        o.setId(1);
        o.setInfo("服务熔断降级");
        o.setUid(uid);
        list.add(o);
        return list;
    }

}
