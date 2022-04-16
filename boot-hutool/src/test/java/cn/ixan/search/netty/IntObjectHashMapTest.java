package cn.ixan.search.netty;

import io.netty.util.collection.IntObjectHashMap;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/16 21:31
 * @description IntIObjectHashMapTest
 * @version 1.0
 */
public class IntObjectHashMapTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntObjectHashMapTest.class);


	public static void main(String[] args) throws InterruptedException {
		long freeMemory = Runtime.getRuntime().freeMemory();
		LOGGER.info("before freeMemory {} k Byte", freeMemory);
		int maxSize = 1000;
		IntObjectHashMap<String> hashMap = new IntObjectHashMap<>();
		for (int i = 0; i < maxSize; i++) {
			hashMap.put(i, String.valueOf(i));
		}
		//查看内存占用总大小
		LOGGER.info("hashMap size:[{}] Byte", GraphLayout.parseInstance(hashMap).totalSize());
		freeMemory = Runtime.getRuntime().freeMemory();
		LOGGER.info("after freeMemory {} k Byte", freeMemory);
		TimeUnit.HOURS.sleep(1L);
	}
}
