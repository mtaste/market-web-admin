package com.app.market.web.admin.controller.mg;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.market.dto.user.UserInfoDTO;
import com.app.market.support.dto.Result;
import com.app.market.support.util.TokenUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
		UserInfoDTO p = new UserInfoDTO();
		ret.setData(p);
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
		String token = "";
		try {
			token = TokenUtil.encryptToken("userName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ret.setData(token);
		return ret;
	}
}
