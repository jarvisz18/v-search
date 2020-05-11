package cn.ixan.search.service;

public interface RedisService {
    boolean lock(final String key, final long ex);
}
