package com.season.platform.kafka.consumer.lower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiyc on 2017/6/5.
 */
public class JavaKafkaSimpleConsumerAPITest {
	public static void main(String[] args) {
		JavaKafkaSimpleConsumerAPI example = new JavaKafkaSimpleConsumerAPI();
		long maxReads = 300;
		String topic = "test2";
		int partitionID = 0;

		KafkaTopicPartitionInfo topicPartitionInfo = new KafkaTopicPartitionInfo(topic, partitionID);
		List<KafkaBrokerInfo> seeds = new ArrayList<KafkaBrokerInfo>();
		seeds.add(new KafkaBrokerInfo("127.0.0.1", 9092));

		try {
			example.run(maxReads, topicPartitionInfo, seeds);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取该topic所属的所有分区ID列表
		System.out.println(example.fetchTopicPartitionIDs(seeds, topic, 100000, 64 * 1024, "client-id"));
	}
}
