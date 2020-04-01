package com.demo.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.demo.config.AlipayConfig;
import com.demo.entity.OrderVo;
import com.demo.entity.Pay;
import com.demo.mapper.PayMapper;
import com.demo.serivce.PayService;
import com.demo.utils.RedisUtil;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PayServiceImpl implements PayService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired(required = false)
    private PayMapper payMapper;

    @Override
    public Dto pay(@PathVariable("payToken") String payToken, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if (payToken == null) {
            return DtoUtil.returnFail("支付令牌不能为空", ERROR_TOKEN_NOT);
        }
        if (!redisUtil.exists("order_token:" + payToken)) {
            return DtoUtil.returnFail("令牌过期", ERROR_PAST_DUE);
        }

        OrderVo order = JSON.parseObject(redisUtil.get("order_token:" + payToken).toString(), OrderVo.class);
        System.err.println("订单详情："+order);

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getOid();
        // 订单名称，必填
        String subject = order.getOrderName();
        // 付款金额，必填
        String total_amount = order.getPrice().toString();
        // 商品描述，可空
        String body = order.getOrderName();
        // 超时时间 可空
        String timeout_express = "2m";
        // 销售产品码 必填
        String product_code = "QUICK_WAP_PAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        // 订单描述
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);
        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer getPayExist(@PathVariable(value = "oId")String oId) {
        return payMapper.getPayByOrderId(oId);
    }

    @Override
    public Pay getIsPay(@PathVariable(value = "oId")String oId) {
        return payMapper.isPay(oId);
    }


}
