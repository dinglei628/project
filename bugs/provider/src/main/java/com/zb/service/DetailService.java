package com.zb.service;

import com.zb.bug_detail;
import com.zb.bug_project;
import com.zb.mapper.DetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DetailService {
    @Autowired(required = false)
    DetailMapper detailMapper;


    @GetMapping("/getListDetail")
    public List<bug_detail> getListDetail(@RequestParam(value = "projectId",required = false)Integer projectId){
        return  detailMapper.getDetailList(projectId);
    }

    @GetMapping("/getsel")
    public List<bug_project> getsel(){
        return  detailMapper.getSelect();
    }

    @PostMapping("/insert")
    public int insert(@RequestParam(value = "projectId",required = false)Integer projectId,
                      @RequestParam(value = "severity",required = false)Integer severity,
                      @RequestParam(value = "title",required = false)String title,
                      @RequestParam(value = "reportUser",required = false)String reportUser){
       return detailMapper.insert(projectId,severity,title,reportUser);
    }
}
