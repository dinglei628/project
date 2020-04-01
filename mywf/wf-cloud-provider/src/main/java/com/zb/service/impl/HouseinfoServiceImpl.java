package com.zb.service.impl;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;
import com.zb.mapper.HouseinfoMapper;
import com.zb.service.HouseinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseinfoServiceImpl implements HouseinfoService {
    @Autowired
    HouseinfoMapper houseinfoMapper;

    @Override
    public List<Houseinfo> getListHou() {
        return houseinfoMapper.getListHou();
    }

    @Override
    public List<Housetype> getListType() {
        return houseinfoMapper.getListType();
    }

    @Override
    public int insert(Houseinfo h) {
        return houseinfoMapper.insert(h);
    }
}
