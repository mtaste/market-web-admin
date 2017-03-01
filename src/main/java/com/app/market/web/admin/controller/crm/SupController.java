package com.app.market.web.admin.controller.crm;

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
import com.app.market.dto.crm.CrmSupInfoDTO;
import com.app.market.service.crm.SupService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/crm/sup")
public class SupController {
	private static final Logger logger = LoggerFactory.getLogger(SupController.class);
	@Reference(version = Version.NOW)
	private SupService supService;

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
		logger.info("crm.cus.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		CrmSupInfoDTO p = JsonUtil.parse(param, CrmSupInfoDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.supService.getSupList(p, page);
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
	@RequestMapping("/save")
	@ResponseBody
	public Object Save(String param, HttpServletRequest request) {
		logger.info("crm.cus.save");
		Result ret = new Result();
		CrmSupInfoDTO p = JsonUtil.parse(param, CrmSupInfoDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.supService.saveSupData(p);
		ret.setData(r);
		return ret;
	}

}
