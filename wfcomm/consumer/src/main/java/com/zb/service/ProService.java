package com.zb.service;

import com.zb.entity.product;
import com.zb.entity.takeout;

import java.util.List;

public interface ProService {

    List<product> getSel();

    String insert(takeout t,int quantity,int id);

}
