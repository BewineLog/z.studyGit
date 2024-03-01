package com.model2.mvc.service.user;

import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.dao.UserDAO;


public interface UserService {
	
	public void setUserDao(UserDAO userDao);
	
	public int addUser(User User) throws Exception;
	
	public User loginUser(User User) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public List<Object> getUserList(SearchVO searchVO) throws Exception;
	
	public int getTotalCount();
	
	public int updateUser(User User) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
	public int removeUser(String userId) throws Exception;
	
}