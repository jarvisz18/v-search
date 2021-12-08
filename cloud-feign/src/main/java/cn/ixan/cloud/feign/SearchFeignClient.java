package cn.ixan.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/5/13 19:07
 * @description
 */
@FeignClient(name = "v-search",url = "http://localhost:9087")
public interface SearchFeignClient {

    @RequestMapping(value = "/search/archive/batchArchiveInsert", method = RequestMethod.POST)
    Map<String, Object> batchArchiveInsert(@RequestBody List<String> list);
}
