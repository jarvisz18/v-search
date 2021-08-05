package cn.ixan.search.cache.custom;

import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/5 下午9:16
 * @description 过期缓存检测线程
 */
public class ExpireThread implements Runnable {
	private volatile boolean finished = false;

	@Override
	public void run() {
		while (!finished) {
			//每十秒检测一次
			try {
				TimeUnit.SECONDS.sleep(1);
				//缓存检测和清除方法
				expireCache();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void expireCache() {
		System.out.println("检测是否存在过期缓存");
		for (String key : CacheGlobal.concurrentHashMap.keySet()) {
			MyCache myCache = CacheGlobal.concurrentHashMap.get(key);
			//当前时间 - 写入时间
			long timeoutTIme = System.currentTimeMillis() - myCache.getWriteTime();
			if (myCache.getExpireTime() > timeoutTIme) {
				continue;
			}
			//清除过期缓存
			System.out.println("检测到过期key:" + key);
			CacheGlobal.concurrentHashMap.remove(key);
		}
	}

	public void stop() {
		System.out.println("关闭检测");
		finished = true;
	}
}
