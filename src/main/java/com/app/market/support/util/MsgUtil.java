package com.app.market.support.util;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.app.market.service.sys.MsgService;

@Component
public class MsgUtil {
	@Reference(version = Version.NOW)
	private static MsgService msgService;

	/**
	 * 根据code，获取错误信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getMsg(String code) {
		return msgService.getMsg(code);
	}

}