package com.season.platform.flume.thrift;

import com.alibaba.fastjson.JSON;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;


public class FlumeClient {
	public static final String SERVER_IP = "192.168.78.48";
	public static final int SERVER_PORT = 41414;
	public static final int TIMEOUT = 30000;

	public void startClient() {
		TTransport transport = null;
		try {
			transport = new TFastFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			ThriftSourceProtocol.Client client = new ThriftSourceProtocol.Client(protocol);

			transport.open();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("topic", "testflume");

			Data data = new Data();
			data.setEvent("test11");
			data.setHost("xyzs_httplog_server_tx_01");
			data.setTimestamp(1474979590);
			//data.setTimestamp(1474975590);

			Map<String, String> properties = new HashMap<>();
			properties.put("category", "cqss");
			properties.put("cpu_average", "86");
			properties.put("cpu1", "100");
			properties.put("enumTest", "muzhidao");

			data.setProperties(properties);
			//Event event = EventBuilder.withBody(data, charset);

			ThriftFlumeEvent event = new ThriftFlumeEvent(headers, ByteBuffer.wrap(JSON.toJSONString(data).getBytes()));
			client.append(event);
			//client.appendBatch(events)

		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}

	public static void main(String[] args) {
		FlumeClient client = new FlumeClient();
		client.startClient();
	}

}
