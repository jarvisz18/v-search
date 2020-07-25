package cn.ixan.search.configuration;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

public class JedisTool {

    public void deleteLock(Jedis jedis, String key, String val) {
        if (val.equals(jedis.get(key))) {
            jedis.del(key);
        }
    }

    public void setLock(Jedis jedis, String key, String val, int expireTime) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        try {
            String response = jedis.set(key, val, "NX", "PX", expireTime);
        } finally {
            jedis.eval(luaScript, Arrays.asList(key), Arrays.asList(val));
        }
    }
}
