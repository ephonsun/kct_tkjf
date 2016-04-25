package com.framework.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.core.Is;
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
import com.framework.web.service.MoneyApplyService;
import com.framework.web.test.ExportExcel;
import com.framework.web.test.MoneyApply;

@Controller
@RequestMapping("MoneyApplyController")
public class MoneyApplyController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MoneyApplyController.class);

	@Resource
	private MoneyApplyService moneyApplyService;

	/**
	 * 融资增信
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyloan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyloan(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("融资增信begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company", pojo.getCompany());
		args.put("industry", pojo.getIndustry());
		args.put("amount", pojo.getAmount());
		args.put("mode", pojo.getMode());// 0：不限；1：股权融资；2：贷款融资；3：担保融资；4：租赁融资；5：代理融资
		args.put("days", pojo.getDays());
		args.put("asset", pojo.getAsset());
		args.put("sales", pojo.getSales());
		args.put("memo", pojo.getMemo());
		args.put("email", pojo.getEmail());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());// 0 未审核 1 审核中 2 已通过申请
		try {
			int a = this.moneyApplyService.moneyApply_insert(args);
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
			logger.error("融资增信异常", e);
		}
		logger.info("融资增信end---------------");
		return map;
	}

	/**
	 * 保险预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyinsurance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyinsurance(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("保险预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("category", pojo.getCategory());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());// 0 未审核 1 审核中 2 已通过申请
		try {
			int a = this.moneyApplyService.applyinsurance(args);
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
			logger.error("保险预约异常", e);
		}
		logger.info("保险预约begin---------------");
		return map;
	}

	/**
	 * 账务代理预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyaccounting", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyaccounting(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("账务代理预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("category", pojo.getCategory());
		args.put("month", pojo.getMonth());
		args.put("price", pojo.getPrice());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());// 0 未审核 1 审核中 2 已通过申请
		try {
			int a = this.moneyApplyService.applyaccounting(args);
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
			logger.error("账务代理预约异常", e);
		}
		logger.info("账务代理预约begin---------------");
		return map;
	}

	/**
	 * 贷款竞标
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/loanbid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object loanbid(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("贷款竞标begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();

		args.put("company", pojo.getCompany());
		args.put("industry", pojo.getIndustry());
		args.put("name", pojo.getName());
		args.put("mobile", pojo.getMobile());
		args.put("email", pojo.getEmail());
		args.put("product", pojo.getProduct());
		args.put("sales", pojo.getSales());
		args.put("loanAmount", pojo.getLoanAmount());
		args.put("loanBank", pojo.getLoanBank());
		args.put("loanRate", pojo.getLoanRate());
		args.put("loanCertificate", pojo.getLoanCertificate());
		args.put("memo", pojo.getMemo());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.moneyApplyService.loanbid_insert(args);
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
			logger.error("融资增信异常", e);
		}
		logger.info("贷款竞标end---------------");
		return map;
	}

	/**
	 * 理财预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applylicai", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applylicai(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("理财预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> listUser = new ArrayList<>();
		args.put("licai_id", "1");// OtherUtils.EmptToNull(pojo.getLicai_id())
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("amount", OtherUtils.EmptToNull(pojo.getAmount()));
		args.put("mobile", OtherUtils.EmptToNull(pojo.getMobile()));
		args.put("referrer", OtherUtils.EmptToNull(pojo.getReferrer()));
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		args.put("memo", "横西街道理财项目");
		try {
			listUser = (List<Object>) this.moneyApplyService.selectUser(args);
			if (listUser.size() > 0) {
				map.put("resultCode", -29);
				map.put("resultMsg", "您已预约本次理财项目");
				map.put("resultData", list);
			} else {
				int a = this.moneyApplyService.applylicai(args);
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
			logger.error("理财预约异常", e);
		}
		logger.info("理财预约end---------------");
		return map;
	}

	/**
	 * 我要融资EXCEL导出
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/loanExceptExcel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object loanExceptExcel(HttpServletResponse response) throws Exception {
		logger.info("我要融资EXCEL导出begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = null;
		List<MoneyApply> listMoneyApply = new ArrayList<>();
		MoneyApply mA = null;
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String time = simple.format(date);
//		String docsPath = File.separator + "data" + File.separator + "wwwroot" + File.separator + "www.tkjfw.net"
//				+ File.separator + "excel" + File.separator + time + ".xls";//linux生成路径
		String docsPath = "D:\\"+time+".xls";
		String docsName = time + ".xls";
		ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
		String[] headers = { "企业名称", "姓名", "用户名", "金额", "描述", "发布日期" };
		List<MoneyApply> dataset = new ArrayList<MoneyApply>();
		InputStream is = null;
		OutputStream out = null;
		OutputStream out1 = null;
		try {

			listMoneyApply = (List<MoneyApply>) this.moneyApplyService.selectMoneyApplyClass(args);
			System.out.println(listMoneyApply.size());
			if (listMoneyApply.size() > 0) {
				for (int i = 0; i < listMoneyApply.size(); i++) {
//					map1 = (Map<String, Object>) listMoneyApply.get(i);
					// System.out.println(map1.get("company"));
					// System.out.println(map1.get("name"));
					// System.out.println(map1.get("mobile"));
					// System.out.println(map1.get("money"));
					// System.out.println(map1.get("memo"));
					// System.out.println(map1.get("time"));
					dataset.add(listMoneyApply.get(i));
				}
			}
			// dataset.add(new MoneyApply("123", "张三", "123", "123",
			// "123","18651900841"));

			out = new FileOutputStream(docsPath);
			ex.exportExcel(headers, dataset, out);
			out.close();

			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition","attachment;filename="+docsName);
			out1 = response.getOutputStream();
			byte[] bytes = new byte[0xffff];
			is = new FileInputStream(new File(docsPath));
			int b = 0;
			while ((b = is.read(bytes, 0, 0xffff)) > 0) {
				out1.write(bytes, 0, b);
			}
			is.close();
			out1.flush();
//			out1.close();
			File file = new File(docsPath);
			if(file.exists() && file.isFile()){
				file.delete();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			is.close();
			out.close();
			out1.close();
		}

		logger.info("我要融资EXCEL导出end---------------");
		return map;
	}
	
	
	/**
	 * 我要融资EXCEL导出
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object test(HttpServletResponse response) throws Exception {
		logger.info("我要融资EXCEL导出begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = null;
		List<MoneyApply> listMoneyApply = new ArrayList<>();
		
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String time = simple.format(date);
		ExportExcel<MoneyApply> ex = new ExportExcel<MoneyApply>();
		String[] headers = { "企业名称", "姓名", "用户名", "金额", "描述", "发布日期" };
		List<MoneyApply> dataset = new ArrayList<MoneyApply>();

		try {

			listMoneyApply = (List<MoneyApply>) this.moneyApplyService.selectMoneyApplyClass(args);
			if (listMoneyApply.size() > 0) {
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
				
				
				for (int i = 0; i < listMoneyApply.size(); i++) {
					
					
					dataset.add(listMoneyApply.get(i));
				}
			}
			System.out.println(dataset);
			// dataset.add(new MoneyApply("123", "张三", "123", "123",
			// "123","18651900841"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("我要融资EXCEL导出end---------------");
		return map;
	}

}
