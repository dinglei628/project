package com.zb.mapper;

import com.zb.pojo.Exchanged;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExchangedMapper {


    Exchanged getExchangedBy();

    public int addExchanged(Exchanged exchanged);
}
