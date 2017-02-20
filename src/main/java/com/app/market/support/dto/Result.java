package com.app.market.support.dto;

import com.app.market.support.util.MsgUtil;

import jodd.util.StringUtil;

/**
 * 返回类
 * 
 * @author LJF
 *
 */
public class Result {
	private Integer code = 1;
	private String msg;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		String r = MsgUtil.getMsg(code.toString());
		if (!StringUtil.isEmpty(r)) {
			this.msg = r;
		}
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	/**
	 * 设置返回对象，可以判断返回对象是否争取。
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		// 字符串才判断
		if (data instanceof String) {
			String r = MsgUtil.getMsg(data.toString());
			if (!StringUtil.isBlank(r)) {
				this.msg = r;
				this.code = Integer.valueOf(data.toString());
				data = null;
			}
		}
		this.data = data;
	}

}
