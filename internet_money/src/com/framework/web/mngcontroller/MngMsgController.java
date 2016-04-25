package com.framework.web.mngcontroller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.MngMsgService;


@Controller
@RequestMapping("MngMsgController")
public class MngMsgController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(MngMsgController.class);
	
	@Resource
	private MngMsgService msgManageService;
	
	
	
	
	
	/**
	 * 跳转到新闻发布页面
	 * 
	 */
	@RequestMapping(value="/pubnewsRef",method=RequestMethod.GET)
	public ModelAndView pubnewsref() {
		return new ModelAndView("news-pub");
	}
	
	
	/**
	 * 新闻发布
	 * 
	 */
	@RequestMapping(value="/pubnews",method=RequestMethod.POST)
	@ResponseBody
	public Object pubnews(HttpServletRequest request,ModelMap model,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("title", pojo.getTitle());
		args.put("shortContent", pojo.getShortContent());
		args.put("content", pojo.getContent());
		args.put("url", pojo.getUrl());
		args.put("district", pojo.getDistrict());
		args.put("type", pojo.getType());
		args.put("time", pojo.getTime());
		args.put("user_id", this.getUserId());
		try {
			int a = this.msgManageService.pubnews(args);//查询用户填写的号码有没有注册过
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
	 * 跳转到新闻列表，默认查询全部
	 * 
	 */
	@RequestMapping(value="/newslistRef",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public ModelAndView newslistRef(HttpServletRequest request,ModelMap model,@ModelAttribute("pojo")MngPoJo pojo) {
		return new ModelAndView("news-list");
	}
	
	
	/**
	 * 新闻列表
	 * 
	 */
	@RequestMapping(value="/selectnewslist",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public ModelAndView selectnewslist(HttpServletRequest request,ModelMap model,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-list");
		String page = request.getParameter("user_page");//page
		String zflx_id = request.getParameter("zflx_id");
		String curPageNum = "";
		if("".equals(page) || page == null){
			page = "1";
			curPageNum = "1";
		}else{
			curPageNum = page;
		}
		int num = 20;
		args.put("num", num);
		args.put("zflx_id", zflx_id);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.msgManageService.selectnewslist(args);
			int totalCount = this.msgManageService.selectnewslistNum(args);
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
		mav.addObject("zflx_id", zflx_id);
		return mav;
	}
	
	
	/**
	 * 新闻审核
	 * 
	 */
	@RequestMapping(value="/news_sh",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public ModelAndView news_sh(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-sh");
		String id = request.getParameter("id");//新闻ID
		String zflx_id = request.getParameter("zflx_id");
		args.put("id", id);
		args.put("zflx_id", zflx_id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.msgManageService.selectnews(args);
			if(list.size() > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
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
		model.put("map", map);
		model.put("id", id);
		return mav;
	}
	
	
	
	/**
	 * 新闻审核新闻提交
	 * 
	 */
	@RequestMapping(value="/news_updateZt",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ModelAndView news_updateZt(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-sh");
		args.put("state", pojo.getState());
		args.put("id", pojo.getId());
		args.put("type", pojo.getType());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.msgManageService.news_updateZt(args);
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
	 * 新闻编辑页面
	 * 
	 */
	@RequestMapping(value="/newsedit",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String newsedit(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		args.put("id", pojo.getId());
		args.put("type", pojo.getType());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.msgManageService.selectnews(args);//查询用户填写的号码有没有注册过
			if(list.size() > 0){
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
		return "delete-news";
	}
	
	
	/**
	 * 新闻编辑提交
	 * 
	 */
	@RequestMapping(value="/news_editTj",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ModelAndView news_editTj(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("news-sh");
		args.put("id", pojo.getId());
		args.put("title", pojo.getTitle());
		args.put("shortContent", pojo.getShortContent());
		args.put("content", pojo.getContent());
		args.put("url", pojo.getUrl());
		args.put("district", pojo.getDistrict());
		args.put("type", pojo.getType());
		args.put("time", pojo.getTime());
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.msgManageService.news_editTj(args);
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
	 * 新闻删除
	 * 
	 */
	@RequestMapping(value="/delete_news",method=RequestMethod.POST)
	@ResponseBody
	public Object delete_news(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo,ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String id = pojo.getUser_id();//新闻ID
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			int a = this.msgManageService.delete_news(args);//查询用户填写的号码有没有注册过
			if(a > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "删除成功");
				map.put("resultData", list);
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
		return map;
	}
	
	/**
	 * 新闻标签
	 * 
	 */
	@RequestMapping(value="/updatenewstag",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object updatenewstag(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String id = pojo.getId();//新闻ID
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			int a = this.msgManageService.updatenewstag(args);//查询用户填写的号码有没有注册过
			if(a > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -7);
				map.put("resultMsg", "数据未成功更新");
				map.put("resultData", list);
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
	 * 信息发布管理（小二）
	 * 
	 */
	@RequestMapping(value="/waitermsglist",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object waitermsglist(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String page = pojo.getPage();//新闻Page
		int num = 10;
		args.put("page", page);
		args.put("num", num);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.msgManageService.waitermsglist(args);
			if(list.size() > 0){
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
		return map;
	}
	
	/**
	 * 信息发布处理状态
	 * 
	 */
	@RequestMapping(value="/waitermsgzt",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object waitermsgzt(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String page = pojo.getPage();//新闻Page
		int num = 10;
		args.put("page", page);
		args.put("num", num);
		List<Object> list = new ArrayList<>();
		try {
			int a = this.msgManageService.waitermsgzt(args);
			if(a > 0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -7);
				map.put("resultMsg", "数据未成功更新");
				map.put("resultData", list);
			}
			
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		return map;
	}
	
	
}
