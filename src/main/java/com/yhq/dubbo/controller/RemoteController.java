package com.yhq.dubbo.controller;

import java.util.Date;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.paywe.fos.api.dto.paychannel.PayInfoDto;
import cn.paywe.fos.api.service.remote.IXContractService;
import cn.paywe.fos.api.service.remote.IXInstitutionService;
import cn.paywe.fos.api.service.remote.IXMerchantService;
import cn.paywe.fos.api.service.remote.IXPaymentChannelService;

@RestController
@RequestMapping("/remote/")
public class RemoteController {

	@Reference(interfaceClass = IXContractService.class)
	private IXContractService contractService;

	@Reference(interfaceClass = IXInstitutionService.class)
	private IXInstitutionService institutionService;

	@Reference(interfaceClass = IXMerchantService.class)
	private IXMerchantService merchantService;

	@Reference(interfaceClass = IXPaymentChannelService.class)
	private IXPaymentChannelService paymentChannelService;

	@RequestMapping("show")
	public ModelMap show() {
		ModelMap map = new ModelMap();
		map.put("1.获取订单关联商户的退款最长时间配置接口", merchantService.getRefundsConfig("01f57bd6-ed12-4534-9aa8-735380be1dcd"));
		map.put("2.校验员工号（sys_user表主键ID）和门店编号（merchat表主键ID）是否合法", merchantService.verifyUserIDAndMerchantID("123", ""));
		PayInfoDto payInfoDto = new PayInfoDto();
		payInfoDto.setShopId("296386975991959552");
		payInfoDto.setPayType("11");
		map.put("3.获取通道配置 商户所属机构id 清分机构id", paymentChannelService.getPayInfoConfig(payInfoDto));
		map.put("1.通过商户 门店 id 获取详情", merchantService.getTargetDetailsByID("01f57bd6-ed12-4534-9aa8-735380be1dcd"));
		map.put("2.通过机构id 获取详情", institutionService.getTargetDetailsByID("039884fd-c3e4-4f9c-b709-7948b3d878e1"));
		map.put("3.通过合同id获取分润方案", contractService.getShareProfitProposal("288652719097999365111"));
		map.put("4.通过合同id获取通道成本",
				contractService.getChannelCost(new Date(), "01f57bd6-ed12-4534-9aa8-735380be1dcd", 3));
		// map.put("5.根据清分机构获取清分文件配置",
		// institutionService.getClearingConfig(""));
		map.put("6.通过支付方式返回该支付平台的所有子商户ID", merchantService.getChildMerchantID("11"));
		map.put("7.通过门店ID获取所属商户Id和机构Id", merchantService.getMerchantIDAndInstitutionID("288629373589372928"));
		return map;
	}
}
