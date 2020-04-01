package com.zb.mapper;

import com.zb.entity.Message;
//import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 14914
 * @date 2020-3-21
 */
@Mapper
public interface MessageMapper {
    /**
     * 获取最大的会话id
     *
     * @param fromId
     * @param toId
     * @return
     */
    Integer getMaxConversationId(String fromId, String toId);

    /**
     * 新增一条消息
     *
     * @param fromId
     * @param toId
     * @return
     */
    Integer AddMessage(String fromId, String toId);

    /**
     * 根据对方id和自己id 获取 所有聊天信息
     *
     * @param fromId
     * @param toId
     * @return
     */
    List<Message> getMessagesByOther(String fromId, String toId);

    /**
     * 根据会话消息id  对方id 自己id  更改消息状态
     * @param fromId
     * @param toId
     * @param conversationId
     * @return
     */
    Integer updateMessageStatus(String fromId, String toId, String conversationId);


    /**
     * 获取自己的未读消息
     * @param toId
     * @return
     */
    List<Message> getUnreadMessage(String toId);


}
