package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.entity.Curriculum;
import com.zb.entity.User;
import com.zb.service.CurriculumService;
import com.zb.service.UserService;
import com.zb.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    CurriculumService curriculumService;

    @RequestMapping("/sendCode/{phone}")
    public String sendCode(@PathVariable("phone") String phoneNumber) {
        userService.sendCode(phoneNumber);
        return "ok";
    }

    @RequestMapping("/register")
    public Dto sendCode(String phone, String pwd, String code) {
        return userService.register(phone, pwd, code);
    }

    @RequestMapping("changeInfo")
    public Dto changeInfo(User user, String token) {
        Dto dto = userService.UpdateUserInfo(user, token);
        return dto;
    }

    @RequestMapping("curr")
    public Dto<List<Curriculum>> getCurriculum(String token, Integer pageIndex) {
        return curriculumService.findCurriclumByUserId(token, pageIndex);
    }


    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("currentUserbytoken/{token}")
    public User getCurrentUserByToken(@PathVariable("token") String token) {

        return userService.getCurrentUserByToken(token);
    }

    @RequestMapping("currentUserbyphone/{phone}")
    public User getCurrentUserByPhone(@PathVariable("phone") String phone) {
        return userService.getCurrentUserByPhone(phone);
    }

    @RequestMapping("currentUserbyId/{id}")
    public User getCurrentUserByid(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }


}

