package com.kct.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kct.web.dao.UserDao;
import com.kct.web.pojo.User;
import com.kct.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource  
    private UserDao userDao;  
	
    @Override
    public User getUserById(int userId) {  
        return this.userDao.selectByPrimaryKey(userId);

}
    
}
