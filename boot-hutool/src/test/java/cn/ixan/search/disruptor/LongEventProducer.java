package cn.ixan.search.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 17:37
 * @description 生产者
 * @version 1.0
 */
public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer byteBuffer) {
		// 1.ringBuffer 事件队列 下一个槽
		long sequence = ringBuffer.next();
		Long data = null;

		try {
			LongEvent longEvent = ringBuffer.get(sequence);
			data = byteBuffer.getLong(0);
			longEvent.setValue(data);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			System.out.println("生产者准备发送数据");
			//发布事件
			ringBuffer.publish(sequence);
		}
	}
}
