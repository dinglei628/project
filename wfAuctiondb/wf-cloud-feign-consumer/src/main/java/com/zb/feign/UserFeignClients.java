package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.entity.AuctionUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "user-provider")
public interface UserFeignClients {

    @RequestMapping("/login")
    public AuctionUser login(@RequestBody Map<String,Object> map);

    @RequestMapping("/addUser")
    public String addUser(@RequestBody AuctionUser a);

    @RequestMapping("/selAuctionUserById/{userId}")
    public List<AuctionUser> selAuctionUserById(@PathVariable("userId")int userId);
}
