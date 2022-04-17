package cn.ixan.search.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 17:30
 * @description 事件消费者
 * @version 1.0
 */
public class LongEventHandler implements EventHandler<LongEvent> {
	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		System.out.println("消费者:" + longEvent.getValue());
	}
}
