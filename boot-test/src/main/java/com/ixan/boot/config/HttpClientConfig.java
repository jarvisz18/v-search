package com.ixan.boot.config;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/11/18 17:03
 * @description httpclient配置类
 * @version 1.0
 */
@Configuration
public class HttpClientConfig {

	private static final long KEEP_ALIVE_MILL_SEC = 20000L;

	@Bean
	public HttpClientConnectionManager httpClientConnectionManager() {
		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
		//设置最大连接数
		clientConnectionManager.setMaxTotal(25);
		//设置每个连接的路由数
		clientConnectionManager.setDefaultMaxPerRoute(25);
		return clientConnectionManager;
	}

	@Bean
	public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager httpClientConnectionManager) {
		RequestConfig build = RequestConfig.custom()
				.setConnectTimeout(30000)
				.setSocketTimeout(20000)
				.setConnectionRequestTimeout(15000)
				.build();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(httpClientConnectionManager);
		httpClientBuilder.setDefaultRequestConfig(build);
		httpClientBuilder.disableAutomaticRetries();
		httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				long keepAlive = super.getKeepAliveDuration(response, context);
				if (keepAlive == -1) {
					keepAlive = KEEP_ALIVE_MILL_SEC;
				}
				return keepAlive;
			}
		});
		return httpClientBuilder;
	}


}
