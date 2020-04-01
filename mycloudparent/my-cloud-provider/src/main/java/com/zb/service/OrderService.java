package com.zb.service;

import com.zb.entity.order;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderService {


    private static List<order> list = new ArrayList<>();
    static {
        list.add(new order(1,"订单1:8001",1));
        list.add(new order(2,"订单2:8001",2));
        list.add(new order(3,"订单3:8001",1));
        list.add(new order(4,"订单4:8001",2));
        list.add(new order(5,"订单5:8001",1));
        list.add(new order(6,"订单6:8001",1));
    }

    @GetMapping("/show/{uid}")
    public List<order> show(@PathVariable Integer uid){
        List<order> orderList = new ArrayList<>();
        if(uid==1){
            throw new RuntimeException("my 错误");
        }
        for (order order : list){
            if(order.getUid().equals(uid)){
                orderList.add(order);
            }
        }
        return orderList;
    }

    @PostMapping("/add")
    public String add(@RequestBody order order){
        list.add(order);
        return "ok";
    }


    @GetMapping("/del/{id}")
    public String del(@PathVariable Integer id){
        list.remove(id);
        return "删除成功";
    }
}
