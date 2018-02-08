package com.xin.service;

import com.xin.dao.IUserDAO;
import com.xin.dao.UserDAOImple;
import com.xin.vo.User;

public class UserServiceImple implements IUserService {
  
    private IUserDAO dao = new UserDAOImple();
	
	@Override
	public boolean login(User user) {
		return dao.login(user);
	}

}
