package com.model2.mvc.service.user.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.domain.User;


public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO=new UserDAO();
	}

	public void addUser(User User) throws Exception {
		userDAO.insertUser(User);
	}

	public User loginUser(User User) throws Exception {
			User dbUser=userDAO.findUser(User.getUserId());

			if(! dbUser.getPassword().equals(User.getPassword()))
				throw new Exception("�α��ο� �����߽��ϴ�.");
			
			return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}

	public void updateUser(User User) throws Exception {
		userDAO.updateUser(User);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User User=userDAO.findUser(userId);
		if(User != null) {
			result=false;
		}
		return result;
	}
	
	public void removeUser(String userId) throws Exception{
		userDAO.removeUser(userId);
	}
}