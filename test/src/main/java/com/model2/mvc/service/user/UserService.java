package com.model2.mvc.service.user;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.User;


public interface UserService {
	
	public void addUser(User User) throws Exception;
	
	public User loginUser(User User) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public HashMap<String, Object> getUserList(SearchVO searchVO) throws Exception;
	
	public void updateUser(User User) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
	public void removeUser(String userId) throws Exception;
	
}