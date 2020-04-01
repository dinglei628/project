package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.common.Constants;
import com.demo.config.RabbitConfig;
import com.demo.mqService.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;


@RestController
public class MessageController {

    @Autowired
    private MessageService service;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${msg.ordertemplate}")
    private String msgTemplate;

    @RequestMapping("/send")
    public String send() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("uId", "123");
        String msgDescribe = msgTemplate.replace("{}","小明").replace("[]","AA");
        map.put("describe", msgDescribe);
        map.put("type",Constants.ORDER_MSG);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC_MSG, "msg.send", JSON.toJSONString(map));

        return "ok";
    }
}
