package com.zb.mapper;

import com.zb.entity.AuctionUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    AuctionUser userLogin(@Param("userName")String userName,@Param("userPasswrod")String userPasswrod);

    int addUser(AuctionUser a);

    List<AuctionUser> selAuctionUserById(@Param("userId")int userId);

}
