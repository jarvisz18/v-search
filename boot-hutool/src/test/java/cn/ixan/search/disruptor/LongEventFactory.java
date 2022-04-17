package cn.ixan.search.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 17:29
 * @description 事件工厂
 * @version 1.0
 */
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}
}
