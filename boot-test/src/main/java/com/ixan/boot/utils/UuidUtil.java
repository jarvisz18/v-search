package com.ixan.boot.utils;

import java.util.UUID;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 20:59
 * @description UuidUtil
 * @version 1.0
 */
public class UuidUtil {
	public static String get32UUID() {
		return UUID.randomUUID().toString().toLowerCase();
	}
}
