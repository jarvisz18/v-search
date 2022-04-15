package com.ixan.boot.test.juc.collections.blocking;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/19 下午5:05
 * @description 生成订单30分钟未支付，则自动取消
 */
public class DelayQueueExample3Test {

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		list.add("00000001");
		list.add("00000002");
		list.add("00000003");
		list.add("00000004");
		list.add("00000005");
		list.add("00000006");

		DelayQueue<OrderDelay> queue = new DelayQueue<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 6; i++) {
			queue.put(new OrderDelay(list.get(i),
					TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));

			try {
				queue.take().print();
				System.out.println("After " + (System.currentTimeMillis() - start) + " MilliSeconds");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class OrderDelay implements Delayed {
		/**
		 * 订单编号
		 */
		private String orderId;
		/**
		 * 超时时间
		 */
		private long timeout;

		OrderDelay(String orderId, long timeout) {
			this.orderId = orderId;
			this.timeout = timeout + System.nanoTime();
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
		}

		@Override
		public int compareTo(Delayed other) {
			if (other == this)
				return 0;
			OrderDelay t = (OrderDelay) other;
			long d = (getDelay(TimeUnit.NANOSECONDS) - t
					.getDelay(TimeUnit.NANOSECONDS));
			return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
		}

		void print() {
			System.out.println(orderId + "编号的订单要删除啦...");
		}
	}
}
