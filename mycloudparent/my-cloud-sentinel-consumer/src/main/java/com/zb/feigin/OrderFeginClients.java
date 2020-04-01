package com.zb.feigin;

import com.zb.entity.order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "order-provider")
public interface OrderFeginClients {

    @GetMapping("/show/{uid}")
    public List<order> show(@PathVariable("uid") Integer uid);

    @PostMapping("/add")
    public String add(@RequestBody order order);
}
