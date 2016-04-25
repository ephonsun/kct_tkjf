package com.framework.core.utils;

import java.io.Serializable;


@SuppressWarnings({ "serial"})
public class GlobalConst implements Serializable {
	
	public static long stime = 1800000;//超时时长（毫秒）
	public static String LOG_PATH = "";
	public static String ADMIN = "emkt";//超级系统管理员
	public static String IS_DEBUG = "1";//测试帐号标志位
	public static String TARGET_USER_ADDR = "";//目标客户群地址
	public static String TARGET_CRM_ADDR = "";//CRM配置地址
	public static final String DB_NAME = "emkt.";//数据库用户名
	public static final String DATA_DB = "DATA_DB";
	public static final String SYSMGMT_DB = "SYSMGMT_DB";
	public static final String DIALECT = "ORACLE";
	public static String DATE_PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final int NUMBERS_PER_PAGE = 15;
	public static final String SESSION_KEY = "mp_appsession";//系统session键值常量
	public static String ATTACH_UPLOAD_PATH = "";//附件路径
	public static final String HLEP_FILE_NAME = "会签审核(客户协议、客服口径、风险分析).xls";
	public static String OaServicePortAddress = "";//Oa接口服务端地址
	public static String OaServicePortNewAddress = "";//Oa接口服务端地址
	public static String OaServicePortYXHDBGCreateAddress = "";//Oa变更服务端地址
	public static String OaServicePortYXHDBGCreateNewAddress = "";//Oa变更服务端地址
	public static String SJBBDraftServiceAddress = "";//Oa充值科目接口服务端地址
	public static String TARGET = "";//sso单点登录请求ip
	public static String SSOADDR = "";//sso服务地址
	public static String FRAME_ID = "F00000000016";//imei群
	
	public static String sendBOSSUrl_13_addPlan_ntyz = "";
	public static String sendBOSSUrl_13_addPlan_tzyc = "";
	public static String sendBOSSUrl_14_addPlan_wx = "";
	public static String sendBOSSUrl_14_addPlan_czhazj = "";
	public static String sendBOSSUrl_13_addPlan_nj = "";
	public static String sendBOSSUrl_13_addPlan_xzlygsq = "";
	public static String sendBOSSUrl_14_addPlan_sz = "";
	public static String TestReqBossServer = "";
	public static String AddMutualExclusionGroup = "新增营销互斥组";
	public static String EditMutualExclusionGroup = "修改营销互斥组";
	public static String ViewMutualExclusionGroup = "查看营销互斥组";
	public static String sendCrmUrl = "";
	public static String sendCrmUrlMR = "";
	public static String HELP_DOCUMENT_FILE_NAME = "中国移动通信集团江苏有限公司营销全过程操作手册.docx";
	
	
	
	/** 核稿 */
	public static final String STATE_HG = "2";
	/**核稿不通过*/
	public static final String STATE_HG_NG = "3";
	/**待品管法律审核*/
	public static final String STATE_PGFL = "4";
	/**待法律审核*/
	public static final String STATE_FL = "5";
	/**法律审核不通过*/
	public static final String STATE_FL_NG = "6";
	/**待品管审核*/
	public static final String STATE_PGSH = "7";
	/**品管审核不通过*/
	public static final String STATE_PG_NG = "8";
	/**配置审核*/
	public static final String STATE_PZSH = "9";
	/**配置审核不通过*/
	public static final String STATE_PZSH_NG = "10";
	/**待提交OA审核*/
	public static final String STATE_DTJOA = "11";
	/**待OA审核*/
	public static final String STATE_DOA = "12";
	/**待省IT审核*/
	public static final String STATE_DIT = "13";
	/**省IT审核不通过*/
	public static final String STATE_IT_NG = "14";
	/**待上线*/
	public static final String STATE_DSX = "15";
	/**已上线*/
	public static final String STATE_YSX = "17";
	/** 已结束 */
	public static final String STATE_END = "18";
	/** OA审核不通过 */
	public static final String STATE_OA_NG = "19";
	/** 待提交使用 */
	public static final String STATE_WAIT_COMMIT = "20";
	/** 已提交使用 */
	public static final String STATE_ALREADY_COMMIT = "21";
	/** 配置不通过(模板未能使用) */
	public static final String STATE_CONF_NG_NOTUSER = "22";
	/** 变更流程待省公司审核 */
	public static final String STATE_PROVINCE_CHECK = "25";
	/** 变更流程省公司审核不通过 */
	public static final String STATE_PROVINCE_CHECK_NG = "26";
	/** 变更流程待提交工单 */
	public static final String STATE_SUBMIT_FLOW = "27";
	/** 变更流程工单完成 */
	public static final String STATE_FLOW_END = "28";
	/** 商业计划书停止使用 */
	public static final String STATE_STOP_USE = "29";

	/** 默认状态 */
	public static final String SUB_STATE0 = "0";
	/** 法律未审核，品管未审核 */
	public static final String SUB_STATE1 = "1";
	/** 法律审核通过，品管未审核 */
	public static final String SUB_STATE2 = "2";
	/** 法律未审核，品管审核通过 */
	public static final String SUB_STATE3 = "3";
	/** 法律审核通过，品管审核通过 */
	public static final String SUB_STATE4 = "4";

	/** 充值工单状态策划 */
	public static final String STATE_PLAN = "1";
	/** 充值工单状态待提交OA */
	public static final String STATE_TOOA = "2";
	/** 充值工单状态待OA审核 */
	public static final String STATE_WAIT_OA = "3";
	/** 充值工单状态OA审核不通过 */
	public static final String STATE_OA_CHECK_NG = "4";
	/** 充值工单状态待省IT处理 */
	public static final String STATE_WAIT_IT_DEAL = "5";
	/** 充值工单状态省IT处理不通过 */
	public static final String STATE_IT_DEAL_NG = "6";
	/** 充值工单状态完成 */
	public static final String STATE_RECHARGE_END = "7";
	/**
	 * 省公司地区编码
	 */
	public static final String PROVINCE_AREA_ID = "2000250";//江苏省

	/**
	 * 文件大小限制20M
	 */
	public static final long FILE_MAX_SIZE = 20971520;
}
