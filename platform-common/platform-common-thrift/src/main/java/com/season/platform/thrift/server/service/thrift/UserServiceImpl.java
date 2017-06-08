package com.season.platform.thrift.server.service.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import com.season.platform.thrift.server.gen.UserService;
import com.season.platform.thrift.support.EnableThriftServer;
import com.season.platform.thrift.support.ThriftServerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * UserServiceImpl
 * <p>
 * <p>
 * </p>
 *
 * @author Vigor Yuan
 */
@Service
@EnableThriftServer(genClass = UserService.class)
public class UserServiceImpl implements UserService.Iface, ThriftServerService {

    @Override
    public String getName() {
        return "userService";
    }

    @Override
    public TProcessor getProcessor(ThriftServerService bean) {
        UserService.Iface impl = (UserService.Iface) bean;
        return new UserService.Processor<UserService.Iface>(impl);
    }

    @Override
    public List<String> findAll() throws TException {
        return Arrays.asList(new String[] { "a", "b" });
    }
}
