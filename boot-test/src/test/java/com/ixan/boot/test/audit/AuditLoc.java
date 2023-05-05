package com.ixan.boot.test.audit;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/5 22:53
 * @description audit loc
 */
@Data
public class AuditLoc extends AbstractAudit{
	private Integer locNo;
	private String locName;

	private Integer entryId;
	private Timestamp entryDatetime;
	private String UVersion;
}
