package com.ixan.boot.controller;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.controller.dto.AccountDTO;
import com.ixan.boot.domain.Account;
import com.ixan.boot.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/26 23:22
 * @description 账户信息操作
 */
@Api(tags = "账户信息操作")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "修改账户数据", notes = "修改账户数据")
    @PostMapping("/update")
    public Result<Integer> update(@RequestBody AccountDTO account) {
        return ResultGenerate.success(accountService.update(account));
    }

    @ApiOperation(value = "根据主键查询账户", notes = "根据主键查询账户")
    @GetMapping("/findById/{id}")
    public Result<Account> findById(@PathVariable("id") String id) {
        return ResultGenerate.success(accountService.findById(id));
    }

    @ApiOperation(value = "根据站点模糊查询账户数据", notes = "根据站点模糊查询账户数据")
    @PostMapping("/findAccountBySite")
    public Result<List<Account>> findAccountBySite(@RequestBody AccountDTO account) {
        return ResultGenerate.success(accountService.findAccountBySite(account));
    }

    @ApiOperation(value = "新增账户数据", notes = "新增账户数据")
    @PostMapping("/add")
    public Result<Integer> add(@RequestBody AccountDTO account) {
        return ResultGenerate.success(accountService.add(account));
    }

    @ApiOperation(value = "查询所有账户数据", notes = "查询所有账户数据")
    @PostMapping("/findAll")
    public Result<PageInfo<Account>> findAll(@RequestBody AccountDTO accountDTO) {
        return ResultGenerate.success(accountService.findAll(accountDTO));
    }
}
