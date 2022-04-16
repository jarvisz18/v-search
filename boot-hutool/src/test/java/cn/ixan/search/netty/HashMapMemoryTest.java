package cn.ixan.search.netty;

import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/16 21:44
 * @description HashMapTest
 * @version 1.0
 */
public class HashMapMemoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HashMapMemoryTest.class);


	public static void main(String[] args) throws InterruptedException {
		long freeMemory = Runtime.getRuntime().freeMemory();
		LOGGER.info("before freeMemory {} Byte", freeMemory);
		int maxSize = 1000;
		HashMap<String,String> hashMap = new HashMap<>();
		for (int i = 0; i < maxSize; i++) {
			hashMap.put(String.valueOf(i), String.valueOf(i));
		}
		//查看内存占用总大小
		LOGGER.info("hashMap size:[{}] Byte", GraphLayout.parseInstance(hashMap).totalSize());
		freeMemory = Runtime.getRuntime().freeMemory();
		LOGGER.info("after freeMemory {} Byte", freeMemory);
		TimeUnit.HOURS.sleep(1L);
	}
}
