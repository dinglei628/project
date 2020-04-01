package com.zb.service;

import com.alibaba.fastjson.JSONObject;
import com.zb.model.User;
import com.zb.model.WebSocketMessage;
import com.zb.util.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.zb.constant.MessageCodeConstant.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-05-12
 * @Time 20:57
 */
public class MessageService {
    public String messageJSONStringFactory(int messageCode, String chatMessage, int systemMessageCode, Object o) {
        WebSocketMessage webSocketMessage = new WebSocketMessage(messageCode, chatMessage,
                DateUtils.date2String(new Date()));
        Map<String, Object> map = new HashMap<>();
        map.put("systemMessageCode", systemMessageCode);
        map.put("object", o);
        webSocketMessage.setBody(map);
        return JSONObject.toJSONString(webSocketMessage);
    }

    public String messageJSONStringFactory(int messageCode, String chatMessage, User user, String receiverId) {
        WebSocketMessage webSocketMessage = new WebSocketMessage(messageCode, chatMessage, user
                , receiverId, DateUtils.date2String(new Date()));
        return JSONObject.toJSONString(webSocketMessage);
    }
}
