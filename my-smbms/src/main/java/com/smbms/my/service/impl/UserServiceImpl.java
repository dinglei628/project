package com.smbms.my.service.impl;

import com.smbms.my.dao.UserMapper;
import com.smbms.my.pojo.Page;
import com.smbms.my.pojo.User;
import com.smbms.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public Page<User> getPageUsers(int pageIndex, int pageSize) {
        int from = (pageIndex-1)*pageSize;
        List<User> list = userMapper.listUsers(from, pageSize);
        int count = userMapper.getCount();
        Page<User> page = new Page<>(pageSize,pageIndex,count,list);
        return page;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
