package com.ixan.boot.web.external;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.domain.Dict;
import com.ixan.boot.domain.OperLog;
import com.ixan.boot.domain.OperLogConst;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 15:30
 * @description 数据字典
 * @version 1.0
 */
@RestController
public class DictController {

	@ApiOperation(value = "查询所有数据字典", notes = "查询所有数据字典")
	@PostMapping("/findAll")
	@OperLog(operModul = "字典管理-查询", operType = OperLogConst.QUERY, operDesc = "字典管理-查询")
	public Result<PageInfo<Dict>> findAll() {
		Dict dict = new Dict();
		dict.setCreateTime(new Date());
		dict.setUpdateTime(new Date());
		dict.setVersion(1L);
		Dict dict2 = new Dict();
		dict2.setCreateTime(new Date());
		dict2.setUpdateTime(new Date());
		dict2.setVersion(3L);
		return ResultGenerate.success(new PageInfo<>(Arrays.asList(dict, dict2)));
	}
}
