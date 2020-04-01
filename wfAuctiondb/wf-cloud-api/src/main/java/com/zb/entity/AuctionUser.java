package com.zb.entity;

public class AuctionUser {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userCardNo;
    private String userTel;
    private String userAddress;
    private String userPostNumber;
    private Integer userIsadmin;
    private String userQuestion;
    private String userAnswer;

    @Override
    public String toString() {
        return "AuctionUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCardNo='" + userCardNo + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPostNumber='" + userPostNumber + '\'' +
                ", userIsadmin=" + userIsadmin +
                ", userQuestion='" + userQuestion + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCardNo() {
        return userCardNo;
    }

    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPostNumber() {
        return userPostNumber;
    }

    public void setUserPostNumber(String userPostNumber) {
        this.userPostNumber = userPostNumber;
    }

    public Integer getUserIsadmin() {
        return userIsadmin;
    }

    public void setUserIsadmin(Integer userIsadmin) {
        this.userIsadmin = userIsadmin;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
