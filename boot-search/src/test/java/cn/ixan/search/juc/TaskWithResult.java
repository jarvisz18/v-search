package cn.ixan.search.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/8 12:37
 * @description 有返回值的任务
 * @version 1.0
 */
public class TaskWithResult implements Callable<String> {
	private final Logger logger = LoggerFactory.getLogger(TaskWithResult.class);
	private String guid;

	public TaskWithResult(String guid) {
		this.guid = guid;
	}

	@Override
	public String call() throws Exception {
		logger.info(Thread.currentThread().getName() + "线程被调用了");
		//返回提示信息，该返回结果将在Future的get方法中得到。
		return "call() 方法被调用，任务返回的结果是：" + guid + "  " + Thread.currentThread().getName();
	}
}
