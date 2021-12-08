package cn.ixan.search.cache.custom;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/5 下午9:10
 * @description 自定义缓存
 */
@Getter
@Setter
public class MyCache implements Comparable<MyCache> {
	//缓存键
	private Object key;
	//缓存值
	private Object value;
	//最后访问时间
	private long lastTime;
	//创建时间
	private long writeTime;
	//存活时间
	private long expireTime;
	//命中次数
	private Integer hitCount;

	@Override
	public int compareTo(MyCache myCache) {
		return hitCount.compareTo(myCache.hitCount);
	}
}
