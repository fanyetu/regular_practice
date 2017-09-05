package cn.fanyetu.kafka.consumer2;

import org.junit.Test;

/**
 * Created by zhanghaonan on 2017/9/5.
 */
public class JavaKafkaConsumerHighAPITest {

	@Test
	public void test1(){
//		String zookeeper = "192.168.244.136:2181";
		String zookeeper = "35.10.28.201:2181";
//		String groupId = "group1";
		String groupId = "portal_create_consumer";
//		String topic = "test2";
		String topic = "portal_create_emp";
		int threads = 1;

		JavaKafkaConsumerHighAPI example = new JavaKafkaConsumerHighAPI(topic,
				threads, zookeeper, groupId);
		new Thread(example).start();

		// 执行10秒后结束
		int sleepMillis = 600000;
		try {
			Thread.sleep(sleepMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 关闭
		example.shutdown();
	}
}
