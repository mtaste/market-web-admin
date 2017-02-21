package com.app.market.support.util;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jodd.util.StringUtil;

public class Request {

	public static Map<String, Hashtable<String, String>> USER = new Hashtable<String, Hashtable<String, String>>();

	/**
	 * 获取用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserId(HttpServletRequest request) {
		String id = "";
		if (request.getAttribute("userId") != null) {
			id = request.getAttribute("userId").toString();
		} else if (StringUtil.isEmpty(id)) {
			String token = request.getHeader("token");
			if (StringUtil.isEmpty(token)) {
				return "";
			}
			try {
				id = TokenUtil.decryptToken(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * 获取校验码
	 * 
	 * @param mobile
	 * @return
	 */
	public static String getRandomCode(String mobile) {
		if (!USER.containsKey(mobile)) {
			USER.put(mobile, new Hashtable<String, String>());
		}
		// 随机４位数
		String code = RandomNumber.getRandomString(4, true);
		USER.get(mobile).put("code", code);
		return code;
	}

	/**
	 * 检查随机码
	 * 
	 * @param mobile
	 * @param code
	 * @return
	 */
	public static boolean checkRandomCode(String mobile, String code) {
		if (!USER.containsKey(mobile)) {
			USER.put(mobile, new Hashtable<String, String>());
		}
		if (code.equals(USER.get(mobile).get("code"))) {
			return true;
		}
		return false;
	}
}