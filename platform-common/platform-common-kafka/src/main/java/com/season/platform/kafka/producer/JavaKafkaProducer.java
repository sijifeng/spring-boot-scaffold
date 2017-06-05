package com.season.platform.kafka.producer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jiyc on 2017/6/5.
 */
public class JavaKafkaProducer {
	private Logger logger = Logger.getLogger(JavaKafkaProducer.class);
	public static final String TOPIC_NAME = "test";
	public static final char[] charts = "qazwsxedcrfvtgbyhnujmikolp1234567890".toCharArray();
	public static final int chartsLength = charts.length;


	public static void main(String[] args) {
		String brokerList = "192.168.187.149:9092";
		brokerList = "192.168.187.149:9092,192.168.187.149:9093,192.168.187.149:9094,192.168.187.149:9095";
		brokerList = "192.168.187.146:9092";
		Properties props = new Properties();
		props.put("metadata.broker.list", brokerList);
		/**
		 * 0表示不等待结果返回<br/>
		 * 1表示等待至少有一个服务器返回数据接收标识<br/>
		 * -1表示必须接收到所有的服务器返回标识，及同步写入<br/>
		 * */
		props.put("request.required.acks", "0");
		/**
		 * 内部发送数据是异步还是同步
		 * sync：同步, 默认
		 * async：异步
		 */
		props.put("producer.type", "async");
		/**
		 * 设置序列化的类
		 * 可选：kafka.serializer.StringEncoder
		 * 默认：kafka.serializer.DefaultEncoder
		 */
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		/**
		 * 设置分区类
		 * 根据key进行数据分区
		 * 默认是：kafka.producer.DefaultPartitioner ==> 安装key的hash进行分区
		 * 可选:kafka.serializer.ByteArrayPartitioner ==> 转换为字节数组后进行hash分区
		 */
		props.put("partitioner.class", "JavaKafkaProducerPartitioner");

		// 重试次数
		props.put("message.send.max.retries", "3");

		// 异步提交的时候(async)，并发提交的记录数
		props.put("batch.num.messages", "200");

		// 设置缓冲区大小，默认10KB
		props.put("send.buffer.bytes", "102400");

		// 2. 构建Kafka Producer Configuration上下文
		ProducerConfig config = new ProducerConfig(props);

		// 3. 构建Producer对象
		final Producer<String, String> producer = new Producer<String, String>(config);

		// 4. 发送数据到服务器，并发线程发送
		final AtomicBoolean flag = new AtomicBoolean(true);
		int numThreads = 50;
		ExecutorService pool = Executors.newFixedThreadPool(numThreads);
		for (int i = 0; i < 5; i++) {
			pool.submit(new Thread(new Runnable() {
				@Override
				public void run() {
					while (flag.get()) {
						// 发送数据
						KeyedMessage message = generateKeyedMessage();
						producer.send(message);
						System.out.println("发送数据:" + message);

						// 休眠一下
						try {
							int least = 10;
							int bound = 100;
							Thread.sleep(ThreadLocalRandom.current().nextInt(least, bound));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					System.out.println(Thread.currentThread().getName() + " shutdown....");
				}
			}, "Thread-" + i));

		}

		// 5. 等待执行完成
		long sleepMillis = 600000;
		try {
			Thread.sleep(sleepMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag.set(false);

		// 6. 关闭资源

		pool.shutdown();
		try {
			pool.awaitTermination(6, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		} finally {
			producer.close(); // 最后之后调用
		}
	}

	/**
	 * 产生一个消息
	 *
	 * @return
	 */
	private static KeyedMessage<String, String> generateKeyedMessage() {
		String key = "key_" + ThreadLocalRandom.current().nextInt(10, 99);
		StringBuilder sb = new StringBuilder();
		int num = ThreadLocalRandom.current().nextInt(1, 5);
		for (int i = 0; i < num; i++) {
			sb.append(generateStringMessage(ThreadLocalRandom.current().nextInt(3, 20))).append(" ");
		}
		String message = sb.toString().trim();
		return new KeyedMessage(TOPIC_NAME, key, message);
	}

	/**
	 * 产生一个给定长度的字符串
	 *
	 * @param numItems
	 * @return
	 */
	private static String generateStringMessage(int numItems) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numItems; i++) {
			sb.append(charts[ThreadLocalRandom.current().nextInt(chartsLength)]);
		}
		return sb.toString();
	}
}
