package com.framework.core.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.framework.core.entity.SessionBean;
import com.framework.core.entity.WebResult;

public class AuthInterceptor implements HandlerInterceptor {

private List<String> excludeUrls;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		boolean isBoolean = false;
		for(int i=0;i<excludeUrls.size();i++){//如果地址配置在spring-mvc.xml中，那么不需要登录就可以操作
			if(request.getRequestURI().equals(excludeUrls.get(i))){
				isBoolean = true;
				break;
			}
		}
		if (!isBoolean) {//没有配置过地址的，表示需要进行登录进行验证后，再操作
			HttpSession session = ContextHolderUtils.getSession();
			if(session!=null){
				//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/sessionOut.jsp";
				try {
					SessionBean sessionBean = (SessionBean)session.getAttribute(GlobalConst.SESSION_KEY);
					if(sessionBean==null){
						//response.sendRedirect(basePath);
						WebResult webResult = new WebResult();
						webResult.setResultCode(-4);
						webResult.setResultMsg("未登录");
						webResult.setResultData(new ArrayList<>());
						response.setHeader("Content-type", "text/html;charset=UTF-8");//告诉浏览器用UTF-8来解析数据
						response.getWriter().write(JSON.toJSONString(webResult));
						return false;
					}else{
						response.setHeader("P3P", "CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
						request.setCharacterEncoding("utf-8");
						return true;
					}
				} catch (ClassCastException e) {
					//response.sendRedirect(basePath);
					return false;
				}
			}else{
				request.setAttribute("type", "3");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return false;
			}
		}
		return isBoolean;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}
