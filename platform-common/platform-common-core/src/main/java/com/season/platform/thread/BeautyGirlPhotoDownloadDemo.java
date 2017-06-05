package com.season.platform.thread;

/**
 * Created by jiyc on 2017/6/5.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;


public class BeautyGirlPhotoDownloadDemo {
	public static void main(String[] args) {
		String[] categorys = new String[]{"rihan", "yule", "dongm"};
		ConcurrentMap<String, BlockingQueue<String>> map = new ConcurrentHashMap<String, BlockingQueue<String>>();

		// 分别启用线程来获取图片的url
		for (String category : categorys) {
			BlockingQueue<String> queue = new LinkedBlockingDeque<>();
			map.put(category, queue); // 添加一个初始化
			Thread thread = new Thread(new FetchImageUrlRunnable(category, queue), "fetchimg_" + category);
			thread.start();
		}

		File imagePath = new File("F:/image/");
		// 每一个品类其两个线程去下载
		for (Map.Entry<String, BlockingQueue<String>> entry : map.entrySet()) {
			for (int i = 0; i < 2; i++) {
				Thread thread = new Thread(new DownloadImageRunnable(imagePath, entry.getKey(), entry.getValue()), "downloadimage_" + entry.getKey() + i);
				thread.start();
			}
		}
	}

	/**
	 * 解析页面代码，保存图片url链接
	 *
	 * @author jsliuming
	 */
	public static class FetchImageUrlRunnable implements Runnable {
		private String category;
		private BlockingQueue<String> imageUrlQueue;

		public FetchImageUrlRunnable(String category, BlockingQueue<String> queue) {
			this.category = category;
			this.imageUrlQueue = queue;
		}

		@Override
		public void run() {
			try {
				String url = "";
				BufferedReader br = null;
				for (int i = 10; i < 100; i++) {
					for (int j = 1; j < 20; j++) {
						url = "http://www.mm8mm8.com/" + this.category + "/list_" + i + "_" + j + ".html";
						System.out.println(Thread.currentThread().getName() + ":" + url);

						StringBuffer content = new StringBuffer();
						try {
							URLConnection connection = new URL(url).openConnection();
							connection.connect();
							br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
							String line = null;
							while ((line = br.readLine()) != null) {
								content.append(line);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (br != null) {
								try {
									br.close();
								} catch (Exception e) {
								}
							}
						}

						// 已经拿到内容，开始解析url
						if (content.length() == 0) {
							// empty
							break;
						} else {
							// 开始解析
							for (String u : this.getHtmlImageUrlList(content.toString())) {
								this.imageUrlQueue.put(u);
							}
						}
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		/**
		 * 获取图片url
		 *
		 * @param htmlText
		 * @return
		 */
		private List<String> getHtmlImageUrlList(String htmlText) {
			List<String> list = new ArrayList<String>();
			Pattern pattern = Pattern.compile("<img\\s*src\\s*=\\s*\"(?<imgUrl>[^\\s\"'<>]*)\"");
			Matcher matcher = pattern.matcher(htmlText);
			while (matcher.find()) {
				list.add(matcher.group("imgUrl"));
			}
			return list;
		}
	}

	/**
	 * 下载图片用线程
	 *
	 * @author jsliuming
	 */
	public static class DownloadImageRunnable implements Runnable {
		private String category;
		private BlockingQueue<String> imageUrlQueue;
		private File baseFile;

		public DownloadImageRunnable(File path, String category, BlockingQueue<String> queue) {
			this.category = category;
			this.imageUrlQueue = queue;
			baseFile = new File(path, this.category);
		}

		@Override
		public void run() {
			int index = 0;
			InputStream input = null;
			ImageOutputStream ios = null;
			while (true) {
				try {
					String imgurl = this.imageUrlQueue.take();

					URLConnection connection = new URL(imgurl).openConnection();
					connection.connect();
					input = connection.getInputStream();
					ios = new FileImageOutputStream(new File(baseFile, Thread.currentThread().getId() + "_" + index++ + ".jpg"));
					byte[] buf = new byte[2048];
					int n = -1;
					while ((n = input.read(buf)) > 0) {
						ios.write(buf, 0, n);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (Exception e) {
						}
					}
					if (ios != null) {
						try {
							ios.close();
						} catch (Exception e) {
						}
					}
				}
			}
		}

	}
}

