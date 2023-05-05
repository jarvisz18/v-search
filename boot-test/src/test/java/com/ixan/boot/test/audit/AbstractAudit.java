package com.ixan.boot.test.audit;

import java.sql.Timestamp;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/5 23:19
 * @description abstract audit
 */
public abstract class AbstractAudit implements AuditNew {

	@Override
	public void audit(Integer entryId, Timestamp entryDatetime) {
		setEntryId(entryId);
		setEntryDatetime(entryDatetime);
		setUVersion("!");
	}

	abstract void setEntryId(Integer entryId);

	abstract void setEntryDatetime(Timestamp entryDatetime);

	abstract void setUVersion(String UVersion);
}
