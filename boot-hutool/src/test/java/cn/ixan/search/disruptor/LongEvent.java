package cn.ixan.search.disruptor;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 17:28
 * @description 定义事件
 * @version 1.0
 */
public class LongEvent {
	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
