package com.ixan.boot.test.audit;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/5 22:55
 * @description audit contact
 */
@Data
public class AuditContact extends AbstractAudit{
	private Integer contactNo;
	private String contactName;

	private Integer entryId;
	private Timestamp entryDatetime;
	private String UVersion;
}
