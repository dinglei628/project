package com.zb.entity;

public class Houseinfo {
    private Integer houseId;
    private String houseDesc;
    private Integer typeId;
    private String typeName;

    public Houseinfo(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    private Integer monthlyRent;
    private String publishDate;

    public Houseinfo() {
    }

    public Houseinfo(Integer houseId, String houseDesc, Integer typeId, Integer monthlyRent, String publishDate) {
        this.houseId = houseId;
        this.houseDesc = houseDesc;
        this.typeId = typeId;
        this.monthlyRent = monthlyRent;
        this.publishDate = publishDate;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer id) {
        this.houseId = id;
    }

    public String getHouseDesc() {
        return houseDesc;
    }

    public void setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Integer monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
