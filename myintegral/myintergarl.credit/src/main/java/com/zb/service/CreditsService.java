package com.zb.service;

import com.zb.pojo.Credit;
import com.zb.pojo.Credit_exchange;
import com.zb.pojo.Exchanged;

import java.util.List;

public interface CreditsService {
    Credit getCreditBy();

    public String addExchanged(Exchanged exchanged);

    Exchanged getExchangedBy();

    public String addCredit_exchangeMapper(Credit_exchange credit_exchange);
}
