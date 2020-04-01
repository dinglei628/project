package com.demo.mqService;


import com.alibaba.fastjson.JSON;
import com.demo.adapter.MessageAdapter;
import com.demo.config.RabbitConfig;
import com.demo.entity.Messages;
import com.demo.mapper.MessageMapper;
import com.demo.send.EmailMessage;
import com.demo.send.SmsMessage;
import com.rabbitmq.client.Channel;
import com.zb.util.IdWorker;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;


@Service
public class MessageService {

    @Autowired(required = false)
    private MessageMapper messageMapper;

    private MessageAdapter messageAdapter;

    @Autowired
    private SmsMessage smsMessage;

    @Autowired
    private EmailMessage emailMessage;

    @RabbitListener(queues = RabbitConfig.QUEUE_MSG)
    public void sendMessage(String json, Message message, Channel channel) {

        LinkedHashMap<String, Object> data = JSON.parseObject(json, LinkedHashMap.class);
        String uId = data.get("uId").toString();
        String describe = data.get("describe").toString();
        String type = data.get("type").toString();
        Messages messages = new Messages();
        messages.setId(IdWorker.getId());
        messages.setDescribe(describe);
        messages.setUserId(uId);
        messages.setType(type);

        if (data.get("uId").toString() == "email") {
            messages.setWay("email");
            messageAdapter = emailMessage;
            messageAdapter.sendMsg(uId,describe);
            messageMapper.addMsg(messages);
        }
        if (data.get("uId").toString() == "sms") {
            messages.setWay("sms");
            messageAdapter = smsMessage;
            messageAdapter.sendMsg(uId,describe);
            messageMapper.addMsg(messages);
        }
    }
}
