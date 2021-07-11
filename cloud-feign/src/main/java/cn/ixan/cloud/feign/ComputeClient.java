package cn.ixan.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name = "compute-service",fallback = ComputeClientHystrix.class)
public interface ComputeClient {

	@RequestMapping(method = RequestMethod.GET,value = "/add")
	Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
