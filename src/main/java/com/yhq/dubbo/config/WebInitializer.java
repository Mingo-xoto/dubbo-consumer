package com.yhq.dubbo.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring servlet initialization
 */

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("初始化RootServlet");
		return new Class<?>[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("初始化DispatcherServlet");
		return new Class[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * 注册过滤器，映射路径与DispatcherServlet一致，
	 * 路径不一致的过滤器需要注册到另外的WebApplicationInitializer中
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}
}
