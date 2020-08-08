package com.ns.rabbitmq;

import com.ns.rabbitmq.sender.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    Sender sender;

    @Test
    void contextLoads() {
        sender.sendDirectMessage();
    }

    @Test
    void contextLoads1() {
        sender.sendTopicMessage1();
//        sender.sendTopicMessage2();
    }

    @Test
    void contextLoads2() {
        sender.sendFanoutMessage();
    }

    @Test
    void contextLoad3() {
//        sender.testMessageAck1();
        sender.testMessageAck2();
    }
}
