package com.season.platform.kafka.producer;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by jiyc on 2017/6/5.
 */
public class JavaKafkaProducerPartitioner implements Partitioner {

	/**
	 * 无参构造函数
	 */
	public JavaKafkaProducerPartitioner() {
		this(new VerifiableProperties());
	}

	/**
	 * 构造函数，必须给定
	 *
	 * @param properties 上下文
	 */
	public JavaKafkaProducerPartitioner(VerifiableProperties properties) {
		// nothings
	}

	@Override
	public int partition(Object key, int numPartitions) {
		int num = Integer.valueOf(((String) key).replaceAll("key_", "").trim());
		return num % numPartitions;
	}
}