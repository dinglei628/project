package com.zb.mapper;

import com.zb.pojo.Credit_exchange;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Credit_exchangeMapper {

    public int addCredit_exchangeMapper(Credit_exchange credit_exchange);

}
