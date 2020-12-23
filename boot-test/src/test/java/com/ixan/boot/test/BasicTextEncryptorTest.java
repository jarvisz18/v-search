package com.ixan.boot.test;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Before;
import org.junit.Test;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/12/9 17:48
 * @description 测试加密
 * @version 1.0
 */
public class BasicTextEncryptorTest {
	BasicTextEncryptor textEncryptor;

	@Before
	public void setUp() {
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("lsl!@#$%^&*()");
	}

	@Test
	public void encrypt() {
		// 加密
		System.out.println("ENC(" + textEncryptor.encrypt("12345678") + ")");
	}

	@Test
	public void decyptPwd() {
		//解密
		System.out.println(textEncryptor.decrypt("Rh2CPbKgqvskbuur9mV0bd4hO8bfUPeD"));
	}
}
