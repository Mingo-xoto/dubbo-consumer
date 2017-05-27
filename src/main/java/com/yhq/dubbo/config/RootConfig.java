package com.yhq.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Spring Application Bean 初始化
 */

@Configuration
@ComponentScan(basePackages = { "cn.paywe.fos.api.service" }, excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {
	
	/**
	 * 由于放在WebConfig无法获取到bean
	 * 暂时先放在这里，之后可以移动到适当的位置
	 * @return
	 */
	@Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        return new RequestMappingHandlerMapping();
    }
}
