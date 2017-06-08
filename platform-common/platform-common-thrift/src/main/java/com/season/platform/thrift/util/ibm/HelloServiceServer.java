package com.season.platform.thrift.util.ibm;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;


public class HelloServiceServer {
	/**
	 * 启动 Thrift 服务器
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServiceServer server = new HelloServiceServer();
		//server.ThreadPoolServer();
		//server.NonblockingServer();
		//server.HsHaServer();
		server.AsynServer();
		//server.ThreadedSelectorServer();
	}


	public void SimpleServer() {
		try {
			// 设置服务端口为 7911
			TServerSocket serverTransport = new TServerSocket(7911);
			// 设置协议工厂为 TBinaryProtocol.Factory
			Factory proFactory = new Factory(true, true);
			// 关联处理器与 Hello 服务的实现
			//Hello.Processor processor = new Processor(new HelloServiceImpl());
			TProcessor processor = new Hello.Processor(new HelloServiceImpl());

			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(proFactory);

			TServer server = new TSimpleServer(tArgs);

			System.out.println("Start server on port 7911...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}


	public void ThreadPoolServer() {
		try {
			// 设置服务端口为 7911
			TServerSocket serverTransport = new TServerSocket(7911);
			// 设置协议工厂为 TBinaryProtocol.Factory
			Factory proFactory = new Factory(true, true);
			// 关联处理器与 Hello 服务的实现
			//Hello.Processor processor = new Processor(new HelloServiceImpl());
			TProcessor processor = new Hello.Processor(new HelloServiceImpl());

			//Args args1 = new Args(serverTransport);
			TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
					serverTransport);
			ttpsArgs.processor(processor);
			ttpsArgs.protocolFactory(proFactory);

			TServer server = new TThreadPoolServer(ttpsArgs);

			System.out.println("Start server on port 7911...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public void NonblockingServer() {
		try {
			System.out.println("HelloWorld TNonblockingServer start ....");
			// 设置服务端口为 7911
			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(7911);

			// 关联处理器与 Hello 服务的实现
			//Hello.Processor processor = new Processor(new HelloServiceImpl());
			TProcessor tprocessor = new Hello.Processor(new HelloServiceImpl());

			//Args args1 = new Args(serverTransport);
			TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(
					tnbSocketTransport);
			tnbArgs.processor(tprocessor);
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new TCompactProtocol.Factory());

			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new TNonblockingServer(tnbArgs);

			System.out.println("Start server on port 7911...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}


	public void HsHaServer() {
		try {
			System.out.println("HelloWorld THsHaServer start ....");
			// 设置服务端口为 7911
			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(7911);

			// 关联处理器与 Hello 服务的实现
			//Hello.Processor processor = new Processor(new HelloServiceImpl());
			TProcessor tprocessor = new Hello.Processor(new HelloServiceImpl());

			//Args args1 = new Args(serverTransport);
			THsHaServer.Args tnbArgs = new THsHaServer.Args(
					tnbSocketTransport);
			tnbArgs.processor(tprocessor);
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new Factory());

			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new THsHaServer(tnbArgs);

			System.out.println("Start server on port 7911...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}


	public void AsynServer() {
		try {
			System.out.println("HelloWorld TNonblockingServer start ....");

			TProcessor tprocessor = new Hello.Processor(new HelloServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					7911);
			TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(
					tnbSocketTransport);
			tnbArgs.processor(tprocessor);
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new TCompactProtocol.Factory());

			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new TNonblockingServer(tnbArgs);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}


	public void ThreadedSelectorServer() {
		try {
			// 设置服务端口为 7911
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(7911);

			//异步IO，需要使用TFramedTransport，它将分块缓存读取。
			TTransportFactory transportFactory = new TFramedTransport.Factory();

			// 设置协议工厂为 TBinaryProtocol.Factory
			Factory proFactory = new Factory();
			// 关联处理器与 Hello 服务的实现
			//Hello.Processor processor = new Processor(new HelloServiceImpl());
			TProcessor processor = new Hello.Processor(new HelloServiceImpl());


			TThreadedSelectorServer.Args options = new TThreadedSelectorServer.Args(serverTransport);
			options.workerThreads(8);
			options.selectorThreads(4);
			options.processor(processor);
			options.transportFactory(transportFactory);
			options.protocolFactory(proFactory);
			TServer dataServer = new TThreadedSelectorServer(options);
			System.out.println("Launching Thrift server.");
			dataServer.serve();
			System.out.println("Thrift server exited.");

			System.out.println("Start server on port 7911...");
			//server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
