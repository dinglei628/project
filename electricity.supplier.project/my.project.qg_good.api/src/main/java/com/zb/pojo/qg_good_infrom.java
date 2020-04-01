package com.zb.pojo;

import java.io.Serializable;

public class qg_good_infrom implements Serializable {
    private Integer qg_id;
    private String goodsAddress;
    private Double videoPrice;
    private Integer stock;
    private String createdTime;
    private String updatedTime;
    private String cron;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getQg_id() {
        return qg_id;
    }

    public void setQg_id(Integer qg_id) {
        this.qg_id = qg_id;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public Double getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(Double videoPrice) {
        this.videoPrice = videoPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
