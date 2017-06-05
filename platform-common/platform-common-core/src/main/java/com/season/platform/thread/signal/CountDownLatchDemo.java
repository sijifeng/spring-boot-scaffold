package com.season.platform.thread.signal;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link CountDownLatch}
 * 倒计时计时器，调用对象的countDown方法将计时器数减少一，那么直到0的时候，就会让所有等待的线程开始运行。
 * <p>
 * Created by jiyc on 2017/6/5.
 */
public class CountDownLatchDemo {
	static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		int n = 3;
		final CountDownLatch cdAnswer = new CountDownLatch(n);
		for (int i = 0; i < n; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					String name = Thread.currentThread().getName();
					try {
						System.out.println("线程[" + name + "]准备接受命令");
						cdOrder.await();
						long t1 = Math.abs(random.nextLong()) % 20000;
						System.out.println("线程[" + name + "]已经接受到命令,处理时间需要" + t1);
						Thread.sleep(t1);
						System.out.println("线程[" + name + "]回应命令处理结束");
						cdAnswer.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			service.execute(runnable);
		}

		try {
			Thread.sleep(Math.abs(random.nextLong()) % 3000);
			String name = Thread.currentThread().getName();
			System.out.println("线程[" + name + "]即将发布命令");
			cdOrder.countDown();
			System.out.println("线程[" + name + "]已发布命令，等待结果响应");
			cdAnswer.await();
			System.out.println("线程[" + name + "]收到所有的响应结果");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
}