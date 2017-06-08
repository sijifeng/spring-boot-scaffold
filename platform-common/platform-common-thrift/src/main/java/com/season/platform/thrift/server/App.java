package com.season.platform.thrift.server;

import com.season.platform.thrift.support.ThriftApplicationListener;
import com.season.platform.thrift.util.ibm.HelloServiceServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "com.season.platform.thrift")
@EnableAspectJAutoProxy
public class App {
	private static final Logger logger = LoggerFactory.getLogger(HelloServiceServer.class);
	public static void main(String[] args) {
		new SpringApplicationBuilder().listeners(new ThriftApplicationListener()).sources(App.class).run(args);
		logger.info("springboot start");

	}

}
