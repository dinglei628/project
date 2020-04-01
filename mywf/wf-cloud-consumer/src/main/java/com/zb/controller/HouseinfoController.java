package com.zb.controller;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;
import com.zb.service.HouseinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class HouseinfoController {
    @Autowired
    HouseinfoService houseinfoService;

    @GetMapping("/show")
    public String show(Model model){
        List<Houseinfo> listHou = houseinfoService.getListHou();
        model.addAttribute("listHou",listHou);
        return "index";
    }

    @GetMapping("/type")
    public String type(Model model){
        List<Housetype> listType = houseinfoService.getListType();
        model.addAttribute("listType",listType);
        return "add";
    }
//,produces = "application/json;charset=UTF-8
    @PostMapping(value = "/insert")
    public String insert(Houseinfo h){
            houseinfoService.insert(h);
            return  "redirect:/user/show";
    }
}
