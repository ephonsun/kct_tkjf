package com.framework.web.mngcontroller;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.entity.SessionBean;
import com.framework.core.utils.GlobalConst;
import com.framework.web.service.LoginService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("MngLoginController")
public class MngLoginController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(MngLoginController.class);
	
	@Resource
	private LoginService loginService;
	
	
	@RequestMapping(value="/loginceshi")
	public String loginceshi(ModelMap model) throws Exception{
		model.put("ceshi", "c");
		return "manage/index";
	}
	
	
	/**
	 * 用户图片验证码校验
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/checkimage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object checkimage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String managecode = (String) request.getSession().getAttribute("managecode");
		String imageCode = request.getParameter("imageCode");
		if (imageCode.equals(managecode)) {
			map.put("resultCode", 0);
			map.put("resultMsg", "验证码输入正确");
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
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object login(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws Exception{
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String email = pojo.getEmail();//用户名
		String password = pojo.getPassword();//密码
		try {
			SessionBean sessionBean = this.loginService.getSessionBean(email, password);
			if(sessionBean == null){
				map.put("resultCode", -3);
				map.put("resultMsg", "用户名或密码不对");
				map.put("resultData", list);
			}else{
				map.put("resultCode", 0);
				map.put("resultMsg", "登录成功");
				map.put("resultData", list);
				request.getSession().setAttribute(GlobalConst.SESSION_KEY, sessionBean);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		//return jsonObject.toString();
		return map;
	}
	
	@RequestMapping(value="/loginref")
	public String loginref(HttpServletRequest request,ModelMap model) throws Exception{
		String id = request.getParameter("id");
		model.put("ceshi", "c");
		if("1".equals(id)){
			return "manage/top";
		}else if("2".equals(id)){
			return "manage/left";
		}else if("3".equals(id)){
			return "manage/list_hb";
		}else if("4".equals(id)){
			
		}
		
		return null;
	}
	
	
	

}
