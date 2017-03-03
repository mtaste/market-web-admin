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
import com.app.market.dto.im.ImProductChangeDTO;
import com.app.market.dto.im.ImProductChangeDetailDTO;
import com.app.market.service.im.ProductService;
import com.app.market.support.dto.Result;
import com.app.market.support.util.JsonUtil;
import com.app.market.support.util.Request;
import com.app.market.support.util.Version;

@Controller
@RequestMapping("/im/product/")
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
	@RequestMapping("book-in/list")
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
	@RequestMapping("book-in/save")
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

	// =================================库存变更================================
	/**
	 * 获取列表
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("change/list")
	@ResponseBody
	public Object changeList(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.productService.getChangeList(page, p);
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
	@RequestMapping("change/save")
	@ResponseBody
	public Object changeSave(String param, HttpServletRequest request) {
		logger.info("org.save");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.saveChangeData(p);
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
	@RequestMapping("change/app")
	@ResponseBody
	public Object changeApp(String param, HttpServletRequest request) {
		logger.info("org.app");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.appChangeData(p);
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
	@RequestMapping("change/auth")
	@ResponseBody
	public Object changeAuth(String param, HttpServletRequest request) {
		logger.info("org.auth");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.authChangeData(p);
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
	@RequestMapping("change/reject")
	@ResponseBody
	public Object changeReject(String param, HttpServletRequest request) {
		logger.info("org.auth");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.rejectChangeData(p);
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
	@RequestMapping("change/remove")
	@ResponseBody
	public Object changeRemove(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String r = this.productService.removeChangeData(p);
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
	@RequestMapping("change/productList")
	@ResponseBody
	public Object changeProductList(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.productService.getChangeProductList(page, p);
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
	@RequestMapping("change/detail")
	@ResponseBody
	public Object changeDetail(String param, HttpServletRequest request) {
		logger.info("org.list");
		Result ret = new Result();
		PageDTO page = JsonUtil.parse(param, PageDTO.class);
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		PageBean<Map<String, String>> r = this.productService.changeDetail(page, p);
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
	@RequestMapping("change/removeDetail")
	@ResponseBody
	public Object changeRemoveDetail(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String r = this.productService.removeChangeDetail(p);
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
	@RequestMapping("change/addDetail")
	@ResponseBody
	public Object changeAddDetail(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		ImProductChangeDTO p = JsonUtil.parse(param, ImProductChangeDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.addChangeDetail(p);
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
	@RequestMapping("change/detailQty")
	@ResponseBody
	public Object changeDetailQty(String param, HttpServletRequest request) {
		logger.info("org.remove");
		Result ret = new Result();
		ImProductChangeDetailDTO p = JsonUtil.parse(param, ImProductChangeDetailDTO.class);
		String userId = Request.getUserId(request);
		p.setUpdateUser(userId);
		String r = this.productService.addChangeDetailQty(p);
		ret.setData(r);
		return ret;
	}
}
