package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.service.domain.Product;


public interface ProductDao {
	public void setSqlSession(SqlSession sqlSession);
	public int addProduct(Product product) throws Exception;
	public Product getProduct(int prodNo) throws Exception;
	public List<Object> getProductList(SearchVO search) throws Exception;
	public int updateProduct(Product product) throws Exception;
	public int getTotalCount(SearchVO search) throws Exception;
	public int removeProduct(String prodName);
	public List<String> getAutocompleteList(String keyword) throws Exception;
}
