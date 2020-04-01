package com.demo.mapper;

import com.demo.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {

    List<Users> getUsers();
    Users getUsersBy(@Param("userName")String userName);
}
