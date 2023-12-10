package cn.ixan.search.juc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/8 12:28
 * @description 测试线程池
 * @version 1.0
 */
public class ExecutorsTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorsTest.class);

	public static void main(String[] args) {
		long startMills = System.currentTimeMillis();
		//系统可用线程数
		int N_CPUS = Runtime.getRuntime().availableProcessors();
		LOGGER.info("系统可用线程数availableProcessors :[{}]", N_CPUS);
		ExecutorService executorService = Executors.newFixedThreadPool(N_CPUS);
		try {
			for (int i = 0; i < 200; i++) {
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LOGGER.info("正在处理Task" + i);
				UUIDGenerator uuidGenerator = new TimeBasedUUIDGenerator();
				executorService.execute(new Runnable() {

					@Override
					public void run() {
						LOGGER.info("current state:" + Thread.currentThread().getState());
						LOGGER.info(Thread.currentThread().getName() + "线程被调用了,生成主键:" + uuidGenerator.getBase64UUID());
						LOGGER.info("current state:" + Thread.currentThread().getState());
					}
				});
			}
		} finally {
			if (!executorService.isShutdown()) {
				LOGGER.info("线程池关闭状态:[{}]", executorService.isShutdown());
				//关闭方法，调用后执行之前提交的任务，不再接受新的任务
				executorService.shutdown();
				LOGGER.info("线程池关闭状态:[{}]", executorService.isShutdown());
			}
		}
		while (true) {
			if (executorService.isTerminated()) {
				long endMills = System.currentTimeMillis();
				LOGGER.info("线程执行完毕,耗时:[{}]ms", (endMills - startMills));
				break;
			}
		}
	}

	@Test
	public void testCallableTask() {
		//系统可用线程数
		int N_CPUS = Runtime.getRuntime().availableProcessors();
		//这里通过newCacheThread（）方法来实现线程池，当然也可以用其他三种方法
		ExecutorService executorService = Executors.newFixedThreadPool(N_CPUS);
		//Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
		//计算完成后只能使用 get 方法来获取结果，如有必要，计算完成前可以阻塞此方法。
		ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();

		//创建10个任务并执行
		for (int i = 0; i < 10; i++) {
			String uuid = new TimeBasedUUIDGenerator().getBase64UUID();
			//使用ExecutorService执行Callalbe类型的任务，并将结果保存在future变量中
			Future<String> future = executorService.submit(new TaskWithResult(uuid));
			//将任务执行结果存储在List中。
			resultList.add(future);
		}
		for (Future<String> fs : resultList) {

			try {
				//一直等待Future的返回结果，直到返回完成，打印输出返回结果。
				while (!fs.isDone()) ;
				LOGGER.info("Future的返回结果:{}", fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				//启动一次顺序关闭，执行以前提交的任务，但不接受新任务
				executorService.shutdown();
			}
		}

	}

	@Test
	public void testAvailableProcessors() {
		int N_CPUS = Runtime.getRuntime().availableProcessors();
		System.out.println("CPU的数目:" + N_CPUS);
	}

}
