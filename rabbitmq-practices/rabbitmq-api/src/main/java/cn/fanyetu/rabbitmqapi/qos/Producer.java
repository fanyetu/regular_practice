package cn.fanyetu.rabbitmqapi.qos;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zhanghaonan
 * @date 2019/6/30
 */
public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_qos_exchange";
        String routingKey = "qos.save";

        for (int i = 0; i < 10; i++) {
            String msg = "测消费端限流 " + i;
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
