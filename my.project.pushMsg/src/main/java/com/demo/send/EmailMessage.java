package com.demo.send;

import com.demo.adapter.MessageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailMessage implements MessageAdapter {

    @Autowired(required = false)
    private JavaMailSender javaMailSender;
    @Override
    public void sendMsg(String call,String templatel) {

        //处理发送邮件
        //String email = body.getString("email");
//        if (StringUtils.isEmpty(email)) {
//            return;
//        }
        System.err.println("消息服务平台发送邮件开始："+call);
        //log.info("消息服务平台发送邮件开始：{}", email);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //来自账号
        simpleMailMessage.setFrom("zhou528120@163.com");
        //发送账号
        simpleMailMessage.setTo(call);
        //标题
        simpleMailMessage.setSubject("网站消息推送");
        //内容
        simpleMailMessage.setText(templatel);
        //发送邮件
        javaMailSender.send(simpleMailMessage);
        System.out.println("消息服务平台发送邮件结束");

    }
}
