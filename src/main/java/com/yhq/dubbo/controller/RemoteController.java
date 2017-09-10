package com.yhq.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yhq.dubbo.service.IOwnerService;
import com.yhq.service.IXService;

@RestController
@RequestMapping("/remote/")
public class RemoteController {

	static {
		System.out.println("初始化");
	}

	{
		System.out.println("动态");
	}
	@Reference(interfaceClass = IXService.class, version = "1.0")
	private IXService service1;

	@Reference(interfaceClass = IXService.class, version = "2.0")
	private IXService service2;

	@Autowired
	private IOwnerService ownerService;

	@RequestMapping("show")
	public ModelMap show() {
		ModelMap map = new ModelMap();
		map.put("Version-1.0", service1.show());
		map.put("Version-2.0", service2.show());
		ownerService.show();
		return map;
	}
}
