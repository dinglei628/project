package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.RabbitConfig;
import com.zb.dto.Dto;
import com.zb.mapper.QgGoodInendMapper;
import com.zb.mapper.QgGoodsMapper;
import com.zb.pojo.qg_good_end;
import com.zb.pojo.qg_good_infrom;
import com.zb.service.QgGoodService;
import com.zb.util.RedisUtils;
import com.zb.vo.GoodsVo;
import com.zb.vo.MqMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class QgGoodServiceImpl implements QgGoodService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired(required = false)
    private QgGoodsMapper qgGoodsMapper;
    @Autowired(required = false)
    private QgGoodInendMapper qgGoodInendMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitTemplate.ConfirmCallback confirmCallback=new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            if(ack){
                System.out.println("成功投递数据：投递的数据商品信息是:"+correlationData);
            }else{
                System.out.println("消息投递失败：投递的数据商品信息是:"+correlationData);
            }
        }
    };

    public RabbitTemplate.ReturnCallback returnCallback=new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {

        }
    };

    @Override
    public String initGoodsToRedisById(Integer qg_id) throws Exception {
        //获取抢购的商品信息
        qg_good_infrom qgGoodsById = null;
        //初始化到redis中
        String key = "goods:" + qg_id;
        if(redisUtils.hasKey(key)){
            String json = redisUtils.get(key).toString();
            qgGoodsById = JSON.parseObject(json, qg_good_infrom.class);
        }else{
            qgGoodsById = qgGoodsMapper.getQgGoodsById(qg_id);
            //将信息初始化到redis
            redisUtils.set(key, JSON.toJSONString(qgGoodsById));
        }
        return "ok";
    }

    @Override
    public int checkGoods(String goodsId) throws Exception {
        String key = "goods:" + goodsId;
        //查询redis中的商品信息
        String json = redisUtils.get(key).toString();
        //商品信息
        GoodsVo goodsVo = JSON.parseObject(json, GoodsVo.class);
        return 1;
    }

    @Override
    public int lockGoods(String goodsId, String userId) throws Exception {
        String key = "goods:" + goodsId;
        qg_good_end qg_good_end = new qg_good_end();
        //获取商品的信息
        String json = redisUtils.get(key).toString();
        //封装临时商品对象
        GoodsVo goodsVo = JSON.parseObject(json, GoodsVo.class);
        qg_good_infrom qgGoodsById = qgGoodsMapper.getQgGoodsById(Integer.parseInt(goodsId));
        //主键
        qg_good_end.setQg_id(com.zb.util.IdWorker.getId());
        qg_good_end.setStatus(1);
//        qg_good_end.setAmount(goodsVo.getPrice());
        qg_good_end.setAmount(qgGoodsById.getVideoPrice());
        qg_good_end.setGoodsId(Integer.parseInt(goodsId));
        qg_good_end.setUserId(Integer.parseInt("1"));
        //修改redis中的库存数据
        redisUtils.set(key, JSON.toJSONString(goodsVo));
        //执行临时抢购的商品添加
        return qgGoodInendMapper.insertQgGoodEnd(qg_good_end);
    }

    @Override
    public Dto qg(String token, String goodsId) throws Exception {
        MqMessage mqMessage =new MqMessage();
        mqMessage.setGoodsId(goodsId);
        mqMessage.setToken(token);
        CorrelationData correlationData=new CorrelationData(JSON.toJSONString(mqMessage));
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC_INFORM,"inform.qg",mqMessage,correlationData);
        return  com.zb.dto.DtoUtil.returnSuccess("请在排队中请稍后..");
    }

    @RabbitListener(queues =RabbitConfig.QUEUE_QG )
    @RabbitHandler
    public void reviceQgMessage(MqMessage  mqMessage , org.springframework.messaging.Message message, Channel channel){
        Long tag  =(Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        String token = null;
        String goodsId = null;
        token= mqMessage.getToken();
        goodsId= mqMessage.getGoodsId();
        //验证用户是否登录
        String currentUser = "user1";
        if (currentUser == null) {
            return ;
        }
        String key="lock:"+goodsId;
        try {
            while(!redisUtils.lock(key)){
                Thread.sleep(3000);
            }
            //检查库存
            int val = this.checkGoods(goodsId);
            if (val == 0) {
                return ;
            }
            //执行用户锁定商品
            val = this.lockGoods(goodsId, currentUser);
            if (val > 0) {
                channel.basicAck(tag,false);
                System.out.println("nack"+goodsId+"..."+token);
                return ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                channel.basicNack(tag,false,false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            redisUtils.unLock(key);

        }
        return;
    }


    @Override
    public List<qg_good_infrom> getQgGoodBy() {
        List<qg_good_infrom> qgGoodBy = qgGoodsMapper.getQgGoodBy();
        return qgGoodBy;
    }

}
