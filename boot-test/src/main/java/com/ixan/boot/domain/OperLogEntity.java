package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 20:26
 * @description 操作日志实体
 * @version 1.0
 */
@Data
@Table(name = "oper_log")
public class OperLogEntity {
	@Id
	@Column(name = "oper_id")
	private String operId;//主键ID
	@Column(name = "oper_modul")
	private String operModul;//功能模块
	@Column(name = "oper_type")
	private String operType;//操作类型
	@Column(name = "oper_desc")
	private String operDesc;//操作描述
	@Column(name = "oper_requ_param")
	private String operRequParam;//请求参数
	@Column(name = "oper_resp_param")
	private String operRespParam;//返回参数

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
