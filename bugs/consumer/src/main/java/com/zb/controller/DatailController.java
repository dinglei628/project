package com.zb.controller;

import com.zb.bug_detail;
import com.zb.bug_project;
import com.zb.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DatailController {

    @Autowired
    DetailService detailService;


    @RequestMapping("/show")
    public String getListDetail(@RequestParam(value = "projectId",required = false)Integer projectId, Model model){
        List<bug_detail> detailList = detailService.getDetailList(projectId);
        model.addAttribute("d",detailList);
        List<bug_project> select = detailService.getSelect();
        model.addAttribute("select",select);
        return "show";

    }


    @GetMapping("/add")
    public String show(Model model){
        List<bug_project> sel = detailService.getSelect();
        System.out.println("------------------------"+sel);
        model.addAttribute("sel",sel);
        return "add";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam(value = "projectId",required = false)Integer projectId,
                         @RequestParam(value = "severity",required = false)Integer severity,
                         @RequestParam(value = "title",required = false)String title,
                         @RequestParam(value = "reportUser",required = false)String reportUser){
        int insert = detailService.insert(projectId,severity,title,reportUser);
        if(insert == 1){
            return "redirect:/show";
        }else{
            return "redirect:/add";
        }

    }

}
