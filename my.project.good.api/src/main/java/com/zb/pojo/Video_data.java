package com.zb.pojo;

import java.io.Serializable;
import java.util.Date;

public class Video_data implements Serializable {
    private Integer id;
    private String videoTitle;
    private Integer typeId;
    private String typeVideoName;

    public String getTypeVideoName() {
        return typeVideoName;
    }

    public void setTypeVideoName(String typeVideoName) {
        this.typeVideoName = typeVideoName;
    }

    private String lecturerName;
    private Integer difficultyLevel;
    private Integer browseCount;
    private String uploadTime;
    private Integer notUpload;
    private Integer videoTypeId;
    private Double videoPrice;
    private Integer notVip;
    private Integer lowerShelf;
    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getNotUpload() {
        return notUpload;
    }

    public void setNotUpload(Integer notUpload) {
        this.notUpload = notUpload;
    }

    public Integer getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(Integer videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public Double getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(Double videoPrice) {
        this.videoPrice = videoPrice;
    }

    public Integer getNotVip() {
        return notVip;
    }

    public void setNotVip(Integer notVip) {
        this.notVip = notVip;
    }

    public Integer getLowerShelf() {
        return lowerShelf;
    }

    public void setLowerShelf(Integer lowerShelf) {
        this.lowerShelf = lowerShelf;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
