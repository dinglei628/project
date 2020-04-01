package com.zb.feign;

import com.zb.dto.Page;
import com.zb.entity.Auction;
import com.zb.entity.Auctionrecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "comm-provider")
public interface AuctionFeignClients {

    @RequestMapping("/addComm")
    public String addComm(@RequestBody Auction a);

    @RequestMapping("/updateComm/{auctionId}")
    public String updateComm(@PathVariable("auctionId") int auctionId);

    @RequestMapping("/selAuction")
    public Page<Auction> selAuction(@RequestBody Map<String, Object> map) ;

    @RequestMapping("/delcomm/{}")
    public String delComm(@PathVariable("auctionId") int auctionId);

    @RequestMapping("/selCommById/{auctionId}")
    public Auction selCommById(@PathVariable("auctionId") int auctionId);

    @RequestMapping("/addAuctionrecord")
    public String addAuctionrecord(@RequestBody Auctionrecord a);

    @RequestMapping("/selRecord/{auctionId}")
    public List<Auctionrecord> selRecord(@PathVariable("auctionId") int auctionId);

    @RequestMapping("/getAuction")
    public List<Auction> getAuction();
}
