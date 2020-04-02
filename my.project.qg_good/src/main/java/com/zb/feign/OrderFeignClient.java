package com.zb.feign;

import com.zb.dto.Dto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ORDER")
public interface OrderFeignClient {
    public Dto createOrder(@RequestParam(value = "token") String token,
                           @RequestParam(value = "goodsId") String goodsId);

}
