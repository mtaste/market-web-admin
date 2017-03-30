package com.app.market.web.admin.controller.asset;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.app.market.dto.asset.AssetManageDTO;
import com.app.market.dto.asset.AssetRegisterDTO;
import com.app.market.dto.common.PageBean;
import com.app.market.dto.common.PageDTO;
import com.app.market.dto.user.SysUserDTO;
import com.app.market.service.asset.AssetManageService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;
import com.app.market.web.admin.controller.agent.AgentInfoController;

@Controller
@RequestMapping("/asset/manage/")
public class AssetManageController {
	private static final Logger logger = LoggerFactory.getLogger(AgentInfoController.class);
	@Reference(version = Version.NOW)
	private AssetManageService assetManageService;

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object list(String param, HttpServletRequest request) {
		logger.info("list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		AssetRegisterDTO p = JsonUtil.parse(param, AssetRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.assetManageService.getAssetList(p, page);
		ret.setData(r);
		return ret;
	}

	/**
	 * 选择用户
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("user-choose")
	@ResponseBody
	public Object userChoose(String param, HttpServletRequest request) {
		logger.info("list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		SysUserDTO p = JsonUtil.parse(param, SysUserDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.assetManageService.getAssetUserChooseList(p, page);
		ret.setData(r);
		return ret;
	}

	/**
	 * 借出
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("detail")
	@ResponseBody
	public Object detail(String param, HttpServletRequest request) {
		logger.info("detail");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		AssetRegisterDTO p = JsonUtil.parse(param, AssetRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.assetManageService.getAssetDetailList(p, page);
		ret.setData(r);
		return ret;
	}

	/**
	 * 借出功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("loan")
	@ResponseBody
	public Object loan(String param, HttpServletRequest request) {
		logger.info("loan");
		Result ret = new Result();
		AssetManageDTO p = JsonUtil.parse(param, AssetManageDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.assetManageService.assetLoan(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 归还列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("revert-detail")
	@ResponseBody
	public Object revertDetail(String param, HttpServletRequest request) {
		logger.info("detail");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		AssetRegisterDTO p = JsonUtil.parse(param, AssetRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.assetManageService.getAssetRevertDetailList(p, page);
		ret.setData(r);
		return ret;
	}

	/**
	 * 归还功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("revert")
	@ResponseBody
	public Object revert(String param, HttpServletRequest request) {
		logger.info("detail");
		Result ret = new Result();
		AssetManageDTO p = JsonUtil.parse(param, AssetManageDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.assetManageService.assetRevert(p);
		ret.setData(r);
		return ret;
	}
}
