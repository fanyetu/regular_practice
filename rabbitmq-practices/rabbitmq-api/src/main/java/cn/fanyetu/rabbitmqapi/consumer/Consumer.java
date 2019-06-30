package cn.fanyetu.rabbitmqapi.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 自定义消费，继承DefaultConsumer类，并覆盖其handleDelivery方法即可
 *
 * @author zhanghaonan
 * @date 2019/6/30
 */
public class Consumer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("192.168.200.131");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.#";
        String queueName = "test_consumer_queue";

        channel.exchangeDeclare(exchangeName, "topic", true, false, false, null);
        channel.queueDeclare(queueName, true, false, false, null);

        channel.queueBind(queueName, exchangeName, routingKey);

        // 使用自定义的consumer，不用再while循环去取消息了
        channel.basicConsume(queueName, true, new MyConsumer(channel));
    }
}
