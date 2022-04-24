package com.ixan.boot.test.juc.executor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/24 17:43
 * @description Future 真实案例优化
 * @version 1.0
 */
public class FutureExample3 {
	private static final int MAX_EMPL_COUNT = 6_500;

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		long start = System.currentTimeMillis();
		List<String> userIdList = getEmplIds();
		Set<String> existEmpl = new HashSet<>();
		List<Future<Employee>> futureList = new ArrayList<>();

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		//distinct employee Id
		Set<String> collect = new HashSet<>(userIdList);
		for (String id : collect) {
			Future<Employee> future = executorService.submit(() -> findById(id));
			futureList.add(future);
		}

		for (Future<Employee> future : futureList) {
			Employee employee = future.get();
			if (null != employee) {
				existEmpl.add(employee.getId());
			}
		}
		System.out.printf("执行耗时:%s[ms] existEmpl size:[%s]\n", (System.currentTimeMillis() - start), existEmpl.size());
		executorService.shutdown();
	}

	public static List<String> getEmplIds() {
		List<String> userIdList = new ArrayList<>();
		for (int i = 0; i < MAX_EMPL_COUNT; i++) {
			userIdList.add("103011");
		}
		return userIdList;
	}

	public static class Employee {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	public static Employee findById(String id) {
		Employee employee = new Employee();
		employee.setId(id);
		try {
			TimeUnit.MILLISECONDS.sleep(50L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
