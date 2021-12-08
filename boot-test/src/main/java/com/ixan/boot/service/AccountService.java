package com.ixan.boot.service;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.domain.Account;
import com.ixan.boot.domain.dto.AccountDTO;

import java.util.List;

/**
 * @author stackzhang@126.com
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
