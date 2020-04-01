package com.zb.service;

import com.zb.dto.Page;
import com.zb.entity.Auction;
import com.zb.entity.AuctionUser;
import com.zb.entity.Auctionrecord;

import java.util.List;

public interface UserService {
    AuctionUser userLogin(String userName,String userPassword);

    String addUser(AuctionUser a);

    String addComm(Auction a);

    String updateComm(int auctionId);

    Page<Auction> selAuction(String auctionName,String auctionDesc,String auctionStartTime,String auctionEndTime,Integer auctionStartPrice,int pageIndex,int pageSize);

    String delComm(int auctionId);

    Auction selCommById(int auctionId);

    String addAuctionrecord(Auctionrecord a);

    List<Auctionrecord> selRecord(int auctionId);

    List<Auction> getAuction();

    List<AuctionUser> selAuctionUserById(int userId);
}
