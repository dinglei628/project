package com.demo.controller;

import com.demo.entity.Page;
import com.demo.entity.Product;
import com.demo.entity.Sale;
import com.demo.entity.Users;
import com.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UsersService service;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("doLogin")
    public String login(@RequestParam(value = "userName",required = false)String userName,
                        @RequestParam(value = "userPwd",required = false)String userPwd,
                        HttpSession session, HttpServletRequest request){
        Users login = service.getLogin(userName, userPwd);
        if(login!=null){
            session.setAttribute("login",login);
            return "index";
        }else{
            request.setAttribute("info","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("addto")
    public String addto(Model model){
        List<Product> sel = service.getSel();
        model.addAttribute("sel",sel);
        return "add";
    }

    @RequestMapping("add")
    public String add(Sale s){
        int insert = service.insert(s);
        if(insert == 1){
            return "list";
        }else{//redirect:
            return "add";
        }
    }

    @RequestMapping("list")
    public String list(@RequestParam(value = "pageIndex",defaultValue = "1")int pageIndex,
                       @RequestParam(value = "a",defaultValue = "0")int a,
                       Model model,HttpSession session){
        int pageSize = 3;
        Users u = (Users) session.getAttribute("login");
        Page<Sale> page = service.getPage(u.getId(), a, pageIndex, pageSize);
        model.addAttribute("page",page);
        model.addAttribute("a",a);
        List<Product> sel = service.getSel();
        model.addAttribute("sel",sel);
        return "list";
    }

    @RequestMapping("count")
    public String count(@RequestParam(value = "productName",required = false)String productName,Model model){
        List<Product> sel = service.getSel();
        model.addAttribute("sel",sel);
        Product one = service.getOne(productName);
        model.addAttribute("one",one);
        return "count";
    }
}
