package com.zb.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 编号
     */
    private String id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号状态：0未认证，1已认证，3 请求更换
     */
    private Integer phonestate;

    /**
     * 邮箱状态：0未认证，1已认证，3 请求更换
     */
    private Integer emailstate;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户密码
     */
    private String userpwd;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户性别:0女 1男 ，2保密
     */
    private Integer sex;

    /**
     * 个性签名
     */
    private String permsg;

    /**
     * 微信id
     */
    private String vxid;

    /**
     * QQid
     */
    private String qqid;

    /**
     * 头像路径
     */
    private String picsrc;
}