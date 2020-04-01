package com.zb.service;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.entity.AuctionUser;
import com.zb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @RequestMapping("/login")
    public AuctionUser login(@RequestBody Map<String,Object> map){
        String userName = map.get("userName").toString();
        String userPassword = map.get("userPassword").toString();
        return userMapper.userLogin(userName,userPassword);
    }

    @RequestMapping("addUser")
    public String addUser(@RequestBody AuctionUser a){
        int i = userMapper.addUser(a);
        if(i==1){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    @RequestMapping("/selAuctionUserById/{userId}")
    public List<AuctionUser> selAuctionUserById(@PathVariable("userId")int userId){
        return userMapper.selAuctionUserById(userId);
    }
}
