package cn.ixan.search.web.dto;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/10 15:34
 * @description message
 * @version 1.0
 */
public class Message {
	/**
	 * topic名称
	 */
	private String topic;
	/**
	 * 消息内容
	 */
	private String content;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
