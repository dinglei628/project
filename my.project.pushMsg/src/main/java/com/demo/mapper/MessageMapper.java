package com.demo.mapper;

import com.demo.entity.Messages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    Integer addMsg(Messages messages);
}
