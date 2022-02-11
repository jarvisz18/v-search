package cn.ixan.jmh.utils;

import java.util.concurrent.TimeUnit;

/**
 * @link https://github.com/alibaba/Sentinel/blob/master/sentinel-core/src/main/java/com/alibaba/csp/sentinel/util/TimeUtil.java
 */
public class TimeUtil_Sentinel {
	private static volatile long currentMills;

	static {
		currentMills = System.currentTimeMillis();
		Thread daemon = new Thread(() -> {
			while (true) {
				currentMills = System.currentTimeMillis();
				try {
					TimeUnit.MILLISECONDS.sleep(1L);
				} catch (InterruptedException e) {
					//nothing to do
				}
			}
		});
		daemon.setDaemon(true);
		daemon.setName("sentinel-time-tick-thread");
		daemon.start();
	}

	public static long currentTimeMillis() {
		return currentMills;
	}
}
