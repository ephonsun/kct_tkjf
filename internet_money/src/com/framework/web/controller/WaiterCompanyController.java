package com.framework.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.entity.pubstockrightsBean;
import com.framework.core.entity.pubaccountingBean;
import com.framework.core.entity.pubactivityBean;
import com.framework.core.entity.pubbillBean;
import com.framework.core.entity.pubcapitalBean;
import com.framework.core.entity.pubcooperationBean;
import com.framework.core.entity.pubequipmentBean;
import com.framework.core.entity.pubfactoryBean;
import com.framework.core.entity.pubjobBean;
import com.framework.core.entity.publogisticsBean;
import com.framework.core.entity.pubtrainingBean;
import com.framework.core.entity.serviceconsultBean;
import com.framework.core.utils.OtherUtils;
import com.framework.core.utils.SHA1;
import com.framework.web.service.WaiterCompanyService;

@Controller
@RequestMapping("WaiterCompanyController")
public class WaiterCompanyController extends BaseController {
	
	private static final String TOKEN = "weixintycfwcomcn";
	
	private static Logger logger = LoggerFactory.getLogger(WaiterCompanyController.class);

	@Resource
	private WaiterCompanyService waiterCompanyService;

	/**
	 * 获取各类信息的列表(不需登录)
	 * 
	 */
	@RequestMapping(value = "/getwaiterlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getwaiterlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取各类信息的列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		String category = request.getParameter("category");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("table_name", format_tablename(request.getParameter("category")));
		args.put("district", request.getParameter("district"));
		//args.put("type", request.getParameter("type"));
		args.put("side", request.getParameter("side"));
		args.put("page", page);
		args.put("user_id", this.getUserId());
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		args.put("category", category);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getwaiterlist(args);
			int totalCount = this.waiterCompanyService.getwaiterlistNum(args);
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
			logger.error("获取各类信息的列表异常",e);
		}
		logger.info("获取各类信息的列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 咨询企业治理相关服务
	 * 
	 */
	@RequestMapping(value="/companyconsult",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object companyconsult(HttpServletRequest request,@ModelAttribute("bean")serviceconsultBean bean) {
		logger.info("咨询企业治理相关服务begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("table_name", format_tablename(bean.getCategory()));
//		args.put("category", bean.getCategory());
		args.put("company", bean.getCompany());
		args.put("industry", bean.getIndustry());
		args.put("name", bean.getName());
		args.put("mobile", bean.getMobile());
		args.put("content", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("title", bean.getTitle());
		args.put("user_id", this.getUserId());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.companyconsult(args);
			if(a>0){
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -6);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("咨询企业治理相关服务异常",e);
		}
		logger.info("咨询企业治理相关服务end---------------");
		return map;
	}
	
	
	

	/**
	 * (短工坊)获取热门的二十个标签
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getduangongtags", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangongtags(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("获取热门的二十个标签begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getduangongtags(args);
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
			logger.error("获取热门的二十个标签异常",e);
		}
		logger.info("获取热门的二十个标签end---------------");
		return map;
	}

	/**
	 * 获取短工招聘列表
	 * 
	 */
	@RequestMapping(value = "/getduangonglist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangonglist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取短工招聘列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("category", request.getParameter("category"));// 1,大学生 2,在职人员
																// 3,自由职业者
		args.put("tagId", request.getParameter("tagId"));
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getduangonglist(args);
			int totalCount = this.waiterCompanyService.getduangonglistNum(args);
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
			logger.error("获取短工招聘列表异常",e);
		}
		logger.info("获取短工招聘列表end---------------");
		return map;
	}

	/**
	 * 发布短工招聘信息
	 * @throws ParseException 
	 * 
	 */
	@RequestMapping(value = "/pubduangong", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubduangong(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException, ParseException {
		logger.info("发布短工招聘信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> args2 = new HashMap<>();
		Map<String, Object> args3 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		args.put("title", pojo.getTitle());
		args.put("company", pojo.getCompany());
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("position", pojo.getPosition());
		args.put("district", pojo.getDistrict());
		args.put("number", pojo.getNumber());
		args.put("pay", pojo.getPay());
		args.put("payUnit", pojo.getPayUnit());
		args.put("payType", pojo.getPayType());
		args.put("gender", pojo.getGender());
		args.put("beginDay", OtherUtils.format_date(pojo.getBeginDay()));
		args.put("category", pojo.getCategory());
		args.put("endDay", OtherUtils.format_date(pojo.getEndDay()));
		args.put("location", pojo.getLocation());
		args.put("detail", pojo.getDetail());
		args.put("tags", pojo.getTags());// 未知格式，暂时不处理，用来保存到r_group_tag_post
											// 和帖子作对应，另外检查e_group_tag表，有新的标签就添加进去
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		// args.put("user_id", "15");
		String[] tags = pojo.getTags().split(",");
		try {
			int a = this.waiterCompanyService.pubduangong(args);// 1、首先插入帖子的信息，2、获取帖子的ID，3、检验有没有新的标签，有的话加入表里，并查询新标签的ID，4、将帖子的ID和新标签的ID加入关联表
			args1 = (Map<String, Object>) this.waiterCompanyService.selectTieZiId(args);
			String id = String.valueOf(args1.get("id"));// 新帖子的ID
			for (int i = 0; i < tags.length; i++) {
				list1 = (List<Object>) this.waiterCompanyService.selectBiaoQian(tags[i]);// 查询标签存不存在
				if (list1.size() == 0) {// 标签不存在
					int b = this.waiterCompanyService.insertTag(tags[i]);// 标签不存在，插入新的标签
					args2 = (Map<String, Object>) this.waiterCompanyService.selectTagId(tags[i]);// 查询新标签的ID
					String tagid = String.valueOf(args2.get("id"));
					int c = this.waiterCompanyService.insertTag_post(id, tagid);
				} else {// 标签存在，取tagid，保存到关联表
					args3 = (Map<String, Object>) list1.get(0);
					String tagid = String.valueOf(args3.get("id"));
					int c = this.waiterCompanyService.insertTag_post(id, tagid);
				}
			}
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
			logger.error("发布短工招聘信息异常",e);
		}
		logger.info("发布短工招聘信息end---------------");
		return map;
	}

	/**
	 * 查看短工招聘的详细信息
	 * 
	 */
	@RequestMapping(value = "/getduangongdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangongdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("查看短工招聘的详细信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		args.put("id", request.getParameter("id"));
		args.put("category", request.getParameter("category"));
		try {
			HashMap<String, Object> testMap = (HashMap<String, Object>) this.waiterCompanyService
					.getduangongdetail(args);
			list1 = (List<Object>) this.waiterCompanyService.getduangongdetail1(args);// 查询帖子的
																						// 标签
			if (!testMap.isEmpty()) {
				testMap.put("tags", list1);
				this.waiterCompanyService.updateduangongCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", testMap);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看短工招聘的详细信息异常",e);
		}
		logger.info("查看短工招聘的详细信息end---------------");
		return map;
	}

	/**
	 * 应聘短工
	 * 
	 */
	@RequestMapping(value = "/applyduangong", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyduangong(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("应聘短工begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		args.put("id", pojo.getId());// 帖子的ID
		args.put("user_id", this.getUserId());// 申请人的ID
		// args.put("user_id", "15");
		String userid = this.getUserId();// 首先查询此人是否填写了简历
		try {
			list1 = (List<Object>) this.waiterCompanyService.selectjianli(args);// 查询简历
			if (list1.size() > 0) {// 已经填写简历，可以应聘
				list2 = (List<Object>) this.waiterCompanyService.getduangongNum(args);
				if (list2.size() > 0) {// 说明招聘人数未满，增加招聘人数，同时保存此人信息和帖子的关联
					int a = this.waiterCompanyService.updateApply(args);// 更新申请人数
					if (a > 0) {
						int b = this.waiterCompanyService.insertApply(args);// 保存申请人的ID和帖子的ID
						map.put("resultCode", 0);
						map.put("resultMsg", "操作成功");
						map.put("resultData", list);
					} else {
						map.put("resultCode", -6);
						map.put("resultMsg", "数据未成功插入");
						map.put("resultData", list);
					}
				} else {// 招聘已经满员，返回提示信息
					map.put("resultCode", -18);
					map.put("resultMsg", "招聘人数已满");
					map.put("resultData", list);
				}

			} else {// 未填写简历
				map.put("resultCode", -17);
				map.put("resultMsg", "未填写简历");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("招聘短工异常",e);
		}
		logger.info("应聘短工end---------------");
		return map;
	}

	/**
	 * (短工坊)获取热门的十个个人标签
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getduangongselftags", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangongselftags(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("获取热门的十个个人标签begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getduangongselftags(args);
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
			logger.error("获取热门的十个个人标签异常",e);
		}
		logger.info("获取热门的十个个人标签end---------------");
		return map;
	}

	/**
	 * (短工坊)获取热门的十个个人标签
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getduangongskilltags", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangongskilltags(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("获取热门的十个个人标签begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getduangongskilltags(args);
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
			logger.error("获取热门的十个个人标签异常",e);
		}
		logger.info("获取热门的十个个人标签end---------------");
		return map;
	}

	/**
	 * (短工坊)获取短工简历列表
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getduangongapplicationlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangongapplicationlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("获取短工简历列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		args.put("category", request.getParameter("category"));
		args.put("selfTagId", OtherUtils.format_null(request.getParameter("selfTagId")));
		args.put("skillTagId", OtherUtils.format_null(request.getParameter("skillTagId")));
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getduangongapplicationlist(args);
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
			logger.error("获取短工简历列表异常",e);
		}
		logger.info("获取短工简历列表end---------------");
		return map;
	}

	/**
	 * (短工坊)发布短工简历信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/pubduangongapplication", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubduangongapplication(HttpServletRequest request, HttpServletResponse response,
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
		args.put("category", pojo.getCategory());
		args.put("userName", pojo.getUserName());
		args.put("name", pojo.getName());
		args.put("picUrl", pojo.getPicUrl());
		args.put("gender", pojo.getGender());
		args.put("age", pojo.getAge());
		args.put("title", pojo.getTitle());
		args.put("height", pojo.getHeight());
		args.put("time", System.currentTimeMillis());
		args.put("mobile", pojo.getMobile());
		args.put("district", pojo.getDistrict());
		args.put("department", pojo.getDepartment());
		args.put("declaration", pojo.getDeclaration());
		args.put("freeTime", pojo.getFreeTime());// 0：日；1：一；2：二；3：三；4：四；5：五；6：六
		args.put("experience", pojo.getExperience());
		args.put("selfTags", pojo.getSelfTags());// 个人标签
		args.put("skillTags", pojo.getSkillTags());// 技能标签
		args.put("user_id", this.getUserId());
		// args.put("user_id", "15");
		String[] selfTags = pojo.getSelfTags().split(",");
		String[] skillTags = pojo.getSkillTags().split(",");

		try {
			// 0、先检查用户有没有填写过简历
			// 1、首先插入简历的信息，2、获取简历的ID，3、检验有没有新的标签，有的话加入标签表里，并查询新标签的ID，4、将简历的ID和新标签的ID加入关联表
			list3 = (List<Object>) this.waiterCompanyService.selectjianli(args);// 查询简历
			if (list3.size() > 0) {
				map.put("resultCode", -22);
				map.put("resultMsg", "您已经填写过简历");
				map.put("resultData", list);
			} else {
				int a = this.waiterCompanyService.pubduangongapplication(args);
				args1 = (Map<String, Object>) this.waiterCompanyService.selectJianLiId(args);
				String id = String.valueOf(args1.get("id"));// 新简历的ID

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

				// 重复另一个标签
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
			logger.error("发布短工简历信息异常",e);
		}
		logger.info("发布短工简历信息end---------------");
		return map;
	}

	/**
	 * (短工坊)发布短工视频
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/pubduangongapplicationvideo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubduangongapplicationvideo(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("发布短工视频begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		File video = pojo.getVideo();
		List<Object> list = new ArrayList<>();
		try {
			// list = (List<Object>)
			// this.waiterCompanyService.pubduangongapplicationvideo(args);
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
			logger.error("发布短工视频异常",e);
		}
		logger.info("发布短工视频end---------------");
		return map;
	}

	/**
	 * (短工坊)查看短工招聘简历的详细信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getduangongapplicationdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getduangongapplicationdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("查看短工招聘简历的详细信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		args.put("category", pojo.getCategory());
		args.put("id", pojo.getId());

		try {
			HashMap<String, Object> testMap = (HashMap<String, Object>) this.waiterCompanyService
					.getduangongapplicationdetail(args);
			list1 = (List<Object>) this.waiterCompanyService.getselfTags(args);// 查询帖子的
																				// 标签
			list2 = (List<Object>) this.waiterCompanyService.getskillTags(args);// 查询帖子的
																				// 标签
			if (!testMap.isEmpty()) {
				testMap.put("selfTags", list1);
				testMap.put("skillTags", list2);
				map.put("resultCode", 0);
				this.waiterCompanyService.updateJianLiCount(args);
				map.put("resultMsg", "操作成功");
				map.put("resultData", testMap);
			} else {
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("查看短工招聘简历的详细信息异常",e);
		}
		logger.info("查看短工招聘简历的详细信息end---------------");
		return map;
	}

	/**
	 * (短工坊)招聘短工
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/inviteduangong", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object inviteduangong(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) throws IOException {
		logger.info("招聘短工begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", pojo.getId());
		args.put("user_id", this.getUserId());
		// args.put("user_id", "15");
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		try {
			list1 = (List<Object>) this.waiterCompanyService.selectPub(args);// 检查此用户有没有发过招聘的帖子
			if (list1.size() > 0) {
				int a = this.waiterCompanyService.applicationduangong(args);
				if (a > 0) {
					map.put("resultCode", 0);
					map.put("resultMsg", "操作成功");
					map.put("resultData", list);
				} else {
					map.put("resultCode", -5);
					map.put("resultMsg", "无数据");
					map.put("resultData", list);
				}
			} else {
				map.put("resultCode", -19);
				map.put("resultMsg", "您还没有发布过招聘信息");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("招聘短工异常",e);
		}
		logger.info("招聘短工end---------------");
		return map;
	}

	/**
	 * 发布厂房租售信息 table_name think_changfangzs
	 */
	@RequestMapping(value = "/pubfactory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubfactory(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("发布厂房租售信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("title", bean.getTitle());
		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("area", bean.getArea());
		args.put("amount", bean.getAmount());
		args.put("pics", bean.getPics());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("type", bean.getType());
		//args.put("category", "1");// 默认为1
		args.put("user_id", this.getUserId());
		args.put("time", System.currentTimeMillis());

		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubfactory(args);
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
			logger.error("发布厂房租售信息异常",e);
		}
		logger.info("发布厂房租售信息end---------------");
		return map;
	}

	/**
	 * 发布设备调剂信息 table_name 
	 */
	@RequestMapping(value = "/pubequipment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubequipment(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubequipmentBean bean) {
		logger.info("发布设备调剂信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("equipment", bean.getEquipment());
		args.put("model", bean.getModel());
		args.put("vender", bean.getVender());
		args.put("productionDate", bean.getProductionDate());
		args.put("quantity", bean.getQuantity());
		args.put("amount", bean.getAmount());
		args.put("pics", bean.getPics());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		//args.put("category", "2");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		// ---------------------------------------
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubequipment(args);
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
			logger.error("发布设备调剂信息异常",e);
		}
		logger.info("发布设备调剂信息end---------------");
		return map;
	}

	public boolean format(String str) {
		boolean flag = false;
		if (!"".equals(str) && str != null) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 发布物流服务信息 table_name e_logistics
	 */
	@RequestMapping(value = "/publogistics", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object publogistics(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") publogisticsBean bean) {
		logger.info("发布物流服务信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		//args.put("category", "3");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.publogistics(args);
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
			logger.error("发布物流服务信息异常",e);
		}
		logger.info("发布物流服务信息end---------------");
		return map;
	}

	/**
	 * 发布员工招聘信息 table_name pubactivity
	 */
	@RequestMapping(value = "/pubjob", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubjob(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubjobBean bean) {
		logger.info("发布员工招聘信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("quantity", bean.getQuantity());
		args.put("salaryMin", bean.getSalaryMin());
		args.put("salaryMax", bean.getSalaryMax());
		args.put("education", bean.getEducation());
		args.put("year", bean.getYear());
		args.put("model", bean.getModel());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		//args.put("category", "4");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubjob(args);
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
			logger.error("发布员工招聘信息异常",e);
		}
		logger.info("发布员工招聘信息end---------------");
		return map;
	}

	/**
	 * 发布技术交流信息 table_name pubactivity
	 */
	@RequestMapping(value = "/pubactivity", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubactivity(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubactivityBean bean) {
		logger.info("发布技术交流信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		// args.put("company", "1");
		// args.put("mobile", "1");
		// args.put("name", "1");
		// args.put("password", "1");
		// args.put("district", "1");
		// args.put("industry", "1");
		// args.put("memo", "1");
		// args.put("authority", "1");
		// args.put("side", "1");

		//args.put("category", "5");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubactivity(args);
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
			logger.error("发布技术交流信息异常",e);
		}
		logger.info("发布技术交流信息end---------------");
		return map;
	}

	/**
	 * 发布产业协作信息 table_name think_chanyexz
	 */
	@RequestMapping(value = "/pubcooperation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubcooperation(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubcooperationBean bean) {
		logger.info("发布产业协作信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		// args.put("company", "1");
		// args.put("mobile", "1");
		// args.put("name", "1");
		// args.put("password", "1");
		// args.put("district", "1");
		// args.put("industry", "1");
		// args.put("memo", "1");
		// args.put("authority", "1");
		// args.put("side", "1");

		//args.put("category", "6");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubcooperation(args);
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
			logger.error("发布产业协作信息异常",e);
		}
		logger.info("发布产业协作信息end---------------");
		return map;
	}

	/**
	 * 发布员工培训信息 table_name think_yuangongpx
	 */
	@RequestMapping(value = "/pubtraining", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubtraining(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubtrainingBean bean) {
		logger.info("发布员工培训信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("quantity", bean.getQuantity());
		args.put("days", bean.getDays());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		// args.put("company", "1");
		// args.put("mobile", "1");
		// args.put("name", "1");
		// args.put("district", "1");
		// args.put("industry", "1");
		// args.put("quantity", "1");
		// args.put("days", "1");
		// args.put("memo", "1");
		// args.put("authority", "1");
		// args.put("side", "1");
		//args.put("category", "7");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubtraining(args);
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
			logger.error("发布员工培训信息异常",e);
		}
		logger.info("发布员工培训信息end---------------");
		return map;
	}

	/**
	 * 发布工商注册信息
	 */
	@RequestMapping(value = "/pubregistration", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubregistration(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubaccountingBean bean) {
		logger.info("发布工商注册信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		//args.put("category", "12");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubregistration(args);
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
			logger.error("发布工商注册信息异常",e);
		}
		logger.info("发布工商注册信息end---------------");
		return map;
	}

	/**
	 * 发布账务代理信息 table_name think_yuangongpx
	 */
	@RequestMapping(value = "/pubaccounting", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubaccounting(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubaccountingBean bean) {
		logger.info("发布账务代理信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		//args.put("category", "8");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubaccounting(args);
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
			logger.error("发布账务代理信息异常",e);
		}
		logger.info("发布账务代理信息end---------------");
		return map;
	}

	/**
	 * 发布股权转让信息 table_name think_yuangongpx
	 */
	@RequestMapping(value = "/pubstockrights", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubstockrights(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubstockrightsBean bean) {
		logger.info("发布股权转让信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		// args.put("company", "1");
		// args.put("mobile", "1");
		// args.put("name", "1");
		// args.put("password", "1");
		// args.put("district", "1");
		// args.put("industry", "1");
		// args.put("memo", "1");
		// args.put("authority", "1");
		// args.put("side", "1");
		//args.put("category", "9");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubstockrights(args);
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
			logger.error("发布股权转让信息异常",e);
		}
		logger.info("发布股权转让信息end---------------");
		return map;
	}

	/**
	 * 发布代理融资信息 table_name think_yuangongpx
	 */
	@RequestMapping(value = "/pubcapital", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubcapital(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubcapitalBean bean) {
		logger.info("发布代理融资信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("industry", bean.getIndustry());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());
		// ---------------------------------------
		//args.put("category", "10");
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubcapital(args);
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
			logger.error("发布代理融资信息异常",e);
		}
		logger.info("发布代理融资信息end---------------");
		return map;
	}

	/**
	 * 发布承兑汇票信息 
	 */
	@RequestMapping(value = "/pubbill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubbill(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubbillBean bean) {
		logger.info("发布承兑汇票信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("district", bean.getDistrict());
		args.put("bank", bean.getBank());
		args.put("amount", bean.getAmount());
		args.put("repaymentDate", bean.getRepaymentDate());
		args.put("memo", bean.getMemo());
		args.put("authority", bean.getAuthority());
		args.put("side", bean.getSide());
		args.put("title", bean.getTitle());

		// ---------------------------------------
		args.put("count", "0");
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubbill(args);
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
			logger.error("发布承兑汇票信息异常",e);
		}
		logger.info("发布承兑汇票信息end---------------");
		return map;
	}
	
	
	/**
	 * 发布起名策划 
	 */
	@RequestMapping(value = "/pubnameregistration", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubnameregistration(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") PoJo pojo) {
		logger.info("发布起名策划信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		args.put("company", pojo.getCompany());
		args.put("management", pojo.getManagement());
		args.put("legalPerson", pojo.getLegalperson());
		args.put("birthTime", pojo.getBirthTime());
		args.put("birthPlace", pojo.getBirthPlace());
		args.put("name", pojo.getName());
		args.put("mobile", pojo.getMobile());
		args.put("qq", pojo.getQq());
		args.put("memo", pojo.getMemo());

		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.waiterCompanyService.pubnameregistration(args);
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
			logger.error("发布起名策划信息异常",e);
		}
		logger.info("发布起名策划信息end---------------");
		return map;
	}
	
	
	/**
	 * 工商注册预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyregistration", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyregistration(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("工商注册预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("district", pojo.getDistrict());
		args.put("price", pojo.getPrice());
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("type", pojo.getType());
		args.put("memo", pojo.getMemo());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applyregistration(args);
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
			logger.error("工商注册预约异常", e);
		}
		logger.info("工商注册预约begin---------------");
		return map;
	}
	
	
	/**
	 * 管理咨询预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applymanagement", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applymanagement(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("管理咨询预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company_id", pojo.getCompany_id());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applymanagement(args);
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
			logger.error("管理咨询预约异常", e);
		}
		logger.info("管理咨询预约begin---------------");
		return map;
	}
	
	
	
	/**
	 * 管理咨询公司列表
	 * 
	 */
	@RequestMapping(value = "/getmanagementcompanylist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getmanagementcompanylist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取管理咨询公司列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("page", page);
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getmanagementcompanylist(args);
			int totalCount = this.waiterCompanyService.getmanagementcompanylistNum(args);
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
			logger.error("获取管理咨询公司列表异常",e);
		}
		logger.info("获取管理咨询公司列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 管理咨询公司详情
	 * 
	 */
	@RequestMapping(value = "/getmanagementcompanydetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getmanagementcompanydetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("管理咨询公司详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getmanagementcompanydetail(args);
			if (list.size() > 0) {
//				this.waiterCompanyService.updateCount(args);
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
			logger.error("管理咨询公司详情异常",e);
		}
		logger.info("管理咨询公司详情end---------------");
		return map;
	}
	
	
	
	
	
	
	/**
	 * 法律事务预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applylegalaffairs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applylegalaffairs(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("法律事务预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company_id", pojo.getCompany_id());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applylegalaffairs(args);
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
			logger.error("法律事务预约异常", e);
		}
		logger.info("法律事务预约begin---------------");
		return map;
	}
	
	
	
	/**
	 * 法律事务公司列表
	 * 
	 */
	@RequestMapping(value = "/getlegalaffairslist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getlegalaffairslist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取法律事务公司列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("page", page);
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getlegalaffairslist(args);
			int totalCount = this.waiterCompanyService.getlegalaffairslistNum(args);
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
			logger.error("获取法律事务公司列表异常",e);
		}
		logger.info("获取管理咨询公司列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 法律事务公司详情
	 * 
	 */
	@RequestMapping(value = "/getlegalaffairsdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getlegalaffairsdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("法律事务公司详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getlegalaffairsdetail(args);
			if (list.size() > 0) {
//				this.waiterCompanyService.updateCount(args);
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
			logger.error("法律事务公司详情异常",e);
		}
		logger.info("法律事务公司详情end---------------");
		return map;
	}
	
	
	
	
	
	/**
	 * 财税管理预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyfinancialmanage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyfinancialmanage(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("财税管理预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company_id", pojo.getCompany_id());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applyfinancialmanage(args);
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
			logger.error("财税管理预约异常", e);
		}
		logger.info("财税管理预约begin---------------");
		return map;
	}
	
	
	/**
	 * 财税管理公司列表
	 * 
	 */
	@RequestMapping(value = "/getfinancialmanagelist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getfinancialmanagelist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取财税管理公司列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("page", page);
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getfinancialmanagelist(args);
			int totalCount = this.waiterCompanyService.getfinancialmanagelistNum(args);
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
			logger.error("获取财税管理公司列表异常",e);
		}
		logger.info("获取财税管理公司列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 财税管理公司详情
	 * 
	 */
	@RequestMapping(value = "/getfinancialmanagedetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getfinancialmanagedetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("财税管理公司详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getfinancialmanagedetail(args);
			if (list.size() > 0) {
//				this.waiterCompanyService.updateCount(args);
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
			logger.error("财税管理公司详情异常",e);
		}
		logger.info("财税管理公司详情end---------------");
		return map;
	}
	
	
	
	
	
	/**
	 * 资产评估预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyequityassessment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyequityassessment(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("资产评估预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company_id", pojo.getCompany_id());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applyequityassessment(args);
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
			logger.error("资产评估预约异常", e);
		}
		logger.info("资产评估预约begin---------------");
		return map;
	}
	
	
	
	/**
	 * 资产评估公司列表
	 * 
	 */
	@RequestMapping(value = "/getequityassessmentlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getequityassessmentlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取资产评估公司列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("page", page);
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getequityassessmentlist(args);
			int totalCount = this.waiterCompanyService.getequityassessmentlistNum(args);
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
			logger.error("获取资产评估公司列表异常",e);
		}
		logger.info("获取资产评估公司列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 资产评估公司详情
	 * 
	 */
	@RequestMapping(value = "/getequityassessmentdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getequityassessmentdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("资产评估公司详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getequityassessmentdetail(args);
			if (list.size() > 0) {
//				this.waiterCompanyService.updateCount(args);
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
			logger.error("资产评估公司详情异常",e);
		}
		logger.info("资产评估公司详情end---------------");
		return map;
	}
	
	
	
	
	
	/**
	 * 专利申请预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applypatent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applypatent(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("专利申请预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company_id", pojo.getCompany_id());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applypatent(args);
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
			logger.error("专利申请预约异常", e);
		}
		logger.info("专利申请预约begin---------------");
		return map;
	}
	
	
	
	/**
	 * 专利申请公司列表
	 * 
	 */
	@RequestMapping(value = "/getpatentlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getpatentlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取专利申请公司列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("page", page);
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getpatentlist(args);
			int totalCount = this.waiterCompanyService.getpatentlistNum(args);
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
			logger.error("获取专利申请公司列表异常",e);
		}
		logger.info("获取专利申请公司列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 专利申请公司详情
	 * 
	 */
	@RequestMapping(value = "/getpatentdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getpatentdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("专利申请公司详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.getpatentdetail(args);
			if (list.size() > 0) {
//				this.waiterCompanyService.updateCount(args);
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
			logger.error("专利申请公司详情异常",e);
		}
		logger.info("专利申请公司详情end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 人事服务预约
	 * 
	 * @throws Exception
	 *             table think_financingapply
	 */
	@RequestMapping(value = "/applyhr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object applyhr(HttpServletRequest request, @ModelAttribute("pojo") PoJo pojo) throws Exception {
		logger.info("人事服务预约begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("mobile", pojo.getMobile());
		args.put("name", pojo.getName());
		args.put("company_id", pojo.getCompany_id());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.waiterCompanyService.applyhr(args);
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
			logger.error("人事服务预约异常", e);
		}
		logger.info("人事服务预约begin---------------");
		return map;
	}
	
	
	
	/**
	 * 人事服务公司列表
	 * 
	 */
	@RequestMapping(value = "/gethrlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object gethrlist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo pojo) {
		logger.info("获取人事服务公司列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String page = request.getParameter("page");
		if ("".equals(page) || page == null) {// 没有page默认给 1
			page = "1";
		}
		args.put("page", page);
		int num = 10;// 每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page, num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.gethrlist(args);
			int totalCount = this.waiterCompanyService.gethrlistNum(args);
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
			logger.error("获取人事服务公司列表异常",e);
		}
		logger.info("获取人事服务公司列表end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 人事服务公司详情
	 * 
	 */
	@RequestMapping(value = "/gethrdetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object gethrdetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("人事服务公司详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", bean.getId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.gethrdetail(args);
			if (list.size() > 0) {
//				this.waiterCompanyService.updateCount(args);
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
			logger.error("人事服务公司详情异常",e);
		}
		logger.info("人事服务公司详情end---------------");
		return map;
	}
	
	
	
	
	
	/**
	 * 查看联系电话
	 * 
	 */
	@RequestMapping(value = "/viewmobile", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object viewmobile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("小二跑腿查看联系电话begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("id", request.getParameter("id"));
		args.put("table_name", format_tablename(request.getParameter("category")));
		args.put("category", request.getParameter("category"));
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.viewmobile(args);
			if (list.size() > 0) {
				int a = this.waiterCompanyService.updateviewmobileLog(args);
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
			logger.error("小二跑腿查看联系电话异常",e);
		}
		logger.info("小二跑腿查看联系电话end---------------");
		return map;
	}
	
	

	/**
	 * 厂房租售信息详情
	 * 
	 */
	@RequestMapping(value = "/pubfactorydetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubfactorydetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") pubfactoryBean bean) {
		logger.info("小二跑腿信息详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String type = "xiaoer";
		args.put("id", bean.getId());
		args.put("type", type);
		args.put("table_name", format_tablename(bean.getCategory()));
		// ---------------------------------------
		// args.put("id", "1");
		// args.put("table_name",
		// format_tablename(request.getParameter("category_id")));
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.waiterCompanyService.pubfactorydetail(args);
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
			logger.error("小二跑腿信息详情异常",e);
		}
		logger.info("小二跑腿信息详情end---------------");
		return map;
	}
	
	
	
	/**
	 * 微信测试
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value = "/dopost", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void dopost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String reSignature = null; 
		try { 
			String [] str = {TOKEN,timestamp,nonce};
			Arrays.sort(str);
			String bigstr = str[0]+str[1]+str[2];   
			reSignature = new SHA1().getDigestOfString(bigstr.getBytes())
					.toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("signature"+signature);
		logger.info("reSignature"+reSignature);
		if(reSignature.equals(signature)){
			out.print(echostr);
		}
		out.flush();
		out.close();
	} 
	

	public static String format_tablename(String str) {
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
			tablename = "";
		} else if ("11".equals(str)) {
			tablename = "e_bill";
		} else if ("12".equals(str)) {
			tablename = "e_registration";
		} else if ("13".equals(str)) {
			tablename = "e_management";	
		} else if ("14".equals(str)) {
			tablename = "e_legalaffairs";
		} else if ("15".equals(str)) {
			tablename = "e_financialmanage";
		} else if ("16".equals(str)) {
			tablename = "e_copywriter";
		} else if ("17".equals(str)) {
			tablename = "e_equityassessment";
		} else if ("18".equals(str)) {
			tablename = "e_studyreport";
		} else {
			tablename = "e_pub_factory";
		}
		return tablename;
	}

}
