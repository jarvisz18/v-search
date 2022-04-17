package cn.ixan.search.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 17:43
 * @description DisruptorMain
 * @version 1.0
 */
public class DisruptorMain {
	public static void main(String[] args) {
		//1.创建线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		//2.创建工厂
		LongEventFactory longEventFactory = new LongEventFactory();
		//3.创建ringBuffer大小
		int ringBufferSize = 1024 * 1024;

		//4.创建Disruptor
		Disruptor<LongEvent> longEventDisruptor = new Disruptor<>(longEventFactory, ringBufferSize, executorService,
				ProducerType.SINGLE,
				new YieldingWaitStrategy());
		//5.消费端方法
		longEventDisruptor.handleEventsWith(new LongEventHandler());
		//6.启动
		longEventDisruptor.start();
		//7.创建RingBuffer容器
		RingBuffer<LongEvent> ringBuffer = longEventDisruptor.getRingBuffer();
		//8.创建生产者
		LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
		//9.指定缓冲区大小
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for (int i = 0; i < 100; i++) {
			byteBuffer.putLong(0, i);
			longEventProducer.onData(byteBuffer);
		}
		//10.关闭disruptor和Executor
		longEventDisruptor.shutdown();
		executorService.shutdown();

	}
}
