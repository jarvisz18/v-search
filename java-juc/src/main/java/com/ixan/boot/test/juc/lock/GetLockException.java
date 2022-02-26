package com.ixan.boot.test.juc.lock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/22 下午1:02
 * @description 获取锁异常
 */
public class GetLockException extends RuntimeException {
	public GetLockException() {
		super();
	}

	public GetLockException(String message) {
		super(message);
	}

}
