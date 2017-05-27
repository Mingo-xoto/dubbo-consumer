package com.yhq.dubbo.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring servlet initialization
 */

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { DubboConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	// @Bean
	// public DelegatingFilterProxy delegatingFilterProxy() {
	// return new DelegatingFilterProxy();
	// }
	//
	// 配置过滤�?
	@Override
	protected Filter[] getServletFilters() {

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		// 其他拦击器配置在�?

		return new Filter[] { characterEncodingFilter };
	}

	// 当registerDispatcherServlet完成时自定义registration
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// 转义字符配置 防止xss 攻击
		registration.setInitParameter("defaultHtmlEscape", "true");
	}

}
