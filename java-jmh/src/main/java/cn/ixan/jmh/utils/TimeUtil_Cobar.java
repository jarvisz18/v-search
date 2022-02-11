package cn.ixan.jmh.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xianmao.hexm 2011-1-18 下午06:10:55
 * @description 弱精度的计时器，考虑性能不使用同步策略。
 */
public final class TimeUtil_Cobar {
	private static long CURRENT_TIME = System.currentTimeMillis();
	private static final Timer timer;

	static {
		timer = new Timer();
		timer.schedule(updateTime(), 0L, 20L);
	}

	public static long currentTimeMillis() {
		return CURRENT_TIME;
	}

	public static void update() {
		CURRENT_TIME = System.currentTimeMillis();
	}

	private static TimerTask updateTime() {
		return new TimerTask() {
			@Override
			public void run() {
				TimeUtil_Cobar.update();
			}
		};
	}
}
