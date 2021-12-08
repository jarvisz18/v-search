package cn.ixan.search.service;

import cn.ixan.search.domain.User;

import java.util.List;

/**
 * @author stackzhang@126.com
 */
public interface UserService {
    /**
     * 同步用户信息到ES库
     *
     * @return boolean
     */
    boolean syncUserInfo2ES();

    /**
     * 查询所有用户信息
     *
     * @return List<User>
     */
    List<User> listUser(Integer pageNum, Integer pageSize);

    /**
     * 添加用户信息
     *
     * @param user 用户信息
     */
    void insertByUser(User user);

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return User
     */
    User findUserById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 删除用户信息
     *
     * @param id 主键
     */
    void delete(Long id);
}
