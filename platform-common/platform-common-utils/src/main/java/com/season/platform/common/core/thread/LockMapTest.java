package com.season.platform.common.core.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jiyc on 2017/4/19.
 */
public class LockMapTest {
	private static SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss:sss");
	private static LockMap map=new LockMap();

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runner("w"),"Write-Thread1").start();
		new Thread(new Runner("r"),"Read-Thread1").start();
		new Thread(new Runner("r"),"Read-Thread2").start();
		new Thread(new Runner("r"),"Read-Thread3").start();
		new Thread(new Runner("r"),"Read-Thread4").start();
	}

	public static class Runner implements Runnable{

		private String readWrite;

		public Runner(String readWrite){
			this.readWrite=readWrite;
		}
		public void run() {
			while (true){
				if ("w".equals(readWrite)){
					try {
						System.out.println(format.format(new Date())+" Current thread "+Thread.currentThread().getName()+" writing ");
						map.put("first", UUID.randomUUID().toString());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else if ("r".equals(readWrite)){
					try {
						System.out.println(format.format(new Date())+" Current thread "+Thread.currentThread().getName()+" get value "+map.get("first"));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
