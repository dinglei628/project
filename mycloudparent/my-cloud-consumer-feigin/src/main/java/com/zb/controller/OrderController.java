package com.zb.controller;

import com.zb.entity.order;
import com.zb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/user/")
public class OrderController {
    @Value("${name}")
    private String name;
    @Autowired
    OrderService orderService;

    @GetMapping("show")
    public List<order> show(Integer uid){
        System.out.println("name:--------------------"+name);
        return orderService.getListOreder(uid);
    }
    @PostMapping(value = "add",produces = "application/json;charset=UTF-8")
    public String add(@RequestBody order order){
        return orderService.addOrderByUser(order);
    }

}
