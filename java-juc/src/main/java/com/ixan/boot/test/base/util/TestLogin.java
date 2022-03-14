package com.ixan.boot.test.base.util;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/14 12:33
 * @description 测试JDNP登录
 * @version 1.0
 */
public class TestLogin {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		CountDownLatch countDownLatch = new CountDownLatch(100);
		executorService.execute(() -> {
			try {
				TestLogin testLogin = new TestLogin();
				String userName = String.valueOf(System.currentTimeMillis());
				String password = String.valueOf(System.currentTimeMillis());
				boolean authenticate = testLogin.authenticate(userName, password);
				System.out.println(Thread.currentThread().getName() + "login result:" + authenticate);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		});

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}

	/**
	 * 请描述 方法 authenticate 有什么bug
	 * ----在登录认证的高峰期， 系统抛出异常 StackTrace: javax.naming.NamingException: LDAP response read timed out
	 * ----During the peak login authentication period, the system throws an exception "StackTrace: javax.naming.NamingException: LDAP response read timed out"
	 * ---- what is the bug within method authenticate ?
	 */
	public boolean authenticate(String username, String password) throws Exception {
		boolean authenticated = false;
		DirContext ctx = null;
		try {
			ctx = getLdapCtx();
			Hashtable<String, String> tmpEnv = initLdapEnv();
			String userDN = "uid=" + username + "," + getLdapPassportDN();
			tmpEnv.put(Context.SECURITY_PRINCIPAL, userDN);
			tmpEnv.put(Context.SECURITY_CREDENTIALS, password);
			ctx = new InitialDirContext(tmpEnv);
			authenticated = true;
		} finally {
			closeLdapCtx(ctx);
		}
		return authenticated;
	}

	private DirContext getLdapCtx() throws Exception {
		DirContext ctx = new InitialDirContext(initLdapEnv());
		return ctx;
	}


	private Hashtable<String, String> initLdapEnv() throws Exception {
		String ldapServerAccount = "cn=root,dc=synnex,dc=com";
		String ldapServerPwd = "mypassword";
		String ldapServerUrl = "ldap://myldap.synnex.org:2000";

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapServerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ldapServerAccount);
		env.put(Context.SECURITY_CREDENTIALS, ldapServerPwd);
		env.put("com.sun.jndi.ldap.read.timeout", "15000");

		return env;
	}

	private void closeLdapCtx(DirContext ctx) {
		if (ctx != null) {
			try {
				ctx.close();
			} catch (NamingException e) {
			}
			ctx = null;
		}
		ctx = null;
	}

	private String getLdapPassportDN() throws Exception {
		return "dc=mydept,dc=synnex,dc=com";
	}

}
