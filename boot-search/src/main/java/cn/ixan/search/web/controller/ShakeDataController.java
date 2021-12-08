package cn.ixan.search.web.controller;

import cn.ixan.search.service.ShakeDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/9/6 20:13
 * @description 备份ES数据到数据库
 */
@RestController
public class ShakeDataController {
	@Autowired
	private ShakeDataService shakeDataService;

	@ApiOperation(value = "初始化莎士比亚数据到索引库", notes = "初始化莎士比亚数据到索引库")
	@PostMapping("/shake/init")
	public boolean init() {
		return shakeDataService.init();
	}

	@ApiOperation(value = "重新加载数据", notes = "重新加载数据")
	@PostMapping("/shake/reload")
	public Map<String, Object> reload() {
		return shakeDataService.reload();
	}

	@ApiOperation(value = "备份数据", notes = "备份数据")
	@PostMapping("/shake/backup")
	public Map<String, Object> backup() {
		Map<String, Object> result = new HashMap<>();
		Instant now = Instant.now();
		boolean backup = shakeDataService.backupToDatabase();
		Instant end = Instant.now();
		long millis = Duration.between(now, end).toMillis();
		result.put("result", backup);
		result.put("millis", millis + "ms");
		return result;
	}
}
