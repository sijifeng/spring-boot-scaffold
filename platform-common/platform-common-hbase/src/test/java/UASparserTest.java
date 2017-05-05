import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;

/**
 * Created by jiyc on 2017/5/4.
 */
public class UASparserTest {
	static UASparser uasParser = null;

	// 初始化uasParser对象
	static {
		try {
			uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String str = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1";
		System.out.println(str);
		try {
			UserAgentInfo userAgentInfo = UASparserTest.uasParser.parse(str);
			System.out.println("操作系统名称："+userAgentInfo.getOsFamily());//
			System.out.println("操作系统："+userAgentInfo.getOsName());//
			System.out.println("浏览器版本："+userAgentInfo.getBrowserVersionInfo());//
			System.out.println("设备类型："+userAgentInfo.getDeviceType());
			System.out.println("浏览器:"+userAgentInfo.getUaName());
			System.out.println("类型："+userAgentInfo.getType());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
