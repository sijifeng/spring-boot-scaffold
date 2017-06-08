package com.season.platform.flume.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;


public class FlumeServer {
    private static String ip = "127.0.0.1";
    private static int port = 2345;

    
    public void startServer() {
        try {
            System.out.println("FlumeServer start ....");
            
            ThriftSourceProtocol.Processor<ThriftSourceProtocol.Iface> transmissionService = new ThriftSourceProtocol.Processor<ThriftSourceProtocol.Iface>(new ThriftSourceProtocolImpl());

            // 简单的单线程服务模型，一般用于测试
            TServerSocket serverTransport = new TServerSocket(port);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(transmissionService);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        FlumeServer server = new FlumeServer();
        server.startServer();
    }
}
