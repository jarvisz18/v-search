package com.ixan.boot.utils;

import java.util.UUID;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/8/15 21:53
 * @description guid generate
 */
public final class GUIDsGenerater {
	public static String guid() {
		return UUID.randomUUID().toString().toLowerCase();
	}
}
