package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/14 下午1:49
 * @description 日志文件
 */
@Data
@Table(name = "log_file")
public class LogFile {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "content")
	private String content;
}
