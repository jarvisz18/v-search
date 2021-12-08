package cn.ixan.search.service;

import cn.ixan.search.domain.Shakespeare;

import java.util.List;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/9/6 20:10
 * @description 莎士比亚DB操作
 */
public interface ShakeDataService {

	boolean init();

	/**
	 * <p>重新加载数据</p>
	 * <p>保证接口的幂等性</p>
	 *
	 * @return Map<String, Object>
	 */
	Map<String, Object> reload();

	void bulkDataToES(List<Shakespeare> list);

	boolean backupToDatabase();
}
