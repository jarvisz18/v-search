package com.ixan.boot.test.audit;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/5 22:56
 * @description test audit save
 */
public class AuditSaveTest {

	@Test
	public void test_save1() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AuditContact auditContact = new AuditContact();
		auditContact.setContactNo(1);
		auditContact.setContactName("jarvis zhang");
		int entryId = 9527;
		fillAudit(auditContact, entryId, timestamp);

		AuditLoc auditLoc = new AuditLoc();
		auditLoc.setLocNo(100020);
		auditLoc.setLocName("Beijing ChaoYang");
		fillAudit(auditLoc, entryId, timestamp);

		System.out.println(auditContact);
		System.out.println(auditLoc);
	}

	public void fillAudit(Object object, Integer id, Timestamp entryDate) {
		Class<?> aClass = object.getClass();
		try {
			Field entryId = aClass.getDeclaredField("entryId");
			Field entryDatetime = aClass.getDeclaredField("entryDatetime");
			Field UVersion = aClass.getDeclaredField("UVersion");

			entryId.setAccessible(true);
			entryDatetime.setAccessible(true);
			UVersion.setAccessible(true);
			entryId.set(object, id);
			entryDatetime.set(object, entryDate);
			UVersion.set(object, "!");
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}
}
