package cn.ixan.search.configuration;

import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.concurrent.TimeUnit;


//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	//todo:添加静态资源缓存注册
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/templates/")
				.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)
						.cachePrivate()
						.mustRevalidate())
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
}
