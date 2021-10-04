package com.ixan.boot.web.controller;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ixan.boot.domain.ConvertTask;
import com.ixan.boot.domain.LogFile;
import com.ixan.boot.mapper.LogOneLineFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/14 下午1:55
 * @description 每次读取一行日志
 */
@RestController
@Slf4j
@RequestMapping("/log")
public class LogOneLineFileController {
	@Autowired
	private LogOneLineFileMapper logOneLineFileMapper;

	@GetMapping("/doTaskBatch")
	public void doTaskBatch() throws InterruptedException {
		long start = System.currentTimeMillis();
		File file = new File("/Users/mac/Desktop/" + "service.log");
		ExecutorService executorService = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(10),
				new ThreadFactoryBuilder().setNameFormat("test-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
		try (LineIterator iterator = IOUtils.lineIterator(new FileInputStream(file), "UTF-8")) {
			//存储单个任务执行行数
			LinkedBlockingQueue<ConvertTask> queue = new LinkedBlockingQueue<>(10);
			List<LogFile> lines = Lists.newArrayList();
			while (iterator.hasNext()) {
				String line = iterator.nextLine();
				LogFile lineFile = new LogFile();
				lineFile.setContent(line);
				lines.add(lineFile);
				if (lines.size() == 1000) {
					queue.put(new ConvertTask(Lists.newArrayList(lines), logOneLineFileMapper));
					lines.clear();
				}
				//从队列中取任务执行
				while (!queue.isEmpty()) {
					ConvertTask take = queue.take();
					executorService.submit(take);
				}
			}
			log.info("最后执行剩余[{}]行", lines.size());
			//读取剩余不足1000行
			executorService.submit(new ConvertTask(Lists.newArrayList(lines), logOneLineFileMapper));
			lines.clear();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		executorService.shutdown();
		while (true) {
			if (executorService.isTerminated()) {
				long end = System.currentTimeMillis();
				log.info("Batch程序执行完毕,耗时[{}]ms", end - start);
				break;
			}
		}
	}

	@GetMapping("/doTaskRead")
	public void doTaskRead() {
		long start = System.currentTimeMillis();
		File file = new File("/Users/mac/Desktop/" + "service.log");
		ExecutorService executorService = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(10000),
				new ThreadFactoryBuilder().setNameFormat("test-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
		try (LineIterator iterator = IOUtils.lineIterator(new FileInputStream(file), "UTF-8")) {
			while (iterator.hasNext()) {
				String line = iterator.nextLine();
				executorService.submit(() -> {
					convertToDB(line);
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		executorService.shutdown();
		while (true) {
			if (executorService.isTerminated()) {
				long end = System.currentTimeMillis();
				log.info("程序执行完毕,耗时[{}]ms", end - start);
				break;
			}
		}
	}

	@GetMapping("/doRead")
	public void doRead() {
		long start = System.currentTimeMillis();
		File file = new File("/Users/mac/Desktop/" + "service.log");
		try (LineIterator iterator = IOUtils.lineIterator(new FileInputStream(file), "UTF-8")) {
			while (iterator.hasNext()) {
				String line = iterator.nextLine();
				convertToDB(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		log.info("程序执行完毕,耗时[{}]ms", end - start);
	}

	private void convertToDB(String line) {
		LogFile oneLineFile = new LogFile();
		oneLineFile.setContent(line);
		logOneLineFileMapper.insert(oneLineFile);
	}
}
