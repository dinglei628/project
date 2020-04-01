package com.zb.service;

import com.zb.entity.TbUser;
import org.springframework.stereotype.Service;

public interface TbUserService {
    public TbUser findUserLogin(String username);
}
