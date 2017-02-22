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
import com.app.market.dto.sys.SysAuthDTO;
import com.app.market.service.common.CrudService;
import com.app.market.service.user.AuthService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/auth/")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Reference(version = Version.NOW)
	private AuthService authService;
	@Reference(version = Version.NOW)
	private CrudService crudService;

	/**
	 * 权限定义列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("define/list")
	@ResponseBody
	public Object defineList(String param, HttpServletRequest request) {
		logger.info("auth.define.list");
		Result ret = new Result();
		List<Map<String, String>> r = this.authService.getAuthDefineList();
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
	@RequestMapping("define/save")
	@ResponseBody
	public Object defineSave(String param, HttpServletRequest request) {
		logger.info("auth.define.save");
		Result ret = new Result();
		SysAuthDTO p = JsonUtil.parse(param, SysAuthDTO.class);
		String r = this.authService.saveDefineData(p);
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
	@RequestMapping("define/remove")
	@ResponseBody
	public Object defineRemove(String param, HttpServletRequest request) {
		logger.info("auth.define.remove");
		Result ret = new Result();
		SysAuthDTO p = JsonUtil.parse(param, SysAuthDTO.class);
		String r = this.authService.removeDefineData(p);
		ret.setData(r);
		return ret;
	}

}
