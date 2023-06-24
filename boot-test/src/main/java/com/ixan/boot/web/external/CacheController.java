package com.ixan.boot.web.external;

import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.domain.base.OperLog;
import com.ixan.boot.domain.base.OperLogConst;
import com.ixan.boot.domain.vo.CacheEntity;
import com.ixan.boot.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/10/6 16:09
 * @description 缓存控制器
 */
@RequestMapping("/cache")
@RestController
public class CacheController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
	@Resource
	private CacheService cacheService;

	@GetMapping("/cacheId/{cacheId}")
	@OperLog(operModul = "缓存管理-查询", operType = OperLogConst.QUERY, operDesc = "缓存管理-查询")
	public Result<CacheEntity> find(@PathVariable("cacheId") Integer cacheId) {
		LOGGER.info("get cache :{}", cacheId);
		return ResultGenerate.success(cacheService.find(cacheId));
	}

	@PostMapping("/cacheId/{cacheId}")
	@OperLog(operModul = "缓存管理-查询/新增", operType = OperLogConst.QUERY, operDesc = "缓存管理-查询、新增")
	public Result<CacheEntity> create(@PathVariable("cacheId") Integer cacheId) {
		LOGGER.info("get cache :{}", cacheId);
		return ResultGenerate.success(cacheService.create(cacheId));
	}

	@PutMapping("/cacheId/{cacheId}")
	@OperLog(operModul = "缓存管理-修改", operType = OperLogConst.QUERY, operDesc = "缓存管理-修改")
	public Result<CacheEntity> update(@PathVariable("cacheId") Integer cacheId) {
		LOGGER.info("update cacheId :{}",cacheId);
		return ResultGenerate.success(cacheService.update(cacheId));
	}

	@DeleteMapping("/cacheId/{cacheId}")
	@OperLog(operModul = "缓存管理-删除", operType = OperLogConst.QUERY, operDesc = "缓存管理-删除")
	public Result<CacheEntity> delete(@PathVariable("cacheId") Integer cacheId) {
		LOGGER.info("delete cacheId :{}",cacheId);
		cacheService.delete(cacheId);
		return ResultGenerate.success("ok");
	}

	@GetMapping("/list")
	@OperLog(operModul = "缓存管理-查询所有", operType = OperLogConst.QUERY, operDesc = "缓存管理-查询所有")
	public Result<Object> findAll(@RequestParam(required = false) String name) {
		LOGGER.info("get cache :{}", System.currentTimeMillis());
		return ResultGenerate.success(cacheService.findAll(name));
	}

	@PostMapping("/clear")
	@OperLog(operModul = "缓存管理-删除", operType = OperLogConst.DELETE, operDesc = "缓存管理-删除")
	public Result<Object> clear() {
		LOGGER.info("reload:{}", System.currentTimeMillis());
		cacheService.clear();
		return ResultGenerate.success("ok");
	}
}
