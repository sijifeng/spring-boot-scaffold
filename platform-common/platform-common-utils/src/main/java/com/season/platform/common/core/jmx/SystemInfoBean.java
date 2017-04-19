package com.season.platform.common.core.jmx;

import lombok.Data;

import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jiyc on 2017/4/18.
 */
@Data
public class SystemInfoBean {
	// 加载类的数量
	private int loadClazzCount;
	// 已经加载类的数量
	private long hasloadClazzCount;
	// 尚未加载类的数量
	private long hasUnloadClazzCount;
	// 堆内存信息
	private MemoryUsage heapMemoryUsage;
	// 非堆内存信息
	private MemoryUsage nonHeapMemoryUsage;
	// 操作系统的名称
	private String operateName;
	// 操作系统的进程数
	private int processListCount;
	// 操作系统的架构
	private String archName;
	// 操作系统的版本号码
	private String versionName;
	// 虚拟机的名称
	private String vmName;
	// 虚拟机的版本
	private String vmVersion;
	// 系统的供应商的名称
	private String vmVendor;
	// JVM启动时间
	private Date startTime;
	// 输入参数
	private List<String> arguments;
	// 系统参数
	private Map<String, String> systemProperties;
}
