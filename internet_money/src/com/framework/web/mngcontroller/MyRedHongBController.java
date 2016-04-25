package com.framework.web.mngcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.framework.web.service.GovernmentCarService;
import com.framework.web.service.MngMyRedHongBManageService;
import com.framework.web.service.MyRedEnvelopeService;

@Controller
@RequestMapping("ManagementController")
public class MyRedHongBController extends BaseController {
	
	private static List<String> mobileList = new ArrayList<>();
	
	private static Logger logger = LoggerFactory.getLogger(MyRedHongBController.class);

	@Resource
	private MyRedEnvelopeService myRedEnvelopeService;

	@Resource
	private MngMyRedHongBManageService myRedHongBManageService;

	@Resource
	private GovernmentCarService governmentCarService;

	/**
	 * 跳转到红包消费初始页面
	 * 
	 */
	@RequestMapping(value = "/hongbao_xf", method = RequestMethod.GET)
	public ModelAndView selectnews(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		return new ModelAndView("manage/grant_hb");
	}

	/**
	 * 红包发放初始页面
	 * 
	 */
	@RequestMapping(value = "/hongbao_ff", method = RequestMethod.GET)
	public ModelAndView hongbao_ff(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		return new ModelAndView("manage/consume");
	}

	/**
	 * 跳转到红包规则添加页面
	 * 
	 */
	@RequestMapping(value = "/hongbao_add", method = RequestMethod.GET)
	public ModelAndView hongbao_add(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		return new ModelAndView("manage/add_hb");
	}

	/**
	 * 红包消费初始页面
	 * 
	 */
	@RequestMapping(value = "/selectUserhongbao_ff", method = RequestMethod.GET)
	public ModelAndView selectUserhongbao_ff(HttpServletRequest request, ModelMap model,
			@ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		List<Object> list = new ArrayList<>();
		ModelAndView mav = new ModelAndView("hongbao-userfaf");
		String userid = request.getParameter("id");
		String user_lx = "4";
		args.put("user_lx", user_lx);
		args.put("user", userid);
		try {
			list = (List<Object>) this.myRedHongBManageService.selectuser(args);// 查询用户填写的号码有没有注册过
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
			e.printStackTrace();
		}
		mav.addObject("map", map);
		return mav;
	}

	/**
	 * 红包消费初始页面
	 * 
	 */
	@RequestMapping(value = "/selectUserhongbao", method = RequestMethod.GET)
	public ModelAndView selectUserhongbao(HttpServletRequest request, ModelMap model,
			@ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		List<Object> list = new ArrayList<>();
		ModelAndView mav = new ModelAndView("hongbao-user");
		String userid = request.getParameter("id");
		String user_lx = "4";
		args.put("user_lx", user_lx);
		args.put("user", userid);
		try {
			list = (List<Object>) this.myRedHongBManageService.selectuser(args);// 查询用户填写的号码有没有注册过
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
			e.printStackTrace();
		}
		mav.addObject("map", map);
		return mav;
	}

	/**
	 * 查找用户
	 * 
	 */
	@RequestMapping(value = "/gethongbao", method = RequestMethod.GET)
	@ResponseBody
	public Object gethongbao(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String mobile = request.getParameter("mobile");
		args.put("mobile", mobile);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myRedHongBManageService.gethongbao(args);
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			} else {
				map.put("resultCode", -10);
				map.put("resultMsg", "手机号不存在");
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
	 * 红包消费
	 * 
	 */
	@RequestMapping(value = "/usehongbao", method = RequestMethod.POST)
	@ResponseBody
	public Object usehongbao(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapid = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> resultmap = new HashMap<>();
		String mobile = pojo.getMobile();
		String cost = pojo.getCost();// 消费金额
		// String type = request.getParameter("type");// （1：星空咖啡）
		String operation = pojo.getOperation();// 操作 （0：计算；1：扣除）
		args.put("mobile", mobile);
		args.put("type", "1");// 消费红包
		args.put("rule_id", "2");// 先定义为 红包规则中的 2
		args.put("memo", "星空咖啡消费");// 情况描述
		args.put("createTime", System.currentTimeMillis());
		args.put("operator_id", this.getUserId());
		int moneyNum = Integer.valueOf(cost);// 总消费金额
		double xfje = Double.valueOf(cost);
		int zkNum = (int) Math.ceil(xfje * 0.3);// 红包可抵扣金额
		args.put("cost", cost);
		// int moneyNum =
		// OtherUtils.format_Number(cost);//计算一共要打的折扣是多少钱,折扣的力度是70%
		// int zkNum = (int) (moneyNum * 0.3);//可以打的折扣是这么多，但是要考虑余额够不够
		int xkkf = 0;// 还要给星空咖啡多少钱
		int b = 0;
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		if("13585175697".equals(this.getMobile())){// 15195765664
			
		
		try {
			mapid = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(mobile);
			if (mapid != null) {
				args.put("user_id", mapid.get("id"));
				map1 = (Map<String, Object>) this.myRedHongBManageService.gethongbaoMap(args);  
				if (map1 != null) {
					int yeNum = (int) map1.get("amount");// 现在红包的余额
					if (yeNum >= zkNum) {// 表示余额够用
						int sxNum = yeNum - zkNum;
						args.put("sxnum", sxNum);
						args.put("amount", zkNum);
						if ("1".equals(operation)) {
							b = this.myRedHongBManageService.updatehongbao(args);
							if (b > 0) {
								int c = this.myRedHongBManageService.insertXkkfHongBaoLogTj(args);
							}
						}
						resultmap.put("hongbao", zkNum);
						resultmap.put("cash", moneyNum - zkNum);
						list.add(resultmap);
					} else {// 表示余额不够，只能抵扣部分
						args.put("amount", yeNum);
						if ("1".equals(operation)) {
							b = this.myRedHongBManageService.updatehongbaoZero(args);
							if(b > 0){
								int c = this.myRedHongBManageService.insertXkkfHongBaoLogTj(args);
							}
						}
						resultmap.put("hongbao", yeNum);
						resultmap.put("cash", moneyNum - yeNum);
						list.add(resultmap);
					}
					if ("0".equals(operation)) {
						map.put("resultCode", 0);
						map.put("resultMsg", "红包消费结果查询成功");
						map.put("resultData", list);
					} else {
						if (b > 0) {
							// list = (List<Object>)
							// this.myRedHongBManageService.selectuser(args);
							map.put("resultCode", 0);
							map.put("resultMsg", "操作成功");
							map.put("resultData", list);
						} else {
							map.put("resultCode", -24);
							map.put("resultMsg", "红包未成功消费");
							map.put("resultData", list1);
						}
					}
				} else {
					map.put("resultCode", -24);
					map.put("resultMsg", "红包未成功消费");
					map.put("resultData", list1);
				}
			} else {
				map.put("resultCode", -10);
				map.put("resultMsg", "手机号不存在");
				map.put("resultData", list1);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		}else {
			map.put("resultCode", -25);
			map.put("resultMsg", "无此操作权限");
			map.put("resultData", list);
		}
		return map;
	}
	
	
	
	/**
	 * 金融消费
	 * 
	 */
	@RequestMapping(value = "/usehongbaoFinance", method = RequestMethod.POST)
	@ResponseBody
	public Object usehongbaoFinance(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapid = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> resultmap = new HashMap<>();
		String mobile = pojo.getMobile();
		String cost = pojo.getCost();// 服务费
		// String type = request.getParameter("type");
		String operation = pojo.getOperation();// 操作 （0：计算；1：扣除）
		args.put("mobile", mobile);
		args.put("type", "1");// 消费红包
		args.put("rule_id", "4");// 先定义为 红包规则中的 4
		args.put("memo", "金融服务消费");// 情况描述
		args.put("createTime", System.currentTimeMillis());
		args.put("operator_id", this.getUserId());
		int moneyNum = Integer.valueOf(cost);// 服务费金额
		int maxMoney = 500;//最大抵扣500元
		int zkNum;
//		double xfje = Double.valueOf(cost);
		if(moneyNum > maxMoney){
			zkNum = maxMoney;
		}else {
			zkNum = moneyNum;
		}
//		int zkNum = (int) Math.ceil(xfje * 0.3);// 红包可抵扣金额
		args.put("cost", cost);
		int xkkf = 0;
		int b = 0;
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		if(mobileList.contains(this.getMobile())){//当前登录人的号码，是否有操作权限
			
		
		try {
			mapid = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(mobile);//
			if (mapid != null) {
				args.put("user_id", mapid.get("id"));
				map1 = (Map<String, Object>) this.myRedHongBManageService.gethongbaoMap(args);
				if (map1 != null) {
					int yeNum = (int) map1.get("amount");// 现在红包的余额
					if (yeNum >= zkNum) {// 表示余额够用
						int sxNum = yeNum - zkNum;
						args.put("sxnum", sxNum);
						args.put("amount", zkNum);
						if ("1".equals(operation)) {
							b = this.myRedHongBManageService.updatehongbao(args);
							if (b > 0) {
								int c = this.myRedHongBManageService.insertXkkfHongBaoLogTj(args);
							}
						}
						resultmap.put("hongbao", zkNum);
						resultmap.put("cash", moneyNum - zkNum);
						list.add(resultmap);
					} else {// 表示余额不够，只能抵扣部分
						args.put("amount", yeNum);
						if ("1".equals(operation)) {
							b = this.myRedHongBManageService.updatehongbaoZero(args);
							if(b > 0){
								int c = this.myRedHongBManageService.insertXkkfHongBaoLogTj(args);
							}
						}
						resultmap.put("hongbao", yeNum);
						resultmap.put("cash", moneyNum - yeNum);
						list.add(resultmap);
					}
					if ("0".equals(operation)) {
						map.put("resultCode", 0);
						map.put("resultMsg", "红包消费结果查询成功");
						map.put("resultData", list);
					} else {
						if (b > 0) {
							// list = (List<Object>)
							// this.myRedHongBManageService.selectuser(args);
							map.put("resultCode", 0);
							map.put("resultMsg", "操作成功");
							map.put("resultData", list);
						} else {
							map.put("resultCode", -24);
							map.put("resultMsg", "红包未成功消费");
							map.put("resultData", list1);
						}
					}
				} else {
					map.put("resultCode", -24);
					map.put("resultMsg", "红包未成功消费");
					map.put("resultData", list1);
				}
			} else {
				map.put("resultCode", -10);
				map.put("resultMsg", "手机号不存在");
				map.put("resultData", list1);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		}else {
			map.put("resultCode", -25);
			map.put("resultMsg", "无此操作权限");
			map.put("resultData", list);
		}
		return map;
	}
	
	
	
	
	
	/**
	 * 代办、咨询服务
	 * 
	 */
	@RequestMapping(value = "/usehongbaoCommission", method = RequestMethod.POST)
	@ResponseBody
	public Object usehongbaoCommission(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapid = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> resultmap = new HashMap<>();
		String mobile = pojo.getMobile();
		String cost = pojo.getCost();// 服务费
		// String type = request.getParameter("type");// （1：星空咖啡）
		String operation = pojo.getOperation();// 操作 （0：计算；1：扣除）
		args.put("mobile", mobile);
		args.put("type", "1");// 消费红包
		args.put("rule_id", "5");// 先定义为 红包规则中的 5
		args.put("memo", "代办、咨询服务消费");// 情况描述
		args.put("createTime", System.currentTimeMillis());
		args.put("operator_id", this.getUserId());
		int moneyNum = Integer.valueOf(cost);// 服务费金额
		double xfje = Double.valueOf(cost);
		int zkNum = (int) Math.ceil(xfje * 0.1);// 红包可抵扣金额
		args.put("cost", cost);
		int b = 0;
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		if(mobileList.contains(this.getMobile())){//当前登录人的号码，是否有操作权限
			
		
		try {
			mapid = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(mobile);//
			if (mapid != null) {
				args.put("user_id", mapid.get("id"));
				map1 = (Map<String, Object>) this.myRedHongBManageService.gethongbaoMap(args);
				if (map1 != null) {
					int yeNum = (int) map1.get("amount");// 现在红包的余额
					if (yeNum >= zkNum) {// 表示余额够用
						int sxNum = yeNum - zkNum;
						args.put("sxnum", sxNum);
						args.put("amount", zkNum);
						if ("1".equals(operation)) {
							b = this.myRedHongBManageService.updatehongbao(args);
							if (b > 0) {
								int c = this.myRedHongBManageService.insertXkkfHongBaoLogTj(args);
							}
						}
						resultmap.put("hongbao", zkNum);
						resultmap.put("cash", moneyNum - zkNum);
						list.add(resultmap);
					} else {// 表示余额不够，只能抵扣部分
						args.put("amount", yeNum);
						if ("1".equals(operation)) {
							b = this.myRedHongBManageService.updatehongbaoZero(args);
							if(b > 0){
								int c = this.myRedHongBManageService.insertXkkfHongBaoLogTj(args);
							}
						}
						resultmap.put("hongbao", yeNum);
						resultmap.put("cash", moneyNum - yeNum);
						list.add(resultmap);
					}
					if ("0".equals(operation)) {
						map.put("resultCode", 0);
						map.put("resultMsg", "红包消费结果查询成功");
						map.put("resultData", list);
					} else {
						if (b > 0) {
							// list = (List<Object>)
							// this.myRedHongBManageService.selectuser(args);
							map.put("resultCode", 0);
							map.put("resultMsg", "操作成功");
							map.put("resultData", list);
						} else {
							map.put("resultCode", -24);
							map.put("resultMsg", "红包未成功消费");
							map.put("resultData", list1);
						}
					}
				} else {
					map.put("resultCode", -24);
					map.put("resultMsg", "红包未成功消费");
					map.put("resultData", list1);
				}
			} else {
				map.put("resultCode", -10);
				map.put("resultMsg", "手机号不存在");
				map.put("resultData", list1);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		}else {
			map.put("resultCode", -25);
			map.put("resultMsg", "无此操作权限");
			map.put("resultData", list);
		}
		return map;
	}
	
	
	

	/**
	 * 红包发放
	 * 
	 */
	@RequestMapping(value = "/hongbao_ff", method = RequestMethod.POST)
	@ResponseBody
	public Object hongbao_ff(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") MngPoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		String money = pojo.getMoney();
		String user_id = pojo.getUser_id();
		args.put("hongbao", money);
		args.put("id", user_id);
		List<Object> list = new ArrayList<>();
		try {
			int a = this.myRedHongBManageService.hongbao_ff(args);
			if (a > 0) {
				list = (List<Object>) this.myRedHongBManageService.selectuser(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
				map.put("id", user_id);
			} else {
				map.put("resultCode", -24);
				map.put("resultMsg", "红包未成功消费");
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
	 * 用户红包余额查看(列表)
	 * 
	 */
	@RequestMapping(value = "/hongbao_yue", method = RequestMethod.GET)
	public ModelAndView hongbao_yue(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("manage/list_hb");
		String page = request.getParameter("page");// page
		// String zflx_id = request.getParameter("zflx_id");
		String curPageNum = "";
		if ("".equals(page) || page == null) {
			page = "1";
			curPageNum = "1";
		} else {
			curPageNum = page;
		}
		int num = 15;
		args.put("num", num);
		// args.put("zflx_id", zflx_id);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myRedHongBManageService.hongbao_yue(args);// 查询红包列表
			int totalCount = this.myRedHongBManageService.hongbao_yueNum(args);
			int totalPageNum = (totalCount + num - 1) / num;
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
				map.put("totalCount", totalCount);
				map.put("totalPageNum", totalPageNum);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
				// response.getWriter().write(JSON.toJSONString(object));
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
		// mav.addObject("zflx_id", zflx_id);
		return mav;
	}

	/**
	 * 用户红包余额查询(手机号码)
	 * 
	 */
	@RequestMapping(value = "/searchMobile", method = RequestMethod.GET)
	public ModelAndView searchMobile(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("manage/list_hb");
		String page = request.getParameter("page");// page
		String type = request.getParameter("op");
		String mobile = request.getParameter("mobile");
		// String zflx_id = request.getParameter("zflx_id");
		String curPageNum = "";
		if ("".equals(page) || page == null) {
			page = "1";
			curPageNum = "1";
		} else {
			curPageNum = page;
		}
		int num = 15;
		args.put("num", num);
		// args.put("zflx_id", zflx_id);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		args.put("mobile", mobile);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myRedHongBManageService.searchMobile(args);// 查询红包列表
			int totalCount = this.myRedHongBManageService.searchMobileNum(args);
			int totalPageNum = (totalCount + num - 1) / num;
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
				map.put("totalCount", totalCount);
				map.put("totalPageNum", totalPageNum);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
				// response.getWriter().write(JSON.toJSONString(object));
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
		// mav.addObject("zflx_id", zflx_id);
		return mav;
	}

	/**
	 * 用户红包余额查询(红包余额范围)
	 * 
	 */
	@RequestMapping(value = "/searchYe", method = RequestMethod.GET)
	public ModelAndView searchYe(HttpServletRequest request, ModelMap model, @ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		ModelAndView mav = new ModelAndView("manage/list_hb");
		String page = request.getParameter("page");// page
		String max = request.getParameter("max");
		String min = request.getParameter("min");
		String typeNum = "3";
		String curPageNum = "";
		if ("".equals(page) || page == null) {
			page = "1";
			curPageNum = "1";
		} else {
			curPageNum = page;
		}
		int num = 4;
		args.put("num", num);
		// args.put("zflx_id", zflx_id);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		args.put("max", max);
		args.put("min", min);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.myRedHongBManageService.searchYe(args);// 查询红包列表
			int totalCount = this.myRedHongBManageService.searchYeNum(args);
			int totalPageNum = (totalCount + num - 1) / num;
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
				map.put("totalCount", totalCount);
				map.put("totalPageNum", totalPageNum);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
				// response.getWriter().write(JSON.toJSONString(object));
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
		mav.addObject("typeNum", typeNum);
		mav.addObject("max", max);
		mav.addObject("min", min);
		// mav.addObject("zflx_id", zflx_id);
		return mav;
	}
	
	
	
	static{
		mobileList.add("17712890502");
		mobileList.add("13921442298");
		mobileList.add("15861803616");
		mobileList.add("13951013145");
		mobileList.add("18651853778");
		mobileList.add("15195765664");
		mobileList.add("18651900841");
		mobileList.add("13913811274");
		mobileList.add("13101890263");
		mobileList.add("18018030255");
		mobileList.add("18915949520");
		mobileList.add("13951678531");
		mobileList.add("13770907966");
		mobileList.add("13901594101");
		mobileList.add("15996348677");
		mobileList.add("13952023906");
		mobileList.add("15950482223");
		mobileList.add("18663729227");
		mobileList.add("15950528829");
		mobileList.add("15305156065");
		mobileList.add("18652979831");
		mobileList.add("15150679890");
		mobileList.add("13952024607");
		mobileList.add("18751850888");
		mobileList.add("13770630190");
		mobileList.add("18120139953");
		mobileList.add("15195896998");
		mobileList.add("18061776658");
		mobileList.add("18118805899");
		mobileList.add("13776568944");
		mobileList.add("15261491948");
		mobileList.add("18705170213");
		mobileList.add("13951961181");
		mobileList.add("13584010107");
		mobileList.add("15094310204");
		mobileList.add("15950451813");
		mobileList.add("15951851145");
		mobileList.add("13611591327");
		mobileList.add("13912912535");
		mobileList.add("15605155389");
		mobileList.add("13776549818");
		mobileList.add("13770957911");
		mobileList.add("15261891455");
		mobileList.add("13357719149");
		mobileList.add("15996367628");
	}

}
