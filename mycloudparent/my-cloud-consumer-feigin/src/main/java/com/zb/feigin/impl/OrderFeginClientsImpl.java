package com.zb.feigin.impl;

import com.zb.entity.order;
import com.zb.feigin.OrderFeginClients;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderFeginClientsImpl implements OrderFeginClients {
    @Override
    public List<order> show(Integer uid) {
        List<order> list = new ArrayList<>();
        order o = new order();
        o.setId(1);
        o.setInfo("服务熔断降级");
        o.setUid(uid);
        list.add(o);
        return list;
    }

    @Override
    public String add(order order) {
        return "服务熔断降级";
    }
}
