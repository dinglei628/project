package com.zb.service.impl;

import com.zb.entity.order;
import com.zb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Override
    public List<order> getListOreder(Integer id) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("order-provider");
//        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://order-provider/show/" + id;
        List<order> forObject = restTemplate.getForObject(url, List.class);
        return forObject;
    }

    @Override
    public String addOrderByUser(order order) {
        String url = "http://localhost:8001/add";
        String str = restTemplate.postForObject(url, order,String.class );
        return str;
    }
}
