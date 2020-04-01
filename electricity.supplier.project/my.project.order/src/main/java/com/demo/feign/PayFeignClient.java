package com.demo.feign;

import com.demo.entity.Pay;
import com.demo.serivce.PayService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PAY")
public interface PayFeignClient {


    @GetMapping("/exist/{oId}")
    Integer getPayExist(@PathVariable(value = "oId") String oId);


    @GetMapping("/isPay/{oId}")
    Pay getIsPay(@PathVariable(value = "oId") String oId);
}
