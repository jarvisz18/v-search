package cn.ixan.search.service;

import cn.ixan.search.domain.User;

import java.util.List;

public interface UserService {
	/**
	 * 同步用户信息到ES库
	 * @return
	 */
	boolean syncUserInfo2ES();

	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<User> findAll(Integer pageNum, Integer pageSize);

	/**
	 * 添加用户信息
	 * @param user
	 */
	void insertByUser(User user);

	/**
	 * 根据主键查询用户信息
	 * @param id
	 * @return
	 */
	Object findById(Long id);

	/**
	 * 修改用户信息
	 * @param user
	 */
	void update(User user);

	/**
	 * 删除用户信息
	 * @param id
	 */
	void delete(Long id);
}
