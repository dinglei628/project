package com.zb.feign;

import com.zb.bug_detail;
import com.zb.bug_project;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "provider")
public interface MyFeign {
    @GetMapping("/getListDetail")
    public List<bug_detail> getListDetail(@RequestParam(value = "projectId",required = false)Integer projectId);


    @GetMapping("/getsel")
    public List<bug_project> getsel();

    @PostMapping("/insert")
    public int insert(@RequestParam(value = "projectId",required = false)Integer projectId,
                      @RequestParam(value = "severity",required = false)Integer severity,
                      @RequestParam(value = "title",required = false)String title,
                      @RequestParam(value = "reportUser",required = false)String reportUser);
}
