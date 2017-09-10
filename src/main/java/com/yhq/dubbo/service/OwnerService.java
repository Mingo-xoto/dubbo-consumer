package com.yhq.dubbo.service;

import org.springframework.stereotype.Service;

@Service
public class OwnerService implements IOwnerService {

	@Override
	public void show() {
		System.out.println("内部服务");
	}

}
