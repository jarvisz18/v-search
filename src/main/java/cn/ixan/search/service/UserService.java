package cn.ixan.search.service;

public interface UserService {
	/**
	 * 同步用户信息到ES库
	 * @return
	 */
	boolean syncUserInfo2ES();
}
