package com.ixan.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ixan.boot.domain.Account;
import com.ixan.boot.domain.dto.AccountDTO;
import com.ixan.boot.mapper.AccountMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/26 23:27
 * @description
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Integer update(AccountDTO accountDTO) {
        Example example = new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", accountDTO.getUsername());
        criteria.andEqualTo("site", accountDTO.getSite());
        List<Account> accounts = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(accounts)) {
            return 0;
        }
        Account account = accounts.get(0);
        account.setPassword(accountDTO.getPassword());
        account.setUpdate_time(new Date());
        account.setVersion(account.getVersion() + 1);
        return accountMapper.updateByPrimaryKey(account);
    }

    @Override
    public Account findById(String id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Account> findAccountBySite(AccountDTO account) {
        String site = account.getSite();
        Example example = new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("site", "%" + site + "%");
        return accountMapper.selectByExample(example);
    }

    @Override
    public int add(AccountDTO account) {
        Account instance = new Account();
        BeanUtils.copyProperties(account, instance);
        instance.setCreate_time(new Date());
        instance.setUpdate_time(new Date());
        instance.setVersion(1);
        return accountMapper.insert(instance);
    }

    @Override
    public PageInfo<Account> findAll(AccountDTO accountDTO) {
        PageHelper.startPage(accountDTO.getPageNum(),accountDTO.getPageSize());
        List<Account> accountList = accountMapper.findAll();
        return new PageInfo<>(accountList);
    }
}
