package cn.fanyetu.rabbitmqapi.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者
 *
 * @author zhanghaonan
 * @date 2019/6/5
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        // 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置connectionFactory
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 创建Connection
        Connection connection = connectionFactory.newConnection();

        // 创建Channel
        Channel channel = connection.createChannel();

        // 设置message的properties
        Map<String, Object> headers = new HashMap<>();
        headers.put("header1", "111");
        headers.put("header2", "222");

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 设置deliveryMode为2，表示对消息进行持久化
                .expiration("10000") // 设置消息的过期时间为10s，消息如果在10s内没有被消费，那么就会被销毁
                .headers(headers)
                .build();

        // 通过channel向rabbitmq发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ " + i;
            channel.basicPublish("", "test001", properties, msg.getBytes());
        }

        // 关闭相关的连接
        channel.close();
        connection.close();
    }
}
