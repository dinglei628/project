package com.demo.mq;

import com.demo.config.DelayRabbitConfig;
import com.demo.config.RabbitConfig;

import com.demo.entity.History;
import com.demo.entity.Order;
import com.demo.entity.OrderVo;
import com.demo.feign.PayFeignClient;
import com.demo.mapper.HistoryMapper;
import com.demo.mapper.OrderMapper;
import com.rabbitmq.client.Channel;
import com.zb.util.IdWorker;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderMq {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private HistoryMapper historyMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PayFeignClient payFeignClient;

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDER)
    public void addOrder(OrderVo orderVo, Channel channel) {

        System.err.println("Mysql-----------添加用户订单");
        Order order = new Order();
        order.setId(orderVo.getOid());
        order.setUserId(orderVo.getUid());
        order.setGoodId(orderVo.getGid());
        order.setStatus(0);
        order.setPrice(orderVo.getPrice());
        order.setOrderName(orderVo.getOrderName());
        orderMapper.addOrder(order);
        System.err.println("10分钟之后会发送检查订单状态的消息");
        rabbitTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, orderVo.getOid(), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(1000*60*10 + "");
                return message;
            }
        });
    }

    @RabbitListener(queues = DelayRabbitConfig.ORDER_QUEUE_NAME)
    public void checkOrder(String id, Message message, Channel channel) {
        System.err.println("获取订单的信息----------->" + id);
        Integer result = payFeignClient.getPayExist(id);
        if (result == 0) {
            System.err.println("订单过期----------->" + id);
            addHistory(id);
        }

    }




    @RabbitListener(queues = RabbitConfig.QUEUE_HISTORY)
    public void UpdateOrder(String orderId, Channel channel) {
        addHistory(orderId);
    }

    public void addHistory(String orderId) {
        Order order = orderMapper.getById(orderId);
        History history = new History();
        history.setId(IdWorker.getId());
        history.setOrderId(orderId);
        history.setOrderName(order.getOrderName());
        history.setOrderPrice(order.getPrice());
        history.setUserId(order.getUserId());
        history.setUserName("补数据");
        history.setGoodId(order.getGoodId());
        history.setGoodName("补数据");
        history.setIspay(order.getStatus());
        history.setOrderCreateDate(order.getCreateDate());
        history.setPayDate(order.getPayTime());
        System.err.println("历史订单添加数据------>" + orderId);
        Integer result = historyMapper.addHistory(history);
        if (result > 0) {
            System.err.println("删除订单------>" + orderId);
            orderMapper.delOrder(orderId);
        }
    }
}
