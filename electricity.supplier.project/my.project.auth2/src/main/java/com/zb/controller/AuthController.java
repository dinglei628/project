package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.entity.User;
import com.zb.feign.UserFeignClient;
import com.zb.service.AuthService;
import com.zb.token.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    UserFeignClient userFeignClient;

    @PostMapping("/userlogin")
    public Dto<AuthToken> userlogin(String username, String password) {
        return authService.userlogin(username, password);
    }

    @RequestMapping("/userinfo")
    public User userinfo(@RequestParam("token") String token) {
        return authService.getUserInfo(token);
    }

    @GetMapping("/userout")
    public Dto userOut(String token) {
        return authService.loginOut(token);
    }

    @RequestMapping("/changetoken")
    public Dto changetoken(String token) {
        return authService.changetoken(token);
    }

}
