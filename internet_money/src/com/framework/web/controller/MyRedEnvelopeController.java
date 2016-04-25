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
import com.framework.web.service.MyRedEnvelopeService;

@Controller
@RequestMapping("MyRedEnvelopeController")
public class MyRedEnvelopeController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(MyRedEnvelopeController.class);
	
	@Resource
	private MyRedEnvelopeService myRedEnvelopeService;
	
	
	/**
	 * 查看我的红包余额
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/gethongbao", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object gethongbao(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("查看我的红包余额begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("user_id", this.getUserId());
		try {
			list = (List<Object>) this.myRedEnvelopeService.gethongbao(args);
			if (list.size() > 0) {
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
			logger.error("查看我的红包余额异常",e);
		}
		logger.info("查看我的红包余额end---------------");
		return map;
	}
	
	
	/**
	 * 查看我的红包记录
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/gethongbaolog", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object gethongbaolog(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("查看我的红包记录begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("user_id", this.getUserId());
		try {
			list = (List<Object>) this.myRedEnvelopeService.gethongbaolog(args);
			if (list.size() > 0) {
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
			logger.error("查看我的红包记录异常",e);
		}
		logger.info("查看我的红包记录begin---------------");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
