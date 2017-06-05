package com.season.platform.thread.signal;



import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link CyclicBarrier}表示请大家等待，等所有集合都准备好了，那么就开始运行，这个过程可以循环。<br/>
 * 比如：公司部门的周末准备一起出去游玩，先等到所有的人到达汽车才开始启动车辆到目的地去，到后自由玩，然后到1点在一起吃饭。
 * <p>
 * Created by jiyc on 2017/6/5.
 */
public class CyclicBarrierDemo {
	final static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		int n = 3;
		final CyclicBarrier barrier = new CyclicBarrier(n); // 总共十个人
		for (int i = 0; i < n; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						String name = Thread.currentThread().getName();
						long t1 = Math.abs(random.nextLong()) % 2000;
						System.out.println("线程[" + name + "] " + t1 + " 后到达集合地点1，现在已经有" + (barrier.getNumberWaiting()) + "人到达!");
						Thread.sleep(t1);
						System.out.println("线程[" + name + "]已经到达集合地点1，现在已经有" + (barrier.getNumberWaiting() + 1) + "人到达!");
						barrier.await(); // 等待

						System.out.println("线程[" + name + "]在车上...自由活动....");

						t1 = Math.abs(random.nextLong()) % 2000;
						System.out.println("线程[" + name + "] " + t1 + " 后到达集合地点2，现在已经有" + (barrier.getNumberWaiting()) + "人到达!");
						Thread.sleep(t1);
						System.out.println("线程[" + name + "]已经到达集合地点2，现在已经有" + (barrier.getNumberWaiting()) + "人到达!");
						barrier.await(); // 等待
						System.out.println("线程[" + name + "]觉得是美好的一天.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			service.execute(runnable);
		}
		service.shutdown();
	}
}