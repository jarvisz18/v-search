package com.ixan.boot.test.audit;

import java.sql.Timestamp;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/5 23:35
 * @description audit new entity
 */
public interface AuditNew {
	void audit(Integer id, Timestamp entryDatetime);
}
