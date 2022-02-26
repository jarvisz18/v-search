package com.ixan.boot.test.juc.lock;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 10:59
 * @description 更新金额, 读取金额的例子
 * @link https://mp.weixin.qq.com/s/7-vdq0-VCJsKVFUQxtTEuw
 * @version 1.0
 * StampedLock 适合读多写少的场景,可以替代ReadWriteLock
 */
public class StampedLockExample2 {
	private static final StampedLock stampedLock = new StampedLock();

	public static void main(String[] args) throws InterruptedException {
		Balance balance = new Balance(new BigDecimal(10_000L));
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, 50).boxed().forEach(i -> {
			executorService.submit(new WriteAmount(balance));
			executorService.submit(new ReadAmount(balance));
		});
		executorService.shutdown();
		executorService.awaitTermination(30, TimeUnit.MINUTES);
	}

	private static class ReadAmount implements Runnable {
		private final Balance balance;

		private ReadAmount(Balance balance) {
			this.balance = balance;
		}

		@Override
		public void run() {
			long stamp = stampedLock.tryOptimisticRead();
			if (!stampedLock.validate(stamp)) {
				stamp = stampedLock.readLock();
				try {
					System.out.println("Read:" + balance.getAmount());
				} finally {
					stampedLock.unlockRead(stamp);
				}
			} else {
				System.out.println("Optimistic Read fail");
			}
		}
	}

	private static class WriteAmount implements Runnable {
		private final Balance balance;

		private WriteAmount(Balance balance) {
			this.balance = balance;
		}

		@Override
		public void run() {
			long stamp = -1L;
			try {
				stamp = stampedLock.writeLock();
				balance.setAmount(balance.getAmount().add(new BigDecimal(1000)));
				System.out.println("Write:" + balance.getAmount());
			} finally {
				stampedLock.unlockWrite(stamp);
			}
		}
	}

	private static class Balance {
		/**
		 * 账户余额
		 */
		private BigDecimal amount;

		private Balance(BigDecimal amount) {
			this.amount = amount;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	}
}
