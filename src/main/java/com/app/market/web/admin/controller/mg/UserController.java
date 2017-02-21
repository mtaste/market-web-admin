package com.app.market.web.admin.controller.mg;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.app.market.dto.user.UserInfoDTO;
import com.app.market.service.user.AuthService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Reference(version = Version.NOW)
	private AuthService authService;

	/**
	 * 获取菜单
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/userMenu")
	@ResponseBody
	public Object user(String param, HttpServletRequest request) {
		logger.info("user");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		List<Map<String, String>> r = this.authService.getUserMenus(userId);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取Token值.需要用户名/密码
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String param, HttpServletRequest request) {
		logger.info("user");
		Result ret = new Result();
		UserInfoDTO p = JsonUtil.parse(param, UserInfoDTO.class);
		String token = this.authService.authUser(p);
		ret.setData(token);
		return ret;
	}
}
