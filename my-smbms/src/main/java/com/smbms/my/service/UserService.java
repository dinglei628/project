package com.smbms.my.service;

import com.smbms.my.pojo.Page;
import com.smbms.my.pojo.User;

import java.util.List;

public interface UserService {

    Page<User> getPageUsers(int pageIndex,int pageSize);

    List<User> getUsers();

    User getUserById(int id);
    int updateUser(User user);
}
