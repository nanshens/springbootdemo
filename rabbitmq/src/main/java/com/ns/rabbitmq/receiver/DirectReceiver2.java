package com.ns.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ns
 * @create 2020-08-07
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver2 {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者2收到消息  : " + testMessage.toString());
    }

}
