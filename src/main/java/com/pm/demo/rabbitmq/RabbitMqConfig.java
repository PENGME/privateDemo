package com.pm.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置队列 队列的名称，发送者和接受者的名字必须一致，否则接受不到消息
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue queue(){
        return new Queue("pmTest");
    }
}
