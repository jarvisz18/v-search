package com.ixan.boot.test.audit;

import org.junit.Test;

import java.sql.Timestamp;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/5 22:56
 * @description test audit save
 */
public class AuditSaveTest2 {

	@Test
	public void test_save() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AuditContact auditContact = new AuditContact();
		auditContact.setContactNo(1);
		auditContact.setContactName("jarvis zhang");
		int entryId = 9527;
		auditContact.audit(entryId, timestamp);

		AuditLoc auditLoc = new AuditLoc();
		auditLoc.setLocNo(100020);
		auditLoc.setLocName("Beijing ChaoYang");
		auditLoc.audit(entryId, timestamp);

		System.out.println(auditContact);
		System.out.println(auditLoc);
	}
}
