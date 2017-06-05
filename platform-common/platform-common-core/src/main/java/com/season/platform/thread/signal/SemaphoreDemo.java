package com.season.platform.thread.signal;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore}
 * 可以维护当前访问自身的线程数，并提供同步机制，使用Semahore可以控制同时访问资源的线程个数，例如：实现一个地下停车库。<br/>
 * 单个信号变量semphore对象可以实现互斥锁的功能，并且可以是其中一个线程获得锁，另外一个线程释放锁，那么可应用于死锁恢复的一些场所。
 * Created by jiyc on 2017/6/5.
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		try {
			final Semaphore semaphore = new Semaphore(3); // 3个同步变量
			for (int i = 0; i < 10; i++) {
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						try {
							System.out.println("线程[" + name + "]开始获取资源....");
							semaphore.acquire(); // 请求资源，有阻塞效果
							System.out.println("线程[" + name + "]需要的资源获取到.");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						long time = (long) (Math.random() * 2000);
						System.out.println("线程[" + name + "]已经进入，当前有:" + (3 - semaphore.availablePermits()) + "个线程运行.准备停留:" + time);

						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							semaphore.release(); // 放回
						}
						System.out.println("线程[" + name + "]运行完成!");
					}
				};
				service.execute(runnable);
			}
		} finally {
			service.shutdown();
		}

	}
}