package com.season.platform.common.crawler;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleImageCrawler extends ImageCrawler {
	
	//tbm=isch
	//q=美女 搜索关键字
	//start=60 开始条数
	//tbs=isz:l 搜索条件
	//	尺寸
	//	tbs=isz:l 大
	//	tbs=isz:m 中
	//	颜色
	//	tbs=ic:color 彩色
	//	tbs=ic:gray 黑白
	//	tbs=ic:trans 透明
	//	类型
	//	tbs=itp:face 脸部特写
	//	tbs=itp:photo 照片
	//	tbs=itp:clipart 剪贴画
	//	tbs=itp:lineart 素描画
	//	tbs=itp:animated 动画
	//	条件组合
	//	tbs=isz:l,ic:color,itp:face
	private static final String GOOGLE_IMAGE_SEARCH_URL = "https://www.google.com/search?tbm=isch&q=%s&start=%d&tbs=isz:l";
	private static final int PAGE_SIZE = 20 * 5;
	private static final String IMAGE_URL_REG = "\"ou\":\"(https?://[^\"]+)\"";
	private static final Pattern IMAGE_PATTERN = Pattern.compile(IMAGE_URL_REG);
	
	@Override
	public String getSearchUrl(String keyword, int begin) {
		return String.format(GOOGLE_IMAGE_SEARCH_URL, keyword, begin);
	}

	@Override
	public int getPageSize() {
		return PAGE_SIZE;
	}

	@Override
	public int parseImageUrl(ConcurrentLinkedQueue<String> queue, StringBuffer data) {
		int count = 0;
		Matcher matcher = IMAGE_PATTERN.matcher(data);
		while (matcher.find()) {
			queue.offer(matcher.group(1));
			count++;
		}
		return count;
	}
}
