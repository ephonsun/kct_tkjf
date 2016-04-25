package com.framework.web.mngcontroller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.MngPoJo;
import com.framework.core.entity.PoJo;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.MngDishonestyEService;

@Controller
@RequestMapping("MngDishonestyEController")
public class MngDishonestyEController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(MngDishonestyEController.class);
	
	@Resource
	private MngDishonestyEService mngDishonestyEService;
	
	
	/**
	 * 失信信息发布
	 * 
	 */
	@RequestMapping(value="/pubdishonesty",method=RequestMethod.POST)
	@ResponseBody
	public Object pubdishonesty(HttpServletRequest request,ModelMap model,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String lx = pojo.getLx();//lx
		if("0".equals(lx)){
			args.put("name", pojo.getName());
			args.put("gender", pojo.getGender());
			args.put("age", pojo.getAge());
			args.put("idCardNumber", pojo.getIdCardNumber());
			args.put("area", pojo.getArea());
			args.put("amount", pojo.getAmount());
			args.put("publisher", pojo.getPublisher());
			args.put("department", pojo.getDepartment());
		}else {
			args.put("corporation", pojo.getCorporation());
			args.put("type", pojo.getType());
			args.put("code", pojo.getCode());
			args.put("name", pojo.getName());
			args.put("amount", pojo.getAmount());
			args.put("publisher", pojo.getPublisher());
		}
		args.put("lx", lx);
		try {
			int a = this.mngDishonestyEService.pubdishonesty(args);//查询用户填写的号码有没有注册过
			if(a > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
				//response.getWriter().write(JSON.toJSONString(object));
			}
			
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * 失信信息列表
	 * 
	 */
	@RequestMapping(value="/selectdislist",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public ModelAndView selectdislist(HttpServletRequest request,ModelMap model,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-list");
		String page = request.getParameter("user_page");//page
		String lx = request.getParameter("lx");//page
		String curPageNum = "";
		if("".equals(page) || page == null){
			page = "1";
			curPageNum = "1";
		}else{
			curPageNum = page;
		}
		int num = 20;
		args.put("num", num);
		args.put("lx", lx);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.mngDishonestyEService.selectdislist(args);
			int totalCount = this.mngDishonestyEService.selectdislistNum(args);
			int totalPageNum = (totalCount  +  num  - 1) / num;
			if(list.size() > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
				map.put("totalCount", totalCount);
				map.put("totalPageNum", totalPageNum);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
				//response.getWriter().write(JSON.toJSONString(object));
			}
			
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		mav.addObject("map", map);
		mav.addObject("totalPageNum", map.get("totalPageNum"));
		mav.addObject("curPageNum", curPageNum);
		mav.addObject("lx", lx);
		return mav;
	}
	
	
	
	/**
	 * 失信曝光详情跳转到修改页面
	 * 
	 */
	@RequestMapping(value="/getdisdetail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getdisdetail(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		//logger.info("获取创业导师详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		String dslx = request.getParameter("dslx");
		args.put("id", id);
		args.put("dslx", dslx);
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		try {
			String user_id = this.getUserId();
			Map<String, Object> mentorMap = (Map<String, Object>) this.mngDishonestyEService.getdisdetail(args);
			if(!mentorMap.isEmpty()){
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
			//logger.error("获取创业导师详情异常",e);
		}
		//logger.info("获取创业导师详情end---------------");
		return map;
	}
	
	
	/**
	 * 失信曝光修改
	 * 
	 */
	@RequestMapping(value="/dis_update",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ModelAndView dis_update(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-sh");
		String dslx = pojo.getType();
		if("0".equals(dslx)){
			args.put("id", pojo.getId());
			args.put("name", pojo.getName());
			args.put("gender", pojo.getGender());
			args.put("age", pojo.getAge());
			args.put("idCardNumber", pojo.getIdCardNumber());
			args.put("area", pojo.getArea());
			args.put("amount", pojo.getAmount());
			args.put("publisher", pojo.getPublisher());
			args.put("department", pojo.getDepartment());
		}else {
			args.put("id", pojo.getId());
			args.put("corporation", pojo.getCorporation());
			args.put("type", pojo.getType());
			args.put("code", pojo.getCode());
			args.put("name", pojo.getName());
			args.put("amount", pojo.getAmount());
			args.put("publisher", pojo.getPublisher());
		}
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.mngDishonestyEService.dis_update(args);
			if(a > 0){
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
			e.printStackTrace();
		}
		model.put("map", map);
		return mav;
	}
	
	
	
	/**
	 * 失信曝光审核
	 * 
	 */
	@RequestMapping(value="/dis_sh",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public ModelAndView dis_sh(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-sh");
		String id = request.getParameter("id");//新闻ID
		String zflx_id = request.getParameter("zflx_id");
		args.put("id", id);
		args.put("zflx_id", zflx_id);
		List<Object> list = new ArrayList<>();
		try {
			Map<String, Object> mentorMap = (Map<String, Object>) this.mngDishonestyEService.getdisdetail(args);
			if(!mentorMap.isEmpty()){
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
			e.printStackTrace();
		}
		model.put("map", map);
		model.put("id", id);
		return mav;
	}
	
	
	
	/**
	 * 失信曝光审核提交
	 * 
	 */
	@RequestMapping(value="/dis_updateZt",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ModelAndView dis_updateZt(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-sh");
		args.put("state", pojo.getState());
		args.put("id", pojo.getId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.mngDishonestyEService.dis_updateZt(args);
			if(a > 0){
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
			e.printStackTrace();
		}
		model.put("map", map);
		return mav;
	}
	
	
	
	
	

}
