package com.zb.mapper;

import com.zb.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TbUserMapper {
    public TbUser findUserLogin(@Param("username") String username);
}
