package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.mapper.UsersMapper;
import com.demo.pojo.Users;
import com.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper mapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<Users> getUsers() {
        return mapper.getUsers();
    }

    @Override
    public Users getUsersBy(String userName) {
        Users user=null;
        boolean luck = this.addNmae();
        System.out.println(luck);
        if(userName!=null&&luck==false){
            //在从缓存中获取要参加抽奖的用户添加到set里
            redisTemplate.opsForSet().add("luckname:", userName+"");
            System.out.println("参加成功");
            Set members = redisTemplate.opsForSet().members("luckname:");
            System.out.println(members);
            Long size = redisTemplate.opsForSet().size("luckname:");
            if(size%4==0){
                Object pop = redisTemplate.opsForSet().randomMember("luckname:");
                System.out.println("中奖的是："+pop);
//                redisTemplate.opsForSet().remove("luckname:", pop);
            }
        }else {
            List<Users> list = this.getUsers();
            redisTemplate.opsForValue().set("luck:", JSON.toJSONString(list));
            System.out.println("先从数据库中获取抽奖用户的信息");
        }
        return mapper.getUsersBy(userName);
    }

    @Override
    public Boolean addNmae() {
        Boolean luck = redisTemplate.hasKey("luck");
        return luck;
    }
}
