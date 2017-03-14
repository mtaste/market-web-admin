package com.app.market.web.admin.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.app.market.dto.common.PageBean;
import com.app.market.dto.common.PageDTO;
import com.app.market.dto.sys.SysAuthDTO;
import com.app.market.dto.user.SysUserDTO;
import com.app.market.service.user.AuthService;
import com.app.market.service.user.UserService;
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
	@Reference(version = Version.NOW)
	private UserService userService;

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/auth/funcs")
	@ResponseBody
	public Object authFuncs(String param, HttpServletRequest request) {
		logger.info("user");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		SysAuthDTO p = JsonUtil.parse(param, SysAuthDTO.class);
		List<Map<String, String>> r = this.authService.getUserMenuFuncs(userId, p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/userInfo")
	@ResponseBody
	public Object userInfo(String param, HttpServletRequest request) {
		logger.info("user");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		Map<String, String> r = this.authService.getUserInfo(userId);
		ret.setData(r);
		return ret;
	}

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
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		String token = this.authService.authUser(p);
		ret.setData(token);
		return ret;
	}

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		String userId = Request.getUserId(request);
		p.setUserId(userId);
		PageBean<Map<String, String>> r = this.userService.getUserList(p, page);
		ret.setData(r);
		return ret;
	}

	/**
	 * 保存定义
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object Save(String param, HttpServletRequest request) {
		logger.info("org.save");
		Result ret = new Result();
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		String userId = Request.getUserId(request);
		p.setUserId(userId);
		String r = this.userService.saveUserData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 删除定义
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("remove")
	@ResponseBody
	public Object remove(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		String userId = Request.getUserId(request);
		p.setUserId(userId);
		String r = this.userService.removeUserData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public Object info(String param, HttpServletRequest request) {
		logger.info("user");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		Map<String, String> r = this.userService.getInfo(userId);
		ret.setData(r);
		return ret;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/change-info")
	@ResponseBody
	public Object changeInfo(String param, HttpServletRequest request) {
		logger.info("change info");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		p.setId(userId);
		String r = this.userService.changeInfo(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/change-password")
	@ResponseBody
	public Object changePassword(String param, HttpServletRequest request) {
		logger.info("changePassword");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		p.setId(userId);
		String r = this.userService.changePassWord(p);
		ret.setData(r);
		return ret;
	}
}
