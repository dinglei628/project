package com.zb.entity;

import java.util.Date;

public class Auctionrecord {
    private Integer id;
    private Integer userId;
    private Integer auctionId;
    private String auctionTime;
    private Integer auctionPrice;

    @Override
    public String toString() {
        return "Auctionrecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", auctionId=" + auctionId +
                ", auctionTime='" + auctionTime + '\'' +
                ", auctionPrice=" + auctionPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuctionTime() {
        return auctionTime;
    }

    public void setAuctionTime(String auctionTime) {
        this.auctionTime = auctionTime;
    }

    public Integer getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(Integer auctionPrice) {
        this.auctionPrice = auctionPrice;
    }
}
