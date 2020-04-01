package com.zb.service;

import com.zb.dto.Dto;
import com.zb.entity.User;

public interface UserService {

    /**
     * 发送验证码
     *
     * @param phoneNumber 手机号码
     * @return
     */
    void sendCode(String phoneNumber);

    /**
     * 注冊
     *
     * @param phone
     * @param code
     * @return
     */
    Dto register(String phone, String pwd, String code);

    /**
     * 修改用户信息
     *
     * @param newUser
     * @return
     */
    Dto UpdateUserInfo(User newUser, String token);

    /**
     * 修改头像
     *
     * @param src
     * @param token
     * @return
     */
    Integer updatePirSrc(String src, String token);

    /**
     * 根据token获取用户
     *
     * @param token
     * @return
     */
    User getCurrentUserByToken(String token);

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    Dto login(String phone, String password);

    /**
     * 根据手机号获取用户对象
     *
     * @param phone
     * @return
     */
    User getCurrentUserByPhone(String phone);

    /**
     * 修改密码
     *
     * @param password
     * @param oldpassword
     * @param token
     * @return
     */
    Integer updatePassword(String password, String oldpassword, String token);

    /**
     * 根据手机修改密码
     *
     * @param password
     * @param token
     * @return
     */
    Integer updatePasswordByPhone(String password, String token);


    /**
     * 发送修改 密码 验证码
     *
     * @param token
     * @return
     */
    void sendChangePasswordCode(String token);


    /**
     *
     * @param code
     * @param token
     * @return
     */
    Dto confirmChangePasswordCode(String code, String token);

    /***
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(String id);


}
