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
import com.app.market.dto.common.PageBean;
import com.app.market.dto.common.PageDTO;
import com.app.market.dto.mg.MgOrgAuthDTO;
import com.app.market.dto.mg.MgOrgRegisterDTO;
import com.app.market.service.mg.MgOrgService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
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
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.saveOrgData(p);
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
	@RequestMapping("register/app")
	@ResponseBody
	public Object registerApp(String param, HttpServletRequest request) {
		logger.info("org.app");
		Result ret = new Result();
		MgOrgRegisterDTO p = JsonUtil.parse(param, MgOrgRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.appRegisterData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 审核功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("register/auth")
	@ResponseBody
	public Object registerAuth(String param, HttpServletRequest request) {
		logger.info("org.auth");
		Result ret = new Result();
		MgOrgRegisterDTO p = JsonUtil.parse(param, MgOrgRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.authRegisterData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 否决功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("register/reject")
	@ResponseBody
	public Object registerReject(String param, HttpServletRequest request) {
		logger.info("org.auth");
		Result ret = new Result();
		MgOrgRegisterDTO p = JsonUtil.parse(param, MgOrgRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.rejectRegisterData(p);
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

	// ========================授权请求
	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("authBill/list")
	@ResponseBody
	public Object authBillList(String param, HttpServletRequest request) {
		logger.info("org.authBill list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.mgOrgService.getOrgAuthBillList(page, p);
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
	@RequestMapping("authBill/save")
	@ResponseBody
	public Object authBillSave(String param, HttpServletRequest request) {
		logger.info("org.authBill save");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.saveOrgAuthBillData(p);
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
	@RequestMapping("authBill/app")
	@ResponseBody
	public Object authBillApp(String param, HttpServletRequest request) {
		logger.info("org.authBill app");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.appAuthBillData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 审核功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("authBill/auth")
	@ResponseBody
	public Object authBillAuth(String param, HttpServletRequest request) {
		logger.info("org.authBill auth");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.authAuthBillData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 否决功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("authBill/reject")
	@ResponseBody
	public Object authBillReject(String param, HttpServletRequest request) {
		logger.info("org.authBill auth");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mgOrgService.rejectAuthBillData(p);
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
	@RequestMapping("authBill/remove")
	@ResponseBody
	public Object authBillRemove(String param, HttpServletRequest request) {
		logger.info("org.authBill remove");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		String r = this.mgOrgService.removeOrgAuthBillData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 可授权列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("authBill/authList")
	@ResponseBody
	public Object authBillAuthList(String param, HttpServletRequest request) {
		logger.info("org.authBill authList");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		List<Map<String, String>> r = this.mgOrgService.getAuthBillAuthList(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 机构选择列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("authBill/orgList")
	@ResponseBody
	public Object authBillOrgList(String param, HttpServletRequest request) {
		logger.info("org.authBill orgList");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		PageBean<Map<String, String>> r = this.mgOrgService.getOrgAuthOrgList(page, p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 单据明细
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("authBill/detail")
	@ResponseBody
	public Object authBillDetail(String param, HttpServletRequest request) {
		logger.info("org.authBill detail");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		List<Map<String, String>> r = this.mgOrgService.getAuthBillDetail(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取机构权限树
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("auths")
	@ResponseBody
	public Object auths(String param, HttpServletRequest request) {
		logger.info("org.authBill detail");
		Result ret = new Result();
		MgOrgAuthDTO p = JsonUtil.parse(param, MgOrgAuthDTO.class);
		List<Map<String, String>> r = this.mgOrgService.getAuths(p);
		ret.setData(r);
		return ret;
	}

}
