package cn.fanyetu.kafka.consumer1;

/**
 * 自定义bean类，主要功能是保存读取具体分区的信息，包括topic名称和partition ID
 * Created by zhanghaonan on 2017/9/5.
 */
public class KafkaTopicPartitionInfo {

	// 主题名称
	public final String topic;
	// 分区id
	public final int partitionID;

	public KafkaTopicPartitionInfo(String topic, int partitionID) {
		this.topic = topic;
		this.partitionID = partitionID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		KafkaTopicPartitionInfo that = (KafkaTopicPartitionInfo) o;

		if (partitionID != that.partitionID) return false;
		return topic != null ? topic.equals(that.topic) : that.topic == null;

	}

	@Override
	public int hashCode() {
		int result = topic != null ? topic.hashCode() : 0;
		result = 31 * result + partitionID;
		return result;
	}
}
