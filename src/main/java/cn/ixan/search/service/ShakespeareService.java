package cn.ixan.search.service;

import cn.ixan.search.domain.ResultBean;
import cn.ixan.search.domain.Shakespeare;

import java.util.List;
import java.util.Map;

public interface ShakespeareService {
    ResultBean<String> settings(String indexName, String indexType);

    /**
     * 判断索引是否存在
     * @return boolean
     * @param indexName
     * @param indexType
     */
    ResultBean<String> mapping(String indexName, String indexType);
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
