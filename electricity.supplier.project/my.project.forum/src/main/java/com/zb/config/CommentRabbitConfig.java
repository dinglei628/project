package com.zb.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Administrator
 */
@Configuration
public class CommentRabbitConfig {
    public static final String QUEUE_COMMENT="comment_queue";
    public static final String EXCHANGE_TOPIC_COMMENT="exchange_topic_comment";

    @Bean(EXCHANGE_TOPIC_COMMENT)
    public Exchange createExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_COMMENT).durable(true).build();
    }
    @Bean(QUEUE_COMMENT)
    public Queue createQGQueue(){
        Queue queue =new Queue(QUEUE_COMMENT);
        return queue;
    }

    @Bean
    public Binding bindingEmail(@Qualifier(EXCHANGE_TOPIC_COMMENT) Exchange exchange ,@Qualifier(QUEUE_COMMENT) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("inform.comment").noargs();
    }

}