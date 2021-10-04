package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 20:34
 * @description 错误日志实体
 * @version 1.0
 */
@Data
@Table(name = "error_log")
public class ErrorLog {
	@Id
	@Column(name = "exc_id")
	private String excId;//主键ID
	@Column(name = "exc_requ_param")
	private String excRequParam;//请求参数
	@Column(name = "exc_name")
	private String excName;//异常名称
	@Column(name = "exc_message")
	private String excMessage;//异常信息
	@Column(name = "oper_user_id")
	private String operUserId;//操作员id
	@Column(name = "oper_user_name")
	private String operUserName;//操作员名称
	@Column(name = "oper_method")
	private String operMethod;//操作方法
	@Column(name = "oper_uri")
	private String operUri;//请求URI
	@Column(name = "oper_ip")
	private String operIp;//请求IP
	@Column(name = "oper_create_time")
	private Date operCreateTime;//操作时间
	@Column(name = "oper_ver")
	private String operVer;//操作版本号
}
