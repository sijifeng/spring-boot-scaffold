package com.season.platform.kafka.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 配置文件 后续扩展
 * Created by jiyc on 2017/6/14.
 */
public class KafkaConfig implements Serializable {
	public final String topic;
	public final int maxReads;
	public final List<BrokerInfo> brokerInfos;
	public final int partitionID;
	public long whichTime = kafka.api.OffsetRequest.EarliestTime();

	public KafkaConfig(String topic, List<BrokerInfo> brokerInfos, int maxReads, int partitionID) {
		this.topic = topic;
		this.brokerInfos = brokerInfos;
		this.maxReads = maxReads;
		this.partitionID = partitionID;
	}

	public KafkaConfig(String topic, List<BrokerInfo> brokerInfos, int maxReads, int partitionID, long whichTime) {
		this.topic = topic;
		this.brokerInfos = brokerInfos;
		this.maxReads = maxReads;
		this.partitionID = partitionID;
		this.whichTime = whichTime;
	}
}
