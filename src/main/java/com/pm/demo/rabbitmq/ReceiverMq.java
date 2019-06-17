package com.pm.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 创建接受者ReceiverMq
 */
@Component
@RabbitListener(queues = "pmTest")
public class ReceiverMq {

    @RabbitHandler
    public void receive(String msg){
        System.out.println("Test receivemq收到的消息:"+msg);
    }
}
