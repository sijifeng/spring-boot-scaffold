package com.season.platform;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Created by jiyc on 2017/6/6.
 */
public class HdfsUtil {

	private FileSystem hdfs;

	/**
	 * @return 得到hdfs的连接 FileSystem类
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static FileSystem getFileSystem() throws URISyntaxException, IOException, InterruptedException {
		/*Configuration conf = new Configuration();
		System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");
		conf.set("mapred.jop.tracker", "hdfs://192.168.121.167:8030");
		conf.set("fs.default.name", "hdfs://192.168.121.167:8020");
		FileSystem fs = FileSystem.get(conf);*/


		//获取FileSystem类的方法有很多种，这里只写一种
		Configuration config = new Configuration();
		URI uri = new URI("hdfs://192.xxx.x.xxx:xxx");
		return FileSystem.get(uri, config, "your user");// 第一位为uri，第二位为config，第三位是登录的用户
	}

	/**
	 * 检查文件或者文件夹是否存在
	 *
	 * @param filename
	 * @return
	 */
	public boolean checkFileExist(String filename) {
		try {
			Path f = new Path(filename);
			return hdfs.exists(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建文件夹
	 *
	 * @param dirName
	 * @return
	 */
	public boolean mkdir(String dirName) {
		if (checkFileExist(dirName))
			return true;
		try {
			Path f = new Path(dirName);
			System.out.println("Create and Write :" + f.getName() + " to hdfs");
			return hdfs.mkdirs(f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 创建一个空文件
	 *
	 * @param filePath 文件的完整路径名称
	 * @return
	 */
	public boolean mkfile(String filePath) {
		try {
			Path f = new Path(filePath);
			FSDataOutputStream os = hdfs.create(f, true);
			os.close();
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 复制文件到指定目录
	 *
	 * @param srcfile 复制的文件路径
	 * @param desfile 粘贴的路径
	 * @return
	 */
	public boolean hdfsCopyUtils(String srcfile, String desfile) {
		Configuration conf = new Configuration();
		Path src = new Path(srcfile);
		Path dst = new Path(desfile);
		try {
			FileUtil.copy(src.getFileSystem(conf), src,
					dst.getFileSystem(conf), dst, false, conf);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * 移动文件或者文件夹
	 *
	 * @param src 初始路径
	 * @param dst 移动结束路径
	 * @throws Exception
	 */
	public void movefile(String src, String dst) throws Exception {
		Path p1 = new Path(src);
		Path p2 = new Path(dst);
		hdfs.rename(p1, p2);
	}

	/**
	 * 删除文件或者文件夹
	 *
	 * @param src
	 * @throws Exception
	 */
	public void delete(String src) throws Exception {
		Path p1 = new Path(src);
		if (hdfs.isDirectory(p1)) {
			hdfs.delete(p1, true);
			System.out.println("删除文件夹成功: " + src);
		} else if (hdfs.isFile(p1)) {
			hdfs.delete(p1, false);
			System.out.println("删除文件成功: " + src);
		}
	}

	/**
	 * 读取本地文件到HDFS系统, 保证文件格式是utf-8
	 *
	 * @param localFilename
	 * @param hdfsPath
	 * @return
	 */
	public boolean copyLocalFileToHDFS(String localFilename, String hdfsPath) {
		try {
			// 如果路径不存在就创建文件夹
			mkdir(hdfsPath);

			File file = new File(localFilename);
			FileInputStream is = new FileInputStream(file);

			// 如果hdfs上已经存在文件，那么先删除该文件
			if (this.checkFileExist(hdfsPath + "/" + file.getName())) {
				delete(hdfsPath + "/" + file.getName());
			}

			Path f = new Path(hdfsPath + "/" + file.getName());

			FSDataOutputStream os = hdfs.create(f, true);
			byte[] buffer = new byte[10240000];
			int nCount = 0;

			while (true) {
				int bytesRead = is.read(buffer);
				if (bytesRead <= 0) {
					break;
				}

				os.write(buffer, 0, bytesRead);
				nCount++;
				if (nCount % (100) == 0)
					System.out.println((new Date()).toLocaleString() + ": Have move " + nCount + " blocks");
			}

			is.close();
			os.close();
			System.out.println((new Date()).toLocaleString() + ": Write content of file " + file.getName()
					+ " to hdfs file " + f.getName() + " success");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 复制本地文件夹到hdfs的文件
	 *
	 * @param localPath
	 * @param hdfsPath
	 * @return
	 */
	public boolean CopyLocalDirTohdfs(String localPath, String hdfsPath) {
		try {
			File root = new File(localPath);
			File[] files = root.listFiles();

			for (File file : files) {
				if (file.isFile()) {
					copyLocalFileToHDFS(file.getPath().toString(), hdfsPath);

				} else if (file.isDirectory()) {
					CopyLocalDirTohdfs(localPath + "/" + file.getName(), hdfsPath + "/" + file.getName());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * 从hdfs下载
	 *
	 * @param hdfsFilename
	 * @param localPath
	 * @return
	 */
	public boolean downloadFileFromHdfs(String hdfsFilename, String localPath) {
		try {
			Path f = new Path(hdfsFilename);

			FSDataInputStream dis = hdfs.open(f);
			File file = new File(localPath + "/" + f.getName());
			FileOutputStream os = new FileOutputStream(file);

			byte[] buffer = new byte[1024000];
			int length = 0;
			while ((length = dis.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

			os.close();
			dis.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * HDFS 到 HDFS 的合并
	 * hdfs提供了一种FileUtil.copyMerge（）的方法， 注意下面的 false 这个，如果改为true，就会删除这个目录
	 *
	 * @param folder 需要合并的目录
	 * @param file   要合并成的文件，完整路径名称
	 */
	public void copyMerge(String folder, String file) {
		Configuration conf = new Configuration();
		Path src = new Path(folder);
		Path dst = new Path(file);

		try {
			FileUtil.copyMerge(src.getFileSystem(conf), src,
					dst.getFileSystem(conf), dst, false, conf, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 列出所有DataNode的名字信息
	 */
	public void listDataNodeInfo() {
		try {
			DistributedFileSystem fs = null;
			fs = (DistributedFileSystem) hdfs;
			DatanodeInfo[] dataNodeStats = fs.getDataNodeStats();
			String[] names = new String[dataNodeStats.length];
			System.out.println("List of all the datanode in the HDFS cluster:");

			for (int i = 0; i < names.length; i++) {
				names[i] = dataNodeStats[i].getHostName();
				System.out.println(names[i]);
			}
			System.out.println(hdfs.getUri().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检测是否是备用节点
	 *
	 * @throws Exception
	 */
	public boolean checkStandbyException(String filename) {
		try {
			Path f = new Path(filename);
			hdfs.exists(f);
		} catch (org.apache.hadoop.ipc.RemoteException e) {
			if (e.getClassName().equals("org.apache.hadoop.ipc.StandbyException")) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}


	public boolean mergeDirFiles(List<FileStatus> fileList, String tarPath, String rowTerminateFlag) {
		//rowTerminateFlag   \n
		FSDataOutputStream tarFileOutputStream = null;
		FSDataInputStream srcFileInputStream = null;

		try {
			Path tarFile = new Path(tarPath);
			tarFileOutputStream = hdfs.create(tarFile, true);

			byte[] buffer = new byte[1024000];
			int length = 0;
			long nTotalLength = 0;
			int nCount = 0;
			boolean bfirst = true;
			for (FileStatus file : fileList) {
				if (file.getPath().equals(tarFile)) {
					continue;
				}
				System.out.println(" merging file from  " + file.getPath() + " to " + tarPath);

				if (!bfirst) {
					//添加换行符
					tarFileOutputStream.write(rowTerminateFlag.getBytes(), 0, rowTerminateFlag.length());
				}

				srcFileInputStream = hdfs.open(file.getPath(), buffer.length);
				while ((length = srcFileInputStream.read(buffer)) > 0) {
					nCount++;
					tarFileOutputStream.write(buffer, 0, length);
					nTotalLength += length;
					//System.out.println(" file length " + file.getLen() + " read " + length);
					if (nCount % 1000 == 0) {
						tarFileOutputStream.flush();
						System.out.println((new Date()).toLocaleString() + ": Have move " + (nTotalLength / 1024000) + " MB");
					}

				}

				srcFileInputStream.close();

				bfirst = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				delete(tarPath);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return false;
		} finally {
			try {
				if (tarFileOutputStream != null) {
					tarFileOutputStream.flush();
					tarFileOutputStream.close();
					srcFileInputStream.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return true;
	}
}
