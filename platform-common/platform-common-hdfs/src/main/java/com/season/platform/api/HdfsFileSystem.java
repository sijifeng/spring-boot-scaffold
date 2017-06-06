package com.season.platform.api;

import java.util.List;

/**
 * Created by jiyc on 2017/6/6.
 */
public interface HdfsFileSystem {
	/**
	 * 创建一个文件，如果已存在则返回失败
	 *
	 * @param dst HDFS文件目录地址
	 * @param contents 要写入文件数据
	 * @return 成功返回true 如果存在返回 false
	 * @throws Exception
	 */
	boolean createFile(String dst, String contents) throws Exception;

	/**
	 * 上传本地文件
	 *
	 * @param src 源文件
	 * @param dst 目标文件地址
	 *
	 * @return 成功返回true 如果存在返回 false
	 * @throws Exception
	 */
	boolean uploadFile(String src, String dst) throws Exception;

	/**
	 * 重命名文件
	 *
	 * @param oldName 原文件名
	 * @param newName 命名后文件名
	 * @return 成功返回true 失败返回false
	 * @throws Exception
	 */
	boolean rename(String oldName, String newName) throws Exception;

	/**
	 * 新建文件夹
	 *
	 * @param filePath 文件路径
	 * @return 成功返回true 失败返回false
	 * @throws Exception
	 */
	boolean mkdir(String filePath) throws Exception;

	/**
	 * 删除文件
	 *
	 * @param filePath 文件路径
	 * @return 成功返回true 失败返回false
	 * @throws Exception
	 */
	boolean delete(String path) throws Exception;

	/**
	 * 下载文件
	 *
	 * @param src hdfs文件路径
	 * @param dst 下载到本地路径
	 * @return 成功返回true 失败返回false
	 * @throws Exception
	 */
	boolean downloadFile(String src, String dst) throws Exception;

	/**
	 * 判断文件是否存在
	 *
	 * @param path 文件路径
	 * @return 存在返回true 不存在返回false
	 * @throws Exception
	 */
	boolean isExist(String path) throws Exception;

	/**
	 * 读取分布式文件系统文件，每行放入一个字符串，返回一个字符串数组
	 *
	 * @param path 文件路径
	 * @return 成功返回字符串数组 不成功返回null
	 * @throws Exception
	 */
	List<String> readFile(String path) throws Exception;

	/**
	 * 从文件夹中读取文件
	 * @param path
	 * @return
	 * @throws Exception
	 */
	List<String> readFileFromDirectory(String path) throws Exception;

}
