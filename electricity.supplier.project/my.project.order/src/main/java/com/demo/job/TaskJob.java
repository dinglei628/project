package com.demo.job;

import com.demo.entity.Order;
import com.demo.entity.Pay;
import com.demo.feign.PayFeignClient;
import com.demo.mapper.OrderMapper;
import com.demo.mq.OrderMq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskJob {
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired
    private PayFeignClient payFeignClient;
    @Autowired
    private OrderMq orderMq;

    @Scheduled(cron = "0/3 * * * * *")
    public void send() {
        System.err.println("======================>执行定时任务");
        List<Order> awaitOrder = orderMapper.getAwaitOrder();
        if (awaitOrder.size() == 0 || awaitOrder == null) {
            System.err.println("======================>没有要执行的任务");
        }
        for (Order order : awaitOrder) {
            System.err.println("查询该订单是否已经支付======================>"+order.getId());
            Pay isPay = payFeignClient.getIsPay(order.getId());
            if (isPay != null) {
                System.err.println("订单已经支付、修改订单信息、新增历史订单表======================>"+order.getId());
                orderMapper.updateOrderstatus(isPay.getOrderId(), 1,isPay.getMentDate());
                orderMq.addHistory(isPay.getOrderId());
            }else{
                System.err.println("订单未支付,并且已经过期,新增历史订单表======================>"+order.getId());
                orderMq.addHistory(order.getId());
            }
        }
        System.err.println("======================>结束定时任务");
    }
}
