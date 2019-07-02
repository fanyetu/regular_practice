package com.fanyetu.rabbitmqspring;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghaonan
 * @date 2019/7/2
 */
@Configuration
@ComponentScan("com.fanyetu.rabbitmqspring")
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        // 使用spring amqp提供的connectionFactory
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("192.168.200.131:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    /**
     * 声明RabbitAdmin，这个是spring amqp用于操作rabbitMQ的核心类
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 这里必须将autoStartup设置成true，否则spring容器启动的时候rabbitAdmin类不会加载
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
