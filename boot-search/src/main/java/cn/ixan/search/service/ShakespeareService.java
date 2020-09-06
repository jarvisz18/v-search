package cn.ixan.search.service;

import cn.ixan.search.domain.Shakespeare;

import java.util.List;
import java.util.Map;

public interface ShakespeareService {

    /**
     * <p>重新加载数据</p>
     * <p>保证接口的幂等性</p>
     * @return Map<String, Object>
     */
    Map<String, Object> reload();
    boolean clean();
    boolean init();

    void bulkDataToES(List<Shakespeare> list);

    boolean backupToDatabase();
    List<Shakespeare> query(Integer from, Integer size);

    int save(Integer from, Integer size);

}
