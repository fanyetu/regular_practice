package cn.fanyetu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * 测试kafka生产者
 *
 * Created by zhanghaonan on 2017/9/5.
 */
public class TestProducer {

	@Test
	public void test1(){

		// kafka属性设置
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.244.136:9092");
		//The "all" setting we have specified will result in blocking on the full commit of the record, the slowest but most durable setting.
		//“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
		props.put("acks", "all");
		//如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
		props.put("retries", 0);

		//The producer maintains buffers of unsent records for each partition.
		props.put("batch.size", 16384);
		//默认立即发送，这里这是延时毫秒数
		props.put("linger.ms", 1);
		//生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
		props.put("buffer.memory", 33554432);
		//The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// 创建kafka生产者
		Producer<String,String> producer = new KafkaProducer<String, String>(props);

		//生产者的主要方法
		// close();//Close this producer.
		// close(long timeout, TimeUnit timeUnit); //This method waits up to timeout for the producer to complete the sending of all incomplete requests.
		// flush() ;所有缓存记录被立刻发送

		for(int i = 0; i < 100; i++){
			System.out.println("send start");
			//这里平均写入4个分区
			producer.send(
					new ProducerRecord<String, String>("foo",i%4,
							Integer.toString(i), Integer.toString(i)));
			System.out.println("send complete");
		}

		producer.close();
	}
}
