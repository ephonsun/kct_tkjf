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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.web.service.OtherService;

@Controller
@RequestMapping("OtherController")
public class OtherController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(OtherController.class);
	
	@Resource
	private OtherService otherService;
	
	/**
	 * 获取行业下拉框
	 * 
	 */
	@RequestMapping(value="/getindustrylist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getindustrylist(HttpServletRequest request) {
		logger.info("获取行业下拉框begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.otherService.getindustrylist();
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
			logger.error("获取行业下拉框异常",e);
		}
		logger.info("获取行业下拉框end---------------");
		return map;
	}

}
