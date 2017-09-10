package com.yhq.dubbo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring Mvc Web Bean
 */
@Configuration
@EnableWebMvc
@Import(DubboConfig.class)
@ComponentScan(basePackages = "com.yhq.dubbo.controller", useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.valueOf("text/plain;charset=UTF-8"));
		converter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(converter);
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setSupportedMediaTypes(supportedMediaTypes);
		messageConverter.setPrettyPrint(true);
		converters.add(messageConverter);
	}

}
