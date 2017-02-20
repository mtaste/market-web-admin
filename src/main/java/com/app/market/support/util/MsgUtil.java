package com.app.market.support.util;

import java.util.Hashtable;

import org.springframework.stereotype.Component;

@Component
public class MsgUtil {
	private static Hashtable<String, String> MSG = new Hashtable<String, String>();
	static {
		MSG.put("-201", "用户名或密码错误.");

	}

	/**
	 * 根据code，获取错误信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getMsg(String code) {
		if (!MSG.containsKey(code)) {
			return "";
		}
		return MSG.get(code);
	}

}
