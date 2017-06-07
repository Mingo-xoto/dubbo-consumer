package com.yhq.dubbo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * 由dubbo引入springmvc的上下文配置，
 * 否则因为不确定启动时候是先扫的是springmvc还是dubbo的实例，如果先扫springmvc的controller实例，
 * 会出现在controller取dubbo的reference的时候服务接口为空
 * 
 * @author Administrator
 *
 */
@Configurable
@ComponentScan(basePackages = ("com.yhq.dubbo.controller"))
@Import(WebConfig.class)
public class DubboConfig {

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
		// RegistryConfig registry = new RegistryConfig("10.1.21.4:2181");
		RegistryConfig registry = new RegistryConfig("127.0.0.1:2180,127.0.0.1:2181,127.0.0.1:2182");
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
		protocol.setPort(20880);
		return protocol;
	}

	// @Bean
	// public ReferenceConfig<IMerchantService> referenceConfig() {
	// ReferenceConfig<IMerchantService> reference = new
	// ReferenceConfig<IMerchantService>(); //
	// 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
	// reference.setApplication(application());
	// reference.setRegistry(registry()); // 多个注册中心可以用setRegistries()
	// reference.setInterface(IMerchantService.class);
	// IMerchantService merchantService = reference.get();
	// System.out.println("哈哈："+merchantService.getTargetDetailsById(""));
	// return reference;
	// }

}
