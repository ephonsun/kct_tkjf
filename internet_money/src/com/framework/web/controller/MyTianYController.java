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
import com.framework.core.entity.setuserBean;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.LoginService;
import com.framework.web.service.MyTianYService;
import com.framework.web.service.WaiterCompanyService;

@Controller
@RequestMapping("MyTianYController")
public class MyTianYController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MyTianYController.class);

	@Resource
	private MyTianYService myTianYService;

	@Resource
	private WaiterCompanyService waiterCompanyService;
	
	@Resource
	private LoginService loginService;

	/**
	 * 查看个人信息
	 * 
	 */
	@RequestMapping(value = "/getuser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getuser(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看个人信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		args.put("id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.selectPerson(args);
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
			logger.error("查看个人信息异常", e);
		}
		logger.info("查看个人信息end---------------");
		return map;
	}
	
	
	/**
	 * 查看企业信息
	 * 
	 */
	@RequestMapping(value = "/getusercompany", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getusercompany(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看企业信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getusercompany(args);
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
			logger.error("查看个人信息异常", e);
		}
		logger.info("查看企业信息end---------------");
		return map;
	}
	
	
	/**
	 * 查看企业信息列表
	 * 
	 */
	@RequestMapping(value = "/getusercompanylist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getusercompanylist(HttpServletRequest request, HttpServletResponse response) {
		logger.info("查看企业信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		args.put("id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getusercompanylist(args);
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
			logger.error("查看个人信息异常", e);
		}
		logger.info("查看企业信息end---------------");
		return map;
	}
	
	
	

	/**
	 * 设置企业信息
	 * 
	 */
	@RequestMapping(value = "/setusercompany", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object setusercompany(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo poJo) {
		logger.info("设置个人信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		
		args.put("id", poJo.getId());
		args.put("name", OtherUtils.format_void(poJo.getName()));
		args.put("district", OtherUtils.format_void(poJo.getDistrict()));
		args.put("address", OtherUtils.format_void(poJo.getAddress()));
		args.put("industry", OtherUtils.format_void(poJo.getIndustry()));
		args.put("license", OtherUtils.format_void(poJo.getLicense()));
		args.put("organization", OtherUtils.format_void(poJo.getOrganization()));
		args.put("registeredCapital", OtherUtils.format_void(poJo.getRegisteredCapital()));
		args.put("introduction", OtherUtils.format_void(poJo.getIntroduction()));
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		try {
			list1 = (List<Object>) this.myTianYService.doSelect_usercompany(args);
			if (list1.size() > 0) {// 表示有重复企业名
				map.put("resultCode", -26);
				map.put("resultMsg", "企业名称已存在");
				map.put("resultData", list);
			} else {
				int a = this.myTianYService.updateusercompany(args);
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
			logger.error("设置个人信息异常", e);
		}
		logger.info("设置个人信息end---------------");
		return map;
	}
	
	/**
	 * 企业用户绑定企业信息 
	 */
	@RequestMapping(value = "/addusercompany", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addusercompany(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		logger.info("企业用户绑定企业信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String name = pojo.getName();
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("district", OtherUtils.EmptToNull(pojo.getDistrict()));
		args.put("address", OtherUtils.EmptToNull(pojo.getAddress()));
		args.put("industry", OtherUtils.EmptToNull(pojo.getIndustry()));
		args.put("license", OtherUtils.EmptToNull(pojo.getLicense()));
		args.put("organization", OtherUtils.EmptToNull(pojo.getOrganization()));
		args.put("registeredCapital", OtherUtils.EmptToNull(pojo.getRegisteredCapital()));
		args.put("introduction", OtherUtils.EmptToNull(pojo.getIntroduction()));
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		if("".equals(name) || name == null){
			map.put("resultCode", -28);
			map.put("resultMsg", "请输入要绑定的企业名称");
			map.put("resultData", list);
		}else{
			try {
				list1 = (List<Object>) this.myTianYService.doSelect_addusercompany(args);
				if (list1.size() > 0) {// 表示有重复企业名
					map.put("resultCode", -26);
					map.put("resultMsg", "企业名称已存在");
					map.put("resultData", list);
				} else {
					int a = this.myTianYService.addusercompany(args);
					if (a > 0) {
						map.put("resultCode", 0);
						map.put("resultMsg", "操作成功");
						map.put("resultData", list);
					} else {
						map.put("resultCode", -6);
						map.put("resultMsg", "数据未成功插入");
						map.put("resultData", list);
					}
				}
			} catch (Exception e) {
				map.put("resultCode", -2);
				map.put("resultMsg", "服务器异常");
				map.put("resultData", list);
				logger.error("企业用户绑定企业信息异常",e);
			}
		}
		
		logger.info("企业用户绑定企业信息end---------------");
		return map;
	}
	
	
	
	
	
	/**
	 * 设置个人信息
	 * 
	 */
	@RequestMapping(value = "/setuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object setuser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") setuserBean bean) {
		logger.info("设置个人信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> usermap = new HashMap<>();
		Map<String, Object> args = new HashMap<>();

		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		args.put("id", bean.getId());
		try {
			usermap = (Map<String, Object>) this.myTianYService.selectUserMap(args);
			args.put("id", bean.getId());
			args.put("name", fNull(bean.getName(), usermap.get("name")));
			args.put("userName", fNull(bean.getUserName(), usermap.get("userName")));
			args.put("gender", fNull(bean.getGender(), usermap.get("gender")));
			args.put("email", fNull(bean.getEmail(), usermap.get("email")));
			args.put("avatar", fNull(bean.getAvatar(), usermap.get("avatar")));
//			args.put("mobile", fNull(bean.getMobile(), usermap.get("mobile")));
			args.put("identification", fNull(bean.getIdentification(), usermap.get("identification")));
			args.put("company", fNull(bean.getCompany(), usermap.get("company")));
			args.put("address", fNull(bean.getAddress(), usermap.get("address")));
			args.put("lastTime", System.currentTimeMillis());
			args.put("lastip", request.getRemoteAddr());
			if(bean.getUserName() == null){
				int a = this.myTianYService.updateUser(args);
				if (a > 0) {
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", list);
				} else {
					map.put("resultCode", -7);
					map.put("resultMsg", "数据未成功更新");
					map.put("resultData", list);
				}
			}else{
				list1 = (List<Object>) this.myTianYService.selectusername(args);// 查询用户名是否重复
				if (list1.size() > 0) {// 表示有重复用户名
					map.put("resultCode", -23);
					map.put("resultMsg", "用户名已存在");
					map.put("resultData", list);
				} else {
					int a = this.myTianYService.updateUser(args);
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
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("设置个人信息异常", e);
		}
		logger.info("设置个人信息end---------------");
		return map;
	}
	
	//如果传入的参数为空，引用的是从数据库中取出的字段，保证字段不会被置为null
	public Object fNull(Object object,Object object2) {
		Map<String, Object> args = new HashMap<>();
		Object object3 = null;
		if(object != null){
			object3 = object;
		}else{
			object3 = object2;
		}
		return object3;
	}
	
	

	/**
	 * 查看代办申请信息列表
	 * 
	 */
	@RequestMapping(value = "/getagentapplicationlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getagentapplicationlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看代办申请信息列表begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		// args.put("page", request.getParameter("page"));
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("id", this.getUserId());
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getagentapplicationlist(args);
			int totalCount = this.myTianYService.getagentapplicationlistNum(args);
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
			logger.error("查看代办申请信息列表异常", e);
		}
		logger.info("查看代办申请信息列表end---------------");
		return map;
	}

	/**
	 * 查看融资申请信息列表
	 * 
	 */
	@RequestMapping(value = "/getloanapplicationlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getloanapplicationlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看融资申请信息列表begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("id", this.getUserId());
		// args.put("page", request.getParameter("page"));
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getloanapplicationlist(args);
			int totalCount = this.myTianYService.getloanapplicationlistNum(args);
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
			logger.error("查看融资申请信息列表异常", e);
		}
		logger.info("查看融资申请信息列表end---------------");
		return map;
	}

	/**
	 * 查看融资申请信息详情
	 * 
	 */
	@RequestMapping(value = "/getloanapplicationdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getloanapplicationdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看融资申请信息详情begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		args.put("id", bean.getId());
		args.put("num", num);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getloanapplicationdetail(args);
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
			logger.error("查看融资申请信息详情异常", e);
		}
		logger.info("查看融资申请信息详情begin---------------");
		return map;
	}

	/**
	 * 修改小二跑腿发布信息
	 * 
	 */
	@RequestMapping(value = "/updateWaiterDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object updateWaiterDetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		String category = pojo.getCategory();// 模块id
		String mkName = format_hanzi(category);// 模块名
		logger.info("更新小二跑腿" + mkName + "信息begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = null;
		args = format_Map(pojo, category);
		args.put("table_name", format_tablename(category));
		args.put("category", category);
		args.put("userid", this.getUserId());
		List<Object> list = new ArrayList<>();
		int a = 0;
		try {
			a = this.myTianYService.updateWaiterDetail(args);
			if (a > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -7);
				map.put("resultMsg", "数据未成功更新");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("更新小二跑腿" + mkName + "信息异常", e);
		}
		logger.info("更新小二跑腿" + mkName + "信息end---------------");
		return map;
	}

	/**
	 * 查看预约的保险信息
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/getinsuranceapplicationlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getinsuranceapplicationlist(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo)
			throws Exception {
		logger.info("查看预约的保险信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String page = request.getParameter("page");
		int num = 10;
		if ("".equals(page) || page == null) {
			page = "1";
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		args.put("user_id", this.getUserId());// 0 未审核 1 审核中 2 已通过申请
		try {
			list = (List<Object>) this.myTianYService.getinsurancelist(args);
			int totalCount = this.myTianYService.getinsurancelistNum(args);
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
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
			logger.error("查看预约的保险信息异常", e);
		}
		logger.info("查看预约的保险信息end---------------");
		return map;
	}

	/**
	 * 查看小二跑腿发布信息列表
	 * 
	 */
	@RequestMapping(value = "/getpublist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getpublist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看小二跑腿发布信息列表begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("id", this.getUserId());
		// args.put("page", request.getParameter("page"));
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getpublist(args);
			int totalCount = this.myTianYService.getpublistNum(args);
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
			logger.error("查看小二跑腿发布信息列表异常", e);
		}
		logger.info("查看小二跑腿发布信息列表begin---------------");
		return map;
	}

	/**
	 * 查看小二跑腿发布信息详情
	 * 
	 */
	@RequestMapping(value = "/getpubdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getpubdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看小二跑腿发布信息详情begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String type = "myty";
		args.put("id", bean.getId());
		args.put("type", type);
		args.put("table_name", format_tablename(bean.getCategory()));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.pubfactorydetail(args);
			// list = (List<Object>) this.myTianYService.getpubdetail(args);
			if (list.size() > 0) {
				this.waiterCompanyService.updateCount(args);
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
			logger.error("查看小二跑腿发布信息详情异常", e);
		}
		logger.info("查看小二跑腿发布信息详情end---------------");
		return map;
	}

	/**
	 * 查看短工招聘简历的详细信息(我的天开)
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getapplication", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getapplication(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("查看短工招聘简历的详细信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		List<Object> list3 = new ArrayList<>();
		args.put("user_id", this.getUserId());
		try {
			HashMap<String, Object> testMap = (HashMap<String, Object>) this.myTianYService.getapplication(args);
			if (testMap != null) {//能查到此人简历
				args.put("id", testMap.get("id"));
				list1 = (List<Object>) this.waiterCompanyService.getselfTags(args);// 查询帖子的标签
				list2 = (List<Object>) this.waiterCompanyService.getskillTags(args);// 查询帖子的标签
					testMap.put("selfTags", list1);
					testMap.put("skillTags", list2);
					//list3.add(testMap);
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", testMap);
			} else {
				map.put("resultCode", -17);
				map.put("resultMsg", "未填写简历");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看短工招聘简历的详细信息异常", e);
		}
		logger.info("查看短工招聘简历的详细信息end---------------");
		return map;
	}
	
	
	/**
	 * 删除我的简历(我的天开)
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/deleteapplication", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteapplication(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("删除我的简历的详细信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("user_id", this.getUserId());
		String id = "";
		int a = 0;
		try {
			HashMap<String, Object> testMap = (HashMap<String, Object>) this.myTianYService.getapplication(args);
			if (testMap != null) {//能查到此人简历
				args.put("id", testMap.get("id"));//此人简历ID
				id = testMap.get("id")+"";
				a = this.myTianYService.deleteapplication(args);
			}
			if(a > 0){
				int d = this.myTianYService.deleteSelfTag_post(id);// 删除标签
				int e = this.myTianYService.deleteSkillTag_post(id);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else {
				map.put("resultCode", -8);
				map.put("resultMsg", "数据未成功删除");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("删除我的简历的详细信息异常", e);
		}
		logger.info("删除我的简历的详细信息end---------------");
		return map;
	}
	
	

	/**
	 * 我的天元简历信息的更新
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/setapplication", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object setapplication(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("发布短工简历信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> args2 = new HashMap<>();
		Map<String, Object> args3 = new HashMap<>();
		Map<String, Object> args4 = new HashMap<>();
		Map<String, Object> args5 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		List<Object> list3 = new ArrayList<>();
		// args.put("category", pojo.getCategory());
		String id = pojo.getId();// 简历的ID
		args.put("id", pojo.getId());
		args.put("title", pojo.getTitle());
		args.put("userName", pojo.getUserName());
		args.put("name", pojo.getName());
		args.put("picUrl", pojo.getPicUrl());
		args.put("category", pojo.getCategory());
		args.put("gender", pojo.getGender());
		args.put("age", pojo.getAge());
		args.put("height", pojo.getHeight());
		args.put("mobile", pojo.getMobile());
		args.put("district", pojo.getDistrict());
		args.put("department", pojo.getDepartment());
		args.put("declaration", pojo.getDeclaration());
		args.put("freeTime", pojo.getFreeTime());// 0：日；1：一；2：二；3：三；4：四；5：五；6：六
		args.put("experience", pojo.getExperience());
		args.put("selfTags", pojo.getSelfTags());// 个人标签
		args.put("skillTags", pojo.getSkillTags());// 技能标签
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		// args.put("user_id", "15");
		if(!"".equals(pojo.getSelfTags())){
			
		
		String[] selfTags = pojo.getSelfTags().split(",");
		String[] skillTags = pojo.getSkillTags().split(",");

		try {
			// 0、先检查用户有没有填写过简历
			// 1、首先插入简历的信息，2、获取简历的ID，3、检验有没有新的标签，有的话加入标签表里，并查询新标签的ID，4、将简历的ID和新标签的ID加入关联表

			int f = this.myTianYService.updateMyApplication(args);
			if (f > 0) {
			int d = this.myTianYService.deleteSelfTag_post(id);// 删除标签
			int e = this.myTianYService.deleteSkillTag_post(id);

			if(!"".equals(pojo.getSelfTags())){
			for (int i = 0; i < selfTags.length; i++) {// 个人标签循环
				list1 = (List<Object>) this.waiterCompanyService.selectselfBiaoQian(selfTags[i]);// 查询标签存不存在
				if (list1.size() == 0) {// 标签不存在
					int b = this.waiterCompanyService.insertSelfTag(selfTags[i]);// 标签不存在，插入新的标签
					args2 = (Map<String, Object>) this.waiterCompanyService.selectSelfTagId(selfTags[i]);// 查询新标签的ID
					String tagid = String.valueOf(args2.get("id"));
					int c = this.waiterCompanyService.insertSelfTag_post(id, tagid);// 插入关联表
				} else {// 标签存在，取tagid，保存到关联表
					args3 = (Map<String, Object>) list1.get(0);
					String tagid = String.valueOf(args3.get("id"));
					int c = this.waiterCompanyService.insertSelfTag_post(id, tagid);
				}
			}
			}

			// 重复另一个标签
			if(!"".equals(pojo.getSkillTags())){
			for (int i = 0; i < skillTags.length; i++) {// 个人标签循环
				list2 = (List<Object>) this.waiterCompanyService.selectSkillBiaoQian(skillTags[i]);// 查询标签存不存在
				if (list2.size() == 0) {// 标签不存在
					int b = this.waiterCompanyService.insertSkillTag(skillTags[i]);// 标签不存在，插入新的标签
					args4 = (Map<String, Object>) this.waiterCompanyService.selectSkillTagId(skillTags[i]);// 查询新标签的ID
					String tagid = String.valueOf(args4.get("id"));
					int c = this.waiterCompanyService.insertSkillTag_post(id, tagid);// 插入关联表
				} else {// 标签存在，取tagid，保存到关联表
					args5 = (Map<String, Object>) list2.get(0);
					String tagid = String.valueOf(args5.get("id"));
					int c = this.waiterCompanyService.insertSkillTag_post(id, tagid);
				}
			}
			}
			
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
			logger.error("发布短工简历信息异常", e);
		}
		}else{
			map.put("resultCode", -100);
			map.put("resultMsg", "self为空");
			map.put("resultData", list);
		}
		logger.info("发布短工简历信息end---------------");
		return map;
	}

	/**
	 * 查看咨询信息列表(企业治理)
	 * 
	 */
	@RequestMapping(value = "/getconsultlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getconsultlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看咨询信息列表(企业治理)begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		// args.put("page", request.getParameter("page"));
		args.put("user_id", this.getUserId());
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getconsultlist(args);
			int totalCount = this.myTianYService.getconsultlistNum(args);
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
			logger.error("查看咨询信息列表(企业治理)异常", e);
		}
		logger.info("查看咨询信息列表(企业治理)end---------------");
		return map;
	}

	/**
	 * 查看咨询信息详情(企业治理)
	 * 
	 */
	@RequestMapping(value = "/getconsultdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getconsultdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看咨询信息详情(企业治理)begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		args.put("id", bean.getId());
		// args.put("category", bean.getCategory());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getconsultdetail(args);
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
			logger.error("查看咨询信息详情(企业治理)异常", e);
		}
		logger.info("查看咨询信息详情(企业治理)end---------------");
		return map;
	}

	/**
	 * 查看聘用的导师的信息(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/getmymentorlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getmymentorlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看聘用的导师的信息(创业咨询)begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		// args.put("page", request.getParameter("page"));
		args.put("user_id", this.getUserId());
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		// args.put("user_id", "15");
		// args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getmymentorlist(args);
			int totalCount = this.myTianYService.getmymentorlistNum(args);
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
			logger.error("查看聘用的导师的信息(创业咨询)异常", e);
		}
		logger.info("查看聘用的导师的信息(创业咨询)end---------------");
		return map;
	}

	/**
	 * 查看代办申请信息详情
	 * 
	 */
	@RequestMapping(value = "/getagentapplicationdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getagentapplicationdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看代办申请信息详情begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getagentapplicationdetail(args);
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
			logger.error("查看代办申请信息详情异常", e);
		}
		logger.info("查看代办申请信息详情end---------------");
		return map;
	}

	/**
	 * 查看咨询的信息列表(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/getmystartupconsultlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getmystartupconsultlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看咨询的信息列表(创业咨询)begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		// args.put("page", request.getParameter("page"));
		args.put("user_id", this.getUserId());
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getmystartupconsultlist(args);
			int totalCount = this.myTianYService.getmystartupconsultlistNum(args);
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
			logger.error("查看咨询的信息列表(创业咨询)异常", e);
		}
		logger.info("查看咨询的信息列表(创业咨询)end---------------");
		return map;
	}

	/**
	 * 查看申请的详情信息(创业咨询)
	 * 
	 */
	@RequestMapping(value = "/getmystartupconsultdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getstartupconsultdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo bean) {
		logger.info("查看申请的详情信息(创业咨询)begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myTianYService.getmystartupconsultdetail(args);
			if (list.size() > 0) {
				int a = this.myTianYService.updatemyStartupConsultCount(args);
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
			logger.error("查看申请的详情信息(创业咨询)异常", e);
		}
		logger.info("查看申请的详情信息(创业咨询)end---------------");
		return map;
	}

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
			list = (List<Object>) this.myTianYService.gethongbao(args);
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
			logger.error("查看我的红包余额异常", e);
		}
		logger.info("查看我的红包余额end---------------");
		return map;
	}

	/**
	 * 
	 * @Title: format_Map @Description: TODO(接收小二跑腿各模块要修改信息的参数) @param @return
	 * 参数说明 @return HashMap<String,Object> 返回类型 @throws
	 */
	public HashMap<String, Object> format_Map(PoJo pojo, String category) {
		HashMap<String, Object> args = new HashMap<>();
		args.put("id", pojo.getId());
		args.put("title", pojo.getTitle());
		args.put("time", System.currentTimeMillis());
		args.put("company", pojo.getCompany());
		args.put("name", pojo.getName());
		args.put("mobile", pojo.getMobile());
		args.put("district", pojo.getDistrict());
		args.put("memo", pojo.getMemo());
		if ("1".equals(category)) {// 厂房租售
			args.put("area", pojo.getArea());
			args.put("amount", pojo.getAmount());
			args.put("pics", pojo.getPics());
		} else if ("2".equals(category)) {
			args.put("equipment", pojo.getEquipment());
			args.put("model", pojo.getModel());
			args.put("vender", pojo.getVender());
			args.put("productionDate", pojo.getProductionDate());
			args.put("quantity", pojo.getQuantity());
			args.put("amount", pojo.getAmount());
			args.put("pics", pojo.getPics());
		} else if ("4".equals(category)) {
			args.put("quantity", pojo.getQuantity());
			args.put("salaryMin", pojo.getSalaryMin());
			args.put("salaryMax", pojo.getSalaryMax());
			args.put("education", pojo.getEducation());
			args.put("year", pojo.getYear());
			args.put("model", pojo.getModel());
		} else if ("7".equals(category)) {
			args.put("industry", pojo.getIndustry());
			args.put("quantity", pojo.getQuantity());
			args.put("days", pojo.getDays());
		} else if ("10".equals(category)) {

		} else if ("11".equals(category)) {
			args.put("bank", pojo.getBank());
			args.put("amount", pojo.getAmount());
			args.put("repaymentDate", pojo.getRepaymentDate());
		} else {
			args.put("industry", pojo.getIndustry());
		}
		return args;
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
		int num = 10;
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		// args.put("page", request.getParameter("page"));
		args.put("user_id", this.getUserId());
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		try {
			list = (List<Object>) this.myTianYService.gethongbaolog(args);
			int totalCount = this.myTianYService.gethongbaologNum(args);
			System.out.println(totalCount);
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
			logger.error("查看我的红包记录异常", e);
		}
		logger.info("查看我的红包记录end---------------");
		return map;
	}

	@RequestMapping(value = "/jdbcTest", method = RequestMethod.GET)
	@ResponseBody
	public void jdbcTest() {
		try {
			this.myTianYService.jdbcTest();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String format_tablename(String str) {
		String tablename = "";
		if ("1".equals(str)) {
			tablename = "e_pub_factory";
		} else if ("2".equals(str)) {
			tablename = "e_pub_equipment";
		} else if ("3".equals(str)) {
			tablename = "e_logistics";
		} else if ("4".equals(str)) {
			tablename = "e_job";
		} else if ("5".equals(str)) {
			tablename = "e_tech";
		} else if ("6".equals(str)) {
			tablename = "e_cooperation";
		} else if ("7".equals(str)) {
			tablename = "e_training";
		} else if ("8".equals(str)) {
			tablename = "e_accounting";
		} else if ("9".equals(str)) {
			tablename = "e_stockright";
		} else if ("10".equals(str)) {
			tablename = "e_capital";
		} else if ("11".equals(str)) {
			tablename = "e_bill";
		} else if ("12".equals(str)) {
			tablename = "e_registration";
		} else {
			tablename = "e_pub_factory";
		}
		return tablename;
	}

	public String format_hanzi(String str) {
		String tablename = "";
		if ("1".equals(str)) {
			tablename = "厂房租售";
		} else if ("2".equals(str)) {
			tablename = "设备调剂";
		} else if ("3".equals(str)) {
			tablename = "物流服务";
		} else if ("4".equals(str)) {
			tablename = "员工招聘";
		} else if ("5".equals(str)) {
			tablename = "技术交流";
		} else if ("6".equals(str)) {
			tablename = "产业协作";
		} else if ("7".equals(str)) {
			tablename = "员工培训";
		} else if ("8".equals(str)) {
			tablename = "账务代理";
		} else if ("9".equals(str)) {
			tablename = "股权转让";
		} else if ("10".equals(str)) {
			tablename = "代理融资";
		} else if ("11".equals(str)) {
			tablename = "承兑汇票";
		} else if ("12".equals(str)) {
			tablename = "工商注册";
		} else {
			tablename = "小二跑腿";
		}
		return tablename;
	}

}
