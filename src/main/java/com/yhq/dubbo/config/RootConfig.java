package com.yhq.dubbo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.yhq.dubbo.service")
public class RootConfig {
	
}
