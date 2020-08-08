# rabbitmq

## 简介

rabbitMQ稳定,可靠,数据一致,支持多协议,有消息确认,性能一般,基于erlang语言,二次开发困难.
kafka高吞吐,高性能,快速持久化,无消息确认,无消息遗漏,可能会有有重复消息,依赖于zookeeper,成本高.
ZeroMQ灵活快速,不支持持久化,需要大量编码来实现稳定可靠.
ActiveMQ不够灵活轻巧,对队列较多情况支持不好.
rocketMQ性能好,高吞吐,高可用性,支持大规模分布式.

## 内容及常见问题



## jar
``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```