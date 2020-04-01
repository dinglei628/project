package com.demo.service.impl;

import com.demo.entity.Page;
import com.demo.entity.Product;
import com.demo.entity.Sale;
import com.demo.entity.Users;
import com.demo.mapper.UsersMapper;
import com.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper mapper;

    @Override
    public Users getLogin(String userName, String userPwd) {
        Users login = mapper.getLogin(userName);
        if(login!=null && login.getUserPwd().equals(userPwd)){
            return  login;
        }
        return null;
    }

    @Override
    public List<Product> getSel() {
        return mapper.getSel();
    }

    @Override
    public int insert(Sale s) {
        return mapper.insert(s);
    }

    @Override
    public Page<Sale> getPage(int userId, int a, int pageIndex, int pageSize) {
        int from = (pageIndex-1)*pageSize;
        List<Sale> list = mapper.getSaleList(userId, a, pageIndex, pageSize);
        int cnt = mapper.getSaleCount(userId, a);
        Page<Sale> page = new Page<>(pageIndex,pageSize,cnt,list);
        return page;
    }

    @Override
    public Product getOne(String productName) {
        return mapper.getOne(productName);
    }
}
