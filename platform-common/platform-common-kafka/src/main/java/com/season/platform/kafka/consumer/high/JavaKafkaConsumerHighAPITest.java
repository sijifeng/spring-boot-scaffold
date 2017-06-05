package com.season.platform.kafka.consumer.high;

/**
 * Created by jiyc on 2017/6/5.
 */
public class JavaKafkaConsumerHighAPITest {
	public static void main(String[] args) {
		String zookeeper = "127.0.0.1:2181";
		String groupId = "group1";
		String topic = "test2";
		int threads = 1;

		JavaKafkaConsumerHighAPI example = new JavaKafkaConsumerHighAPI(topic, threads, zookeeper, groupId);
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
