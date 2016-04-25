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
import com.framework.web.service.GovernmentCarService;
import com.framework.web.service.SystemService;

@Controller
@RequestMapping("SystemController")
public class SystemController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Resource
	private SystemService systemService;

	@Resource
	private GovernmentCarService governmentCarService;

	/**
	 * 查看模块配置信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getmoduleconfig", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getmoduleconfig(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("查看模块配置信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String father_id = request.getParameter("father_id");
		args.put("father_id", father_id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.systemService.getmoduleconfig(args);
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
			logger.error("查看模块配置信息异常",e);
		}
		logger.info("查看模块配置信息end---------------");
		return map;
	}

	/**
	 * 设置模块配置信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/setmoduleconfig", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object setmoduleconfig(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("设置模块配置信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = pojo.getId();
		String name = pojo.getName();
		String father_id = pojo.getFather_id();
		args.put("father_id", father_id);
		args.put("name", name);
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			int a = 0;
			if ("".equals(id) || id == null) {
				a = this.systemService.insertmoduleconfig(args);
				if (a > 0) {
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", list);
				} else {
					map.put("resultCode", -6);
					map.put("resultMsg", "数据未成功插入");
					map.put("resultData", list);
				}
			} else {
				a = this.systemService.updatemoduleconfig(args);
				if (a > 0) {
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", list);
				} else {
					map.put("resultCode", -7);
					map.put("resultMsg", "数据未成功更新");
					map.put("resultData", list);
				}
			}

		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("设置模块配置信息异常",e);
		}
		logger.info("设置模块配置信息end---------------");
		return map;
	}

	/**
	 * 查看红包配置信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/gethongbaoconfig", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object gethongbaoconfig(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("查看红包配置信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.systemService.gethongbaoconfig(args);
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
			logger.error("查看红包配置信息异常",e);
		}
		logger.info("查看红包配置信息end---------------");
		return map;
	}

	/**
	 * 设置红包配置信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/sethongbaoconfig", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object sethongbaoconfig(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("设置红包配置信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("name", pojo.getName());
		args.put("beginTime", pojo.getBeginTime());
		args.put("endTime", pojo.getEndTime());
		args.put("amount", pojo.getAmount());
		//args.put("type", pojo.getType());
		args.put("memo", pojo.getMemo());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.systemService.sethongbaoconfig(args);
			if (a > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -6);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("设置红包配置信息异常",e);
		}
		logger.info("设置红包配置信息end---------------");
		return map;
	}

	/**
	 * 发放红包
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/sethongbao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object sethongbao(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("发放红包begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> args2 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> argstj = new HashMap<>();
		args.put("rule_id", pojo.getRule_id());
		args.put("mobile", pojo.getMobile());
		args.put("type", pojo.getType());
		args.put("time", pojo.getTime());
		args.put("amount", pojo.getAmount());
		args.put("memo", pojo.getMemo());
		args.put("operator_id", this.getUserId());
		args1.put("operator_id", this.getUserId());
		args1.put("amount1", pojo.getAmount());
		List<Object> list = new ArrayList<>();
		int amount = Integer.valueOf(pojo.getAmount());
		int num = -9999;
		try {
			if (amount < 0) {
				args2 = (Map<String, Object>) this.systemService.selectamount(args);
				num = amount + Integer.valueOf((String) args2.get("amount"));
			} else {
				num = 1;
			}
			if (num >= 0) {
				argstj = (Map<String, Object>) this.governmentCarService.doSelect_userid(pojo.getMobile());
				if (argstj != null) {
					args.put("user_id", argstj.get("id"));
				} else {
					args.put("user_id", "-1");
				}
				int a = this.systemService.sethongbao(args);
				if (a > 0) {
					int b = this.systemService.updateUserHbao(args1);//更新用户余额
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", list);
				} else {
					map.put("resultCode", -6);
					map.put("resultMsg", "数据未成功插入");
					map.put("resultData", list);
				}
			} else {
				map.put("resultCode", -16);
				map.put("resultMsg", "余额不足");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("发放红包异常",e);
		}
		logger.info("发放红包end---------------");
		return map;
	}

	

	

}
