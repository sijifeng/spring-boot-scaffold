package com.season.platform.hbase;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import sun.management.resources.agent;


/**
 * Created by jiyc on 2017/5/4.
 */
public class UserAgentParser {
	static UserAgentAnalyzer uaa = null;

	// 初始化uasParser对象
	static {
		uaa = new UserAgentAnalyzer();
	}

	public static void main(String[] args) {
		String str = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1";
		//String str = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)";

		UserAgent agent = uaa.parse(str);
		System.out.println(agent.getValue("OperatingSystemNameVersion"));
		System.out.println(agent.getValue("AgentNameVersion"));



		/*UserAgent userAgent = UserAgent.parseUserAgentString(str);
		System.out.println(userAgent.getBrowser());


		Parser uaParser = null;
		try {
			uaParser = new Parser();
			Client c = uaParser.parse(str);
			System.out.println(c.os);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		/*System.out.println("=====================================================");
		long t1 = System.currentTimeMillis();
		UserAgentAnalyzer uaa = new UserAgentAnalyzer();
		long t2 = System.currentTimeMillis();
		System.out.println("t2-t1=" + (t2 - t1));

		UserAgent agent = uaa.parse("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");
		long t3 = System.currentTimeMillis();
		System.out.println("t3-t2=" + (t3 - t2));
		System.out.println(agent.getValue("OperatingSystemNameVersion"));
		System.out.println(agent.getValue("AgentNameVersion"));

		System.out.println("循环");
		for (String fieldName : agent.getAvailableFieldNamesSorted()) {
			System.out.println(fieldName + " = " + agent.getValue(fieldName));
		}

		long t4 = System.currentTimeMillis();
		System.out.println("t4-t3=" + (t4 - t3));*/
	}
}
