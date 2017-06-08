package com.season.platform.hive;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * 测试hive 的客户端连接
 *
 * @author alexxiyang (https://github.com/alexxiyang)
 */
public class HiveJdbcClient {

	/**
	 * 注意：hive-server2 引用的driver是  org.apache.hive.* 而 hive-server 是 org.apache.hadoop.hive.*
	 */
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		//hive的默认端口是 10000，如果要修改就修改 hive-site.xml 文件的hive.server2.thrift.port 属性值
		//默认用户名hive，默认密码为空
		Connection con = DriverManager.getConnection("jdbc:hive2://192.168.131.132:10000/default", "hive", "");

		Statement stmt = con.createStatement();
		//测试的表名 testhivedrivertable
		String tableName = "abc";

		//如果已经存在就删除
		stmt.execute("drop table if exists " + tableName);

		//创建这张表
		stmt.execute("create table " + tableName + " (key int, value string) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\054'");
		//看下创建是否成功
		String sql = "show tables '" + tableName + "'";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		if (res.next()) {
			System.out.println(res.getString(1));
		}

		//看下表结构
		sql = "describe " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}

		// 加载数据到表里面
		// NOTE: filepath 是本地文件所在的位置，注意这个本地不是你的电脑！
		// 你得先把这个文件上传到服务器，然后这里的路径是服务器上这个文件的路径
		// NOTE: /data/a.txt
		String filepath = "/opt/a.txt";
		sql = "load data local inpath '" + filepath + "' into table " + tableName;
		System.out.println("Running: " + sql);
		stmt.execute(sql);

		// select * query
		sql = "select * from " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
		}
	}
}  