package cn.ixan.search.juc;

import cn.ixan.search.utils.base.TimeBasedUUIDGenerator;
import cn.ixan.search.utils.base.UUIDGenerator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/9/8 12:28
 * @description 测试线程池
 * @version 1.0
 */
public class ExecutorsTest {
	private static final Logger logger = LoggerFactory.getLogger(ExecutorsTest.class);

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 20; i++) {
			final int index = i;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("正在处理Task" + i);
			UUIDGenerator uuidGenerator = new TimeBasedUUIDGenerator();
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					logger.info("current state:" + Thread.currentThread().getState());
					logger.info(Thread.currentThread().getName() + "线程被调用了,生成主键:" + uuidGenerator.getBase64UUID());
					logger.info("current state:" + Thread.currentThread().getState());
				}
			});
		}
		logger.info("线程池关闭状态:[{}]", executorService.isShutdown());
		//关闭方法，调用后执行之前提交的任务，不再接受新的任务
		executorService.shutdown();
		logger.info("线程池关闭状态:[{}]", executorService.isShutdown());
	}

	@Test
	public void testCallableTask() {
		//这里通过newCacheThread（）方法来实现线程池，当然也可以用其他三种方法
		ExecutorService executorService = Executors.newFixedThreadPool(4);
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
				logger.info("Future的返回结果:{}", fs.get());
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
