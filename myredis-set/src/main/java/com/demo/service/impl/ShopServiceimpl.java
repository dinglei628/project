package com.demo.service.impl;

import com.demo.dao.ShopMapper;
import com.demo.pojo.Shop;
import com.demo.pojo.User;
import com.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Service
public class ShopServiceimpl implements ShopService {

    @Autowired
    ShopMapper mapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    //查看全部商品信息
    @Override
    public List<Shop> getList() {
        return mapper.getList();
    }
    //根据id查询商品
    @Override
    public Shop getSel(int id) {
        return mapper.getSel(id);
    }
    //判断redis中是否有数据没有添加否数量加一
    @Override
    public void add(int uid,int id) {
        if(redisTemplate.hasKey("shop:"+uid)){
            redisTemplate.opsForHash().increment("shop:"+uid,id+"",1);
        }else{
            redisTemplate.opsForHash().put("shop:"+uid,id+"",1+"");
        }
    }

    //删除redis中的数据
    @Override
    public void delet(int uid, int id) {
        redisTemplate.opsForHash().delete("shop:"+uid,id+"");
    }

    //查看redis中全部数据以及购物车数量
    int sum = 0;
    @Override
    public List<Shop> ck(int uid) {
        sum = 0;
        List<Shop> list = new ArrayList<>();
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("shop:" + uid);
        for (Object obj : entries.keySet()){
            Integer id = Integer.parseInt(obj.toString());
            Object o = entries.get(obj);
            Integer num = Integer.parseInt(o.toString());
            sum += num;
            Shop sel = mapper.getSel(id);
            list.add(sel);
        }
        return list;
    }
    //控制层调用，用来从看购物车数量
    @Override
    public int count() {
        return sum;
    }
}
