package com.season.platform.engine.db;

import com.facebook.presto.jdbc.PrestoConnection;
import com.facebook.presto.jdbc.PrestoDriver;
import com.season.platform.engine.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static java.lang.String.format;

/**
 * Created by jiyc on 2016/10/14.
 */
public class PrestoClient {

	private final static Logger logger = LoggerFactory.getLogger(PrestoClient.class);
	public static Properties properties;

	static {
		properties = Config.loadConfig("presto.properties");
	}

	private static String host = properties.getProperty("host");
	private static String port = properties.getProperty("port");
	private static String catalog = properties.getProperty("catalog");
	private static String url = "jdbc:presto://%s";
	private static String size = properties.getProperty("size");
	private static PrestoDriver prestoDriver = new PrestoDriver();

	public static Connection getConnection() throws Throwable {
		PrestoConnection connection;
		try {
			url = format(url, host + ":" + port + "/" + catalog);
			logger.info(format("presto connection url: %s", url));
			connection = (PrestoConnection) prestoDriver.connect(url, properties);
//            connection.setSessionProperty("query_priority", properties.getProperty("query_priority"));
//            connection.setSessionProperty("task_concurrency", properties.getProperty("task_concurrency"));
			return connection;
		} catch (Throwable t) {
			logger.error("presto connection error:", t);
			throw t;
		}
	}


	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(String sql, Statement statement) throws SQLException {
		logger.info("query sql :" + sql);
		logger.info("----start presto query----");
		Long startTime = System.currentTimeMillis();
		if (statement == null) {
			throw new RuntimeException("执行sql的statement为空");
		}
		statement.setFetchSize(Integer.parseInt(size));
		ResultSet resultSet = statement.executeQuery(sql);
		Long endTime = System.currentTimeMillis();
		logger.info("----end presto query----");
		logger.info(String.format("presto query cost time :%d ms", endTime - startTime));
		return resultSet;
	}
}

