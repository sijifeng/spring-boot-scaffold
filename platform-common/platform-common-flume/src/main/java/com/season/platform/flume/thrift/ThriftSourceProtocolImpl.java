package com.season.platform.flume.thrift;

import org.apache.thrift.TException;

import java.util.List;

public class ThriftSourceProtocolImpl implements ThriftSourceProtocol.Iface{

    @Override
    public Status append(ThriftFlumeEvent event) throws TException {
        System.out.println("哥收到了单条数据");
        return Status.OK;
    }

    @Override
    public Status appendBatch(List<ThriftFlumeEvent> events) throws TException {
        System.out.println("哥收到了批量数据数据");
        return null;
    }

}
