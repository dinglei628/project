package com.smbms.my.controller;

import com.smbms.my.pojo.Page;
import com.smbms.my.pojo.User;
import com.smbms.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/show")
    public Page<User> listUser(@RequestParam(value = "pageIndex",defaultValue = "1")int pageIndex, Model model){
        int pageSize = 5;
        Page<User> pageUsers = userService.getPageUsers(pageIndex, pageSize);
        return pageUsers;
    }

    @RequestMapping("/getuser")
    public List<User> getUser(){
        return userService.getUsers();
    }

    @RequestMapping("/display")
    public User display(@RequestParam("id")int id, Model model){
        return userService.getUserById(id);
    }

    @RequestMapping("/save")
    public int save(User user){
        return userService.updateUser(user);
    }

}
