package com.zb.service.impl;

import com.zb.mapper.Credit_exchangeMapper;
import com.zb.mapper.CreditsMapper;
import com.zb.mapper.ExchangedMapper;
import com.zb.pojo.Credit;
import com.zb.pojo.Credit_exchange;
import com.zb.pojo.Exchanged;
import com.zb.service.CreditsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreditsServiceImpl implements CreditsService {
    @Autowired(required = false)
    CreditsMapper creditsMapper;
    @Autowired(required = false)
    ExchangedMapper exchangedMapper;
    @Autowired(required = false)
    Credit_exchangeMapper credit_exchangeMapper;

    @Override
    public Credit getCreditBy() {
        Credit creditBy = creditsMapper.getCreditBy();
        return creditBy;
    }

    @Override
    public String addExchanged(Exchanged exchanged) {
        Credit creditBy = creditsMapper.getCreditBy();
        if(creditBy != null){
            Exchanged newExchanged = new Exchanged();
            newExchanged.setUser_id(creditBy.getUser_id());
            newExchanged.setExchanged_credit(10);
            newExchanged.setProduct_id(1001);
            exchangedMapper.addExchanged(newExchanged);
         }
        return "ok";
    }

    @Override
    public Exchanged getExchangedBy() {
        return exchangedMapper.getExchangedBy();
    }

    @Override
    public String addCredit_exchangeMapper(Credit_exchange credit_exchange) {
        Exchanged exchangedBy = exchangedMapper.getExchangedBy();
        if(exchangedBy != null){
            Credit_exchange newcredit_exchange = new Credit_exchange();
            newcredit_exchange.setType(2);
            newcredit_exchange.setCredit_exchange_id(exchangedBy.getId());
            newcredit_exchange.setProduct_id(exchangedBy.getProduct_id());
            credit_exchangeMapper.addCredit_exchangeMapper(newcredit_exchange);
        }
        return "ok";
    }


}
