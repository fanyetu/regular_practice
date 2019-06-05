package cn.fanyetu.rabbitmqapi.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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

        // 通过channel向rabbitmq发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ " + i;
            // 第一个参数 exchange 当exchange为空的时候，使用的是RabbitMQ提供的default exchange，
            // 其规则英文解释为（The default exchange is implicitly bound to every queue, with a routing key equal to the queue name. ）
            // 也就是说，当exchange为空的时候，第二个参数routingKey需要等于一个队列的名称
            // 第二个参数 routingKey
            channel.basicPublish("", "test001", null, msg.getBytes());
        }

        // 关闭相关的连接
        channel.close();
        connection.close();
    }
}
