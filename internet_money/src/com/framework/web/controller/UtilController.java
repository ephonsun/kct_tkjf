package com.framework.web.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.utils.Utils;
import com.framework.web.service.UtilService;
import com.framework.web.test.ExportExcel;
import com.framework.web.test.MoneyApply;


@Controller
@RequestMapping("UtilController")
public class UtilController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	@Resource
	private UtilService utilService;
	
	/**
	 * 统一EXCEL导出
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object test(HttpServletResponse response,HttpServletRequest request) throws Exception {
		logger.info("统一EXCEL导出begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String name = request.getParameter("name");
		String time = Utils.formatTime();//当前时间格式
		
		HashMap<String, Object> maplist = freeList(name);
		
		ArrayList<?> list = (ArrayList<?>) maplist.get("classlist");
		ExportExcel<?> ex = (ExportExcel<?>) maplist.get("exportexcel");
		
//		List<MoneyApply> listMoneyApply = new ArrayList<>();
//		ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
		
		String[] headers = { "企业名称", "姓名", "用户名", "金额", "描述", "发布日期" };
		
//		List<MoneyApply> dataset = new ArrayList<MoneyApply>();
		try {
			list = (ArrayList<?>) this.utilService.selectMoneyApplyClass(args);
			if (list.size() > 0) {
//				Iterator<MoneyApply> it = listMoneyApply.iterator();
//				System.out.println(it.hasNext());
//				while(it.hasNext()){
//					MoneyApply t = it.next();
//					Field[] fields = t.getClass().getDeclaredFields();
//					for(int i=0;i< fields.length;i++){
//						Field field = fields[i];
//						System.out.println(field.getName());
//					}
//					
//				}
//				for (int i = 0; i < listMoneyApply.size(); i++) {
//					dataset.add(listMoneyApply.get(i));
//				}
			}
//			System.out.println(dataset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("统一EXCEL导出end---------------");
		return map;
	}
	
	public HashMap<String, Object> freeList(String name) {
		HashMap<String, Object> map = new HashMap<>();
		if("wyrz".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("jwrz".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("dksj".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("bxtg".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("wyzc".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("gqtz".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("dlrz".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("dgf".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("cfzs".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("sbtj".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("qmch".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("gszc".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("zwdl".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("rsfw".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("ygpx".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("ygzp".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("glzx".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("flsw".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("csgl".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("wlfw".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("cdhp".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("zlsq".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("zcpg".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("wach".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("kybg".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("gqzr".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else if("zhpz".equals(name)){
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}else {
			ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
			List<MoneyApply> list = new ArrayList<>();
			map.put("classlist", list);
			map.put("exportexcel", ex);
		}
		return map;
		
	}
	
	
	
	

}
