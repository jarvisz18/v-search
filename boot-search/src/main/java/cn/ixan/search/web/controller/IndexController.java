package cn.ixan.search.web.controller;

import cn.ixan.search.domain.ResultBean;
import cn.ixan.search.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>获取索引基本信息</p>
 *
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/9/6 19:26
 * @description index controller
 */
@Api(tags = "索引基本信息")
@RestController
public class IndexController {
	@Resource
	private IndexService indexService;

	@ApiOperation(value = "获取索引settings信息", notes = "获取索引settings信息")
	@GetMapping("/settings/{indexName}/{indexType}")
	public ResultBean<String> settings(@PathVariable("indexName") String indexName,
									   @PathVariable("indexType") String indexType) {
		return indexService.settings(indexName, indexType);
	}

	@ApiOperation(value = "获取索引mapping信息", notes = "获取索引mapping信息")
	@GetMapping("/mapping/{indexName}/{indexType}")
	public ResultBean<String> mapping(@PathVariable("indexName") String indexName,
									  @PathVariable("indexType") String indexType) {
		return indexService.mapping(indexName, indexType);
	}
}
