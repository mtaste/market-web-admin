package com.app.market.web.admin.controller.mg;

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
import com.app.market.dto.mg.MgOrgRegisterDTO;
import com.app.market.service.mg.MgOrgService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("mg/org/")
public class MgOrgController {
	private static final Logger logger = LoggerFactory.getLogger(MgOrgController.class);
	@Reference(version = Version.NOW)
	private MgOrgService mgOrgService;

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("register/list")
	@ResponseBody
	public Object registerList(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MgOrgRegisterDTO p = JsonUtil.parse(param, MgOrgRegisterDTO.class);
		PageBean<Map<String, String>> r = this.mgOrgService.getOrgList(page, p);
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
	@RequestMapping("register/save")
	@ResponseBody
	public Object registerSave(String param, HttpServletRequest request) {
		logger.info("org.save");
		Result ret = new Result();
		MgOrgRegisterDTO p = JsonUtil.parse(param, MgOrgRegisterDTO.class);
		String r = this.mgOrgService.saveOrgData(p);
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
	@RequestMapping("register/remove")
	@ResponseBody
	public Object registerRemove(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		MgOrgRegisterDTO p = JsonUtil.parse(param, MgOrgRegisterDTO.class);
		String r = this.mgOrgService.removeOrgData(p);
		ret.setData(r);
		return ret;
	}

}