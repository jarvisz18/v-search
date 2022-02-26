package com.ixan.boot.web.controller.ratelimiter;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.*;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class SimplePayControllerTest extends BaseUnitTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimplePayControllerTest.class);
	private static final int MAX_REQUEST_TOTAL = 1000;
	private static final int MAX_THREAD_TOTAL = 25;

	/**
	 * Semaphore +CountDownLatch+ 多线程 测试限流
	 * {@link PayController#sendMessage()}
	 */
	@Test
	public void sendMessage() {
		Semaphore semaphore = new Semaphore(MAX_THREAD_TOTAL);
		CountDownLatch countDownLatch = new CountDownLatch(MAX_REQUEST_TOTAL);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, MAX_REQUEST_TOTAL).boxed().forEach(i -> {
			executorService.execute(() -> {
				String contentAsString = null;
				try {
					semaphore.acquire();
					MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/send/message"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andReturn();
					contentAsString = mvcResult.getResponse().getContentAsString();
					TimeUnit.MILLISECONDS.sleep(200L);
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					LOGGER.info("result:" + contentAsString);
					countDownLatch.countDown();
				}
			});
		});
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}

	/**
	 * Semaphore+CountDownLatch + 多线程 测试限流
	 * {@link PayController#pay()}
	 */
	@Test
	public void pay() {
		Semaphore semaphore = new Semaphore(MAX_THREAD_TOTAL);
		CountDownLatch countDownLatch = new CountDownLatch(MAX_REQUEST_TOTAL);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, MAX_REQUEST_TOTAL).boxed().forEach(i -> {
			executorService.execute(() -> {
				String contentAsString = null;
				try {
					semaphore.acquire();
					MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pay"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andReturn();
					contentAsString = mvcResult.getResponse().getContentAsString();
					TimeUnit.MILLISECONDS.sleep(350L);
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
					LOGGER.info("result:" + contentAsString);
				}
			});
		});

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}