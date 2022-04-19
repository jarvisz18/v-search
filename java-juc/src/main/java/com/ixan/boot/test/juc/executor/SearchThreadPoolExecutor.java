package com.ixan.boot.test.juc.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/19 21:22
 * @description Custom thread pool - singleton mode
 * @version 1.0
 */
public final class SearchThreadPoolExecutor {
	private SearchThreadPoolExecutor() {
		throw new UnsupportedOperationException();
	}

	private static class MySearchThreadPoolExecutor {
		private static final ThreadPoolExecutor INSTACE = new ThreadPoolExecutor(10, 10, 2000L, TimeUnit.MILLISECONDS,
				new LinkedBlockingDeque<>(1024),
				new ThreadFactoryBuilder().setNameFormat("searchTask-%d").build());
	}

	public static ThreadPoolExecutor getMySearchThreadPoolExecutor() {
		return MySearchThreadPoolExecutor.INSTACE;
	}
}
