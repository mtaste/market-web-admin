package com.app.market.web.admin.controller.oa;

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
import com.app.market.dto.oa.OaNoticeDTO;
import com.app.market.service.oa.OaNoticeService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;
import com.app.market.web.admin.controller.agent.AgentInfoController;

@Controller
@RequestMapping("/oa/notice/")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(AgentInfoController.class);
	@Reference(version = Version.NOW)
	private OaNoticeService oaNoticeService;

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
		logger.info("oa.notice.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		OaNoticeDTO p = JsonUtil.parse(param, OaNoticeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.oaNoticeService.getNoticeList(p, page);
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
		logger.info("oa.notice.save");
		Result ret = new Result();
		OaNoticeDTO p = JsonUtil.parse(param, OaNoticeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.oaNoticeService.saveNoticeData(p);
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
	public Object pointsChangeApp(String param, HttpServletRequest request) {
		logger.info("points-change.app");
		Result ret = new Result();
		OaNoticeDTO p = JsonUtil.parse(param, OaNoticeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.oaNoticeService.appNoticeData(p);
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
	public Object pointsChangeRemove(String param, HttpServletRequest request) {
		logger.info("points-change.remove");
		Result ret = new Result();
		OaNoticeDTO p = JsonUtil.parse(param, OaNoticeDTO.class);
		String r = this.oaNoticeService.removeNoticeData(p);
		ret.setData(r);
		return ret;
	}

}
