package com.framework.web.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.DishonestyExposureService;

@Controller
@RequestMapping("DishonestyExposureController")
public class DishonestyExposureController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(DishonestyExposureController.class);
	
	@Resource
	private DishonestyExposureService dishonestyExposureService;
	
	
	/**
	 * 获取失信自然人列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getdishonestperson",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getdishonestperson(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取失信自然人列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		//args.put("page", request.getParameter("page"));
		String page = request.getParameter("page");
		String name = request.getParameter("name");
		String idCardNumber = request.getParameter("idCardNumber");
		String nameNum = "0";
		String idCardNumberNum = "0";
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		if("".equals(name) || name == null){//没有page默认给 1    
			nameNum = "1";
		}
		if("".equals(idCardNumber) || idCardNumber == null){//没有page默认给 1    
			idCardNumberNum = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		args.put("name", name);
		args.put("idCardNumber", idCardNumber);
		args.put("nameNum", nameNum);
		args.put("idCardNumberNum", idCardNumberNum);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		try {
			list = (List<Object>) this.dishonestyExposureService.getdishonestperson(args);
			int totalCount = this.dishonestyExposureService.getdishonestpersonNum(args);
			if(list.size() > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
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
			logger.error("获取失信自然人列表异常",e);
		}
		logger.info("获取失信自然人列表end---------------");
		return map;
	}
	
	/**
	 * 获取失信法人列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getdishonestcorporation",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getdishonestcorporation(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取失信法人列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		//args.put("page", request.getParameter("page"));
		String page = request.getParameter("page");
		String code = request.getParameter("code");
		String corporation = request.getParameter("corporation");
		String codeNum = "0";
		String corporationNum = "0";
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		if("".equals(code) || code == null){//没有page默认给 1    
			codeNum = "1";
		}
		if("".equals(corporation) || corporation == null){//没有page默认给 1    
			corporationNum = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		args.put("code", code);
		args.put("corporation", corporation);
		args.put("codeNum", codeNum);
		args.put("corporationNum", corporationNum);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		try {
			list = (List<Object>) this.dishonestyExposureService.getdishonestcorporation(args);
			int totalCount = this.dishonestyExposureService.getdishonestcorporationNum(args);
			if(list.size() > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
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
			logger.error("获取失信法人列表异常",e);
		}
		logger.info("获取失信法人列表begin---------------");
		return map;
	}
	
	

}
