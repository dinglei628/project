package com.zb.service.impl;

import com.zb.dto.Page;
import com.zb.entity.Auction;
import com.zb.entity.AuctionUser;
import com.zb.entity.Auctionrecord;
import com.zb.feign.AuctionFeignClients;
import com.zb.feign.UserFeignClients;
import com.zb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSerrviceImpl implements UserService {

    @Autowired(required = false)
    private UserFeignClients userFeignClients;
    @Autowired
    private AuctionFeignClients auctionFeignClients;

    @Override
    public AuctionUser userLogin(String userName, String userPassword) {
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userPassword", userPassword);
        return userFeignClients.login(param);
    }

    @Override
    public String addUser(AuctionUser a) {
        return userFeignClients.addUser(a);
    }

    @Override
    public String addComm(Auction a) {
        return auctionFeignClients.addComm(a);
    }

    @Override
    public String updateComm(int auctionId) {
        return auctionFeignClients.updateComm(auctionId);
    }

    @Override
    public Page<Auction> selAuction(String auctionName, String auctionDesc, String auctionStartTime, String auctionEndTime, Integer auctionStartPrice, int pageIndex, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("auctionName", auctionName);
        map.put("auctionDesc", auctionDesc);
        map.put("auctionStartTime", auctionStartTime);
        map.put("auctionEndTime", auctionEndTime);
        map.put("auctionStartPrice", auctionStartPrice);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        return auctionFeignClients.selAuction(map);
    }

    @Override
    public String delComm(int auctionId) {
        return auctionFeignClients.delComm(auctionId);
    }

    @Override
    public Auction selCommById(int auctionId) {
        return auctionFeignClients.selCommById(auctionId);
    }

    @Override
    public String addAuctionrecord(Auctionrecord a) {
        return auctionFeignClients.addAuctionrecord(a);
    }

    @Override
    public List<Auctionrecord> selRecord(int auctionId) {
        return auctionFeignClients.selRecord(auctionId);
    }

    @Override
    public List<Auction> getAuction() {
        return auctionFeignClients.getAuction();
    }

    @Override
    public List<AuctionUser> selAuctionUserById(int userId) {
        return userFeignClients.selAuctionUserById(userId);
    }
}
