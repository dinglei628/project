package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.dto.Page;
import com.zb.entity.Auction;
import com.zb.entity.AuctionUser;
import com.zb.entity.Auctionrecord;
import com.zb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public Dto<AuctionUser> login(String userName, String userPassword) {
        AuctionUser userLogin = userService.userLogin(userName, userPassword);
        return DtoUtil.returnSuccess("ok", userLogin);
    }


    @RequestMapping("/addUser")
    public String addUser(AuctionUser a) {
        return userService.addUser(a);
    }

    @RequestMapping("/addComm")
    public String addComm(Auction a) {
        return userService.addComm(a);
    }

    @RequestMapping("/updateComm")
    public String updateComm(@RequestParam("auctionId") int auctionId) {
        return userService.updateComm(auctionId);
    }

    @RequestMapping("/selAuction")
    public Dto<Page<Auction>> selAuction(@RequestParam(value = "auctionName",required = false)String auctionName,
                                         @RequestParam(value = "auctionDesc",required = false)String auctionDesc,
                                         @RequestParam(value = "auctionStartTime",required = false)String auctionStartTime,
                                         @RequestParam(value = "auctionEndTime",required = false)String auctionEndTime,
                                         @RequestParam(value = "auctionStartPrice",required = false)Integer auctionStartPrice,
                                         @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                         @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {
        Page<Auction> auctionPage = userService.selAuction(auctionName,auctionDesc,auctionStartTime,auctionEndTime,auctionStartPrice, pageIndex, pageSize);
        System.out.println("=============="+auctionPage);
        return DtoUtil.returnSuccess("ok", auctionPage);
    }

    @RequestMapping("/delComm")
    public String delComm(@RequestParam("auctionId") int auctionId){
        return userService.delComm(auctionId);
    }

    @RequestMapping("selCommById")
    public Dto<Auction> selCommById(@RequestParam("auctionId") int auctionId){
        Auction selAuction = userService.selCommById(auctionId);
        return DtoUtil.returnSuccess("ok", selAuction);
    }

    @RequestMapping("/addAuctionrecord")
    public String addAuctionrecord(Auctionrecord a){
        return userService.addAuctionrecord(a);
    }

    @RequestMapping("/selRecord")
    public Dto<List<Auctionrecord>> selRecord(@RequestParam("auctionId") int auctionId){
        List<Auctionrecord> auctionrecords = userService.selRecord(auctionId);
        return DtoUtil.returnSuccess("ok",auctionrecords);
    }


    @RequestMapping("/getAuction")
    public Dto<List<Auction>> getAuction(){
        return DtoUtil.returnSuccess("ok",userService.getAuction());
    }

    @RequestMapping("/selAuctionUserById/{userId}")
    public Dto<List<AuctionUser>> selAuctionUserById(@RequestParam("userId") int userId){
        return DtoUtil.returnSuccess("ok",userService.selAuctionUserById(userId));
    }
}
