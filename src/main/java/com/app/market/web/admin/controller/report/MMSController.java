package com.app.market.web.admin.controller.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.app.market.dto.report.MmsDTO;
import com.app.market.service.report.ReportMMSService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/report/mms/")
public class MMSController {
	private static final Logger logger = LoggerFactory.getLogger(MMSController.class);
	@Reference(version = Version.NOW)
	private ReportMMSService reportMMSService;

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("info")
	@ResponseBody
	public Object info(String param, HttpServletRequest request) {
		logger.info("report.mms.info");
		Result ret = new Result();
		MmsDTO p = JsonUtil.parse(param, MmsDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		List<Map<String, String>> r = this.reportMMSService.getInfo(p);
		ret.setData(r);
		return ret;
	}

}
