package com.ixan.boot.web.external;

import com.ixan.boot.domain.base.OperLog;
import com.ixan.boot.domain.base.OperLogConst;
import com.ixan.boot.service.CacheServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/10/6 16:09
 * @description 缓存控制器
 * @version 1.0
 */
@RestController
public class CacheController {
	private static final Logger logger = LoggerFactory.getLogger(CacheController.class);
	@Autowired
	private CacheServiceImpl cacheService;

	@GetMapping("/cache")
	@OperLog(operModul = "缓存管理-查询", operType = OperLogConst.QUERY, operDesc = "缓存管理-查询")
	public void cache() {
		// 第一次查询，应该走数据库
		logger.info("first query...");
		cacheService.getAccountByName("somebody");
		// 第二次查询，应该不查数据库，直接返回缓存的值
		logger.info("second query...");
		cacheService.getAccountByName("somebody");
	}

	@GetMapping("/reload")
	@OperLog(operModul = "缓存管理-删除", operType = OperLogConst.DELETE, operDesc = "缓存管理-删除")
	public void reload() {
		cacheService.reload();
	}
}
