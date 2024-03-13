package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

//import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.common.Page;
//import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.common.SearchVO;
//import com.model2.mvc.service.user.impl.UserServiceImpl;

public interface PurchaseDao {
	
	public void setSqlSession(SqlSession sqlSession);
	
	
	public int addPurchase(Purchase Purchase) throws Exception;
	
	public Purchase getPurchase(int prodNo) throws Exception;
	
	public Purchase getPurchaseByTranNo(int tranNo) throws Exception;

	public List<Object> getPurchaseList(String buyer_id,Page page) throws Exception;
	
//	public Map<String,Object> getPurchaseListProdNo(String buyer_id,String tranCode) throws Exception;
	
	public int updatePurchase(Purchase purchase) throws Exception;
	
	public int getTotalCount(String buyer_id) throws Exception;
	
	public int deletePurchase(int tranNo) throws Exception;

}