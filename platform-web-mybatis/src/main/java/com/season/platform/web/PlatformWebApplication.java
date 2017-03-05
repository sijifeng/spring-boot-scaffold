package com.season.platform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by jiyc on 2017/2/13.
 */

@SpringBootApplication
public class PlatformWebApplication {
	private static Logger logger = LoggerFactory.getLogger(PlatformWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlatformWebApplication.class, args);
		logger.info("PlatformWebApplication Start Success");
	}
}
