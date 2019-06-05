package cn.fanyetu.rabbitmqapi.quickstart;

import com.rabbitmq.client.*;

/**
 * 消费者
 *
 * @author zhanghaonan
 * @date 2019/6/5
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        // 1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置connectionFactory
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2.创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3.创建Channel
        Channel channel = connection.createChannel();

        // 4.声明一个队列
        String queueName = "test001";
        // 1 队列名称 2 是否持久化 3 是否独占 4 是否自动删除 5 附加属性
        channel.queueDeclare(queueName, true, false, false, null);

        // 5.创建消费者，对于RabbitMq来说，可以不创建Producer，但是必须创建Consumer
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 6.设置channel
        // 1 队列名称 2 是否自动回复 3 消费者
        channel.basicConsume(queueName, true, consumer);

        // 7.接收消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();
            String msg = new String(body);

            // 还可以冲Envelope中获取更多的消息信息
//            Envelope envelope = delivery.getEnvelope();
//            envelope.getRoutingKey();

            System.out.println("消费者：" + msg);
        }
    }
}
