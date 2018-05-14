package com.season.platform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by jiyc on 2017/2/13.
 */

@SpringBootApplication
@ServletComponentScan
public class PlatformAdminApplication {
    private static Logger logger = LoggerFactory.getLogger(PlatformAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlatformAdminApplication.class, args);
        logger.info("PlatformAdminApplication Start Success");
    }
}
