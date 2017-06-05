package com.season.platform.thread.sync;

/**
 * Created by jiyc on 2017/6/5.
 */
public class SyncDemo {
	private static final java.util.Random random = new java.util.Random(System.currentTimeMillis());

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			private int count = 0; // 资源对象

			@Override
			public void run() {
				try {
					int oldCount = 0;
					synchronized (this) {
						oldCount = count;
						Thread.sleep(random.nextInt(1000) + 10); // 处理
						count = oldCount + 1;
						System.out.println(Thread.currentThread().getName() + ", 原有资源:" + oldCount + ", 现在预期资源:" + (oldCount + 1) + ",现在实际资源:" + count);
					}
				} catch (InterruptedException e) {
				}
			}
		};

		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
	}
}
