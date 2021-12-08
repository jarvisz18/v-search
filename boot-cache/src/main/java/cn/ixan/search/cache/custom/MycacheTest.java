package cn.ixan.search.cache.custom;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/5 下午9:37
 * @description 测试缓存
 */
public class MycacheTest {
	public static void main(String[] args) throws InterruptedException {
		CacheUtils cacheUtils = new CacheUtils();
		long startTime = System.currentTimeMillis();
		//存入缓存
		long expire = 10 * 1000L;
		cacheUtils.put("key", "老王", expire);
		ExpireThread expireThread = new ExpireThread();
		Thread thread = new Thread(expireThread);
		thread.start();
		while (expire >= System.currentTimeMillis() - startTime) {
			TimeUnit.SECONDS.sleep(1L);
			System.out.println("获取key的缓存值: " + cacheUtils.get("key") + ",当前时间:" + LocalDateTime.now());
		}
		expireThread.stop();
		//查询不存在的key
		String naval = (String) cacheUtils.get("naval");
		System.out.println(naval);
	}
}
