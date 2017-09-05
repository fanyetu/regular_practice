package cn.fanyetu.kafka.consumer1;

/**
 *
 * Kafka服务器连接参数
 * Created by zhanghaonan on 2017/9/5.
 */
public class KafkaBrokerInfo {

	// 主机名
	public final String brokerHost;
	// 端口号
	public final int brokerPort;

	public KafkaBrokerInfo(String brokerHost, int brokerPort) {
		this.brokerHost = brokerHost;
		this.brokerPort = brokerPort;
	}

	/**
	 * 构造方法， 使用默认端口号9092进行构造
	 *
	 * @param brokerHost
	 */
	public KafkaBrokerInfo(String brokerHost) {
		this(brokerHost, 9092);
	}
}
