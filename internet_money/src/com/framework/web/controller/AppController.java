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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.entity.pubactivityBean;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.AppService;
import com.framework.web.service.CircleService;
import com.framework.web.service.VentureHouseService;

@Controller
@RequestMapping("AppController")
public class AppController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private AppService appService;
	
	@Resource
	private CircleService circleService;
	
	
	@Resource
	private VentureHouseService ventureHouseService;
	
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
		args.put("amount", pojo.getAmount());
		args.put("mode", pojo.getMode());// 0：不限；1：股权融资；2：贷款融资；3：担保融资；4：租赁融资；5：代理融资
		args.put("days", pojo.getDays());
		
//		args.put("company", pojo.getCompany());
//		args.put("industry", "1");
//		args.put("asset", pojo.getAsset());
//		args.put("sales", pojo.getSales());
//		args.put("email", pojo.getEmail());
		
		args.put("memo", pojo.getMemo());
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		try {
			int a = this.appService.moneyApply_insert(args);
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
		args.put("licai_id", OtherUtils.EmptToNull(pojo.getLicai_id()));// OtherUtils.EmptToNull(pojo.getLicai_id())
		args.put("name", OtherUtils.EmptToNull(pojo.getName()));
		args.put("amount", OtherUtils.EmptToNull(pojo.getAmount()));
		args.put("mobile", OtherUtils.EmptToNull(pojo.getMobile()));
		args.put("referrer", OtherUtils.EmptToNull(pojo.getReferrer()));
		
		args.put("time", System.currentTimeMillis());
		args.put("user_id", this.getUserId());
		args.put("memo", "手机端理财预约");
		try {
			listUser = (List<Object>) this.appService.selectUser(args);
			if (listUser.size() > 0) {
				map.put("resultCode", -29);
				map.put("resultMsg", "您已预约本次理财项目");
				map.put("resultData", list);
			} else {
				int a = this.appService.applylicai(args);
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
	 * 发布小二跑腿信息
	 */
	@RequestMapping(value = "/pubdemand", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pubactivity(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pojo") PoJo bean) {
		logger.info("发布小二跑腿信息begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("table_name", WaiterCompanyController.format_tablename(bean.getCategory()));
		args.put("title", bean.getTitle());
		args.put("side", bean.getSide());
		args.put("company", bean.getCompany());
		args.put("mobile", bean.getMobile());
		args.put("name", bean.getName());
		args.put("memo", bean.getMemo());
		args.put("user_id", this.getUserId());
		args.put("time", String.valueOf(System.currentTimeMillis()));
		List<Object> list = new ArrayList<>();
		try {
			int a = this.appService.pubdemand(args);
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
		logger.info("发布小二跑腿信息end---------------");
		return map;
	}
	
	
	/**
	 * (圈子运动)获取热门的十个标签
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/gethottags",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getgovnews(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取热门的十个标签begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.circleService.gethottags(args);
			if(list.size() >0){
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
			logger.error("获取热门的十个标签异常",e);
		}
		logger.info("获取热门的十个标签end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取帖子列表
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getgrouppostlist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getgrouppostlist(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取帖子列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String page = request.getParameter("page");
		args.put("tagId", request.getParameter("tagId"));
		//args.put("page", request.getParameter("page"));
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		try {
			list = (List<Object>) this.circleService.getgrouppostlist(args);
			int totalCount = this.circleService.getgrouppostdetailNum(args);
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
		logger.info("获取帖子列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取帖子详情
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getgrouppostdetail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getgrouppostdetail(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取帖子详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		args.put("id", request.getParameter("id"));
		try {
			HashMap<String, Object> testMap = (HashMap<String, Object>) this.circleService.getgrouppostdetail(args);
			list1 = (List<Object>) this.circleService.getgrouppostdetail1(args);//查询帖子的 标签
			if(!testMap.isEmpty()){
				testMap.put("tags", list1);
				this.circleService.updateCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", testMap);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取帖子详情异常",e);
		}
		logger.info("获取帖子详情end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取帖子的评论
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getgrouppostcomment",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getgrouppostcomment(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取帖子的评论begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("postId", request.getParameter("postId"));
		//args.put("page", request.getParameter("page"));
		String page = request.getParameter("page");
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		int num = 10;//每页条数
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		try {
			list = (List<Object>) this.circleService.getgrouppostcomment(args);
			int totalCount = this.circleService.getgrouppostcommentNum(args);
			if(list.size() > 0){
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
			logger.error("获取帖子的评论异常",e);
		}
		logger.info("获取帖子的评论end---------------");
		return map;
	}
	
	
	
	/**
	 * 发布帖子
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/pubgrouppost",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object pubgrouppost(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("发布帖子begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> args1 = new HashMap<>();
		Map<String, Object> args2 = new HashMap<>();
		Map<String, Object> args3 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		args.put("title", pojo.getTitle());
		args.put("tags", pojo.getTags());//未知格式，暂时不处理，用来保存到r_group_tag_post 和帖子作对应，另外检查e_group_tag表，有新的标签就添加进去
		args.put("content", pojo.getContent());
		args.put("pubTime", System.currentTimeMillis());
		args.put("updateTime", System.currentTimeMillis());
		String[] tags = pojo.getTags().split(",");
		args.put("userid", this.getUserId());
		//args.put("userid", "1");//测试用
		try {
			int a = this.circleService.pubgrouppost(args);//1、首先插入帖子的信息，2、获取帖子的ID，3、检验有没有新的标签，有的话加入表里，并查询新标签的ID，4、将帖子的ID和新标签的ID加入关联表
			args1 = (Map<String, Object>) this.circleService.selectTieZiId(args);
			String id = String.valueOf(args1.get("id"));//新帖子的ID
			for (int i = 0; i < tags.length; i++) {
				list1 = (List<Object>) this.circleService.selectBiaoQian(tags[i]);//查询标签存不存在
				if(list1.size() == 0){//标签不存在
					int b = this.circleService.insertTag(tags[i]);//标签不存在，插入新的标签
					args2 = (Map<String, Object>) this.circleService.selectTagId(tags[i]);//查询新标签的ID
					String tagid = String.valueOf(args2.get("id"));
					int c = this.circleService.insertTag_post(id,tagid);
				}else{//标签存在，取tagid，保存到关联表
					args3 = (Map<String, Object>) list1.get(0);
					String tagid = String.valueOf(args3.get("id"));
					int c = this.circleService.insertTag_post(id,tagid);
				}
			}
			
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
			logger.error("发布帖子异常",e);
		}
		logger.info("发布帖子end---------------");
		return map;
	}
	
	
	
	/**
	 * 发布帖子评论
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/pubgroupcomment",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object pubgroupcomment(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("发布帖子评论begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		args.put("post_id", pojo.getPostId());
		args.put("message", pojo.getMessage());
		args.put("user_id", this.getUserId());
		//args.put("user_id", "1");
		args.put("time", System.currentTimeMillis());
		try {
			int a = this.circleService.pubgroupcomment(args);
			int b = this.circleService.updateTime(args);//更新帖子最后回复时间
			//int c = this.circleService.updateComment(args);//更新评论数
			if(a > 0){
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
			logger.error("发布帖子评论异常",e);
		}
		logger.info("发布帖子评论end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取导师标签
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/getmentortags",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentortags(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("pojo")PoJo pojo) throws IOException{
		logger.info("获取导师标签begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getmentortags(args);
			if(list.size() >0){
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
			logger.error("获取导师标签异常",e);
		}
		logger.info("获取导师标签end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 获取创业导师信息的列表(不需登录)
	 * 
	 */
	@RequestMapping(value="/getmentorlist",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentorlist(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取创业导师信息的列表begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int num = 10;
		String page = request.getParameter("page");
		args.put("tagId", request.getParameter("tagId"));
		//args.put("page", request.getParameter("page"));
		if("".equals(page) || page == null){//没有page默认给 1    
			page = "1";
		}
		args.put("num", num);
		String startNum = OtherUtils.format_limit(page,num);
		args.put("startNum", startNum);
		List<Object> list = new ArrayList<>();
		try {
			list = (List<Object>) this.ventureHouseService.getmentorlist(args);
			int totalCount = this.ventureHouseService.getmentorlistNum(args);
			if(list.size() >0){
				map.put("resultCode", 0);
				map.put("totalCount", totalCount);
				map.put("onePageSize", num);
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
			logger.error("获取创业导师信息的列表异常",e);
		}
		logger.info("获取创业导师信息的列表end---------------");
		return map;
	}
	
	
	
	/**
	 * 获取创业导师详情(不需登录)
	 * 
	 */
	@RequestMapping(value="/getmentordetail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getmentordetail(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("获取创业导师详情begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		args.put("id", id);
		args.put("mentor_id", id);
		args.put("user_id", this.getUserId());
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();
		String hire = "0";
		try {
			String user_id = this.getUserId();
			if(user_id != null){
				list2 = (List<Object>) this.ventureHouseService.selectchoosementor(args);
				if(list2.size() > 0){//说明已经聘用
					hire = "1";
				}
			}
			Map<String, Object> mentorMap = (Map<String, Object>) this.ventureHouseService.getmentordetail(args);
			list1 = (List<Object>) this.ventureHouseService.selectMentorTags(args);
			if(!mentorMap.isEmpty()){
				mentorMap.put("tags", list1);
				mentorMap.put("hire", hire);
				this.ventureHouseService.updateTeacherCount(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", mentorMap);
			}else{
				map.put("resultCode", -5);
				map.put("resultMsg", "无数据");
				map.put("resultData", list);
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("获取创业导师详情异常",e);
		}
		logger.info("获取创业导师详情end---------------");
		return map;
	}
	
	
	
	
	/**
	 * 聘用创业导师
	 * 
	 */
	@RequestMapping(value="/choosementor",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object choosementor(HttpServletRequest request,@ModelAttribute("bean")PoJo bean) {
		logger.info("聘用创业导师begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		args.put("mentor_id", bean.getId());
		args.put("user_id", this.getUserId());
		args.put("company", bean.getCompany());
		args.put("time", System.currentTimeMillis());
		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();
		try {
			list1 = (List<Object>) this.ventureHouseService.selectchoosementor(args);
			if(list1.size() > 0){//说明已经聘用过
				map.put("resultCode", -20);
				map.put("resultMsg", "您已聘用过该导师");
				map.put("resultData", list);
			}else{
			int a = this.ventureHouseService.choosementor(args);
			if(a>0){
				this.ventureHouseService.updateChooseTeacherNum(args);
				map.put("resultCode", 0);
				map.put("resultMsg", "操作成功");
				map.put("resultData", list);
			}else{
				map.put("resultCode", -6);
				map.put("resultMsg", "数据未成功插入");
				map.put("resultData", list);
			}
			}
		} catch (Exception e) {
			map.put("resultCode", -2);
			map.put("resultMsg", "服务器异常");
			map.put("resultData", list);
			logger.error("聘用创业导师异常",e);
		}
		logger.info("聘用创业导师end---------------");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
