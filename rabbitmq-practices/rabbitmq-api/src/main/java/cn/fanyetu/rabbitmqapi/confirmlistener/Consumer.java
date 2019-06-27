package cn.fanyetu.rabbitmqapi.confirmlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author zhanghaonan
 * @date 2019/6/27
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setPort(5672);
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.test";
        String exchangeType = "direct";
        String queueName = "test_confirm_queue";

        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        channel.queueDeclare(queueName, true, false, false, null);

        channel.queueBind(queueName, exchangeName, routingKey);

        // 创建consumer
        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();
            System.out.println("Consumer received: " + new String(body));
        }


    }
}
