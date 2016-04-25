package com.framework.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.CompanyGovService;
import com.framework.web.service.VentureHouseService;

@Controller
@RequestMapping("VentureHouseController")
public class VentureHouseController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(VentureHouseController.class);
	
	@Resource
	private VentureHouseService ventureHouseService;
	
	
	/**
	 * 获取导师标签
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getmentortags",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentortags(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取导师标签begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getmentortags(args);
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
			logger.error("获取导师标签异常",e);
		}
		logger.info("获取导师标签end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取创业导师信息的列表(不需登录)
	 * 
	 */
	@RequestMapping(value="/getmentorlist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentorlist(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取创业导师信息的列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		args.put("tagId", request.getParameter("tagId"));
		//args.put("page", request.getParameter("page"));
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getmentorlist(args);
			int totalCount = this.ventureHouseService.getmentorlistNum(args);
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
			logger.error("获取创业导师信息的列表异常",e);
		}
		logger.info("获取创业导师信息的列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取创业导师详情(不需登录)
	 * 
	 */
	@RequestMapping(value="/getmentordetail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentordetail(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取创业导师详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		args.put("mentor_id", id);
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		String hire = "0";
		try {
			String user_id = this.getUserId();
			if(user_id != null){
				list2 = (List<Object>) this.ventureHouseService.selectchoosementor(args);
				if(list2.size() > 0){//说明已经聘用
					hire = "1";
				}
			}
			Map<String, Object> mentorMap = (Map<String, Object>) this.ventureHouseService.getmentordetail(args);
			list1 = (List<Object>) this.ventureHouseService.selectMentorTags(args);
			if(!mentorMap.isEmpty()){
				mentorMap.put("tags", list1);
				mentorMap.put("hire", hire);
				this.ventureHouseService.updateTeacherCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", mentorMap);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取创业导师详情异常",e);
		}
		logger.info("获取创业导师详情end---------------");
		return map;
	}
	
	
	/**
	 * 咨询创业相关问题
	 * 
	 */
	@RequestMapping(value="/startupconsult",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object startupconsult(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取创业导师详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("mentor_id", bean.getId());
		args.put("title", bean.getTitle());
		args.put("content", bean.getContent());
		args.put("authority", bean.getAuthority());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.ventureHouseService.startupconsult(args);
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
			logger.error("获取创业导师详情异常",e);
		}
		logger.info("获取创业导师详情end---------------");
		return map;
	}
	
	
	/**
	 * 查看咨询的信息列表(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/getstartupconsultlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getstartupconsultlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看咨询的信息列表begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		String mentorId = request.getParameter("mentorId");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		// args.put("page", request.getParameter("page"));
		//args.put("user_id", this.getUserId());
		args.put("num", num);
		args.put("mentorId", mentorId);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getstartupconsultlist(args);
			int totalCount = this.ventureHouseService.getstartupconsultlistNum(args);
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看咨询的信息列表异常",e);
		}
		logger.info("查看咨询的信息列表end---------------");
		return map;
	}
	
	
	/**
	 * 查看申请的详情信息(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/getstartupconsultdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getstartupconsultdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看申请的详情信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getstartupconsultdetail(args);
			if (list.size() > 0) {
				int a = this.ventureHouseService.updateStartupConsultCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看申请的详情信息异常",e);
		}
		logger.info("查看申请的详情信息end---------------");
		return map;
	}
	
	
	/**
	 * 发布咨询信息讨论(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/pubstartupconsultlog", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubstartupconsultlog(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("发布咨询信息讨论begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		args.put("consult_id", bean.getId());
		args.put("message", bean.getContent());
		args.put("user_id", this.getUserId());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.ventureHouseService.updateStartUpConsultLog(args);
			if (a > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("发布咨询信息讨论异常",e);
		}
		logger.info("发布咨询信息讨论end---------------");
		return map;
	}
	
	
	
	/**
	 * 查看咨询信息讨论(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/getstartupconsultlog", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getstartupconsultlog(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看咨询信息讨论begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("consult_id", request.getParameter("id"));
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getstartupconsultlog(args);
			int totalCount = this.ventureHouseService.getstartupconsultlogNum(args);
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看咨询信息讨论异常",e);
		}
		logger.info("查看咨询信息讨论end---------------");
		return map;
	}
	
	
	/**
	 * 聘用创业导师
	 * 
	 */
	@RequestMapping(value="/choosementor",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object choosementor(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("聘用创业导师begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("mentor_id", bean.getId());
		args.put("user_id", this.getUserId());
		args.put("company", bean.getCompany());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		try {
			list1 = (List<Object>) this.ventureHouseService.selectchoosementor(args);
			if(list1.size() > 0){//说明已经聘用过
				map.put("resultCode", -20);
				map.put("resultMsg", "您已聘用过该导师");
				map.put("resultData", list);
			}else{
			int a = this.ventureHouseService.choosementor(args);
			if(a>0){
				this.ventureHouseService.updateChooseTeacherNum(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -6);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
			}
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("聘用创业导师异常",e);
		}
		logger.info("聘用创业导师end---------------");
		return map;
	}
	
	
	/**
	 * 导师评价
	 * 
	 */
	@RequestMapping(value="/commentmentor",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object commentmentor(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("导师评价begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("mentor_id", bean.getId());
		args.put("content", bean.getContent());
		args.put("user_id", this.getUserId());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.ventureHouseService.evaluatementor(args);
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
			logger.error("导师评价异常",e);
		}
		logger.info("导师评价end---------------");
		return map;
	}
	
	
	/**
	 * 获取创业导师信息的列表(不需登录)
	 * 
	 */
	@RequestMapping(value="/getmentorcomment",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentorcomment(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取创业导师信息的列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		args.put("id", request.getParameter("id"));
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getmentorcomment(args);
			int totalCount = this.ventureHouseService.getmentorcommentNum(args);
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
			logger.error("获取创业导师信息的列表异常",e);
		}
		logger.info("获取创业导师信息的列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取产业基地信息的列表(不需登录)
	 * 
	 */
	@RequestMapping(value="/getindustrialbaselist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getindustrialbaselist(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取产业基地信息的列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 6;
		String page = request.getParameter("page");
		String city = request.getParameter("city");
		String industry = request.getParameter("industry");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		if("".equals(city) || city == null){
			args.put("city", null);
		}else{
			args.put("city", city.substring(0, 4));
		}
		
		args.put("industry", industry);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getIndustrialbaselist(args);
			int totalCount = this.ventureHouseService.getIndustrialbaselistNum(args);
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
			logger.error("获取产业基地信息的列表异常",e);
		}
		logger.info("获取产业基地信息的列表end---------------");
		return map;
	}
	
	
	/**
	 * 获取产业基地列表详情
	 * 
	 */
	@RequestMapping(value = "/getindustrialbasedetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getindustrialbasedetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("获取产业基地列表详情begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		Map<String, String> map1 = null;
		Map<String, String> map2 = null;
		
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		try {
//			ArrayList<Object> lista = (ArrayList<Object>) this.ventureHouseService.getIndustrialbasedetailaaa(args);
			
			HashMap<String, Object> testMap = (HashMap<String, Object>) this.ventureHouseService.getIndustrialbasedetail(args);
			HashMap<String, Object> testMap1 = (HashMap<String, Object>) this.ventureHouseService.getIndustrialbasedetail1(args);
			String slideimg = (String) testMap1.get("slideimg");
			String productionimg = (String) testMap1.get("productionimg");
//			System.out.println(slideimg);
//			System.out.println(productionimg);
			String[] c = slideimg.split(",");
			String[] b = productionimg.split(",");
			for(int i=0;i<c.length;i++){
				map1 = new HashMap<>();
				map1.put("img", c[i]);
				list1.add(map1);
			}
			for(int i=0;i<b.length;i++){
				map2 = new HashMap<>();
				map2.put("img", b[i]);
				list2.add(map2);
			}
			if (!testMap.isEmpty()) {
				testMap.put("slideimg", list1);
				testMap.put("productionimg", list2);
				int a = this.ventureHouseService.updateIndustrialbaseCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", testMap);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取产业基地列表详情异常",e);
		}
		logger.info("获取产业基地列表详情end---------------");
		return map;
	}
	
	
	

}
