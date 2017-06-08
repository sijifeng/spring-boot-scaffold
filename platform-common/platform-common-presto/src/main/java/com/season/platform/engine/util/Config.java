package com.season.platform.engine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by jiyc on 2016/11/21.
 */
public class Config {
	static Logger logger = LoggerFactory.getLogger(Config.class);

	public static Properties loadConfig(String property) {
//        Properties properties = null;
//        try {
//            properties = new Properties();
//            InputStream fileInputStream = Config.class.getClassLoader().getResourceAsStream(property);
//            logger.info("configuration file location :"+Config.class.getClassLoader().getResource(property).getPath());
//            properties.load(fileInputStream);
//        } catch (Throwable t) {
//            logger.error("load configuration file:" + property + " error,exit system!", t);
//            System.exit(-1);
//        }
//        return properties;
		PropertyLoader propertyLoader = new PropertyLoader(property);
		return propertyLoader.getProp();
	}
}
