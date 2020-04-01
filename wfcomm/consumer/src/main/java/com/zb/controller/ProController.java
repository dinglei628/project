package com.zb.controller;

import com.zb.entity.product;
import com.zb.entity.takeout;
import com.zb.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProController {

    @Autowired
    ProService proService;

    @GetMapping("/show")
    public String show(Model model){
        List<product> sel = proService.getSel();
        System.out.println("------------------------"+sel);
        model.addAttribute("sel",sel);
        return "show";
    }

    @PostMapping("/insert")
    public String insert(takeout t, @RequestParam("quantity")int quantity,@RequestParam("productId")int id){
        proService.insert(t,quantity,id);
        return "redirect:/show";
    }
}
