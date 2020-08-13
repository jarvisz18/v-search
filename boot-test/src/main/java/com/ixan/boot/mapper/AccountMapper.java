package com.ixan.boot.mapper;

import com.ixan.boot.config.BaseMapper;
import com.ixan.boot.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/26 23:27
 * @description
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {
    List<Account> findAll();
}
