package cn.fanyetu.rabbitmqapi.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列，死信队列就是普通的队列，只是在其他队列设置了死信队列参数到这个队列而已
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

        // 声明死信队列
        String dlxExchangeName = "dlx.exchange";
        String dlxQueueName = "dlx.queue";
        String dlxRoutingKey = "#";

        channel.exchangeDeclare(dlxExchangeName, "topic", true, false, false, null);
        channel.queueDeclare(dlxQueueName, true, false, false, null);
        channel.queueBind(dlxQueueName, dlxExchangeName, dlxRoutingKey);

        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.#";
        String queueName = "test_dlx_queue";

        channel.exchangeDeclare(exchangeName, "topic", true, false, false, null);

        // 在创建队列的时候指定死信队列exchange的名字
        Map<String,Object> arguments = new HashMap<>();
        // 添加x-dead-letter-exchange参数，即可设置死信队列的名字
        arguments.put("x-dead-letter-exchange", "dlx.exchange");
        channel.queueDeclare(queueName, true, false, false, arguments);

        channel.queueBind(queueName, exchangeName, routingKey);

        /*
        消息在三种情况下会被判定为死信：
        1.nack
        2.timeout
        3.queue超长
         */
        channel.basicConsume(queueName, false, new MyConsumer(channel));
    }
}
