package com.zb.controller;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;
import com.zb.service.HouseinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseinfoController {
    @Autowired
    HouseinfoService houseinfoService;

    @GetMapping("/show")
    public List<Houseinfo> show(){
        return houseinfoService.getListHou();
    }

    @GetMapping("/type")
    public List<Housetype> type(){
        return houseinfoService.getListType();
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Houseinfo h){
        int insert = houseinfoService.insert(h);
        if(insert == 1){
            return "添加成功";
        }else{
            return "添加失败";
        }

    }
}
