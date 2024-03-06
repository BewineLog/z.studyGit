package com.model2.mvc.service.user.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.domain.User;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDAO userDAO;
	
	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public int addUser(User User) throws Exception {
		return userDAO.insertUser(User);
	}

	public User loginUser(User User) throws Exception {
			User dbUser=userDAO.findUser(User.getUserId());
			System.out.println("Logon DAO ::" + dbUser.getPassword());
			if(! dbUser.getPassword().equals(User.getPassword()))
				throw new Exception("�α��ο� �����߽��ϴ�.");
			
			return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public List<Object> getUserList(SearchVO searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}
	
	public int getTotalCount() {
		return userDAO.getTotalCount();
	}

	public int updateUser(User User) throws Exception {
		return userDAO.updateUser(User);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User User=userDAO.findUser(userId);
		if(User != null) {
			result=false;
		}
		return result;
	}
	
	public int removeUser(String userId) throws Exception{
		return userDAO.removeUser(userId);
	}
}