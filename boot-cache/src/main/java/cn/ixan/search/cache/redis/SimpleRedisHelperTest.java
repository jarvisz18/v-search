package cn.ixan.search.cache.redis;

import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/15 22:52
 * @description redis helper test
 * @version 1.0
 */
public class SimpleRedisHelperTest {
	public static void main(String[] args) {
		String name = SimpleRedisHelper.get("name");
		System.out.println(name);

		SimpleRedisHelper.hset("tel", "匪警电话", "110");
		SimpleRedisHelper.hset("tel", "火警电话", "119");
		SimpleRedisHelper.hset("tel", "急救电话", "120");
		SimpleRedisHelper.hset("tel", "红十字会", "999");
		SimpleRedisHelper.hset("tel", "交通报警", "122");

		System.out.println(SimpleRedisHelper.hget("tel", "急救电话"));

		Map<String, String> tel = SimpleRedisHelper.hgetAll("tel");
		tel.forEach((k, v) -> System.out.println("k:" + k + "v:" + v));

		// call method return new object
		Map<String, String> object1 = SimpleRedisHelper.hgetAll("tel");
		Map<String, String> object2 = SimpleRedisHelper.hgetAll("tel");
		System.out.println(object1 == object2);
	}
}
