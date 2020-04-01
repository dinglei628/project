package com.demo.controller;

import com.demo.pojo.Shop;
import com.demo.pojo.User;
import com.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("doLogin")
    public String login(@RequestParam("uid")int uid, @RequestParam("name")String name, HttpSession session){
        User u = new User();
        if(uid == u.getUid()&&u.getName().equals(name)){
            session.setAttribute("login",u);
            return "redirect:index";
        }else{
            return "login";
        }
    }

    @RequestMapping("index")
    public String index(Model model){
        List<Shop> list = shopService.getList();
        model.addAttribute("list",list);
        return "index";
    }

    @RequestMapping("add")
    public String add(HttpSession session,Model model){
        User login = (User)session.getAttribute("login");
        List<Shop> ck = shopService.ck(login.getUid());
        model.addAttribute("ck",ck);
        model.addAttribute("sum",shopService.count());
        return "add";
    }

    @RequestMapping("adds")
    public String adds(HttpSession session,@RequestParam("id")int id,Model model){
        List<Shop> list = shopService.getList();
        model.addAttribute("list",list);
        User login = (User)session.getAttribute("login");
        shopService.add(login.getUid(),id);
        return "index";
    }

    @RequestMapping("del")
    public String del(@RequestParam("id")int id,HttpSession session){
        User login = (User)session.getAttribute("login");
        System.out.println(id);
        shopService.delet(login.getUid(),id);
        return "redirect:add";
    }
}
