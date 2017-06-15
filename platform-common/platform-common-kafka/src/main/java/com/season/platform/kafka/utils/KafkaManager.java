package com.season.platform.kafka.utils;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.BrokerEndPoint;
import kafka.common.*;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by jiyc on 2017/6/14.
 */
public class KafkaManager {
	private static final Logger logger = LoggerFactory.getLogger(KafkaManager.class);
	// 保存读取的数据 批量返回
	List<Message> messages = new ArrayList<>();
	// 是否停止的标志位
	private boolean stopFlg = false;
	// broker节点的复制
	private List<BrokerInfo> m_replicaBrokers = new ArrayList<BrokerInfo>();
	// 最大重试次数
	private int maxRetryTimes = 5;
	// 重试间隔时间
	private long retryIntervalMillis = 1000;
	// setting for how often to save the current kafka offset to ZooKeeper
	public long stateUpdateIntervalMs = 2000;
	// 自定义的kafka 配置文件
	private KafkaConfig kafkaConfig;
	// 当前读到的offset
	private long readOffset;
	// kafka 分区 ID
	private int partitionID;
	// kafka partition MetaData
	private PartitionMetadata metadata;
	// lower consumer 引用
	private SimpleConsumer consumer;

	/**
	 * 构造函数 初始化kafka 配置 及 获取offset等
	 *
	 * @param kafkaConfig
	 * @throws Exception
	 */
	public KafkaManager(KafkaConfig kafkaConfig) throws Exception {
		this.kafkaConfig = kafkaConfig;
		this.partitionID = kafkaConfig.partitionID;
		// 默认消费数据的偏移量是当前分区的最早偏移量值
		long whichTime = kafkaConfig.whichTime;
		String topic = kafkaConfig.topic;

		String clientName = createClientName(topic, partitionID);
		String groupId = clientName;
		// 获取当前topic分区对应的分区元数据(主要包括leader节点的连接信息)
		metadata = findLeader(kafkaConfig.brokerInfos, topic, partitionID);
		// 校验元数据
		validatePartitionMetadata(metadata);

		// 连接leader节点构建具体的SimpleConsumer对象
		consumer = this.createSimpleConsumer(metadata.leader().host(),
				metadata.leader().port(), clientName);
		// 获取offset
		readOffset = getLastOffset(consumer, groupId, topic, partitionID, whichTime, clientName);
	}


	public String run() throws Exception {
		int a_maxReads = kafkaConfig.maxReads;
		String leadBroker = metadata.leader().host();
		String topic = kafkaConfig.topic;
		String clientName = createClientName(topic, partitionID);
		String groupId = clientName;
		try {
			int numErrors = 0;
			int i = 0;
			while (!stopFlg) {
				if (consumer == null) {
					consumer = new SimpleConsumer(metadata.leader().host(), metadata.leader().port(), 100000, 64 * 1024, clientName);
				}
				FetchRequest req = new FetchRequestBuilder().clientId(clientName).addFetch(topic, partitionID, readOffset, 10000).build();
				FetchResponse fetchResponse = consumer.fetch(req);

				if (fetchResponse.hasError()) {
					numErrors++;
					// Something went wrong!
					short code = fetchResponse.errorCode(topic, partitionID);
					System.out.println("Error fetching data from the Broker:" + metadata.leader().host() + " Reason: " + code);
					if (numErrors > 5)
						break;
					if (code == ErrorMapping.OffsetOutOfRangeCode()) {
						// We asked for an invalid offset. For simple case ask for
						// the last element to reset
						readOffset = getLastOffset(consumer, groupId, topic, partitionID, kafka.api.OffsetRequest.LatestTime(), clientName);
						continue;
					}
					consumer.close();
					consumer = null;
					leadBroker = findNewLeader(leadBroker, topic, partitionID, metadata.leader().port());
					continue;
				}
				numErrors = 0;

				long numRead = 0;
				for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(topic, partitionID)) {
					long currentOffset = messageAndOffset.offset();
					if (currentOffset < readOffset) {
						System.out.println("Found an old offset: " + currentOffset + " Expecting: " + readOffset);
						continue;
					}

					readOffset = messageAndOffset.nextOffset();
					ByteBuffer payload = messageAndOffset.message().payload();

					byte[] bytes = new byte[payload.limit()];
					payload.get(bytes);
					System.out.println(String.valueOf(messageAndOffset.offset()) + ": " + new String(bytes, "UTF-8"));
					messages.add(new Message(new String(bytes, "UTF-8"), Long.valueOf(messageAndOffset.offset()), partitionID));
					numRead++;
					++i;

					if (i == a_maxReads) {
						System.out.println("达到数量 " + i + ",发送数据");
						Map<String, String> params = new HashMap<>();
						params.put("offset", readOffset + "");
						i = 0;
						messages.clear();
					}
				}

				if (numRead == 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
					}
				}
			}
			System.out.println("stop");
		} finally {
			if (consumer != null)
				consumer.close();
		}
		return null;
	}


	/**
	 * 构建一个SimpleConsumer并返回
	 *
	 * @param host
	 * @param port
	 * @param clientName
	 * @return
	 */
	private SimpleConsumer createSimpleConsumer(String host, int port, String clientName) {
		return new SimpleConsumer(host, port, 100000, 64 * 1024, clientName);
	}

	/**
	 * 构建clientName根据主题名称和分区id
	 *
	 * @param topic
	 * @param partitionID
	 * @return
	 */
	private String createClientName(String topic, int partitionID) {
		return "client_" + topic + "_" + partitionID;
	}

	/**
	 * 验证分区元数据，如果验证失败，直接抛出IllegalArgumentException异常
	 *
	 * @param metadata
	 */
	private void validatePartitionMetadata(PartitionMetadata metadata) {
		if (metadata == null) {
			System.out.println("Can't find metadata for Topic and Partition. Exiting!!");
			throw new IllegalArgumentException("Can't find metadata for Topic and Partition. Exiting!!");
		}
		if (metadata.leader() == null) {
			System.out.println("Can't find Leader for Topic and Partition. Exiting!!");
			throw new IllegalArgumentException("Can't find Leader for Topic and Partition. Exiting!!");
		}
	}

	private PartitionMetadata findLeader(List<BrokerInfo> a_seedBrokers, String a_topic, int a_partition) {
		PartitionMetadata returnMetaData = null;
		loop:
		for (BrokerInfo seed : a_seedBrokers) {
			SimpleConsumer consumer = null;
			try {
				consumer = new SimpleConsumer(seed.brokerHost, seed.brokerPort, 100000, 64 * 1024, "leaderLookup");
				List<String> topics = Collections.singletonList(a_topic);
				TopicMetadataRequest req = new TopicMetadataRequest(topics);
				TopicMetadataResponse resp = consumer.send(req);

				List<TopicMetadata> metaData = resp.topicsMetadata();
				for (TopicMetadata item : metaData) {
					for (PartitionMetadata part : item.partitionsMetadata()) {
						if (part.partitionId() == a_partition) {
							returnMetaData = part;
							break loop;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error communicating with Broker [" + seed + "] to find Leader for [" + a_topic + ", " + a_partition + "] Reason: " + e);
			} finally {
				if (consumer != null)
					consumer.close();
			}
		}

		if (returnMetaData != null) {
			m_replicaBrokers.clear();
			for (BrokerEndPoint replica : returnMetaData.replicas()) {
				m_replicaBrokers.add(new BrokerInfo(replica.host(), replica.port()));
			}
		}
		return returnMetaData;
	}

	/**
	 * 获取当前groupID对应的consumer在对应的topic和partition中对应的offset偏移量
	 *
	 * @param consumer   消费者
	 * @param groupId    消费者分区id
	 * @param topic      所属的Topic
	 * @param partition  所属的分区ID
	 * @param whichTime  用于判断，当consumer从没有消费数据的时候，从当前topic的Partition的那个offset开始读取数据
	 * @param clientName client名称
	 * @return 正常情况下，返回非负数，当出现异常的时候，返回-1
	 */
	public long getLastOffset(SimpleConsumer consumer, String groupId, String topic, int partition, long whichTime, String clientName) {
		// 1. 从ZK中获取偏移量，当zk的返回偏移量大于0的时候，表示是一个正常的偏移量
		long offset = this.getOffsetOfTopicAndPartition(consumer, groupId, clientName, topic, partition);
		if (offset > 0) {
			return offset;
		}

		TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
		Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
		requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
		OffsetRequest request = new OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientName);
		OffsetResponse response = consumer.getOffsetsBefore(request);

		if (response.hasError()) {
			System.out.println("Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition));
			return 0;
		}
		long[] offsets = response.offsets(topic, partition);

		if (offsets != null && offsets.length > 0) {
			return offsets[0];
		} else {
			if (whichTime != kafka.api.OffsetRequest.EarliestTime()) {
				return getLastOffset(consumer, groupId, topic, partition, kafka.api.OffsetRequest.EarliestTime(), clientName);
			} else {
				return 0;
			}
		}
	}


	/**
	 * @param a_oldLeader
	 * @param a_topic
	 * @param a_partition
	 * @param a_port
	 * @return String
	 * @throws Exception 找一个leader broker
	 */
	private String findNewLeader(String a_oldLeader, String a_topic, int a_partition, int a_port) throws Exception {
		for (int i = 0; i < 3; i++) {
			boolean goToSleep = false;
			PartitionMetadata metadata = findLeader(m_replicaBrokers, a_topic, a_partition);
			if (metadata == null) {
				goToSleep = true;
			} else if (metadata.leader() == null) {
				goToSleep = true;
			} else if (a_oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {
				// first time through if the leader hasn't changed give
				// ZooKeeper a second to recover
				// second time, assume the broker did recover before failover,
				// or it was a non-Broker issue
				//
				goToSleep = true;
			} else {
				return metadata.leader().host();
			}
			if (goToSleep) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
				}
			}
		}
		System.out.println("Unable to find new leader after Broker failure. Exiting");
		throw new Exception("Unable to find new leader after Broker failure. Exiting");
	}


	/**
	 * 从保存consumer消费者offset偏移量的位置获取当前consumer对应的偏移量
	 *
	 * @param consumer    消费者
	 * @param groupId     Group Id
	 * @param clientName  client名称
	 * @param topic       topic名称
	 * @param partitionID 分区id
	 * @return
	 */
	public long getOffsetOfTopicAndPartition(SimpleConsumer consumer, String groupId, String clientName, String topic, int partitionID) {
		TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partitionID);
		List<TopicAndPartition> requestInfo = new ArrayList<TopicAndPartition>();
		requestInfo.add(topicAndPartition);
		OffsetFetchRequest request = new OffsetFetchRequest(groupId, requestInfo, 0, clientName);
		OffsetFetchResponse response = consumer.fetchOffsets(request);

		// 获取返回值
		Map<TopicAndPartition, OffsetMetadataAndError> returnOffsetMetadata = response.offsets();
		// 处理返回值
		if (returnOffsetMetadata != null && !returnOffsetMetadata.isEmpty()) {
			// 获取当前分区对应的偏移量信息
			OffsetMetadataAndError offset = returnOffsetMetadata.get(topicAndPartition);
			if (offset.error() == ErrorMapping.NoError()) {
				// 没有异常，表示是正常的，获取偏移量
				return offset.offset();
			} else {
				// 当Consumer第一次连接的时候(zk中不在当前topic对应数据的时候)，会产生UnknownTopicOrPartitionCode异常
				System.out.println("Error fetching data Offset Data the Topic and Partition. Reason: " + offset.error());
			}
		}

		// 所有异常情况直接返回0
		return 0;
	}


	/**
	 * 更新偏移量，当SimpleConsumer发生变化的时候，重新构造一个新的SimpleConsumer并返回
	 *
	 * @param consumer
	 * @param topic
	 * @param partitionID
	 * @param readOffSet
	 * @param groupId
	 * @param clientName
	 * @param times
	 * @return
	 * @throws RuntimeException 当更新失败的情况下
	 */
	private SimpleConsumer updateOffset(SimpleConsumer consumer, String topic, int partitionID, long readOffSet, String groupId, String clientName, int times) {
		// 构建请求对象
		Map<TopicAndPartition, OffsetAndMetadata> requestInfoMap = new HashMap<TopicAndPartition, OffsetAndMetadata>();
		TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partitionID);
		requestInfoMap.put(topicAndPartition, new OffsetAndMetadata(new OffsetMetadata(readOffSet, OffsetMetadata.NoMetadata()), -1, -1));
		OffsetCommitRequest ocRequest = new OffsetCommitRequest(groupId, requestInfoMap, 0, clientName);
		// 提交修改偏移量的请求，并获取返回值
		OffsetCommitResponse response = consumer.commitOffsets(ocRequest);

		// 根据返回值进行不同的操作
		if (response.hasError()) {
			short code = response.errorCode(topicAndPartition);
			if (times > this.maxRetryTimes) {
				throw new RuntimeException("Update the Offset occur exception," +
						" the current response code is:" + code);
			}

			if (code == ErrorMapping.LeaderNotAvailableCode()) {
				// 当异常code为leader切换情况的时候，重新构建consumer对象
				// 操作步骤：先休眠一段时间，再重新构造consumer对象，最后重试
				try {
					Thread.sleep(this.retryIntervalMillis);
				} catch (InterruptedException e) {
					// nothings
				}
				PartitionMetadata metadata = this.findNewLeaderMetadata(consumer.host(),
						topic, partitionID);
				this.validatePartitionMetadata(metadata);
				consumer = this.createSimpleConsumer(metadata.leader().host(),
						metadata.leader().port(), clientName);
				// 重试
				consumer = updateOffset(consumer, topic, partitionID, readOffSet, groupId, clientName, times + 1);
			}

			if (code == ErrorMapping.RequestTimedOutCode()) {
				// 当异常为请求超时的时候，进行重新请求
				consumer = updateOffset(consumer, topic, partitionID, readOffSet, groupId, clientName, times + 1);
			}

			// 其他code直接抛出异常
			throw new RuntimeException("Update the Offset occur exception," +
					" the current response code is:" + code);
		}

		// 返回修改后的consumer对象
		return consumer;
	}


	/**
	 * 根据给定参数获取一个新leader的分区元数据信息
	 *
	 * @param oldLeader
	 * @param topic
	 * @param partitionID
	 * @return
	 */
	private PartitionMetadata findNewLeaderMetadata(String oldLeader,
													String topic,
													int partitionID) {
		/*KafkaTopicPartitionInfo topicPartitionInfo = new KafkaTopicPartitionInfo(topic, partitionID);
		List<KafkaBrokerInfo> brokerInfos = this.replicaBrokers.get(topicPartitionInfo);*/
		for (int i = 0; i < 3; i++) {
			boolean gotoSleep = false;
			PartitionMetadata metadata = this.findLeader(m_replicaBrokers, topic, partitionID);
			if (metadata == null) {
				gotoSleep = true;
			} else if (metadata.leader() == null) {
				gotoSleep = true;
			} else if (oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {
				// leader切换过程中
				gotoSleep = true;
			} else {
				return metadata;
			}

			if (gotoSleep) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// nothings
				}
			}
		}

		System.out.println("Unable to find new leader after Broker failure. Exiting!!");
		throw new RuntimeException("Unable to find new leader after Broker failure. Exiting!!");
	}


	public boolean setStop() {
		this.stopFlg = true;
		return true;
	}
}
