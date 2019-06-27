package cn.fanyetu.rabbitmqapi.confirmlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 在生产者端添加confirmListener，在broker接收到消息之后会发送confirm消息，生产端可以确认消息是否已送到broker
 *
 * @author zhanghaonan
 * @date 2019/6/27
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        // 创建connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.200.131");
        connectionFactory.setPort(5672);

        // 创建connection
        Connection connection = connectionFactory.newConnection();

        // 创建channel
        Channel channel = connection.createChannel();

        // 设置exchange和routingKey
        String exchange = "test_confirm_exchange";
        String routingKey = "confirm.test";

        String msg = "Hello confirm messsage!";

        // 设置消息投递模式为confirm，这点很重要
        channel.confirmSelect();

        // 设置confirmListener
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                // 成功发送的回调
                System.out.println(deliveryTag);
                System.out.println("send success");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                // 发送失败的回调
                System.out.println(deliveryTag);
                System.out.println("send failed");
            }
        });

        channel.basicPublish(exchange, routingKey, null, msg.getBytes());

        // 这里暂时不关闭，用于测试listener的回调
    }
}
