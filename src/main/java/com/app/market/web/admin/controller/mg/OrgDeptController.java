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
import com.app.market.dto.sys.SysDeptDTO;
import com.app.market.dto.sys.SysRoleDTO;
import com.app.market.service.sys.OrgService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/org/dept")
public class OrgDeptController {
	private static final Logger logger = LoggerFactory.getLogger(OrgDeptController.class);
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
		String userId = Request.getUserId(request);
		List<Map<String, String>> r = this.orgService.getDeptList(userId);
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
	public Object save(String param, HttpServletRequest request) {
		logger.info("org.save");
		Result ret = new Result();
		SysDeptDTO p = JsonUtil.parse(param, SysDeptDTO.class);
		String userId = Request.getUserId(request);
		String r = this.orgService.saveDeptData(p, userId);
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
		SysDeptDTO p = JsonUtil.parse(param, SysDeptDTO.class);
		String r = this.orgService.removeDeptData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("role/list")
	@ResponseBody
	public Object roleList(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		SysRoleDTO p = JsonUtil.parse(param, SysRoleDTO.class);
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		PageBean<Map<String, String>> r = this.orgService.getDeptRoleList(page, p);
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
	@RequestMapping("role/save")
	@ResponseBody
	public Object roleSave(String param, HttpServletRequest request) {
		logger.info("org.save");
		Result ret = new Result();
		SysRoleDTO p = JsonUtil.parse(param, SysRoleDTO.class);
		String userId = Request.getUserId(request);
		String r = this.orgService.saveDeptRoleData(p, userId);
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
	@RequestMapping("role/remove")
	@ResponseBody
	public Object roleRemove(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		SysRoleDTO p = JsonUtil.parse(param, SysRoleDTO.class);
		String userId = Request.getUserId(request);
		String r = this.orgService.removeDeptRoleData(p, userId);
		ret.setData(r);
		return ret;
	}
}
