package com.framework.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.entity.serviceconsultBean;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.CompanyGovService;

@Controller
@RequestMapping("CompanyGovController")
public class CompanyGovController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyGovController.class);
	
	@Resource
	private CompanyGovService companyGovService;
	
	
	
	
	
	/**
	 * 获取企业咨询信息的列表(不需登录)
	 * 
	 */
	@RequestMapping(value="/getconsultlist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getconsultlist(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取企业咨询信息的列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		args.put("num", num);
		args.put("category", request.getParameter("category"));
		args.put("page", request.getParameter("page"));
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.companyGovService.getconsultlist(args);
			int totalCount = this.companyGovService.getconsultlistNum(args);
			if(list.size() >0){
				map.put("resultCode", 0);
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取企业咨询信息的列表异常",e);
		}
		logger.info("获取企业咨询信息的列表end---------------");
		return map;
	}
	
	/**
	 * 获取企业咨询详情(不需登录)
	 * 
	 */
	@RequestMapping(value="/consultdetail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object consultdetail(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取企业咨询详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", request.getParameter("id"));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.companyGovService.consultdetail(args);
			if(list.size() > 0){
				this.companyGovService.updateconsultCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取企业咨询详情异常",e);
		}
		logger.info("获取企业咨询详情begin---------------");
		return map;
	}
	
	
	/**
	 * 咨询企业治理相关服务
	 * 
	 */
	@RequestMapping(value="/companyconsult",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object companyconsult(HttpServletRequest request,@ModelAttribute("bean")serviceconsultBean bean) {
		logger.info("咨询企业治理相关服务begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("category", bean.getCategory());
		args.put("company", bean.getCompany());
		args.put("industry", bean.getIndustry());
		args.put("name", bean.getName());
		args.put("mobile", bean.getMobile());
		args.put("content", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("title", bean.getTitle());
		args.put("user_id", this.getUserId());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.companyGovService.companyconsult(args);
			if(a>0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -6);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("咨询企业治理相关服务异常",e);
		}
		logger.info("咨询企业治理相关服务end---------------");
		return map;
	}
	
	
	
	/**
	 * 发布企业治理咨询讨论
	 * 
	 */
	@RequestMapping(value="/pubcompanyconsultlog",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object pubcompanyconsultlog(HttpServletRequest request,@ModelAttribute("bean")serviceconsultBean bean) {
		logger.info("发布企业治理咨询讨论begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("consult_id", bean.getId());
		args.put("content", bean.getContent());
		args.put("user_id", this.getUserId());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.companyGovService.pubcompanyconsultlog(args);
			if(a>0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -6);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("发布企业治理咨询讨论异常",e);
		}
		logger.info("发布企业治理咨询讨论end---------------");
		return map;
	}
	
	
	/**
	 * 查看咨询讨论的详情信息
	 * 
	 */
	@RequestMapping(value="/getcompanyconsultlog",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getcompanyconsultlog(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("查看咨询讨论的详情信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		args.put("consult_id", request.getParameter("id"));
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		args.put("num", num);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.companyGovService.getcompanyconsultlog(args);
			if(list.size() >0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看咨询讨论的详情信息异常",e);
		}
		logger.info("查看咨询讨论的详情信息end---------------");
		return map;
	}
	

}
