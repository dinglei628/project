package com.smbms.my.dao;

import com.smbms.my.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> listUsers(@Param("from")int from,@Param("pageSize")int pageSize);

    int getCount();

    @Select("select * from smbms_user")
    List<User> getUsers();


    User getUserById(@Param("id")int id);
    int updateUser(User user);

}
