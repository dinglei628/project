package com.zb.service;

import com.zb.dto.Page;
import com.zb.entity.Auction;
import com.zb.entity.Auctionrecord;
import com.zb.mapper.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AuctionService {
    @Autowired(required = false)
    AuctionMapper auctionMapper;

    @RequestMapping("/addComm")
    public String addComm(@RequestBody Auction a) {
        int i = auctionMapper.addComm(a);
        if (i == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @RequestMapping("/updateComm/{auctionId}")
    public String updateComm(@PathVariable("auctionId") int auctionId) {
        int i = auctionMapper.updateComm(auctionId);
        if (i == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("/selAuction")
    public Page<Auction> selAuction(@RequestBody Map<String, Object> map) {
        String auctionName = (String) map.get("auctionName");
        String auctionDesc = (String) map.get("auctionDesc");
        String auctionStartTime = (String) map.get("auctionStartTime");
        String auctionEndTime = (String) map.get("auctionEndTime");
        Integer auctionStartPrice = (Integer) map.get("auctionStartPrice");
        Integer pageIndex = (Integer) map.get("pageIndex");
        Integer pageSize = (Integer) map.get("pageSize");

        List<Auction> list = auctionMapper.selAuction(auctionName,auctionDesc,auctionStartTime,auctionEndTime,auctionStartPrice, (pageIndex - 1) * pageSize, pageSize);
        System.out.println("---------------"+list);
        int i = auctionMapper.selAuctionCount(auctionName,auctionDesc,auctionStartTime,auctionEndTime,auctionStartPrice);
        Page<Auction> page = new Page<>(pageIndex, pageSize, i, list);
        return page;
    }

    @RequestMapping("/delcomm/{}")
    public String delComm(@PathVariable("auctionId") int auctionId){
        int i = auctionMapper.delComm(auctionId);
        if (i == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/selCommById/{auctionId}")
    public Auction selCommById(@PathVariable("auctionId") int auctionId){
        return auctionMapper.selCommById(auctionId);
    }

    @RequestMapping("/addAuctionrecord")
    public String addAuctionrecord(@RequestBody Auctionrecord a){
        int i = auctionMapper.addAuctionrecord(a);
        if (i == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @RequestMapping("/selRecord/{auctionId}")
    public List<Auctionrecord> selRecord(@PathVariable("auctionId") int auctionId){
        return auctionMapper.selRecord(auctionId);
    }

    @RequestMapping("/getAuction")
    public List<Auction> getAuction(){
        return auctionMapper.getAuction();
    }
}
