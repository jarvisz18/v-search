package cn.ixan.search.hutool.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午8:51
 * @description Excel校验失败的数据
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelErrorDTO<T> extends BaseEntity {
	/**
	 * 原有实体
	 */
	private T entity;
	/**
	 * 错误信息
	 */
	private String errorMessage;
}
