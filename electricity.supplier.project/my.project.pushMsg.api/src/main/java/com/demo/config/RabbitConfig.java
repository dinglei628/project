package com.demo.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    //Msg的MQ
    public static final String QUEUE_MSG = "msg_queue";

    public static final String EXCHANGE_TOPIC_MSG = "exchange_topic_msg";


    @Bean(EXCHANGE_TOPIC_MSG)
    public Exchange createOrderExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_MSG).durable(true).build();
    }

    @Bean(QUEUE_MSG)
    public Queue createOrderQueue() {
        Queue queue = new Queue(QUEUE_MSG);
        return queue;
    }


    @Bean
    public Binding bindingOrder(@Qualifier(EXCHANGE_TOPIC_MSG) Exchange exchange, @Qualifier(QUEUE_MSG) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("msg.*").noargs();
    }

}