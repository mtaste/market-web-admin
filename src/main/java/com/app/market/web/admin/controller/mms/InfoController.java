package com.app.market.web.admin.controller.mms;

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
import com.app.market.dto.mms.MmsInfoDTO;
import com.app.market.dto.mms.MmsPointsChangeDTO;
import com.app.market.dto.mms.MmsPointsChangeDetailDTO;
import com.app.market.service.mms.MmsService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/mms/info/")
public class InfoController {
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	@Reference(version = Version.NOW)
	private MmsService mmsService;

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
		logger.info("mms.info.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MmsInfoDTO p = JsonUtil.parse(param, MmsInfoDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.mmsService.getInfoList(p, page);
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
		logger.info("mms.info.save");
		Result ret = new Result();
		MmsInfoDTO p = JsonUtil.parse(param, MmsInfoDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.saveInfoData(p);
		ret.setData(r);
		return ret;
	}

	// =================================积分变更================================
	/**
	 * 获取列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("points-change/list")
	@ResponseBody
	public Object pointsChangeList(String param, HttpServletRequest request) {
		logger.info("points-change.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.mmsService.getPointsChangeList(page, p);
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
	@RequestMapping("points-change/save")
	@ResponseBody
	public Object pointsChangeSave(String param, HttpServletRequest request) {
		logger.info("org.save");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.savePointsChangeData(p);
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
	@RequestMapping("points-change/remove")
	@ResponseBody
	public Object pointsChangeRemove(String param, HttpServletRequest request) {
		logger.info("points-change.remove");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String r = this.mmsService.removePointsChangeData(p);
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
	@RequestMapping("points-change/app")
	@ResponseBody
	public Object pointsChangeApp(String param, HttpServletRequest request) {
		logger.info("points-change.app");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.appPointsChangeData(p);
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
	@RequestMapping("points-change/auth")
	@ResponseBody
	public Object changeAuth(String param, HttpServletRequest request) {
		logger.info("points-change.auth");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.authPointsChangeData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 否决
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("points-change/reject")
	@ResponseBody
	public Object pointsChangeReject(String param, HttpServletRequest request) {
		logger.info("points-change.auth");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.rejectPointsChangeData(p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取明细列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("points-change/detail")
	@ResponseBody
	public Object changeDetail(String param, HttpServletRequest request) {
		logger.info("points-change.detail");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.mmsService.pointsChangeDetail(page, p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 获取产品明细列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("points-change/memberList")
	@ResponseBody
	public Object pointsChangeMemberList(String param, HttpServletRequest request) {
		logger.info("points-change.memberList");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.mmsService.getPointsChangeMemberList(page, p);
		ret.setData(r);
		return ret;
	}

	/**
	 * 修改明细数量
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("points-change/detailPoints")
	@ResponseBody
	public Object pointsChangeDetailPoints(String param, HttpServletRequest request) {
		logger.info("points-change.detailPoints");
		Result ret = new Result();
		MmsPointsChangeDetailDTO p = JsonUtil.parse(param, MmsPointsChangeDetailDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.addPointsChangeDetailPoints(p);
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
	@RequestMapping("points-change/removeDetail")
	@ResponseBody
	public Object changeRemoveDetail(String param, HttpServletRequest request) {
		logger.info("points-change.removeDetail");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String r = this.mmsService.removePointsChangeDetail(p);
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
	@RequestMapping("points-change/addDetail")
	@ResponseBody
	public Object changeAddDetail(String param, HttpServletRequest request) {
		logger.info("points-change.addDetail");
		Result ret = new Result();
		MmsPointsChangeDTO p = JsonUtil.parse(param, MmsPointsChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.mmsService.addPointsChangeDetail(p);
		ret.setData(r);
		return ret;
	}

}
