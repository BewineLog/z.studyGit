package com.model2.mvc.service.user.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.service.domain.User;


public interface UserDAO{
	
	public void setSqlSession(SqlSession sqlSession) throws Exception;

	public int insertUser(User user) throws Exception;

	public User findUser(String userId) throws Exception;

	public List<Object> getUserList(SearchVO search) throws Exception;

	public int updateUser(User user) throws Exception;
	
	public int removeUser(String userId) throws Exception;
	
	public int getTotalCount();
	

}