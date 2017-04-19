package com.season.platform.common.core.jmx;

import java.lang.management.*;
import java.util.Date;

/**
 * Created by jiyc on 2017/4/18.
 */
public class SystemInfoUtils {
	private SystemInfoBean infoBean = null;

	private static class SingletonClassInstance {
		private static final SystemInfoUtils instance = new SystemInfoUtils();
	}

	public static SystemInfoUtils getInstance() {
		return SingletonClassInstance.instance;
	}

	private SystemInfoUtils() {
		infoBean = new SystemInfoBean();

		// 操作系统信息
		OperatingSystemMXBean operateSystemMBean = ManagementFactory
				.getOperatingSystemMXBean();
		String operateName = operateSystemMBean.getName();
		infoBean.setOperateName(operateName);
		int processListCount = operateSystemMBean.getAvailableProcessors();
		infoBean.setProcessListCount(processListCount);
		String archName = operateSystemMBean.getArch();// System.getProperty("os.arch");
		infoBean.setArchName(archName);
		String versionName = operateSystemMBean.getVersion();// System.getProperty("os.version");
		infoBean.setVersionName(versionName);

		// 运行时信息
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		String vmName = runtimeMXBean.getVmName();
		infoBean.setVmName(vmName);
		String vmVersion = runtimeMXBean.getVmVersion();
		// infoBean.setVmVersion(vmVersion);
		infoBean.setVmVersion(System.getProperty("java.version") + " ("
				+ vmVersion + ")");
		String vmVendor = runtimeMXBean.getVmVendor();
		infoBean.setVmVendor(vmVendor);
		long startTime = runtimeMXBean.getStartTime();
		infoBean.setStartTime(new Date(startTime));

		infoBean.setArguments(runtimeMXBean.getInputArguments());
		infoBean.setSystemProperties(runtimeMXBean.getSystemProperties());
	}

	public SystemInfoBean getSystemInfo() {
		// 类信息
		ClassLoadingMXBean classLoadMXBean = ManagementFactory
				.getClassLoadingMXBean();
		int loadClazzCount = classLoadMXBean.getLoadedClassCount();
		infoBean.setLoadClazzCount(loadClazzCount);
		long hasloadClazzCount = classLoadMXBean.getTotalLoadedClassCount();
		infoBean.setHasloadClazzCount(hasloadClazzCount);
		long hasUnloadClazzCount = classLoadMXBean.getUnloadedClassCount();
		infoBean.setHasUnloadClazzCount(hasUnloadClazzCount);

		// 内存
		MemoryMXBean memoryUsage = ManagementFactory.getMemoryMXBean();
		infoBean.setHeapMemoryUsage(memoryUsage.getHeapMemoryUsage());
		infoBean.setNonHeapMemoryUsage(memoryUsage.getNonHeapMemoryUsage());
		return infoBean;
	}
}
