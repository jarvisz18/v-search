package com.ixan.boot.service;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.controller.dto.AccountDTO;
import com.ixan.boot.domain.Account;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/26 23:27
 * @description
 */
public interface AccountService {
    Integer update(AccountDTO account);
    Account findById(String id);
    List<Account> findAccountBySite(AccountDTO account);
    int add(AccountDTO account);
    PageInfo<Account> findAll(AccountDTO accountDTO);

}
