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
import com.framework.web.service.EnterpriseCooperationService;

@Controller
@RequestMapping("EnterpriseCooperationController")
public class EnterpriseCooperationController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(EnterpriseCooperationController.class);
	
	@Resource
	private EnterpriseCooperationService enterpriseCService;
	
	
	
	/**
	 * 企业合作信息搜索
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object search(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("获取找技术列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String word = request.getParameter("word");
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 40;//每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		args.put("word", word);
		try {
			list = (List<Object>) this.enterpriseCService.search(args);
			int totalCount = this.enterpriseCService.searchNum(args);
			if(list.size() >0){
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
			logger.error("获取帖子列表(圈子运动)异常",e);
		}
		logger.info("获取找技术列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 获取找技术列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/gettechlist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getgrouppostlist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("获取找技术列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String side = request.getParameter("side");
		String order = request.getParameter("order");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num;//每页条数
		if("0".equals(order)){
			num = 14;
		}else{
			num = 3;
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		args.put("type", type);
		args.put("side", side);
		args.put("order", order);
		try {
			list = (List<Object>) this.enterpriseCService.gettechlist(args);
			int totalCount = this.enterpriseCService.gettechlistNum(args);
			if(list.size() >0){
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
			logger.error("获取帖子列表(圈子运动)异常",e);
		}
		logger.info("获取找技术列表end---------------");
		return map;
	}
	
	
	/**
	 * 发布找技术的信息 
	 */
	@RequestMapping(value = "/pubtech", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubnameregistration(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		logger.info("发布找技术的信息 begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("type", OtherUtils.EmptToNull(pojo.getType()));
		args.put("title", OtherUtils.EmptToNull(pojo.getTitle()));
		args.put("company", OtherUtils.EmptToNull(pojo.getCompany()));
		args.put("mobile", OtherUtils.EmptToNull(pojo.getMobile()));
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("district", OtherUtils.EmptToNull(pojo.getDistrict()));
		args.put("industry", OtherUtils.EmptToNull(pojo.getIndustry()));
		args.put("memo", OtherUtils.EmptToNull(pojo.getMemo()));
//		args.put("authority", OtherUtils.EmptToNull(pojo.getAuthority()));
		args.put("side", OtherUtils.EmptToNull(pojo.getSide()));
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.enterpriseCService.pubtech(args);
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
			logger.error("发布找技术的信息异常",e);
		}
		logger.info("发布找技术的信息end---------------");
		return map;
	}
	
	/**
	 * 查看技术详情信息
	 * 
	 */
	@RequestMapping(value = "/pubtechdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubfactorydetail(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看技术详情信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		args.put("table_name", format_Etablename(1));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.enterpriseCService.pubtechdetail(args);
			if (list.size() > 0) {
				this.enterpriseCService.updateCount(args);
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
			logger.error("查看技术详情信息异常",e);
		}
		logger.info("查看技术详情信息end---------------");
		return map;
	}
	
	
	/**
	 * 获取找原料列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getmateriallist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmateriallist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("获取找技术列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String page = request.getParameter("page");
		String side = request.getParameter("side");
		String order = request.getParameter("order");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num;//每页条数
		if("0".equals(order)){
			num = 14;
		}else{
			num = 3;
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		args.put("side", side);
		args.put("order", order);
		try {
			list = (List<Object>) this.enterpriseCService.getmateriallist(args);
			int totalCount = this.enterpriseCService.getmateriallistNum(args);
			if(list.size() >0){
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
			logger.error("获取找原料列表异常",e);
		}
		logger.info("获取找原料列表end---------------");
		return map;
	}
	
	/**
	 * 发布找原料的信息 
	 */
	@RequestMapping(value = "/pubmaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubmaterial(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		logger.info("发布找原料的信息 begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("title", OtherUtils.EmptToNull(pojo.getTitle()));
		args.put("company", OtherUtils.EmptToNull(pojo.getCompany()));
		args.put("mobile", OtherUtils.EmptToNull(pojo.getMobile()));
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("district", OtherUtils.EmptToNull(pojo.getDistrict()));
		args.put("industry", OtherUtils.EmptToNull(pojo.getIndustry()));
		args.put("memo", OtherUtils.EmptToNull(pojo.getMemo()));
//		args.put("authority", OtherUtils.EmptToNull(pojo.getAuthority()));
		args.put("side", OtherUtils.EmptToNull(pojo.getSide()));
		args.put("time", String.valueOf(System.currentTimeMillis()));
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.enterpriseCService.pubmaterial(args);
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
			logger.error("发布找原料的信息异常",e);
		}
		logger.info("发布找原料的信息end---------------");
		return map;
	}
	
	/**
	 * 查看原料详情信息
	 * 
	 */
	@RequestMapping(value = "/pubmaterialdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubmaterialdetail(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看原料详情信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		args.put("table_name", format_Etablename(2));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.enterpriseCService.pubmaterialdetail(args);
			if (list.size() > 0) {
				this.enterpriseCService.updateCount(args);
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
			logger.error("查看原料详情信息异常",e);
		}
		logger.info("查看原料详情信息end---------------");
		return map;
	}
	
	/**
	 * 获取找上下游列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getcooperationlist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getcooperationlist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("获取找上下游列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String page = request.getParameter("page");
		String side = request.getParameter("side");
		String order = request.getParameter("order");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num;//每页条数
		if("0".equals(order)){
			num = 14;
		}else{
			num = 3;
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		args.put("side", side);
		args.put("order", order);
		try {
			list = (List<Object>) this.enterpriseCService.getcooperationlist(args);
			int totalCount = this.enterpriseCService.getcooperationlistNum(args);
			if(list.size() >0){
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
			logger.error("获取找上下游列表异常",e);
		}
		logger.info("获取找上下游列表end---------------");
		return map;
	}
	
	/**
	 * 发布找上下游信息 
	 */
	@RequestMapping(value = "/pubcooperation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubcooperation(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		logger.info("发布找上下游信息 begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("title", OtherUtils.EmptToNull(pojo.getTitle()));
		args.put("company", OtherUtils.EmptToNull(pojo.getCompany()));
		args.put("mobile", OtherUtils.EmptToNull(pojo.getMobile()));
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("district", OtherUtils.EmptToNull(pojo.getDistrict()));
		args.put("industry", OtherUtils.EmptToNull(pojo.getIndustry()));
		args.put("memo", OtherUtils.EmptToNull(pojo.getMemo()));
//		args.put("authority", OtherUtils.EmptToNull(pojo.getAuthority()));
		args.put("side", OtherUtils.EmptToNull(pojo.getSide()));
		args.put("time", String.valueOf(System.currentTimeMillis()));
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.enterpriseCService.pubcooperation(args);
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
			logger.error("发布找上下游信息异常",e);
		}
		logger.info("发布找上下游信息end---------------");
		return map;
	}
	
	/**
	 * 查看上下游详情信息
	 * 
	 */
	@RequestMapping(value = "/pubcooperationdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubcooperationdetail(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看上下游详情信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		args.put("table_name", format_Etablename(3));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.enterpriseCService.pubcooperationdetail(args);
			if (list.size() > 0) {
				this.enterpriseCService.updateCount(args);
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
			logger.error("查看上下游详情信息异常",e);
		}
		logger.info("查看上下游详情信息end---------------");
		return map;
	}
	
	/**
	 * 获取找投融资列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getfinancelist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getfinancelist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("获取找投融资列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String page = request.getParameter("page");
		String side = request.getParameter("side");
		String order = request.getParameter("order");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num;//每页条数
		if("0".equals(order)){
			num = 14;
		}else{
			num = 3;
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		args.put("side", side);
		args.put("order", order);
		try {
			list = (List<Object>) this.enterpriseCService.getfinancelist(args);
			int totalCount = this.enterpriseCService.getfinancelistNum(args);
			if(list.size() >0){
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
			logger.error("获取找投融资列表异常",e);
		}
		logger.info("获取找投融资列表end---------------");
		return map;
	}
	
	/**
	 * 发布找投融资信息 
	 */
	@RequestMapping(value = "/pubfinance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubfinance(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		logger.info("发布找投融资信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("title", OtherUtils.EmptToNull(pojo.getTitle()));
		args.put("company", OtherUtils.EmptToNull(pojo.getCompany()));
		args.put("mobile", OtherUtils.EmptToNull(pojo.getMobile()));
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("district", OtherUtils.EmptToNull(pojo.getDistrict()));
		args.put("industry", OtherUtils.EmptToNull(pojo.getIndustry()));
		args.put("memo", OtherUtils.EmptToNull(pojo.getMemo()));
//		args.put("authority", OtherUtils.EmptToNull(pojo.getAuthority()));
		args.put("side", OtherUtils.EmptToNull(pojo.getSide()));
		args.put("time", String.valueOf(System.currentTimeMillis()));
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.enterpriseCService.pubfinance(args);
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
			logger.error("发布找投融资信息异常",e);
		}
		logger.info("发布找投融资信息end---------------");
		return map;
	}
	
	/**
	 * 查看投融资详情信息
	 * 
	 */
	@RequestMapping(value = "/pubfinancedetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubfinancedetail(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看投融资详情信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		args.put("table_name", format_Etablename(4));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.enterpriseCService.pubfinancedetail(args);
			if (list.size() > 0) {
				this.enterpriseCService.updateCount(args);
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
			logger.error("查看投融资详情信息异常",e);
		}
		logger.info("查看投融资详情信息end---------------");
		return map;
	}
	
	
	
	
	public String format_Etablename(int a) {
		String tablename = "";
		if (a == 1) {
			tablename = "e_tech";
		} else if (a == 2) {
			tablename = "e_material";
		} else if (a == 3) {
			tablename = "e_cooperation";
		} else if (a == 4) {
			tablename = "e_finance";
		} else {
			tablename = "e_tech";
		}
		return tablename;
	}
	
	

}
