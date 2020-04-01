package com.demo.controller;

import com.demo.pojo.Users;
import com.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    UsersService service;

    @RequestMapping("luckName")
    public Users luckName(@RequestParam(value = "userName",required = false) String userName){
        return service.getUsersBy(userName);
    }

}
