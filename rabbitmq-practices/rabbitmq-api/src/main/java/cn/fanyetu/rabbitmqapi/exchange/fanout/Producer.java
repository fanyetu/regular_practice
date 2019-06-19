package cn.fanyetu.rabbitmqapi.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zhanghaonan
 * @date 2019/6/19
 */
public class Producer {

    public static void main(String[] args) throws Exception{
        // 1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2.设置connectionFactory
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 3.创建Connection
        Connection connection = connectionFactory.newConnection();

        // 4.创建Channel
        Channel channel = connection.createChannel();

        // 5.发送消息
        String msg = "Hello fanout Exchange...";
        String exchangeName = "test_fanout_exchange";
        // 因为是fanout类型的exchange，这里的routingKey没有任何影响，可以随便填
        String routingKey = "123123";

        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        // 6.关闭相关的连接
        channel.close();
        connection.close();
    }
}
