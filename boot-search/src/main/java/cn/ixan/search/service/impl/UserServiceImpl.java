package cn.ixan.search.service.impl;

import cn.ixan.search.domain.User;
import cn.ixan.search.domain.UserExample;
import cn.ixan.search.mapper.UserMapper;
import cn.ixan.search.service.UserService;
import com.github.pagehelper.PageHelper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author stack_zhang@outlook.com
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JestClient jestClient;

    /**
     * 同步用户信息到ES库
     *
     * @return boolean
     */
    @Override
    public boolean syncUserInfo2ES() {
        boolean result = false;
        List<User> userList = userMapper.selectByExample(new UserExample());
        Set<Index> collect = userList.stream()
                .map(user -> new Index.Builder(user).build())
                .collect(Collectors.toSet());
        Bulk bulk = new Bulk.Builder()
                .defaultIndex("user")
                .defaultType("info")
                .addAction(collect).build();
        try {
            BulkResult execute = jestClient.execute(bulk);
            result = execute.isSucceeded();
        } catch (IOException e) {
            log.error("执行批量操作发生异常", e);
        }
        return result;
    }

    /**
     * 查询所有用户信息
     *
     * @return List<User>
     */
    @Cacheable(value = "models")
    @Override
    public List<User> listUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        log.info("查询到用户记录数:[{}]", users.size());
        return users;
    }

    /**
     * 添加用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void insertByUser(User user) {

    }

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return User
     */
    @Cacheable(value = "models")
    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void update(User user) {

    }

    /**
     * 删除用户信息
     *
     * @param id 主键
     */
    @Override
    public void delete(Long id) {

    }
}
