package com.ns.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ns
 * @create 2020-08-07
 */
@Configuration
public class RabbitConfig {
    //*******Direct*************
    @Bean
    public Queue testDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestDirectQueue",true);
    }

    @Bean
    DirectExchange testDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchange",true,false);
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
    //*******topic*************

    public final static String man = "topic.man";
    public final static String woman = "topic.woman";
    public final static String total = "topic.total";

    @Bean
    public Queue manQueue() {
        return new Queue(man);
    }

    @Bean
    public Queue womanQueue() {
        return new Queue(woman);
    }

    @Bean
    public Queue totalQueue() {
        return new Queue(total);
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(womanQueue()).to(exchange()).with(woman);
    }

    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(manQueue()).to(exchange()).with(man);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列


    @Bean
    Binding bindingExchangeMessage3() {
        return BindingBuilder.bind(totalQueue()).to(exchange()).with("topic.#");
    }
    // ********** Fanout **************


    @Bean
    public Queue queueA() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue queueB() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue queueC() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

    // *********** 消息确认 *************
    /*
    *
    *   ①消息推送到server，但是在server里找不到交换机: 触发 ConfirmCallback
    *   ②消息推送到server，找到交换机了，但是没找到队列: 触发 ConfirmCallback和RetrunCallback
    *   ③消息推送到sever，交换机和队列啥都没找到: 触发 ConfirmCallback
    *   ④消息推送成功: 触发 ConfirmCallback
    * */

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
            System.out.println("ConfirmCallback:     "+"确认情况："+ack);
            System.out.println("ConfirmCallback:     "+"原因："+cause);
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("ReturnCallback:     "+"消息："+message);
            System.out.println("ReturnCallback:     "+"回应码："+replyCode);
            System.out.println("ReturnCallback:     "+"回应信息："+replyText);
            System.out.println("ReturnCallback:     "+"交换机："+exchange);
            System.out.println("ReturnCallback:     "+"路由键："+routingKey);
        });

        return rabbitTemplate;
    }

    @Bean
    DirectExchange lonelyDirectExchange1() {
        return new DirectExchange("lonelyDirectExchange1");
    }
}
