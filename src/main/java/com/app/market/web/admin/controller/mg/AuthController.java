package com.app.market.web.admin.controller.mg;

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
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	/**
	 * 获取Token值.需要用户名/密码
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/user")
	@ResponseBody
	public Object user(String param, HttpServletRequest request) {
		logger.info("api.auth.user");
		Result ret = new Result();
		UserInfoDTO p = JsonUtil.parse(param, UserInfoDTO.class);
		ret.setData("");
		return ret;
	}

}
