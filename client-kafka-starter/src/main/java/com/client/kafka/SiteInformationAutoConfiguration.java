package com.client.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/18 13:48
 * @description SiteInformationAutoConfiguration
 * @version 1.0
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(SiteInformation.class)
public class SiteInformationAutoConfiguration {

	@Autowired
	private SiteInformation siteInformation;

	@Bean
	@ConditionalOnMissingBean(SiteInformationProvider.class)
	public SiteInformationProvider siteInformationProvider() {
		SiteInformationProvider provider = new SiteInformationProvider();
		provider.setSiteInformation(siteInformation);
		return provider;
	}
}
