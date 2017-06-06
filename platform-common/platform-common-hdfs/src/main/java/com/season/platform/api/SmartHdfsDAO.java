package com.season.platform.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiyc on 2017/6/6.
 */
public class SmartHdfsDAO implements HdfsFileSystem {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmartHdfsDAO.class);

	@Override
	public boolean createFile(String dst, String contents) throws Exception {
		Configuration conf = new Configuration();

		// windows 本地测试需要配置如下， linux环境可考虑去掉
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");

		FSDataOutputStream outputStream = null;
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
			Path path = new Path(dst);
			outputStream = fs.create(path);
			outputStream.writeBytes(contents);

		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs createFile][dst]=[" + dst + "][contents]=[" + contents + "]");
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			outputStream.close();
			fs.close();
		}
		return true;
	}

	@Override
	public boolean uploadFile(String src, String dst) throws Exception {
		Configuration conf = new Configuration();

		// windows 本地测试需要配置如下， linux环境可考虑去掉
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");

		FileSystem fs = null;

		try {
			fs = FileSystem.get(conf);
			Path srcPath = new Path(src);
			Path dstPath = new Path(dst);
			// 调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
			fs.copyFromLocalFile(false, srcPath, dstPath);
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs uploadFile][src]=[" + src + "][dst]=[" + dst + "]");
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			fs.close();
		}

		return true;
	}

	@Override
	public boolean rename(String oldName, String newName) throws IOException {
		Configuration conf = new Configuration();

		// windows 本地测试需要配置如下， linux环境可考虑去掉
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");

		FileSystem fs = null;
		boolean result = false;

		try {
			fs = FileSystem.get(conf);
			Path oldPath = new Path(oldName);
			Path newPath = new Path(newName);
			result = fs.rename(oldPath, newPath);
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs rename][oldName]=[" + oldName + "][newName]=[" + newName + "]");
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			fs.close();
		}
		return result;
	}

	@Override
	public boolean mkdir(String filePath) throws Exception {
		Configuration conf = new Configuration();

		// 远程会出现问题 Windows测试需要如下配置
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");

		boolean result = false;
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
			Path srcPath = new Path(filePath);
			result = fs.mkdirs(srcPath);
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs mkdir][filePath]=[" + filePath + "]");
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			fs.close();
		}
		return result;
	}

	@Override
	public boolean delete(String path) throws Exception {
		Configuration conf = new Configuration();

		// 远程会出现问题 Windows测试需要如下配置
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");

		boolean result = false;
		FileSystem fs = null;

		try {
			fs = FileSystem.get(conf);
			Path filePath = new Path(path);
			result = fs.deleteOnExit(filePath);
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs delete][path]=[" + path + "]");
		} catch (Exception e) {
			throw e;
		} finally {
			fs.close();
		}
		return result;
	}

	@Override
	public boolean downloadFile(String src, String dst) throws Exception {

		Configuration conf = new Configuration();

		// 远程会出现问题 Windows测试需要如下配置
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");

		FileSystem fs = null;

		try {
			fs = FileSystem.get(conf);
			Path srcPath = new Path(src);
			Path dstPath = new Path(dst);
			fs.copyToLocalFile(srcPath, dstPath);
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs downloadFile][src]=[" + src + "][dst]=[" + dst + "]");
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			fs.close();
		}

		return false;
	}

	@Override
	public boolean isExist(String path) throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = null;
		boolean result = false;

		// 远程会出现问题 Windows测试需要如下配置
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");

//      conf.set("mapred.jop.tracker", "hdfs://192.168.233.128:9000");
//      conf.set("fs.default.name", "hdfs://192.168.233.128:9000");
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");

		try {
			fs = FileSystem.get(conf);
			Path filePath = new Path(path);
			result = fs.exists(filePath);
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs isExist][path]=[" + path + "]");
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			fs.close();
		}
		return result;
	}

	@Override
	public List<String> readFile(String path) throws Exception {
		Configuration conf = new Configuration();

		// 远程会出现问题 Windows测试需要如下配置
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");

		List<String> data = new ArrayList<String>();
		FileSystem fs = null;
		FSDataInputStream in = null;
		BufferedReader br = null;

		try {
			fs = FileSystem.get(conf);
			Path srcPath = new Path(path);
			in = fs.open(srcPath);
			br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while (null != (line = br.readLine())) {
				if (!StringUtils.isEmpty(line)) {
					data.add(line);
				}
			}
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs readFile][path]=[" + path + "]");
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			br.close();
			fs.close();
		}

		return data;
	}

	/**
	 * 从文件接夹中读取数据
	 *
	 * @see cpcn.payment.tool.lang.hdfs.HdfsFileSystem#readFileFromDirectory(java.lang.String)
	 */
	@Override
	public List<String> readFileFromDirectory(String path) throws Exception {
		Configuration conf = new Configuration();

		// 远程会出现问题 Windows测试需要如下配置
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");

		List<String> data = new ArrayList<String>();
		FileSystem fs = null;
		FSDataInputStream in = null;
		BufferedReader br = null;

		try {
			fs = FileSystem.get(conf);
			Path srcPath = new Path(path);
			boolean result = fs.isDirectory(srcPath);
			if (result) {
				FileStatus[] status = fs.listStatus(srcPath);
				for (FileStatus file : status) {
					in = fs.open(file.getPath());
					br = new BufferedReader(new InputStreamReader(in));
					String line = null;
					while (null != (line = br.readLine())) {
						if (!StringUtils.isEmpty(line)) {
							data.add(line);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			LOGGER.info("[hdfs readFile][path]=[" + path + "]");
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			br.close();
			fs.close();
		}
		return data;
	}
}