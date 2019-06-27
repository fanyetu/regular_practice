package cn.fanyetu.rabbitmqapi.returnlistener;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * return 消息是指当我们生产端发送的消息在某些情况下没有命中exchange或者queue的情况下，broker给我们回送的失败消息
 *
 * @author zhanghaonan
 * @date 2019/6/27
 */
public class Producer {

    public static void main(String[] args) throws Exception{
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
        String exchange = "test_return_exchange";
        // 因为abc.test这个routing在test_return_exchange上无法命中queue，所以会返回return消息
        String routingKey = "abc.test";

        String msg = "Hello return message!";

        // 非常重要。这里的mandatory参数必须设置成true，因为如果是false，那么broker如果没有命中，就会直接删除这条消息，就不会返回return消息了
//        channel.basicPublish(exchange, routingKey, false, null, msg.getBytes());
        channel.basicPublish(exchange, routingKey, true, null, msg.getBytes());

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                                     AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("replyCode:" + replyCode);
                System.out.println("replyText:" + replyText);
                System.out.println("exchange:" + exchange);
                System.out.println("routingKey:" + routingKey);
                System.out.println("body:" + new String(body));

                /*
                replyCode:312
                replyText:NO_ROUTE
                exchange:test_return_exchange
                routingKey:abc.test
                body:Hello return message!
                 */
            }
        });
    }
}
