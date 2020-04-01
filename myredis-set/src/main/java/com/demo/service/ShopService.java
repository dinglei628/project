package com.demo.service;

import com.demo.pojo.Shop;
import com.demo.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ShopService {

    List<Shop> getList();
    Shop getSel(int id);

    void add(int uid,int id);

    void delet(int uid,int id);

    List<Shop> ck(int uid);

    int count();
}
