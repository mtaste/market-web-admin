package com.app.market.web.admin.controller.asset;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.app.market.dto.asset.AssetRegisterDTO;
import com.app.market.dto.common.PageBean;
import com.app.market.dto.common.PageDTO;
import com.app.market.service.asset.AssetCheckInService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;
import com.app.market.web.admin.controller.agent.AgentInfoController;

@Controller
@RequestMapping("/asset/check-in/")
public class AssetCheckInController {
	private static final Logger logger = LoggerFactory.getLogger(AgentInfoController.class);
	@Reference(version = Version.NOW)
	private AssetCheckInService assetCheckInService;

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
		PageBean<Map<String, String>> r = this.assetCheckInService.getCheckInList(p, page);
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
		logger.info("save");
		Result ret = new Result();
		AssetRegisterDTO p = JsonUtil.parse(param, AssetRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.assetCheckInService.saveCheckInData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 提交
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("app")
	@ResponseBody
	public Object app(String param, HttpServletRequest request) {
		logger.info("app");
		Result ret = new Result();
		AssetRegisterDTO p = JsonUtil.parse(param, AssetRegisterDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.assetCheckInService.appCheckInData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("remove")
	@ResponseBody
	public Object remove(String param, HttpServletRequest request) {
		logger.info("remove");
		Result ret = new Result();
		AssetRegisterDTO p = JsonUtil.parse(param, AssetRegisterDTO.class);
		String r = this.assetCheckInService.removeCheckInData(p);
		ret.setData(r);
		return ret;
	}

}
