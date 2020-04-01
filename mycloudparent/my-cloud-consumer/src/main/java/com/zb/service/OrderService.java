package com.zb.service;

import com.zb.entity.order;

import java.util.List;

public interface OrderService {
    public List<order> getListOreder(Integer id);

    public String addOrderByUser(order order);
}
