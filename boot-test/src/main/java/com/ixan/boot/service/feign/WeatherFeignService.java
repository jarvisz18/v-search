package com.ixan.boot.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/8/10 20:58
 * @description 彩云天气API
 */
@FeignClient(name = "jdyun", url = "https://way.jd.com")
public interface WeatherFeignService {

	@RequestMapping(value = "/he/freeweather", method = RequestMethod.GET)
	ResponseEntity<byte[]> searchForecast(@RequestParam String city,
										  @RequestParam String appkey);
}
