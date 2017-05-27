package com.yhq.dubbo.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.paywe.fos.api.service.IAppAuthTokenService;
import cn.paywe.fos.api.service.IMerchantService;

@RestController
@RequestMapping("/remote/")
public class RemoteController {

	@Reference(interfaceClass = IAppAuthTokenService.class)
	private IAppAuthTokenService appAuthTokenService;

	// @Reference(interfaceClass = IHelloworldService.class)
	// private IHelloworldService ise;

	@Reference(interfaceClass = IMerchantService.class)
	private IMerchantService merchantService;

	@RequestMapping("show")
	public ModelMap show() {
		ModelMap map = new ModelMap();
//		map.put("version", appAuthTokenService.showServiceVersion());
		// map.put("say", ise.sayHello());
		map.put("merchantID", merchantService.getTargetDetailsById("123456merchantService"));
		map.put("appAuthTokenID", appAuthTokenService.getTargetDetailsById("123456appAuthTokenID"));
		return map;
	}
}
