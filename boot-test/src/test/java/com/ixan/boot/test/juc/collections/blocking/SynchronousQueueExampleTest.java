package com.ixan.boot.test.juc.collections.blocking;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 13:33
 * @description SynchronousQueueExample单元测试
 * @version 1.0
 */
public class SynchronousQueueExampleTest {

	@Test
	public void testSynchronousQueueAdd() throws InterruptedException {
		SynchronousQueue<String> queue = SynchronousQueueExample.create();
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(() -> {
			try {
				String take = queue.take();
				System.out.println(take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 1L, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();
		TimeUnit.SECONDS.sleep(1L);
		queue.put("data1");
	}
}
