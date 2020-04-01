package com.zb.mapper;

import com.zb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    Integer addUserByPhone(User user);

    User getUserByPhone(String phone);

    Integer updateUserInfoById(User user);

    Integer updatePirSrc(@Param("src") String src, @Param("id") String id);

    Integer updatePassword(@Param("password") String password, @Param("id") String id);

    User getUserById(@Param("id") String id);




}