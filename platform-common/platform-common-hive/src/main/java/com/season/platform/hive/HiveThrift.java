package com.season.platform.hive;

/*import org.apache.hadoop.hive.service.ThriftHive;
import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.hive.service.cli.thrift.ThriftCLIServiceClient;*/

import org.apache.hadoop.hive.service.ThriftHive;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class HiveThrift {
	// Thrift Server IP Address
	private static String HOST = "192.168.131.132";
	// Thrift Server Port
	private static int PORT = 10000;
	//public static final Logger logger = Logger.getLogger(HiveThrift.class); // logger

	public static void main(String[] args) throws Exception {
		System.out.println(1);

		//final TSocket tSocket = new TSocket(HOST, PORT);
		TTransport transport = new TSocket(HOST, PORT);
		//TProtocol protocol = new TBinaryProtocol(transport);
		TProtocol protocol = new TBinaryProtocol(transport);
		ThriftHive.Client client = new ThriftHive.Client(protocol);
		//ThriftHiveMetastore.Client client =new ThriftHiveMetastore.Client(protocol);
		transport.open();
		System.out.println(2);
		client.execute("show databases");
		//client.execute("select * from default.abc");

        /*List<String> results = client.fetchAll();
        System.out.println(3);
        for (String result : results) {
            System.out.println(result);
        }*/
		transport.close();
		System.out.println(4);
	}
}
