package com.zb.entity;

import java.util.Date;

public class Auction {
    private Integer auctionId;
    private String auctionName;
    private Integer auctionStartPrice;
    private Integer auctionUpset;
    private Date auctionStartTime;
    private Date auctionEndTime;
    private String auctionPic;
    private String auctionDesc;

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", auctionName='" + auctionName + '\'' +
                ", auctionStartPrice=" + auctionStartPrice +
                ", auctionUpset=" + auctionUpset +
                ", auctionStartTime=" + auctionStartTime +
                ", auctionEndTime=" + auctionEndTime +
                ", auctionPic='" + auctionPic + '\'' +
                ", auctionDesc='" + auctionDesc + '\'' +
                '}';
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Integer getAuctionStartPrice() {
        return auctionStartPrice;
    }

    public void setAuctionStartPrice(Integer auctionStartPrice) {
        this.auctionStartPrice = auctionStartPrice;
    }

    public Integer getAuctionUpset() {
        return auctionUpset;
    }

    public void setAuctionUpset(Integer auctionUpset) {
        this.auctionUpset = auctionUpset;
    }

    public Date getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(Date auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public Date getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Date auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public String getAuctionPic() {
        return auctionPic;
    }

    public void setAuctionPic(String auctionPic) {
        this.auctionPic = auctionPic;
    }

    public String getAuctionDesc() {
        return auctionDesc;
    }

    public void setAuctionDesc(String auctionDesc) {
        this.auctionDesc = auctionDesc;
    }
}
