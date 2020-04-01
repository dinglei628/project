package com.zb.feign;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "provider")
public interface HouseinfoFegin {

    @GetMapping("/show")
    public List<Houseinfo> show();

    @GetMapping("/type")
    public List<Housetype> type();

    @PostMapping("/insert")
    public String insert(@RequestBody Houseinfo h);
}
