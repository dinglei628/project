package com.demo.serivce;

import com.demo.entity.Pay;
import com.zb.dto.Dto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayService {
    //支付令牌不能为空
    String ERROR_TOKEN_NOT = "20001";

    //令牌过期
    String ERROR_PAST_DUE = "20002";

    @RequestMapping("/pay/{payToken}")
    Dto pay(@PathVariable("payToken") String payToken,HttpServletRequest request,HttpServletResponse response) throws Exception;


    @GetMapping("/exist/{oId}")
    Integer getPayExist(@PathVariable(value = "oId")String oId);

    @GetMapping("/isPay/{oId}")
    Pay getIsPay(@PathVariable(value = "oId")String oId);


}
