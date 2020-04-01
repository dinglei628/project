package com.zb.service.impl;

import com.zb.entity.product;
import com.zb.entity.takeout;
import com.zb.feign.MyFeigin;
import com.zb.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProServiceImpl implements ProService {
    @Autowired
    MyFeigin feigns;
    @Override
    public List<product> getSel() {
        return feigns.show();
    }

    @Override
    public String insert(takeout t,int quantity, int id) {
        return feigns.insert(t,quantity,id);
    }


}
