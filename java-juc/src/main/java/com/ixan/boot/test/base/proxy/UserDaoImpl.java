package com.ixan.boot.test.base.proxy;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/28 12:25
 * @description UserDaoImpl
 * @version 1.0
 */
public class UserDaoImpl implements UserDao {
	@Override
	public void saveUser() {
		System.out.println("invoke method saveUser.");
	}
}
