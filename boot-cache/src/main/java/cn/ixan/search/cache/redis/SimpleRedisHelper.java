package cn.ixan.search.cache.redis;

import redis.clients.jedis.Jedis;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/15 22:47
 * @description Redis Helper
 * @version 1.0
 */
public final class SimpleRedisHelper {
	private SimpleRedisHelper() {
		throw new UnsupportedOperationException();
	}

	private static final Jedis jedis;

	static {
		jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("root@#$");
	}

	public static Jedis getJedis() {
		return jedis;
	}

	public static String set(@NotBlank String key, @NotBlank String value) {
		return jedis.set(key, value);
	}

	public static String get(@NotBlank String key) {
		return jedis.get(key);
	}

	public static Long hset(@NotBlank String key, @NotBlank String field, @NotBlank String value) {
		return jedis.hset(key, field, value);
	}

	public static String hget(@NotBlank String key, @NotBlank String field) {
		return jedis.hget(key, field);
	}

	public static Map<String, String> hgetAll(@NotBlank String key) {
		return jedis.hgetAll(key);
	}
}
