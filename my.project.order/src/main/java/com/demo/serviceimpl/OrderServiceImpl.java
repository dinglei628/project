package com.demo.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.demo.config.RabbitConfig;
import com.demo.entity.Order;
import com.demo.entity.OrderVo;
import com.demo.entity.Page;
import com.demo.mapper.OrderMapper;
import com.demo.service.OrderService;
import com.demo.utils.RedisUtil;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.util.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;
import java.util.UUID;

@RestController
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;





    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            if (!ack) {
                System.err.println("异常处理....");
            }
        }
    };

    //回调函数: return返回
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    @Override
    public Dto createOrder(@RequestParam(value = "token") String token,
                           @RequestParam(value = "goodsId") String goodsId) {
        //参数验证
        if (token == "" || token == null) {
            return DtoUtil.returnFail("用户token不能为空", ERROR_USER_NOT);
        }
        if (goodsId == "" || goodsId == null) {
            return DtoUtil.returnFail("商品ID不能为空", ERROR_GOOD_NOT);
        }

        //根据用户token获取用户id
        String userId = UUID.randomUUID().toString();

        OrderVo orderVo = new OrderVo();
        orderVo.setOid(IdWorker.getId());
        orderVo.setUid(userId);
        orderVo.setGid(goodsId);
        orderVo.setPrice(88.56f);
        orderVo.setOrderName("购买课程");
        String orderToken = UUID.randomUUID().toString()+System.currentTimeMillis();
        if (redisUtil.set("order_token:"+orderToken, JSON.toJSONString(orderVo),60*10)) {
            rabbitTemplate.setConfirmCallback(confirmCallback);
            rabbitTemplate.setReturnCallback(returnCallback);
            CorrelationData correlationData = new CorrelationData(orderToken);
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC_ORDER, "order.add", orderVo,correlationData);
            return DtoUtil.returnSuccess("创建订单成功", orderToken);
        }
        return DtoUtil.returnFail("创建订单失败", ERROR);
    }

    @Override
    public Dto getOrderById(@PathVariable("id") String id) {
        Order order = orderMapper.getById(id);
        return DtoUtil.returnSuccess("success",order);
    }


    @Override
    public Dto searchOrder(@PathVariable("token") String token,
                           @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        if (token == "" || token == null) {
            return DtoUtil.returnFail("用户token不能为空", ERROR_USER_NOT);
        }
        //根据用户token获取用户ID
        String userId = "1001";
        Integer orderByUserIdCount = orderMapper.getOrderByUserIdCount(userId);
        List<Order> orders = orderMapper.getOrderByUserId(userId, (pageIndex - 1) * pageSize, pageSize);
        Page<Order> page = new Page<>(pageIndex, pageSize, orderByUserIdCount, orders);
        return DtoUtil.returnSuccess("success", page);
    }

    @Override
    public Dto searchOrderCondition(@RequestParam(value = "uid", required = false) String uid,
                                    @RequestParam(value = "gid", required = false) String gid,
                                    @RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate,
                                    @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        Integer orderbyConditionCount = orderMapper.getOrderbyConditionCount(uid, gid, startDate, endDate);
        List<Order> orders = orderMapper.getOrderbyCondition(uid, gid, startDate, endDate, (pageIndex - 1) * pageSize, pageSize);
        Page<Order> page = new Page<>(pageIndex, pageSize, orderbyConditionCount, orders);
        return DtoUtil.returnSuccess("success", page);
    }

    @Override
    public void changeOrder(@RequestParam(value = "id") String id,@RequestParam(value = "status") Integer status,@RequestParam(value = "payTime") String payTime) {
        System.err.println("修改订单信息------------------->"+id);
        Integer result = orderMapper.updateOrderstatus(id, status,payTime);
        if(result > 0){
            System.err.println("------------------->修改成功");
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC_HISTORY, "history.add",id);
        }
    }

}
