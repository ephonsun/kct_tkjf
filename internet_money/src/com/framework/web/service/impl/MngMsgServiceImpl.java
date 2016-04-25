package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MngMsgService;

@Service("msgManageService")
public class MngMsgServiceImpl extends BaseServiceImpl implements MngMsgService {

	
	@Override
	public int pubnews(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_gov_news (title,shortContent,content,url,type,district,time) ");
		sql.append(
				" values (:title,:shortContent,:content,:url,:type,:district,:time) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}
	
	
	@Override
	public int news_updateZt(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_gov_news SET state="+args.get("state")+" where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}
	
	
	
	
	
	
	@Override
	public List<?> selectnewslist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,t.title,t.type,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,SUBSTR(REPLACE(REPLACE(t.content,'<p>',''),'</p>','') FROM 1 FOR 40) shortContent,t.url FROM e_gov_news t  ");
		if(!"".equals(args.get("zflx_id")) && args.get("zflx_id") != null ){
			sql.append(" WHERE t.type = "+args.get("zflx_id")+" ");
		}
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int selectnewslistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_gov_news t  ");
		if(!"".equals(args.get("zflx_id")) && args.get("zflx_id") != null ){
			sql.append(" WHERE t.type = "+args.get("zflx_id")+" ");
		}
		return this.getQueryForInt(sql.toString());
	}
	
	@Override
	public List<?> selectnews(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.type, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.content, ");
		sql.append(" t.url ");
		sql.append(" FROM e_gov_news t where t.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updatenewstag(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE new SET tag=:tag WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> waitermsglist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM news t ");
		sql.append(" WHERE t.id > "+args.get("num")+"*"+(Integer.valueOf((String) args.get("page"))-1)+" ");
		sql.append("   AND t.id <= "+args.get("num")+"*"+(Integer.valueOf((String) args.get("page"))+1)+" ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int waitermsgzt(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE new SET tag=:tag WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int delete_news(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from e_gov_news where id=:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}


	@Override
	public int news_edit(Map<String, Object> args) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int news_editTj(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_gov_news SET title=:title,shortContent=:shortContent,content=:content,url=:url,district=:district,type=:type,time=:time,user_id=:user_id WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}









	

	

	

	

}
