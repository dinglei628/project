package com.zb.service.Impl;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.rabbitmq.client.Channel;
import com.zb.config.RegisterRabbitConfig;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.entity.User;
import com.zb.feign.AuthFeignClient;
import com.zb.mapper.UserMapper;
import com.zb.service.UserService;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtils;
import com.zb.util.SendUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class UserSerciceImpl implements UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AuthFeignClient authFeignClient;

    @Override
    public void sendCode(String phoneNumber) {
        rabbitTemplate.convertAndSend(RegisterRabbitConfig.EXCHANGE_MY_USER, RegisterRabbitConfig.KEY_MY_USER, phoneNumber);
    }

    /**
     * 用戶注冊 验证通过添加用户
     *
     * @param phone
     * @param pwd
     * @param code
     * @return
     */
    @Override
    public Dto register(String phone, String pwd, String code) {
        String recode = redisUtils.get(phone);
        if (recode != null && recode.equals(code)) {

            User user = new User();
            user.setId(IdWorker.getId().toString());
            //user.setId(IdWorker.getId());//原来的
            user.setPhone(phone);
            user.setPhonestate(1);
            user.setUserpwd(pwd);
            Integer result = userMapper.addUserByPhone(user);
            if (result == 1) {
                redisUtils.delete(recode);
                return DtoUtil.returnSuccess("注册成功");
            }
            return DtoUtil.returnFail("注册失败", "8000");
        }
        return DtoUtil.returnFail("验证码错误", "8001");
    }

    @RabbitListener(queues = RegisterRabbitConfig.QUEUE_USER_CODE)
    public void listenSendCode(String phoneNumber, Channel channel) {
        // System.out.println("异步发送验证码");
        String code = SendUtil.generateCode(4);
        SmsSingleSenderResult result = SendUtil.sendCode(phoneNumber, code);
        System.out.println(phoneNumber);
        if (result.result == 0) {
            redisUtils.setEx(phoneNumber, code, 5, TimeUnit.MINUTES);
        }
        // System.out.println(redisUtils.get(phoneNumber) + "----");

    }

    @Override
    public Dto UpdateUserInfo(User newUser, String token) {
        User user = getCurrentUserByToken(token);
        newUser.setId(user.getId());
        Integer result = userMapper.updateUserInfoById(newUser);
        if (result == 1) {
            return DtoUtil.returnSuccess("修改成功");
        }

        return DtoUtil.returnFail("修改失败", "8005");
    }

    @Override
    public Integer updatePirSrc(String src, String token) {

        User user = getCurrentUserByToken(token);
        return userMapper.updatePirSrc(src, user.getId());
    }


    @Override
    public User getCurrentUserByToken(String token) {
        return authFeignClient.userinfo(token);


    }

    @Override
    public Dto login(String phone, String password) {
        return null;
    }

    @Override
    public User getCurrentUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public Integer updatePassword(String password, String oldpassword, String token) {

        User user = getCurrentUserByToken(token);
        User userById = userMapper.getUserById(user.getId());
        if (userById.getUserpwd().equals(oldpassword)) {

            Integer result = userMapper.updatePassword(password, userById.getId());
            if (result == 1) {
                return 1;
            }

        }

        return 0;
    }

    @Override
    public Integer updatePasswordByPhone(String password, String token) {
        User user = getCurrentUserByToken(token);
        User userById = userMapper.getUserById(user.getId());
        Integer result = userMapper.updatePassword(password, userById.getId());
        return result;
    }

    @Override
    public void sendChangePasswordCode(String token) {
        User user = getCurrentUserByToken(token);
        rabbitTemplate.convertAndSend(RegisterRabbitConfig.EXCHANGE_MY_USER, RegisterRabbitConfig.KEY_MY_USER, user.getPhone());
    }

    @Override
    public Dto confirmChangePasswordCode(String code, String token) {
        User user = getCurrentUserByToken(token);

        String recode = redisUtils.get(user.getPhone());

        if (!code.equals(recode)) {

            return DtoUtil.returnFail("验证码错误", "8010");

        }

        return DtoUtil.returnSuccess("ok");
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }
    
}