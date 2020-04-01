package com.zb.mapper;

import com.zb.entity.Auction;
import com.zb.entity.Auctionrecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuctionMapper {

    int addComm(Auction a);

    int updateComm(@Param("auctionId") int auctionId);

    List<Auction> selAuction(@Param("auctionName")String auctionName,
                             @Param("auctionDesc")String auctionDesc,
                             @Param("auctionStartTime")String auctionStartTime,
                             @Param("auctionEndTime")String auctionEndTime,
                             @Param("auctionStartPrice")Integer auctionStartPrice,
                             @Param("from")Integer from,
                             @Param("pageSize")Integer pageSize);

    int selAuctionCount(@Param("auctionName")String auctionName,
                        @Param("auctionDesc")String auctionDesc,
                        @Param("auctionStartTime")String auctionStartTime,
                        @Param("auctionEndTime")String auctionEndTime,
                        @Param("auctionStartPrice")Integer auctionStartPrice);

    int delComm(@Param("auctionId")int auctionId);

    Auction selCommById(@Param("auctionId")int auctionId);

    int addAuctionrecord(Auctionrecord a);

    List<Auctionrecord> selRecord(@Param("auctionId")int auctionId);

    List<Auction> getAuction();
}
