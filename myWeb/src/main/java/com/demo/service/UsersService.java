package com.demo.service;

import com.demo.entity.Page;
import com.demo.entity.Product;
import com.demo.entity.Sale;
import com.demo.entity.Users;

import java.util.List;

public interface UsersService {

    Users getLogin(String userName,String userPwd);

    List<Product> getSel();

    int insert(Sale s);

    Page<Sale> getPage(int userId ,int a,int pageIndex,int pageSize);

    Product getOne(String productName);

}
