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
import com.app.market.dto.sys.SysOrgAuthDTO;
import com.app.market.dto.sys.SysOrgDTO;
import com.app.market.service.sys.OrgService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Version;
import com.app.market.web.admin.controller.mg.MgOrgController;

@Controller
@RequestMapping("/org")
public class OrgController {
	private static final Logger logger = LoggerFactory.getLogger(MgOrgController.class);
	@Reference(version = Version.NOW)
	private OrgService orgService;

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
		SysOrgDTO p = JsonUtil.parse(param, SysOrgDTO.class);
		PageBean<Map<String, String>> r = this.orgService.getOrgList(page, p);
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
		SysOrgDTO p = JsonUtil.parse(param, SysOrgDTO.class);
		String r = this.orgService.saveOrgData(p);
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
		SysOrgDTO p = JsonUtil.parse(param, SysOrgDTO.class);
		String r = this.orgService.removeOrgData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取机构权限信息
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/auth")
	@ResponseBody
	public Object auth(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		SysOrgAuthDTO p = JsonUtil.parse(param, SysOrgAuthDTO.class);
		List<Map<String, String>> r = this.orgService.getOrgAuthList(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取机构权限信息
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/auth/save")
	@ResponseBody
	public Object authSave(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		SysOrgAuthDTO p = JsonUtil.parse(param, SysOrgAuthDTO.class);
		String r = this.orgService.saveOrgAuth(p);
		ret.setData(r);
		return ret;
	}
}
