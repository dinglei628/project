package com.zb.service;

import com.zb.entity.Houseinfo;
import com.zb.entity.Housetype;

import java.util.List;

public interface HouseinfoService {
    List<Houseinfo> getListHou();

    List<Housetype> getListType();

    String insert(Houseinfo h);
}
