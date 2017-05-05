package com.season.platform.common.core.thread;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jiyc on 2017/4/19.
 */
public class LockMap {
	private static HashMap<String,Object> map=new HashMap<String, Object>();
	private static ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	private static Lock read=lock.readLock();
	private static Lock write=lock.writeLock();

	public Object get(String key) throws InterruptedException {
		read.lock();
		try{
			Object obj=map.get(key);
			return obj;
		}finally {
			read.unlock();
		}
	}

	public void put(String key,Object value) throws InterruptedException {
		write.lock();
		try{
			map.put(key,value);
		}finally {
			write.unlock();
		}
	}
}
