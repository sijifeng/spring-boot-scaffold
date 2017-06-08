package com.season.platform.thrift.util.ibm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.season.platform.thrift.util.ibm.Hello.AsyncClient.helloString_call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloServiceClient {
	private static final Logger logger = LoggerFactory.getLogger(HelloServiceServer.class);

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 7911;
	public static final int TIMEOUT = 30000;

	public static void main(String[] args) {
		HelloServiceClient client = new HelloServiceClient();
		//client.ThreadPoolServer();
		//client.NonblockingServer();
		//client.HsHaServer();
		//client.AsynServer();
		//client.ThreadedSelectorServer();
		client.AsyncClient();
	}


	/**
	 * 调用 Hello 服务
	 */
	public void ThreadPoolServer() {
		TTransport transport = null;
		try {
			System.out.println("client started");
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket("localhost", SERVER_PORT, TIMEOUT);
			transport.open();
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			// 调用服务的 helloVoid 方法
			//client.helloVoid();
			client.helloString("sijifeng");
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


	public void simpleServer() {
		TTransport transport = null;
		try {
			System.out.println("client started");
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket("localhost", SERVER_PORT, TIMEOUT);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(transport);
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			transport.open();
			// 调用服务的 helloVoid 方法
			//client.helloVoid();
			client.helloString("sijifeng");
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


	public void NonblockingServer() {
		TTransport transport = null;
		try {
			System.out.println("client started");
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			//TProtocol protocol = new TBinaryProtocol(transport);
			TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			transport.open();
			// 调用服务的 helloVoid 方法
			//client.helloVoid();
			client.helloString("sijifeng");
			//String result = client.sayHello(userName);
			//System.out.println("Thrify client result =: " + result);
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


	public void HsHaServer() {
		TTransport transport = null;
		try {
			System.out.println("client started");
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			//TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			transport.open();
			// 调用服务的 helloVoid 方法
			client.helloString("sijifeng");

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


	public void ThreadedSelectorServer() {
		TTransport transport = null;
		try {
			System.out.println("client started");
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			//TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			transport.open();
			// 调用服务的 helloVoid 方法
			client.helloString("sijifeng");

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


	public void AsynServer() {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT);

			TProtocolFactory tprotocol = new TCompactProtocol.Factory();
			Hello.AsyncClient asyncClient = new Hello.AsyncClient(
					tprotocol, clientManager, transport);
			System.out.println("Client start .....");

			CountDownLatch latch = new CountDownLatch(1);
			AsyncMethodCallback callBack = new AsynCallback(latch);
			System.out.println("call method sayHello start ...");
			asyncClient.helloString("sijifeng", callBack);
			System.out.println("call method sayHello .... end");
			boolean wait = latch.await(30, TimeUnit.SECONDS);
			System.out.println("latch.await =:" + wait);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("startClient end.");
	}

	public class AsynCallback implements AsyncMethodCallback<helloString_call> {
		private CountDownLatch latch;

		public AsynCallback(CountDownLatch latch) {
			this.latch = latch;
		}

		public void onComplete(helloString_call response) {
			System.out.println("onComplete");
			try {
				// Thread.sleep(1000L * 1);
				System.out.println("AsynCall result =:"
						+ response.getResult().toString());
			} catch (TException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

		public void onError(Exception exception) {
			System.out.println("onError :" + exception.getMessage());
			latch.countDown();
		}
	}


	/**
	 * 调用[非阻塞IO]服务，异步
	 */
	public void AsyncClient() {
		try {
			//异步调用管理器
			TAsyncClientManager clientManager = new TAsyncClientManager();
			//设置传输通道，调用非阻塞IO。
			final TNonblockingTransport transport = new TNonblockingSocket("localhost", 7911);
			//设置协议
			TProtocolFactory protocol = new TCompactProtocol.Factory();
			//创建Client
			final Hello.AsyncClient client = new Hello.AsyncClient(protocol, clientManager, transport);
			// 调用服务
			System.out.println("开始：" + System.currentTimeMillis());

			client.helloBoolean(false, new AsyncMethodCallback<Boolean>() {
				@Override
				public void onComplete(Boolean aBoolean) {
					System.out.println("完成1： " + System.currentTimeMillis());
				}

				@Override
				public void onError(Exception e) {
					System.out.println("错误1： " + System.currentTimeMillis());
				}
			});

			/*client.helloBoolean(false, new AsyncMethodCallback<helloString_call>() {
				public void onError(Exception exception) {
					System.out.println("错误1： " + System.currentTimeMillis());
				}

				public void onComplete(helloString_call response) {
					System.out.println("完成1： " + System.currentTimeMillis());
					try {
						client.helloBoolean(false, new AsyncMethodCallback<helloString_call>() {
							public void onError(Exception exception) {
								System.out.println("错误2： " + System.currentTimeMillis());
							}

							public void onComplete(helloString_call response) {
								System.out.println("完成2： " + System.currentTimeMillis());
								transport.close();
							}
						});
					} catch (TException e) {
						e.printStackTrace();
					}
				}
			});*/
			System.out.println("结束：" + System.currentTimeMillis());
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
