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
import com.app.market.dto.oa.OaAttendanceDTO;
import com.app.market.service.oa.OaAttendanceService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;
import com.app.market.web.admin.controller.agent.AgentInfoController;

@Controller
@RequestMapping("/oa/attendance/")
public class AttendanceController {
	private static final Logger logger = LoggerFactory.getLogger(AgentInfoController.class);
	@Reference(version = Version.NOW)
	private OaAttendanceService oaAttendanceService;

	/**
	 * 今天考勤数据
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("today")
	@ResponseBody
	public Object today(String param, HttpServletRequest request) {
		logger.info("oa.attendance.today");
		Result ret = new Result();
		OaAttendanceDTO p = JsonUtil.parse(param, OaAttendanceDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		Map<String, String> r = this.oaAttendanceService.getTodayInfo(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object list(String param, HttpServletRequest request) {
		logger.info("oa.attendance.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		OaAttendanceDTO p = JsonUtil.parse(param, OaAttendanceDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.oaAttendanceService.getAttendanceList(p, page);
		ret.setData(r);
		return ret;
	}

	/**
	 * 签到
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("signIn")
	@ResponseBody
	public Object signIn(String param, HttpServletRequest request) {
		logger.info("oa.attendance.signIn");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		String r = this.oaAttendanceService.signIn(userId);
		ret.setData(r);
		return ret;
	}

	/**
	 * 签出
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("signOut")
	@ResponseBody
	public Object signOut(String param, HttpServletRequest request) {
		logger.info("oa.attendance.signOut");
		Result ret = new Result();
		String userId = Request.getUserId(request);
		String r = this.oaAttendanceService.signOut(userId);
		ret.setData(r);
		return ret;
	}
}
