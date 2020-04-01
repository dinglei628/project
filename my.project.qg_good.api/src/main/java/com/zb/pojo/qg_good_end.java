package com.zb.pojo;

import java.io.Serializable;
import java.util.Date;

public class qg_good_end implements Serializable {
    private Integer qg_id;
    private Integer userId;
    private Integer goodsId;
    private Integer status;
    private Double amount;
    private String createdTime;
    private String updatedTime;

    public Integer getQg_id() {
        return qg_id;
    }

    public void setQg_id(Integer qg_id) {
        this.qg_id = qg_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
