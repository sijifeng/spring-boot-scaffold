package com.season.platform.common.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class ImageDownloader {

	private static final int CONN_TIME_OUT = 5 * 1000;
	private static final String USER_AGENT = "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36";
	
	private String url;
	private File saveDir;

	public ImageDownloader(String url, File saveDir) {
		this.url = url;
		this.saveDir = saveDir;
	}

	public void download() {
		try {
			URL url = new URL(this.url);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(CONN_TIME_OUT);
			con.setRequestProperty("User-Agent", USER_AGENT);
			InputStream is = con.getInputStream();
			String fileName = getSaveFileName(con.getContentType());
			File outputTmpFile = new File(saveDir.getPath() + "\\" + fileName + ".ren");
			byte[] bs = new byte[1024];
			int len;
			OutputStream os = new FileOutputStream(outputTmpFile);
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			os.close();
			is.close();

			if (outputTmpFile.length() == 0) {
				outputTmpFile.delete();
			} else {
				File outputFile = new File(saveDir.getPath() + "\\" + fileName);
				outputTmpFile.renameTo(outputFile);
				System.out.println(Thread.currentThread().getName() + " - Download Finished. URL：" + this.url);
			}
		} catch (Exception e) {
			System.err.println(Thread.currentThread().getName() + " - Download Failed. Error:" + e.getMessage());
		}
	}

	private String getSaveFileName(String contentType) {
		contentType = contentType.toLowerCase(); 
		String uuid = UUID.randomUUID().toString();
		if ("image/jpeg".equals(contentType)) {
			return uuid + ".jpg";
		} else if ("image/png".equals(contentType)) {
			return uuid + ".png";
		} else if ("image/gif".equals(contentType)) {
			return uuid + ".gif";
		} else {
			return uuid + ".jpg";
		}
	}
	
}
