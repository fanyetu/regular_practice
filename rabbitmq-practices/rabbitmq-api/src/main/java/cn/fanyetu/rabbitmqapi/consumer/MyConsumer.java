package cn.fanyetu.rabbitmqapi.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义消费者
 *
 * @author zhanghaonan
 * @date 2019/6/30
 */
public class MyConsumer extends DefaultConsumer {

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public MyConsumer(Channel channel) {
        super(channel);
    }

    /**
     * 收到消息后的处理
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("consumerTag:" + consumerTag);
        System.out.println("envelope:" + envelope);
        System.out.println("properties:" + properties);
        System.out.println("body:" + new String(body));

        /*
        consumerTag:amq.ctag-TYLHnCShnFAKnqw1gmX-YA
        envelope:Envelope(deliveryTag=1, redeliver=false, exchange=test_consumer_exchange, routingKey=consumer.save)
        properties:#contentHeader<basic>(content-type=null, content-encoding=null, headers=null, delivery-mode=null, priority=null, correlation-id=null, reply-to=null, expiration=null, message-id=null, timestamp=null, type=null, user-id=null, app-id=null, cluster-id=null)
        body:测试自定义consumer
         */
    }
}
