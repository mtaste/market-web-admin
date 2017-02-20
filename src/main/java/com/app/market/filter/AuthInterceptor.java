package com.app.market.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.TokenUtil;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private String[] IGNORE_URI = {};
	private static Map<String, String> map = null;

	public String[] getIGNORE_URI() {
		return IGNORE_URI;
	}

	public void setIGNORE_URI(String[] iGNORE_URI) {
		IGNORE_URI = iGNORE_URI;
		if (map == null) {
			map = new Hashtable<String, String>();
			for (String s : IGNORE_URI) {
				map.put(s, s);
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().replace(request.getContextPath(), "");
		if (map.containsKey(url)) {
			return true;
		}
		// 需要登录后才能操作,检查token,如果token不对,则没有登录.
		// 获取token
		boolean isToken = checkToken(request);
		if (isToken) {
			return true;
		}
		Result ret = new Result();
		ret.setCode(-401);
		resJson(response, ret);
		return false;
	}

	/**
	 * 检查Token值 ,解析获取userId
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkToken(HttpServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		// 如果是权限资源，则处理，获取token
		String token = req.getHeader("token");
		// 页面跳转的时候，使用session获取token;
		if (StringUtils.isBlank(token)) {
			// 获取session的token;
			Object obj = req.getSession().getAttribute("token");
			if (obj != null) {
				token = obj.toString();
			}
		}
		if (StringUtils.isBlank(token)) {
			return false;
		}
		// 有token，需要校验token;
		try {
			// token解析错误
			String userId = TokenUtil.decryptToken(token);
			if (!StringUtils.isBlank(userId)) {
				req.setAttribute("userId", userId);
				req.getSession().setAttribute("token", token);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void resJson(HttpServletResponse response, Object ret) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(JsonUtil.toJson(ret));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}