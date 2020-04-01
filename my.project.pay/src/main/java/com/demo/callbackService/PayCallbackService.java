package com.demo.callbackService;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import com.demo.config.AlipayConfig;
import com.demo.config.Constants;
import com.demo.config.RabbitConfig;
import com.demo.entity.Order;
import com.demo.entity.Pay;
import com.demo.feign.OrderFeignClient;
import com.demo.mapper.PayMapper;
import com.zb.dto.Dto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PayCallbackService {

    @Autowired(required = false)
    private PayMapper payMapper;
    @Autowired
    private OrderFeignClient orderFeignClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Value("${msg.ordertemplate}")
    private String msgTemplate;

    @RequestMapping("/mynotifyurl")
    public void mynotifyurl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.err.println("---------------------->执行异步回调");
        PrintWriter out = response.getWriter();
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        //订单金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
        //订单创建时间
        String gmt_create = new String(request.getParameter("gmt_create").getBytes("ISO-8859-1"), "UTF-8");
        //订单信息
        String subject = request.getParameter("subject");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if (verify_result) {//验证成功
            if (payMapper.getPayById(trade_no) == 0) {
                System.err.println("------------------->添加支付信息");
                Pay pay = new Pay();
                pay.setId(trade_no);
                pay.setOrderId(out_trade_no);
                pay.setAmount(Float.parseFloat(total_amount));
                pay.setIsPay("665");
                pay.setSubject(subject);
                pay.setCreateDate(gmt_create);
                pay.setWay("支付宝");
                payMapper.addPay(pay);
            }
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_SUCCESS")) {
                //订单支付时间
                String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), "UTF-8");
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

                //如果用户已经支付成功，就直接返回success
                if (payMapper.getPayByIdAndStatus(trade_no, "666") > 0) {
                    System.err.println("------------------->用户已经支付成功");
                    out.println("success");
                }
                System.err.println("------------------->修改支付信息");
                payMapper.updatePayInfo("666", gmt_payment, trade_no);
                System.err.println("--------------->修改订单信息");
                orderFeignClient.changeOrder(out_trade_no,1,gmt_payment);

                //封装json推送支付成功的消息
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                Dto dto = orderFeignClient.getOrderById(out_trade_no);
                Order order = (Order) dto.getData();
                map.put("uId", order.getUserId());
                String msgDescribe = msgTemplate.replace("{}","小明").replace("[]","AA");
                map.put("describe", msgDescribe);
                map.put("type",Constants.ORDER_MSG);
                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC_MSG, "msg.send", JSON.toJSONString(map));
            }
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            out.println("success");    //请不要修改或删除

            //////////////////////////////////////////////////////////////////////////////////////////
        } else {//验证失败
            out.println("fail");
        }
        System.err.println("---------------------->异步回调执行结束");
    }

    @RequestMapping("/myreturnurl")
    public void myreturnulr(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.err.println("---------------------->执行同步回调");
        PrintWriter out = response.getWriter();
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if (verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////

            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            out.println("out_trade_no:" + out_trade_no + ",trade_no" + trade_no);
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            //response.sendRedirect("http://127.0.0.1:8080/xxx.html");
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //该页面可做页面美工编辑
            out.println("error");
        }
        System.err.println("---------------------->同步回调执行结束");
    }
}
