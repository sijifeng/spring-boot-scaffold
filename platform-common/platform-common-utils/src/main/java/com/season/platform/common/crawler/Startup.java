package com.season.platform.common.crawler;

public class Startup {

	public static void main(String[] args) {
		new BaiduImageCrawler().fetch("美女", "D:\\data\\baidu\\java1\\");
		//new GoogleImageCrawler().fetch("美女", "D:\\data\\google\\");
	}

}
