package com.client.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/18 13:45
 * @description 站点
 * @version 1.0
 */
@ConfigurationProperties(prefix = "site")
public class SiteInformation {
	private String name;
	private String domain;
	private String copyright;
	private String keywords;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
