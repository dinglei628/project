package com.zb.controller;

import com.zb.mapper.CreditsMapper;
import com.zb.pojo.Credit;
import com.zb.pojo.Credit_exchange;
import com.zb.pojo.Exchanged;
import com.zb.service.CreditsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @Autowired(required = false)
    CreditsService creditsService;


    @RequestMapping("getCredit")
    public String getCredit(Model model){
        model.addAttribute("creditBy",creditsService.getCreditBy());
        return "show";
    }


    @RequestMapping("add")
    public String addCredit(Exchanged exchanged) throws InterruptedException {
        Thread.sleep(2000);
        creditsService.addExchanged(exchanged);
        return  "a";

    }

    @RequestMapping("getexchanged")
    public String getexchanged(Model model){
        Exchanged exchangedBy = creditsService.getExchangedBy();
        model.addAttribute("exchangedBy",exchangedBy);
        return "show2";
    }

    @RequestMapping("addexchanged")
    public String addexchanged(Credit_exchange credit_exchange) throws InterruptedException {
        Thread.sleep(2000);
        creditsService.addCredit_exchangeMapper(credit_exchange);
        return  "a";
    }

}
