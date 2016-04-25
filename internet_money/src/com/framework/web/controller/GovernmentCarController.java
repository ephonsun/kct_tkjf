package com.framework.web.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;

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
import org.springframework.web.servlet.ModelAndView;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.GovernmentCarService;
import com.framework.web.service.LoginService;


@Controller
@RequestMapping("GovernmentCarController")
public class GovernmentCarController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(GovernmentCarController.class);

	@Resource
	private GovernmentCarService governmentCarService;

	@Resource
	private LoginService loginService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test() {
		String str = "你好 生活！";
		System.out.println("哈哈");
		return new ModelAndView("message", "str", str);
	}

	/**
	 * ceshi
	 * 
	 */
	@RequestMapping(value = "/ceshi", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object ceshi(HttpServletRequest request) {
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 2;// 每页条数
		// args.put("num", num);
		// args.put("page", "3");
		List<Object> list = new ArrayList<>();

		map.put("resultCode", 0);
		map.put("resultMsg", "您需要登录的操作，已经登录并操作成功");
		map.put("resultData", list);
		return map;
	}


	

	/**
	 * 获取政务资讯列表(不需登录)
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getgovnews", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getgovnews(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("获取政务资讯列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		// String type = pojo.getType();//查询类型
		// String page = pojo.getPage();//页数
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 6;// 每页条数
		args.put("num", num);
		// args.put("page", page);
		args.put("type", type);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getgovnews(args);
			int totalCount = this.governmentCarService.getgovnewsNum(args);
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
			logger.error("获取政务资讯列表异常",e);
		}
		logger.info("获取政务资讯列表end---------------");
		return map;
	}

	/**
	 * 获取政务资讯详情(不需登录)
	 * 
	 */
	@RequestMapping(value = "/getgovnewsdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getgovnewsdetail(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取政务资讯详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		// String id = pojo.getId();//政务资讯ID
		String id = request.getParameter("id");
		// args.put("id", "5");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getgovnewsdetail(args);
			if (list.size() == 0) {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			} else {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取政务资讯详情异常",e);
		}
		logger.info("获取政务资讯详情end---------------");
		return map;
	}

	/**
	 * 获取政务代办列表(不需登录)
	 * 
	 */
	@RequestMapping(value = "/getgovagent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getgovagent(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取政务代办列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 10;// 每页条数
		// args.put("page", "3");
		args.put("type", type);
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getgovagent(args);
			int totalCount = this.governmentCarService.getgovagentNum(args);
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
			logger.error("获取政务代办列表异常",e);
		}
		logger.info("获取政务代办列表end---------------");
		return map;
	}

	/**
	 * 获取政务代办详情(不需登录)
	 * 
	 */
	@RequestMapping(value = "/getgovagentdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getgovagentdetail(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取政务代办详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		// String id = pojo.getId();//政务资讯ID
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getgovagentdetail(args);
			if (list.size() == 0) {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			} else {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取政务代办详情异常",e);
		}
		logger.info("获取政务代办详情end---------------");
		return map;
	}

	/**
	 * 申请代办
	 * 
	 */
	@RequestMapping(value = "/applygovagent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applygovagent(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("申请代办begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("agent_id", request.getParameter("id"));
		args.put("mobile", request.getParameter("mobile"));
		args.put("name", request.getParameter("name"));
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.governmentCarService.applygovagent(args);
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
			logger.error("申请代办异常",e);
		}
		logger.info("申请代办end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取园区动态列表(不需登录)
	 * 
	 */
	@RequestMapping(value = "/getparknews", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getparknews(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取园区动态列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		args.put("type", type);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getparknews(args);
			int totalCount = this.governmentCarService.getparknewsNum(args);
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
			logger.error("获取园区动态列表异常",e);
		}
		logger.info("获取园区动态列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取新闻速递、政策资讯、通知公告列表
	 * 
	 */
	@RequestMapping(value = "/getrcfwnews", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getrcfwnews(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取新闻速递、政策资讯、通知公告列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		args.put("type", type);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getrcfwnews(args);
			int totalCount = this.governmentCarService.getrcfwnewsNum(args);
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
			logger.error("获取园区动态列表异常",e);
		}
		logger.info("获取新闻速递、政策资讯、通知公告列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取新闻速递、政策资讯、通知公告详情
	 * 
	 */
	@RequestMapping(value = "/getrcfwnewsdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getrcfwnewsdetail(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取新闻速递、政策资讯、通知公告详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		// String id = pojo.getId();//政务资讯ID
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getrcfwnewsdetail(args);
			if (list.size() == 0) {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			} else {
				this.governmentCarService.updatercfwnewsCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取新闻速递、政策资讯、通知公告详情异常",e);
		}
		logger.info("获取新闻速递、政策资讯、通知公告详情end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取新闻速递、政策资讯、通知公告详情
	 * 
	 */
	@RequestMapping(value = "/test", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object test(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String mobile = request.getParameter("mobile");
		System.out.println(pojo.getMobile());
		System.out.println(mobile);
		
		
		
		// String id = pojo.getId();//政务资讯ID
		return map;
	}
	
	
	/**
	 * 获取新闻速递、政策资讯、通知公告详情
	 * 
	 */
	@RequestMapping(value = "/testdelete", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object testdelete(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		System.out.println(request.getParameter("mobile"));
		
		
		
		// String id = pojo.getId();//政务资讯ID
		return map;
	}
	
	
	/**
	 * 获取申报、兑现、绩效列表
	 * 
	 */
	@RequestMapping(value = "/getrcfwpolicy", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getrcfwpolicy(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取申报、兑现、绩效列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		args.put("type", type);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getrcfwpolicy(args);
			int totalCount = this.governmentCarService.getrcfwpolicyNum(args);
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
			logger.error("获取申报、兑现、绩效列表异常",e);
		}
		logger.info("获取申报、兑现、绩效列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 获取园区动态详情(不需登录)
	 * 
	 */
	@RequestMapping(value = "/getparknewsdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getparknewsdetail(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取园区动态详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		// String id = pojo.getId();//政务资讯ID
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.governmentCarService.getparknewsdetail(args);
			if (list.size() == 0) {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			} else {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取园区动态详情异常",e);
		}
		logger.info("获取园区动态详情end---------------");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value = "/test_insert", method = RequestMethod.GET)
	public void test_insert() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "看到信息表示成功");
		this.governmentCarService.doSave(map);
	}

	@RequestMapping(value = "/test_delete", method = RequestMethod.GET)
	public void test_delete() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "看到信息表示成功");
		this.governmentCarService.doDelete(map);
	}

	@RequestMapping(value = "/test_update", method = RequestMethod.GET)
	public void test_update() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "看到信息表示成功_test_update");
		map.put("id", "5");
		this.governmentCarService.doUpdate(map);
	}

	@RequestMapping(value = "/test_select", method = RequestMethod.GET)
	public void test_select() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list = (List<Object>) this.governmentCarService.doSelect();
		System.out.println(list);
	}

	@RequestMapping(value = "/test_baechInsert", method = RequestMethod.GET)
	public void test_baechInsert() throws Exception {
		Map<String, String> map = null;
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < 5; i++) {
			map = new HashMap<String, String>();
			map.put("text", "批量插入");
			map.put("time", "2015");
			list.add(map);
		}
		this.governmentCarService.doBaechInsert(list);
	}

}
