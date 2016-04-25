package com.framework.web.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.transaction.annotation.Transactional;

import com.framework.core.service.impl.BaseServiceImpl;

@Transactional
public class TestPicMain extends BaseServiceImpl {
	
	@Transactional(noRollbackFor=RuntimeException.class)
	public static void main(String[] args) {
		System.out.println("图片测试");
		
		
		
		
		
		
		
		
		
	}

	
	
	
	public Object PicRead() {
		String str = "";
		try {
			InputStream in = new FileInputStream(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
		
		
	
		return null;
	}
	
	
	
	
	
	
	
	
	
}
