package com.demo.service;

import com.demo.pojo.Users;

import java.util.List;

public interface UsersService {

    List<Users> getUsers();

    Users getUsersBy(String userName);

    public Boolean addNmae();
}
