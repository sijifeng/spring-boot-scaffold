package com.season.platform.thrift.server.service.thrift;

import com.season.platform.thrift.server.gen.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import com.season.platform.thrift.support.EnableThriftServer;
import com.season.platform.thrift.support.ThriftServerService;
import org.springframework.stereotype.Service;

/**
 * HelloServiceImpl
 * <p>
 * <p>
 * </p>
 *
 * @author Vigor Yuan
 */
@Service
@EnableThriftServer(genClass = HelloService.class)
public class HelloServiceImpl implements HelloService.Iface, ThriftServerService {

    @Override
    public String getName() {
        return "helloService";
    }

    @Override
    public TProcessor getProcessor(ThriftServerService bean) {
        HelloService.Iface impl = (HelloService.Iface) bean;
        return new HelloService.Processor<HelloService.Iface>(impl);
    }

    @Override
    public String sayHello() throws TException {
        return "Hello,World";
    }

    @Override
    public String sayName(String name) throws TException {
        return "hello," + name;
    }
}
