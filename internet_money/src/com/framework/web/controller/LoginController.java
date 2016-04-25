package com.framework.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
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
import com.framework.core.entity.SessionBean;
import com.framework.core.utils.GlobalConfig;
import com.framework.core.utils.GlobalConst;
import com.framework.core.utils.OtherUtils;
import com.framework.core.utils.RC4;
import com.framework.core.utils.SHA256;
import com.framework.web.service.GovernmentCarService;
import com.framework.web.service.LoginService;

@Controller
@RequestMapping("LoginController")
public class LoginController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource
	private LoginService loginService;

	@Resource
	private GovernmentCarService governmentCarService;

	/**
	 * 用户手机校验
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/checkuser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object checkuser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("用户注册begin---------------");
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		String mobile = request.getParameter("mobile");
		try {
			list = (List<Object>) this.governmentCarService.doSelect_user(mobile);// 查询用户填写的号码有没有注册过
			if (list.size() > 0) {
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list1);
			} else {
				map.put("resultCode", -10);
				map.put("resultMsg", "手机号不存在");
				map.put("resultData", list1);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list1);
			logger.error("用户手机校验异常", e);
		}
		logger.info("用户注册end---------------");
		return map;
	}
	
	
	/**
	 * 企业名称校验
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/checkcompany", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object checkcompany(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("企业名称校验begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String name = request.getParameter("name");
		args.put("name", name);
		try {
			list = (List<Object>) this.loginService.doSelect_username(args);// 查询企业名称是否已经存在
			if (list.size() > 0) {
				map.put("resultCode", -26);
				map.put("resultMsg", "企业名称已存在");
				map.put("resultData", list);
			} else {
				map.put("resultCode", 0);
				map.put("resultMsg", "企业名称不存在");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("用户手机校验异常", e);
			e.printStackTrace();
		}
		logger.info("企业名称校验end---------------");
		return map;
	}
	
	
	

	/**
	 * 用户注册(不需登录)
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("用户注册begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> argstj = new HashMap<>();
		Map<String, Object> argsljtj = new HashMap<>();
		String shortCode = pojo.getSmsCode();// 短信验证码
		String mobile = pojo.getMobile();// 用户手机号码
		String password = SHA256.getSHA256CheckSum(pojo.getPassword(), SHA256.SHA256KEY);// 用户密码
		String mobiletj = pojo.getMobileTuiJian();// 推荐人手机号，用来获取用户ID，如果没有此手机号，那么保存值为-1
		if ("".equals(mobiletj) || mobiletj == null) {
			mobiletj = "-99999";
		}
		// args.put("mobile", "18651900841");
		// args.put("password", "123456");
		args.put("smsCode", pojo.getSmsCode());
		args.put("mobile", pojo.getMobile());
		args.put("userName", "用户"+GlobalConfig.createUserName());
		args.put("password", SHA256.getSHA256CheckSum(pojo.getPassword(), SHA256.SHA256KEY));
		args.put("createTime", System.currentTimeMillis());
		args.put("type", "0");// 发放红包
		args.put("typelx", "0");// 用户的类型,个人用户为 0 企业用户为1
		args.put("rule_id", "1");// 先定义为 红包规则中的 1
		args.put("rule_tjid", "3");// 先定义为 红包规则中的 3
		args.put("amount", "100");// 发放的金额
		args.put("memo", "注册时发放的红包");// 情况描述
		args.put("memotj", "推荐成功发放红包");// 情况描述
		args.put("operator_id", "-10");// 表示系统注册时自动发放或者推荐时候发放
		// args.put("avatar", PoJo.picmr);//默认头像
		args.put("lastip", request.getRemoteAddr());
		List<Object> list = new ArrayList<>();
		List<Object> listsms = new ArrayList<>();
		String mobilels = (String) request.getSession().getAttribute("mobile");
		String ljtj = (String) request.getSession().getAttribute("lianjiemobile");//通过推荐人链接点击注册的用户
		try {
			if (mobilels.equals(pojo.getMobile())) {// 这次的手机号和上次的一样

				listsms = (List<Object>) this.loginService.doSelect_sms(args);
				if (listsms.size() > 0) {// 表示短信验证码正确

					argstj = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(mobiletj);
					if (argstj != null) {
						args.put("mobiletj", argstj.get("id"));
					} else {
						if(!"700".equals(ljtj) && ljtj != null){
							argsljtj = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(ljtj);
							args.put("mobiletj", argsljtj.get("id"));
						}else{
							args.put("mobiletj", "-1");
						}
					}
					list = (List<Object>) this.governmentCarService.doSelect_user(mobile);// 查询用户填写的号码有没有注册过
					if (list.size() == 0) {// list为空表示这个手机号码没有被注册
						int a = this.governmentCarService.register(args);// 插入新用户，同事查询出新用户的
																			// id，然后保存进log表
						int c = 0;
						if (argstj != null) {
							c = this.loginService.updatehongbaotj(args);// 发现推荐人存在，那么就增加一百的红包
						}else {
							if(!"700".equals(ljtj) && ljtj != null){
								args.put("ljtj", ljtj);
								c = this.loginService.updatehongbaoljtj(args);//增加一百的红包，通过推荐人链接注册得到的红包
							}
						}
						if (c > 0) {
							int d = this.governmentCarService.insertHongBaoLogTj(args);// 插入红包的日志表,推荐人的红包记录
						}
						args1 = (Map<String, Object>) this.governmentCarService.selectUserId(args);// 得到新用户的id
						String id = String.valueOf(args1.get("id"));// 新用户的ID
						args.put("user_id", id);
						int b = this.governmentCarService.insertHongBaoLog(args);// 插入红包的日志表
						map.put("resultCode", 0);
						map.put("resultMsg", "注册成功");
						map.put("resultData", list);
					} else {
						// System.out.println("已注册");
						map.put("resultCode", -1);
						map.put("resultMsg", "用户已存在");
						map.put("resultData", list);
						// response.getWriter().write(JSON.toJSONString(object));
					}
				} else {
					map.put("resultCode", -14);
					map.put("resultMsg", "短信验证码不正确");
					map.put("resultData", list);
				}
			} else {
				map.put("resultCode", -15);
				map.put("resultMsg", "手机号不正确");
				map.put("resultData", list);
			}

		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("用户注册异常", e);
		}
		logger.info("用户注册end---------------");
		return map;
	}
	
	
	
	/**
	 * 企业用户注册(不需登录)
	 * 
	 */
	@RequestMapping(value = "/registerCompany", method = RequestMethod.POST)
	@ResponseBody
	public Object registerCompany(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("企业用户注册begin---------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> argstj = new HashMap<>();
		Map<String, Object> argsljtj = new HashMap<>();
		String shortCode = pojo.getSmsCode();// 短信验证码
		String mobile = pojo.getMobile();// 用户手机号码
		String company = pojo.getName();// 公司名称
		String password = SHA256.getSHA256CheckSum(pojo.getPassword(), SHA256.SHA256KEY);// 用户密码
		String mobiletj = pojo.getMobileTuiJian();// 推荐人手机号，用来获取用户ID，如果没有此手机号，那么保存值为-1
		if ("".equals(mobiletj) || mobiletj == null) {
			mobiletj = "-99999";
		}
		// args.put("mobile", "18651900841");
		// args.put("password", "123456");
		args.put("smsCode", pojo.getSmsCode());
		args.put("mobile", pojo.getMobile());
		args.put("userName", "用户"+GlobalConfig.createUserName());
		args.put("password", SHA256.getSHA256CheckSum(pojo.getPassword(), SHA256.SHA256KEY));
		args.put("createTime", System.currentTimeMillis());
		args.put("type", "0");// 发放红包
		args.put("typelx", "1");// 用户的类型,个人用户为 0 企业用户为1
		args.put("rule_id", "1");// 先定义为 红包规则中的 1
		args.put("rule_tjid", "3");// 先定义为 红包规则中的 3
		args.put("amount", "100");// 发放的金额
		args.put("company", company);// 公司名称
		args.put("memo", "注册时发放的红包");// 情况描述
		args.put("memotj", "推荐别人注册成功时发放的红包");// 情况描述
		args.put("operator_id", "-10");// 表示系统注册时自动发放或者推荐时候发放
		// args.put("avatar", PoJo.picmr);//默认头像
		args.put("lastip", request.getRemoteAddr());
		List<Object> list = new ArrayList<>();
		List<Object> listcompany = new ArrayList<>();
		List<Object> listsms = new ArrayList<>();
		String mobilels = (String) request.getSession().getAttribute("mobile");
		String ljtj = (String) request.getSession().getAttribute("lianjiemobile");//通过推荐人链接点击注册的用户
		try {
			listcompany = (List<Object>) this.loginService.doSelect_company(args);// 查询企业名称是否已经存在
			if(listcompany.size() <= 0){
				
			
			if (mobilels.equals(pojo.getMobile())) {// 这次的手机号和上次的一样

				listsms = (List<Object>) this.loginService.doSelect_sms(args);
				if (listsms.size() > 0) {// 表示短信验证码正确

					argstj = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(mobiletj);
					if (argstj != null) {
						args.put("mobiletj", argstj.get("id"));
					} else {
						if(!"700".equals(ljtj) && ljtj != null){
							argsljtj = (Map<String, Object>) this.governmentCarService.doSelect_useridtwo(ljtj);
							args.put("mobiletj", argsljtj.get("id"));
						}else{
							args.put("mobiletj", "-1");
						}
					}
					list = (List<Object>) this.governmentCarService.doSelect_user(mobile);// 查询用户填写的号码有没有注册过
					if (list.size() == 0) {// list为空表示这个手机号码没有被注册
						int a = this.governmentCarService.register(args);// 插入新用户，同事查询出新用户的
																			// id，然后保存进log表
						int c = 0;
						if (argstj != null) {
							c = this.loginService.updatehongbaotj(args);// 发现推荐人存在，那么就增加一百的红包
						}else {
							if(!"700".equals(ljtj) && ljtj != null){
								args.put("ljtj", ljtj);
								c = this.loginService.updatehongbaoljtj(args);//增加一百的红包，通过推荐人链接注册得到的红包
							}
						}
						if (c > 0) {
							int d = this.governmentCarService.insertHongBaoLogTj(args);// 插入红包的日志表,推荐人的红包记录
						}
						args1 = (Map<String, Object>) this.governmentCarService.selectUserId(args);// 得到新用户的id
						String id = String.valueOf(args1.get("id"));// 新用户的ID
						args.put("user_id", id);
						if(a > 0){
							int d = this.governmentCarService.registerCompany(args);//公司名称保存
							int b = this.governmentCarService.insertHongBaoLog(args);// 插入红包的日志表
						}
						
						map.put("resultCode", 0);
						map.put("resultMsg", "注册成功");
						map.put("resultData", list);
					} else {
						// System.out.println("已注册");
						map.put("resultCode", -1);
						map.put("resultMsg", "用户已存在");
						map.put("resultData", list);
						// response.getWriter().write(JSON.toJSONString(object));
					}
				} else {
					map.put("resultCode", -14);
					map.put("resultMsg", "短信验证码不正确");
					map.put("resultData", list);
				}
			} else {
				map.put("resultCode", -15);
				map.put("resultMsg", "手机号不正确");
				map.put("resultData", list);
			}
			}else {
				map.put("resultCode", -26);
				map.put("resultMsg", "企业名称已存在");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("用户注册异常", e);
		}
		logger.info("用户注册end---------------");
		return map;
	}
	
	
	
	

	/**
	 * 用户图片验证码校验
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/checkpic", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object chenkpic(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String randCode = (String) request.getSession().getAttribute("randCode");
		String picCode = request.getParameter("picCode");
		if (picCode.equals(randCode)) {
			map.put("resultCode", 0);
			map.put("resultMsg", "操作成功");
			map.put("resultData", list);
		} else {
			map.put("resultCode", -9);
			map.put("resultMsg", "验证码不正确");
			map.put("resultData", list);
		}
		return map;
	}

	/**
	 * 用户登录
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pojo") PoJo pojo)
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		List<Object> list = new ArrayList<>();
		if((pojo.getMobile() == null) || (pojo.getPassword() == null)){
			map.put("resultCode", -27);
			map.put("resultMsg", "用户名和密码不能为空");
			map.put("resultData", list);
		}else{
			String mobile = pojo.getMobile();// 用户名
			args.put("lastTime", System.currentTimeMillis());
			String password = SHA256.getSHA256CheckSum(pojo.getPassword(), SHA256.SHA256KEY);// 密码
			// String password = pojo.getPassword();
			logger.info("用户登录begin-------------");
			try {
				SessionBean sessionBean = this.loginService.getSessionBean(mobile, password);
				if (sessionBean == null) {
					map.put("resultCode", -3);
					map.put("resultMsg", "用户名或密码不对");
					map.put("resultData", list);
				} else {
					args.put("id", sessionBean.getId());
					int a = this.loginService.updateLastTime(args);
					map.put("resultCode", 0);
					map.put("resultMsg", "登录成功");
					map.put("resultData", list);
					request.getSession().setAttribute(GlobalConst.SESSION_KEY, sessionBean);
				}
			} catch (Exception e) {
				map.put("resultCode", -2);
				map.put("resultMsg", "服务器异常");
				map.put("resultData", list);
				logger.error("登录异常", e);
			}
		}
		
		logger.info("用户登录end---------------");
//		response.setHeader("P3P","CP=CAO PSA OUR");
		return map;
	}

	/**
	 * 用户登录状态
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/loginstate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object loginstate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute(GlobalConst.SESSION_KEY);
		List<Object> list = new ArrayList<>();
		if (sessionBean == null) {
			map.put("resultCode", -4);
			map.put("resultMsg", "未登录");
			map.put("resultData", list);
		} else {
			args.put("userName", sessionBean.getUserName());
			args.put("mobile", sessionBean.getMobile());
			args.put("avatar", sessionBean.getAvatar());
			list.add(args);
			map.put("resultCode", 0);
			map.put("resultMsg", "已登录");
			map.put("resultData", list);
		}
		return map;
	}

	/**
	 * 用户登出
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object logout(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pojo") PoJo pojo)
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		// request.getSession().removeAttribute(GlobalConst.SESSION_KEY);
		request.getSession().invalidate();
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute(GlobalConst.SESSION_KEY);
		List<Object> list = new ArrayList<>();
		list.add(args);
		if (sessionBean == null) {
			map.put("resultCode", 0);
			map.put("resultMsg", "已退出");
			map.put("resultData", list);
		} else {
			map.put("resultCode", -4);
			map.put("resultMsg", "退出未成功");
			map.put("resultData", list);
		}
		return map;
	}

	/**
	 * 用户短信获取(不需登录)
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/getsms", method = RequestMethod.GET)
	@ResponseBody
	public Object getsms(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pojo") PoJo pojo)
			throws Exception {
		logger.info("用户短信获取begin------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> smsmap = new HashMap<>();
		String mobile = request.getParameter("mobile");// 用户手机号码
		String picCode = request.getParameter("picCode");// 用户验证码
		String randCode = (String) request.getSession().getAttribute("randCode");

		args.put("mobile", mobile);
		args.put("picCode", picCode);
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		if (picCode.equals(randCode)) {// 用户输入的和后台验证码相同的话可以发送短信
			String smstime = (String) request.getSession().getAttribute("smstime");
			System.out.println(smstime + "smstime");
			try {
				if (smstime == null) {
					smsmap = (Map<String, Object>) this.loginService.doSelect_sms(mobile);// 查询用户填写的号码有没有注册过
					if (smsmap != null) {// 不为空，作时间比对，否则直接发送短信
						long time = (long) smsmap.get("time");
						long time1 = System.currentTimeMillis() - time;
						long time2 = time1 / 1000;
						if (Integer.valueOf(String.valueOf(time2)) > 60) {
							String sms = GlobalConfig.createRandom(true, 6);// 生成短信验证码
							args.put("sms", sms);
							String result = GlobalConfig.sendTemplateSms(mobile, "JSM40152-0002",
									"@1@=先生/女士,@2@=您好,@3@=" + sms);// 发送短信验证码
							String statusStr = GlobalConfig.parseXML(result);// 解析返回码
							if ("0".equals(statusStr)) {
								request.getSession().setAttribute("mobile", mobile);
								int a = this.loginService.update_smstime(args);// 更新数据库的时间和短信验证码
								request.getSession().setAttribute("smstime",
										String.valueOf(System.currentTimeMillis()));
								map.put("resultCode", 0);
								map.put("resultMsg", "操作成功");
								map.put("resultData", list);
							}
							if ("100".equals(statusStr)) {
								map.put("resultCode", -13);
								map.put("resultMsg", "短信验证码发送失败");
								map.put("resultData", list);
							}
						} else {
							map.put("resultCode", -12);
							map.put("resultMsg", "操作过于频繁");
							map.put("resultData", list);
						}
					} else {// 为空的话发送信息，并且insert 一条信息保存
						String sms = GlobalConfig.createRandom(true, 6);// 生成短信验证码
						args.put("sms", sms);
						String result = GlobalConfig.sendTemplateSms(mobile, "JSM40152-0001",
								"@1@=" + sms + ",@2@=尊敬的天元财富网您好");// 发送短信验证码
						String statusStr = GlobalConfig.parseXML(result);// 解析返回码
						if ("0".equals(statusStr)) {
							request.getSession().setAttribute("mobile", mobile);
							int a = this.loginService.insert_sms(args);// 保存一条信息
							request.getSession().setAttribute("smstime", String.valueOf(System.currentTimeMillis()));
							map.put("resultCode", 0);
							map.put("resultMsg", "操作成功");
							map.put("resultData", list);
						}
						if ("106".equals(statusStr)) {
							map.put("resultCode", -11);
							map.put("resultMsg", "手机号不正确");
							map.put("resultData", list);
						}
					}
				} else {
					long time3 = System.currentTimeMillis() - Long.parseLong(smstime);
					long time4 = time3 / 1000;
					if (Integer.valueOf(String.valueOf(time4)) > 60) {
						smsmap = (Map<String, Object>) this.loginService.doSelect_sms(mobile);// 查询用户填写的号码有没有注册过
						if (smsmap != null) {// 不为空，作时间比对，否则直接发送短信
							long time = (long) smsmap.get("time");
							long time1 = System.currentTimeMillis() - time;
							long time2 = time1 / 1000;
							if (Integer.valueOf(String.valueOf(time2)) > 60) {
								String sms = GlobalConfig.createRandom(true, 6);// 生成短信验证码
								args.put("sms", sms);
								String result = GlobalConfig.sendTemplateSms(mobile, "JSM40152-0001",
										"@1@=" + sms + ",@2@=尊敬的天元财富网您好");// 发送短信验证码
								String statusStr = GlobalConfig.parseXML(result);// 解析返回码
								if ("0".equals(statusStr)) {
									request.getSession().setAttribute("mobile", mobile);
									int a = this.loginService.update_smstime(args);// 更新数据库的时间和短信验证码
									request.getSession().setAttribute("smstime",
											String.valueOf(System.currentTimeMillis()));
									map.put("resultCode", 0);
									map.put("resultMsg", "操作成功");
									map.put("resultData", list);
								}
								if ("100".equals(statusStr)) {
									map.put("resultCode", -13);
									map.put("resultMsg", "短信验证码发送失败");
									map.put("resultData", list);
								}
							} else {
								map.put("resultCode", -12);
								map.put("resultMsg", "操作过于频繁");
								map.put("resultData", list);
							}
						} else {// 为空的话发送信息，并且insert 一条信息保存
							String sms = GlobalConfig.createRandom(true, 6);// 生成短信验证码
							args.put("sms", sms);

							String result = GlobalConfig.sendTemplateSms(mobile, "JSM40152-0001",
									"@1@=" + sms + ",@2@=尊敬的天元财富网您好");// 发送短信验证码
							String statusStr = GlobalConfig.parseXML(result);// 解析返回码
							if ("0".equals(statusStr)) {
								request.getSession().setAttribute("mobile", mobile);
								int a = this.loginService.insert_sms(args);// 保存一条信息
								request.getSession().setAttribute("smstime",
										String.valueOf(System.currentTimeMillis()));
								map.put("resultCode", 0);
								map.put("resultMsg", "操作成功");
								map.put("resultData", list);
							}
							if ("106".equals(statusStr)) {
								map.put("resultCode", -11);
								map.put("resultMsg", "手机号不正确");
								map.put("resultData", list);
							}
						}
					} else {
						map.put("resultCode", -12);
						map.put("resultMsg", "操作过于频繁");
						map.put("resultData", list);
					}
				}
			} catch (Exception e) {
				map.put("resultCode", -2);
				map.put("resultMsg", "服务器异常");
				map.put("resultData", list);
				logger.error("用户短信获取异常", e);
			}
		} else {
			map.put("resultCode", -9);
			map.put("resultMsg", "验证码不正确");
			map.put("resultData", list);
		}
		logger.info("密码重置end---------------");
		return map;
	}

	/**
	 * 密码重置
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object resetpassword(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("密码重置begin------------");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> args = new HashMap<>();
		// request.getSession().removeAttribute(GlobalConst.SESSION_KEY);
		List<Object> list = new ArrayList<>();
		List<Object> listsms = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("password", SHA256.getSHA256CheckSum(pojo.getPassword(), SHA256.SHA256KEY));
		args.put("smsCode", pojo.getSmsCode());
		try {
			listsms = (List<Object>) this.loginService.doSelect_sms(args);
			if (listsms.size() > 0) {// 表示短信验证码正确
				int a = this.loginService.updatePassword(args);
				if (a > 0) {
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", list);
				} else {
					map.put("resultCode", -21);
					map.put("resultMsg", "密码重置失败");
					map.put("resultData", list);
				}
			} else {
				map.put("resultCode", -14);
				map.put("resultMsg", "短信验证码不正确");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("密码重置异常", e);
		}
		logger.info("密码重置end---------------");
		return map;
	}

	/**
	 * 
	 * @Title: name @Description: TODO(测试获取用户IP地址) @param @return 参数说明 @return
	 * return_type 返回类型 @throws
	 */
	@RequestMapping(value = "/ceshiIP", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object ceshiIP(HttpServletRequest request) {
		logger.info("测试获取用户IP地址begin------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		
		String code = null;
		String ip = OtherUtils.getIpAddr(request);
		System.out.println("用户IP=" + ip);
		HashMap<String, Object> map = OtherUtils.JsonParseIp(ip);
		try {
			if("0".equals(map.get("errNum"))){
				System.out.println("用户所在的城市=" + map.get("city"));
				String city = (String) map.get("city");
				String ceshi = "南京";
				args = (Map<String, Object>) this.loginService.selectCityCode(ceshi);
				if(args != null){
					code = (String) args.get("code");
					map.put("code", code);
				}else{
					map.put("code", "320100");
				}
			}else {
				map.put("code", "320100");
			}
		} catch (Exception e) {
			logger.error("测试获取用户IP地址异常", e);
			map.put("code", "320100");
		}
		logger.info("测试获取用户IP地址end---------------");
		return map;
	}

	/**
	 * @throws IOException
	 * 
	 * @Title: urlRdirect @Description:
	 * TODO(解析参数，保存mobile到session，并跳转到新url) @param @param request @param @param
	 * response @param @throws IOException 参数说明 @return void 返回类型 @throws
	 */
	@RequestMapping(value = "/redirect", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//request.getSession().setAttribute("mobileSHA256", "mobileSHA256123");
		List<Object> list = new ArrayList<>();
		String mobile = RC4.decry_RC4_string(request.getParameter("param1"), SHA256.RC4KEY);

		String url = RC4.decry_RC4_string(request.getParameter("param2"), SHA256.RC4KEY);
		try {
			list = (List<Object>) this.governmentCarService.doSelect_user(mobile);// 查询用户填写的号码有没有注册过
			if (list.size() == 0) {
				request.getSession().setAttribute("lianjiemobile", 700);
			} else {
				request.getSession().setAttribute("lianjiemobile", mobile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// request.getSession().getAttribute("mobileSHA256");
		response.sendRedirect(url);

	}

	/**
	 * 
	 * @Title: redirect @Description: TODO(生成URL分享链接) @param @param
	 * request @param @param response @param @throws IOException 参数说明 @return
	 * void 返回类型 @throws
	 */
	@RequestMapping(value = "/urlRdirect", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object urlRdirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute(GlobalConst.SESSION_KEY);
		String mobile = RC4.encry_RC4_string(sessionBean.getMobile(), SHA256.RC4KEY);
		String url = RC4.encry_RC4_string(request.getParameter("url"), SHA256.RC4KEY);
		String longurl = "http://www.tkjfw.net/internet_money/LoginController/redirect.do?param1=" + mobile + "&param2=" + url;
		String shorturl = OtherUtils.getShortUrl(longurl);
		if ("0".equals(shorturl)) {
			list.add(longurl);
			map.put("resultCode", 800);
			map.put("resultMsg", "短地址生成失败");
			map.put("resultData", list);
		} else {
			list.add(shorturl);
			map.put("resultCode", 0);
			map.put("resultMsg", "短地址生成成功");
			map.put("resultData", list);
		}
		return map;
		// request.getSession().setAttribute("mobileSHA256", "mobileSHA256123");
		// request.getSession().getAttribute("mobileSHA256");
		// response.sendRedirect("http://www.tkjfw.net");

	}

}
