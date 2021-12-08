package com.stackzhang.boot.config;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/11/21 8:29 下午
 * @description httpclient相关配置类
 */
@Configuration
public class HttpClientConfig {
	private static final long KEEP_ALIVE_MILL_SEC = 1000;

	@Bean
	public RequestConfig requestConfig() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(3000)
				.setSocketTimeout(20000)
				.setConnectionRequestTimeout(1500)
				.build();
		return requestConfig;
	}

	@Bean
	public HttpClientBuilder httpClientBuilder(RequestConfig requestConfig) {
		ConnectionKeepAliveStrategy connectionKeepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
				long keepAlive = super.getKeepAliveDuration(httpResponse, httpContext);
				if (keepAlive == -1) {
					keepAlive = KEEP_ALIVE_MILL_SEC;
				}
				return keepAlive;
			}
		};
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(new PoolingHttpClientConnectionManager());
		httpClientBuilder.setDefaultRequestConfig(requestConfig);
		httpClientBuilder.disableAutomaticRetries();
		httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy);
		return httpClientBuilder;
	}

}
