package com.zb.pojo;

import java.io.Serializable;

public class Credit_exchange implements Serializable {
    private Integer id;
    private Integer credit_exchange_id;
    private Integer type;
    private Integer product_id;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCredit_exchange_id() {
        return credit_exchange_id;
    }

    public void setCredit_exchange_id(Integer credit_exchange_id) {
        this.credit_exchange_id = credit_exchange_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
