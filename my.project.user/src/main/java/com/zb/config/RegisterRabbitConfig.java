
package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterRabbitConfig {
    public static final String QUEUE_USER_CODE = "queue_user_code";
    public static final String EXCHANGE_MY_USER = "exchange_my_user";
    public static final String KEY_MY_USER = "user.code";

    @Bean(EXCHANGE_MY_USER)
    public Exchange createExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_MY_USER).durable(true).build();
    }

    @Bean(QUEUE_USER_CODE)
    public Queue createEmailQueue() {
        Queue queue = new Queue(QUEUE_USER_CODE);
        return queue;
    }

    @Bean
    public Binding bindingCodeQueue(@Qualifier(EXCHANGE_MY_USER) Exchange exchange, @Qualifier(QUEUE_USER_CODE) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("user.#.code").noargs();
    }


}
