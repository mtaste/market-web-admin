package com.app.market.web.admin.controller.im;

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
import com.app.market.dto.im.ImProductInfoDTO;
import com.app.market.service.im.ProductService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/im/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Reference(version = Version.NOW)
	private ProductService productService;

	/**
	 * 获取菜单功能
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/book-in/list")
	@ResponseBody
	public Object list(String param, HttpServletRequest request) {
		logger.info("crm.cus.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		ImProductInfoDTO p = JsonUtil.parse(param, ImProductInfoDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.productService.getList(p, page);
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
	@RequestMapping("/book-in/save")
	@ResponseBody
	public Object Save(String param, HttpServletRequest request) {
		logger.info("crm.cus.save");
		Result ret = new Result();
		ImProductInfoDTO p = JsonUtil.parse(param, ImProductInfoDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.saveData(p);
		ret.setData(r);
		return ret;
	}

}
