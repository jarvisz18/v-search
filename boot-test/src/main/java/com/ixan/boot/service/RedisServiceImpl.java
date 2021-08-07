package com.ixan.boot.service;

import org.springframework.stereotype.Service;

//import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/11/14 10:13 上午
 * @description
 */
@Service
public class RedisServiceImpl implements RedisService {
	/*@Autowired
	private StringRedisTemplate stringRedisTemplate;*/

	/*
	 * 实现并测试Redis事务
	 */
	@Override
	public void testTransaction() {
		/*stringRedisTemplate.setEnableTransactionSupport(true);
		try {
			stringRedisTemplate.multi();//开启事务
			stringRedisTemplate.opsForValue().increment("transaction", 1);

			if (true)
				throw new Exception();

			stringRedisTemplate.opsForValue().increment("transaction2", 2);
			//提交
			stringRedisTemplate.exec();
		} catch (Exception e) {
			System.out.println("redis事务回滚了");
			//开启回滚
			stringRedisTemplate.discard();

		}*/
	}
}
