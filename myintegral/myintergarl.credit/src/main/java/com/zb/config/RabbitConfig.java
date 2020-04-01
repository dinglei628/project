package com.zb.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String QUEUE_CREDIT="credit_queue";
    public static final String EXCHANGE_TOPIC_INFORM="exchange_topic_credit";



    //添加兑换积分任务交换机
    public static final String EX_LEARNING_ADDCHOOSECOURSE = "ex_learning_addchoosecourse";

    //添加兑换积分消息队列
    public static final String XC_LEARNING_ADDCHOOSECOURSE = "xc_learning_addchoosecourse";

    //完成兑换积分消息队列
    public static final String XC_LEARNING_FINISHADDCHOOSECOURSE = "xc_learning_finishaddchoosecourse";

    //兑换积分路由key
    public static final String XC_LEARNING_ADDCHOOSECOURSE_KEY = "addchoosecourse";
    //完成兑换积分路由key
    public static final String XC_LEARNING_FINISHADDCHOOSECOURSE_KEY = "finishaddchoosecourse";
    public static String myexchage;


    @Bean(EXCHANGE_TOPIC_INFORM)
    public Exchange createExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_INFORM).durable(true).build();
    }
    @Bean(QUEUE_CREDIT)
    public Queue createQGQueue(){
        Queue queue =new Queue(QUEUE_CREDIT);
        return queue;
    }

    @Bean
    public Binding bindingEmail(@Qualifier(EXCHANGE_TOPIC_INFORM) Exchange exchange ,@Qualifier(QUEUE_CREDIT) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.credit.#").noargs();
    }


    /**
     * 交换机配置
     *
     * @return the exchange
     */
    @Bean(EX_LEARNING_ADDCHOOSECOURSE)
    public Exchange EX_DECLARE() {
        return
                ExchangeBuilder.directExchange(EX_LEARNING_ADDCHOOSECOURSE).durable(true).build();
    }

    //声明队列
    @Bean(XC_LEARNING_FINISHADDCHOOSECOURSE)

    public Queue QUEUE_XC_LEARNING_FINISHADDCHOOSECOURSE() {
        Queue queue = new Queue(XC_LEARNING_FINISHADDCHOOSECOURSE);
        return queue;
    }

    //声明队列
    @Bean(XC_LEARNING_ADDCHOOSECOURSE)
    public Queue QUEUE_XC_LEARNING_ADDCHOOSECOURSE() {
        Queue queue = new Queue(XC_LEARNING_ADDCHOOSECOURSE);
        return queue;
    }

    /**
     * 绑定队列到交换机   .
     *
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding
    BINDING_QUEUE_FINISHADDCHOOSECOURSE(@Qualifier(XC_LEARNING_FINISHADDCHOOSECOURSE) Queue queue,
                                        @Qualifier(EX_LEARNING_ADDCHOOSECOURSE) Exchange exchange) {
        return
                BindingBuilder.bind(queue).to(exchange).with(XC_LEARNING_FINISHADDCHOOSECOURSE_KEY).noargs();
    }

    @Bean
    public Binding BINDING_QUEUE_ADDCHOOSECOURSE(@Qualifier(XC_LEARNING_ADDCHOOSECOURSE) Queue queue, @Qualifier(EX_LEARNING_ADDCHOOSECOURSE) Exchange exchange) {
        return
                BindingBuilder.bind(queue).to(exchange).with(XC_LEARNING_ADDCHOOSECOURSE_KEY).noargs();
    }
}