package cn.ixan.search.domain;

import lombok.Data;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/7/5 17:58
 * @description
 * @version 1.0
 */
@Data
public class SendLogDTO {
	private Long id;
	private String type;
	private String templateName;
}
