package cn.fanyetu.rabbitmqapi.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zhanghaonan
 * @date 2019/6/30
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.save";

        String msg = "测试自定义consumer";

        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        channel.close();
        connection.close();
    }
}
