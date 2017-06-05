package com.season.platform.thread.lock;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jiyc on 2017/6/5.
 */
public class CacheDemo {
	private static final int maxSize = 100000; // 最大存储量
	private static CacheDemo demo;
	private Map<String, String> cache = new LinkedHashMap<String, String>() {
		private static final long serialVersionUID = -7259602073057254864L;

		protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
			return maxSize > this.size(); // 超过就移除
		}

		;
	};
	private ReentrantReadWriteLock rrel = new ReentrantReadWriteLock();
	private Lock writeLock = rrel.writeLock(); // 写锁
	private Lock readLock = rrel.readLock(); // 读锁

	/**
	 * 获取cache对象
	 *
	 * @return
	 */
	public static CacheDemo instance() {
		if (demo == null) {
			synchronized (CacheDemo.class) {
				if (demo == null) {
					demo = new CacheDemo();
				}
			}
		}
		return demo;
	}

	/**
	 * 添加
	 *
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		this.writeLock.lock(); // 加锁
		try {
			this.cache.put(key, value);
		} finally {
			// 防止在操作过程中出现异常，使用try-finally保证解锁一定执行。
			this.writeLock.unlock(); // 解锁
		}
	}

	/**
	 * 获取这个对象
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		this.readLock.lock(); // 加锁
		try {
			return this.cache.get(key);
		} finally {
			this.readLock.unlock(); // 解锁
		}
	}

	/**
	 * 移除key
	 *
	 * @param key
	 */
	public void remove(String key) {
		this.writeLock.lock();
		try {
			this.cache.remove(key);
		} finally {
			this.writeLock.unlock();
		}
	}

	/**
	 * 清空
	 */
	public void clean() {
		this.writeLock.lock();
		try {
			this.cache.clear();
		} finally {
			this.writeLock.unlock();
		}
	}
}

