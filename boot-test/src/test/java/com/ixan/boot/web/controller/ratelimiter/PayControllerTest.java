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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PayControllerTest extends BaseUnitTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayControllerTest.class);

	/**
	 * CountDownLatch + 多线程 测试限流
	 * {@link PayController#sendMessage()}
	 */
	@Test
	public void sendMessage() {
		CountDownLatch countDownLatch = new CountDownLatch(100);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, 100).boxed().forEach(i -> {
			executorService.execute(() -> {
				String contentAsString = null;
				try {
					MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/send/message"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andReturn();
					contentAsString = mvcResult.getResponse().getContentAsString();
					TimeUnit.MILLISECONDS.sleep(500L);
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
	 * CountDownLatch + 多线程 测试限流
	 * {@link PayController#pay()}
	 */
	@Test
	public void pay() {
		final int MAX_THREAD = 100;
		CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, 100).boxed().forEach(i -> {
			executorService.execute(() -> {
				String contentAsString = null;
				try {
					MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pay"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andReturn();
					contentAsString = mvcResult.getResponse().getContentAsString();
					TimeUnit.MILLISECONDS.sleep(350L);
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