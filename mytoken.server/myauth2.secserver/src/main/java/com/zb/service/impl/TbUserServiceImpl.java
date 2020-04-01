package com.zb.service.impl;

import com.zb.entity.TbUser;
import com.zb.mapper.TbUserMapper;
import com.zb.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Override
    public TbUser findUserLogin(String username) {
        return tbUserMapper.findUserLogin(username);
    }
}
