package cn.ixan.search.service.impl;

import cn.ixan.search.service.RedisService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;

//@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean lock(final String key,final long ex) {
        Boolean execute = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer valueSerializer = stringRedisTemplate.getValueSerializer();
                RedisSerializer keySerializer = stringRedisTemplate.getKeySerializer();
                Object execute = redisConnection.execute("set", keySerializer.serialize(key),
                        valueSerializer.serialize("lock"),
                        SafeEncoder.encode("NX"),
                        SafeEncoder.encode("EX"),
                        Protocol.toByteArray(ex)
                );
                return null != execute;
            }
        });
        return execute;
    }
}
