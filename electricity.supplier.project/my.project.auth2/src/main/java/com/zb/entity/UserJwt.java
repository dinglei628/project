package com.zb.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;


public class UserJwt extends User implements Serializable {


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

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhonestate() {
        return phonestate;
    }

    public void setPhonestate(Integer phonestate) {
        this.phonestate = phonestate;
    }

    public Integer getEmailstate() {
        return emailstate;
    }

    public void setEmailstate(Integer emailstate) {
        this.emailstate = emailstate;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPermsg() {
        return permsg;
    }

    public void setPermsg(String permsg) {
        this.permsg = permsg;
    }

    public String getVxid() {
        return vxid;
    }

    public void setVxid(String vxid) {
        this.vxid = vxid;
    }

    public String getQqid() {
        return qqid;
    }

    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    public String getPicsrc() {
        return picsrc;
    }

    public void setPicsrc(String picsrc) {
        this.picsrc = picsrc;
    }
}
