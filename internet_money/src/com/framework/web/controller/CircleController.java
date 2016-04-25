package com.framework.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.core.controller.BaseController;
import com.framework.core.entity.PoJo;
import com.framework.core.entity.testbean;
import com.framework.core.utils.OtherUtils;
import com.framework.web.service.CircleService;

@Controller
@RequestMapping("CircleController")
public class CircleController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(CircleController.class);
	
	@Resource
	private CircleService circleService;
	
	
	
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
	 * 发布帖子评论
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/test",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object test(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute("testbean")testbean testbean,BindingResult bindingResult) throws IOException{
		logger.info("发布帖子评论begin---------------");
		Map<String, Object> args = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<FieldError>  err=bindingResult.getFieldErrors(); 
        FieldError fe; 
        String field; 
        String errorMessage; 
        for (int i = 0; i < err.size(); i++) { 
            fe=err.get(i); 
            System.out.println(fe);
            System.out.println(fe.getCode());
            field=fe.getField();//得到哪个字段验证出错 
            errorMessage=fe.getDefaultMessage();//得到错误消息 
            System.out.println("错误字段消息："+field +" : "+errorMessage); 
        } 
		
		return map;
	}
	
	
	
	

}
