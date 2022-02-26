package com.ixan.boot.web.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ixan.boot.domain.UserGender;
import com.ixan.boot.mapper.UserGenderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/11/11 11:09
 * @description UserGender Controller
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class UserGenderController {
	@Resource
	private UserGenderMapper userGenderMapper;

	/**
	 * 多线程+使用mybatis批量插入
	 * 990000 耗时:
	 */
	@GetMapping("/v3/initUserGender")
	public String initUserGenderV3() {
		final int MAX_AVAILABLE = 5;
		Snowflake snowflake = IdUtil.getSnowflake(1, 1);
		long start = System.currentTimeMillis();
		List<UserGender> list = new ArrayList<>(1000);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(MAX_AVAILABLE, MAX_AVAILABLE, 2000L, TimeUnit.MICROSECONDS,
				new LinkedBlockingDeque<>(),
				new ThreadFactoryBuilder().setNameFormat("userTask-%d").build());
		Semaphore semaphore = new Semaphore(MAX_AVAILABLE);
		try {
			for (int i = 10000; i <= 1000030; i++) {
				//使用雪花算法生成id
				long id = snowflake.nextId();
				String serNum = String.valueOf(id);
				UserGender userGender = new UserGender();
				userGender.setUserId(serNum);
				userGender.setUserName("student" + "-" + serNum);
				userGender.setUserGender("0");
				list.add(userGender);
				if (list.size() % 1000 == 0) {
					List<UserGender> finalList = list;
					executor.execute(() -> {
						try {
							semaphore.acquire();
							userGenderMapper.insertBatch(finalList);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							semaphore.release();
						}
					});
					list = new ArrayList<>(1000);
				}
			}
			//执行剩余数据
			if (CollectionUtils.isNotEmpty(list)) {
				List<UserGender> finalList1 = list;
				executor.execute(() -> userGenderMapper.insertBatch(finalList1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
			long end = System.currentTimeMillis();
			log.info("运行结束,耗时:[{}]ms", (end - start));
		}
		return "ok";
	}

	/**
	 * 使用mybatis批量插入
	 * 990000 耗时:[19721]ms
	 */
	@GetMapping("/v2/initUserGender")
	public String initUserGenderV2() {
		Snowflake snowflake = IdUtil.getSnowflake(1, 1);
		long start = System.currentTimeMillis();
		List<UserGender> list = new ArrayList<>(1000);
		for (int i = 10000; i <= 1000030; i++) {
			//使用雪花算法生成id
			long id = snowflake.nextId();
			String serNum = String.valueOf(id);
			UserGender userGender = new UserGender();
			userGender.setUserId(serNum);
			userGender.setUserName("student" + "-" + serNum);
			userGender.setUserGender("0");
			list.add(userGender);
			if (list.size() % 1000 == 0) {
				userGenderMapper.insertBatch(list);
				list.clear();
			}
		}
		//执行剩余数据
		if (CollectionUtils.isNotEmpty(list)) {
			userGenderMapper.insertBatch(list);
		}
		long end = System.currentTimeMillis();
		log.info("运行结束,耗时:[{}]ms", (end - start));
		return "ok";
	}

	/**
	 * 990031 耗时:[417517]ms
	 */
	@GetMapping("/v1/initUserGender")
	public String initUserGender() {
		long start = System.currentTimeMillis();
		for (int i = 10000; i <= 1000030; i++) {
			String serNum = String.valueOf(i);
			UserGender userGender = new UserGender();
			userGender.setUserId(serNum);
			userGender.setUserName("student" + "-" + serNum);
			userGender.setUserGender("0");
			userGenderMapper.insert(userGender);
		}
		long end = System.currentTimeMillis();
		log.info("运行结束,耗时:[{}]ms", (end - start));
		return "ok";
	}

}
