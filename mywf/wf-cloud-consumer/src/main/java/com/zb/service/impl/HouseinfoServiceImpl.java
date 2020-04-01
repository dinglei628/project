package com.zb.service.impl;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;
import com.zb.feign.HouseinfoFegin;
import com.zb.service.HouseinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HouseinfoServiceImpl implements HouseinfoService {
    
    @Autowired
    HouseinfoFegin houseinfoFegin;
    
    @Override
    public List<Houseinfo> getListHou() {
        return houseinfoFegin.show();
    }

    @Override
    public List<Housetype> getListType() {
        return houseinfoFegin.type();
    }

    @Override
    public String insert(Houseinfo h) {

        return houseinfoFegin.insert(h);
    }
}
