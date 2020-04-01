package com.demo.feign;

import com.demo.service.OrderService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("ORDER")
public interface OrderFeignClient extends OrderService {

}
