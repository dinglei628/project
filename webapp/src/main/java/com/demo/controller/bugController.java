package com.demo.controller;

import com.demo.pojo.Bug_detail;
import com.demo.pojo.Bug_pro;
import com.demo.service.bug_datailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class bugController {

    @Autowired
    bug_datailService bug_datailService;


    @RequestMapping("index")
    public String index(@RequestParam(value = "projectId",defaultValue = "0")int projectId, Model model){
        List<Bug_detail> listBug = bug_datailService.getListBug(projectId);
        model.addAttribute("listBug",listBug);
        List<Bug_pro> listBug_por = bug_datailService.getListBug_Por();
        model.addAttribute("listBug_por",listBug_por);
        return "index";
    }

    @RequestMapping("insert1")
    public String insert1(Model model){
        List<Bug_pro> listBug_por = bug_datailService.getListBug_Por();
        model.addAttribute("listBug_por",listBug_por);
        return "insert";
    }

    @RequestMapping("insert")
    public String insert2(Bug_detail b){
        int insert = bug_datailService.insert(b);
        if(insert == 1){
            return "redirect:index";
        }else{
            return "redirect:insert";
        }

    }
}
