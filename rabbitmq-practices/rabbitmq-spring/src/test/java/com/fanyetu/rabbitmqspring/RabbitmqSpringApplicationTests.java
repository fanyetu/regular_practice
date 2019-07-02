package com.fanyetu.rabbitmqspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqSpringApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void rabbitAdminTest(){
        // 声明一个direct类型的exchange，这里spring amqp对各个类型的exchange抽象成了对应的类
        rabbitAdmin.declareExchange(new DirectExchange("spring.direct.exchange", true, false, null));

        rabbitAdmin.declareExchange(new TopicExchange("spring.topic.exchange", true, false, null));

        rabbitAdmin.declareExchange(new FanoutExchange("spring.fanout.exchange", true, false, null));

        // 声明队列
        rabbitAdmin.declareQueue(new Queue("spring.direct.queue", true, false, false, null));

        rabbitAdmin.declareQueue(new Queue("spring.topic.queue", true, false, false, null));

        rabbitAdmin.declareQueue(new Queue("spring.fanout.queue", true, false, false, null));

        // 声明exchange和queue之间的绑定，两种方式，new Binding对象和BindingBuilder
        rabbitAdmin.declareBinding(new Binding("spring.direct.queue", Binding.DestinationType.QUEUE,
                "spring.direct.exchange", "direct.save", null));

        // 这里虽然重复创建了queue和Exchange对象，但是在rabbitMQ中并不会重复创建
        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("spring.topic.queue", true, false, false, null))
                .to(new TopicExchange("spring.topic.exchange", true, false, null)).with("topic.#"));

        // 因为to的是fanout类型的exchange，所以不需要with RoutingKey了
        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("spring.fanout.queue", true, false, false, null))
                .to(new FanoutExchange("spring.fanout.exchange", true, false, null)));

        // 可以使用rabbitAdmin对队列进行清空
        rabbitAdmin.purgeQueue("test001");
    }

}
