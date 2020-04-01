package com.zb.service.impl;

import com.zb.entity.order;
import com.zb.feigin.OrderFeginClients;
import com.zb.feigin.impl.OrderFeginClientsImpl;
import com.zb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderFeginClients orderFeginClients;

    @Override
    public List<order> getListOreder(Integer uid) {
        return orderFeginClients.show(uid);
    }

    @Override
    public String addOrderByUser(order order) {
        return orderFeginClients.add(order);
    }
}
