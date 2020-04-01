package com.demo.service;

import com.zb.dto.Dto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface HistoryService {

    //根据用户token查询已处理的订单信息
    @GetMapping("/searchHistory/{token}")
    Dto searchHistory(@PathVariable("token") String token, @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);



    //条件查询已处理的订单信息
    @GetMapping("/searchHistoryCondition")
    Dto searchHistoryCondition(@RequestParam(value = "uid",required = false) String uid,
                             @RequestParam(value = "gid",required = false)String gid,
                             @RequestParam(value = "startDate",required = false)String startDate,
                             @RequestParam(value = "endDate",required = false)String endDate,
                             @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);
}
