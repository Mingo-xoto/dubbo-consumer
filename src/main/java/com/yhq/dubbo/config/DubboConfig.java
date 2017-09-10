package com.yhq.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

@Configuration
public class DubboConfig {

	static {
		System.out.println("DubboConfig初始化");
	}

	{
		System.out.println("DubboConfig动态");
	}

	/**
	 * dubbo应用信息配置
	 * 
	 * @return
	 */
	@Bean
	public ApplicationConfig application() {
		ApplicationConfig applicationConfig = new ApplicationConfig("consumer");
		return applicationConfig;
	}

	/**
	 * dubbo注册中心
	 * 
	 * @return
	 */
	@Bean
	public RegistryConfig registry() {
		RegistryConfig registry = new RegistryConfig("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
		registry.setProtocol("zookeeper");
		return registry;
	}

	@Bean
	public AnnotationBean annotation() {
		AnnotationBean annotation = new AnnotationBean();
		annotation.setPackage("com.yhq.dubbo.controller");
		return annotation;
	}

	/**
	 * 协议配置，用于配置提供服务的协议信息，协议由提供方指定，消费方被动接受
	 * 
	 * @return
	 */
	@Bean
	public ProtocolConfig protocol() {
		ProtocolConfig protocol = new ProtocolConfig("dubbo");
		protocol.setPort(20882);
		return protocol;
	}

}
