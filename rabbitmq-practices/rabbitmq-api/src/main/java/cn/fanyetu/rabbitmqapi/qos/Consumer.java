package cn.fanyetu.rabbitmqapi.qos;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消费端限流demo
 * 使用basicQos方法
 *
 * @author zhanghaonan
 * @date 2019/6/30
 */
public class Consumer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_qos_exchange";
        String queueName = "test_qos_queue";
        String routingKey = "qos.#";

        channel.exchangeDeclare(exchangeName, "topic", true, false, false, null);
        channel.queueDeclare(queueName, true, false, false, null);

        channel.queueBind(queueName, exchangeName, routingKey, null);

        // 设置channel的qoa
        channel.basicQos(0, 1, false);

        // 在使用qos的时候，autoAck必须设置成false，让consumer手动进行ack
        channel.basicConsume(queueName, false, new MyConsumer(channel));
    }
}
