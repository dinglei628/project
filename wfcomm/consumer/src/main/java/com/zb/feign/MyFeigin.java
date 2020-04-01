package com.zb.feign;

import com.zb.entity.product;
import com.zb.entity.takeout;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "provider")
public interface MyFeigin {

    @GetMapping("/show")
    public List<product> show();

    @PostMapping("insert")
    public String insert(@RequestBody takeout t, @RequestParam(value = "quantity",required = false) int quantity,
                         @RequestParam(value = "productId",required = false) int id);

}
