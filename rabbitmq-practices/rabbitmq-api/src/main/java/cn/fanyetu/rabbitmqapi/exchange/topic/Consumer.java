package cn.fanyetu.rabbitmqapi.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * topic类型的Exchange
 * 可以模糊匹配Routing Key，会将message发送到符合匹配规则的queue上
 *
 * @author zhanghaonan
 * @date 2019/6/19
 */
public class Consumer {

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

        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
        // #匹配多个单词，*匹配一个单词
        String routingKey = "test.#";

        // 5.声明交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 6.声明队列
        channel.queueDeclare(queueName, false, false, false, null);
        // 7.建立交换机和队列的绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);

        // 8.创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 9.设置channel
        channel.basicConsume(queueName, true, consumer);

        // 10.循环获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();
            String msg = new String(body);

            System.out.println("消费者获取消息：" + msg);
        }
    }
}
