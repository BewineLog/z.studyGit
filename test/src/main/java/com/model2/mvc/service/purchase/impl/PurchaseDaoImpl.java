package com.model2.mvc.service.purchase.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

//import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import com.model2.mvc.common.Page;
//import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.common.SearchVO;
//import com.model2.mvc.service.user.impl.UserServiceImpl;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public PurchaseDaoImpl() {};
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int addPurchase(Purchase purchase) throws Exception{
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		String sql = "INSERT INTO transaction values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
//		
//		String keys[] = {"tran_no","order_data"};
//		stmt = con.prepareStatement(sql, keys);
//				
//		
//        stmt.setInt(1, Purchase.getPurchaseProd().getProdNo());
//        stmt.setString(2, Purchase.getBuyer().getUserId());
//        stmt.setString(3, Purchase.getPaymentOption());
//        stmt.setString(4, Purchase.getReceiverName());
//        stmt.setString(5, Purchase.getReceiverPhone());
//        stmt.setString(6, Purchase.getDivyAddr());
//        stmt.setString(7, Purchase.getDivyRequest());
//        stmt.setString(8, "1");
//        
//        System.out.println(Purchase.getPurchaseProd().getProdNo());
//        System.out.println(Purchase.getBuyer().getUserId());
//        System.out.println(Purchase.getPaymentOption());
//        System.out.println(Purchase.getReceiverName());
//        System.out.println(Purchase.getReceiverPhone());
//        System.out.println(Purchase.getDivyAddr());
//        System.out.println(Purchase.getDivyRequest());
//        System.out.println(Purchase.getTranCode());
//        
////        stmt.setDate(9, Purchase.getOrderDate());
//        stmt.setDate(9, Date.valueOf(Purchase.getDivyDate()));	
//		stmt.executeUpdate();
//		
//		rs = stmt.getGeneratedKeys();
//		if(rs.next()) {
//		
//		
//		Purchase.setTranNo(rs.getInt(1));
//		Purchase.setOrderDate(rs.getDate(2));
//		}
//		
//		
//		con.close();
//		stmt.close();
		
		int value = sqlSession.insert("PurchaseMapper.insertPurchase",purchase);
//		Map map = sqlSession.selectOne("PurchaseMapper.getGeneratedKey");
//		
//		purchase.setTranNo( Integer.parseInt(map.get("tran_no").toString()));
		purchase.setOrderDate(Date.valueOf(sqlSession.selectOne("getSysdate").toString()));
		
		return value;
	}
	
	public Purchase getPurchase(int prodNo) throws Exception{
		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		ProductServiceImpl pImpl = new ProductServiceImpl();
//		UserServiceImpl uImpl = new UserServiceImpl();
//		
//		Purchase Purchase = new Purchase();
//		
//		String sql = "SELECT buyer_id, demailaddr, TO_CHAR(dlvy_date, 'YYYY-MM-DD') dlvy_date_f, dlvy_request, order_data,payment_option ,receiver_name,receiver_phone, tran_status_code, tran_no,prod_no FROM transaction WHERE prod_no = ?";
//		
//		stmt = con.prepareStatement(sql);
//		stmt.setInt(1, prodNo);
//		
//		rs = stmt.executeQuery();
//		
//
//		if(rs.next()) {
//			Purchase.setBuyer(uImpl.getUser(rs.getString("buyer_id")));
//			Purchase.setDivyAddr(rs.getString("demailaddr"));
//			Purchase.setDivyDate(rs.getString("dlvy_date_f"));
//			Purchase.setDivyRequest(rs.getString("dlvy_request"));
//			Purchase.setOrderDate(rs.getDate("order_data"));
//			Purchase.setPaymentOption(rs.getString("payment_option"));
//			Purchase.setReceiverName(rs.getString("receiver_name"));
//			Purchase.setReceiverPhone(rs.getString("receiver_phone"));
//			Purchase.setTranCode(rs.getString("tran_status_code"));
//			Purchase.setTranNo(rs.getInt("tran_no"));
//			Purchase.setPurchaseProd(pImpl.getProduct(rs.getInt("prod_no")));
//		} // to bean	
//		
//		
//		return Purchase;
		
		return sqlSession.selectOne("PurchaseMapper.getPurchase",prodNo);
		
		
	}
	
	public Purchase getPurchaseByTranNo(int tranNo) throws Exception{
		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		ProductServiceImpl pImpl = new ProductServiceImpl();
//		UserServiceImpl uImpl = new UserServiceImpl();
//		
//		Purchase Purchase = new Purchase();
//		
//		String sql = "SELECT buyer_id, demailaddr, TO_CHAR(dlvy_date, 'YYYY-MM-DD') dlvy_date_f, dlvy_request, order_data,payment_option ,receiver_name,receiver_phone, tran_status_code, tran_no,prod_no FROM transaction WHERE tran_no = ?";
//		
//		stmt = con.prepareStatement(sql);
//		stmt.setInt(1, tranNo);
//		
//		rs = stmt.executeQuery();
//		
//
//		if(rs.next()) {
//			Purchase.setBuyer(uImpl.getUser(rs.getString("buyer_id")));
//			Purchase.setDivyAddr(rs.getString("demailaddr"));
//			Purchase.setDivyDate(rs.getString("dlvy_date_f"));
//			Purchase.setDivyRequest(rs.getString("dlvy_request"));
//			Purchase.setOrderDate(rs.getDate("order_data"));
//			Purchase.setPaymentOption(rs.getString("payment_option"));
//			Purchase.setReceiverName(rs.getString("receiver_name"));
//			Purchase.setReceiverPhone(rs.getString("receiver_phone"));
//			Purchase.setTranCode(rs.getString("tran_status_code"));
//			Purchase.setTranNo(rs.getInt("tran_no"));
//			Purchase.setPurchaseProd(pImpl.getProduct(rs.getInt("prod_no")));
//		} // to bean	
//		
//		
//		return Purchase;
//		
		
		return sqlSession.selectOne("PurchaseMapper.getPurchaseByTranNo",tranNo);
		
	}
	

	public List<Object> getPurchaseList(String buyer_id,Page page) throws Exception{
//		Map<String,Object> map = new HashMap<String,Object>();
//		
//		ProductServiceImpl pImpl = new ProductServiceImpl();
//		UserServiceImpl uImpl = new UserServiceImpl();
//		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		
//		String sql = "select ROWNUM row_seq , t.* from transaction t where buyer_id = ? ";
//		
//		int totalCount = this.getTotalCount(sql, buyer_id);
//		System.out.println("purchaseDao totalCount: " + totalCount);
//		
//		map.put("count",totalCount);
//		
//		sql += " AND ROWNUM <= " + searchVO.getPage() * searchVO.getPageSize();
//		sql += " ORDER BY tran_no desc";
//		
//		sql = this.makeCurrentPageSql(sql, searchVO);
//		
//		stmt = con.prepareStatement(sql);
//		stmt.setString(1, buyer_id);
//		rs = stmt.executeQuery();
//		
//		
//		
//		
//		
//		List<Purchase> list  = new ArrayList<Purchase>();
//		
//		while(rs.next()) {
//			
//			if(!rs.getString("buyer_id").toString().equals(buyer_id)) {
//				continue;
//			}
//			
//			Purchase Purchase = new Purchase();
//			
//			
//			Purchase.setBuyer(uImpl.getUser(rs.getString("buyer_id")));
//			Purchase.setDivyAddr(rs.getString("demailaddr"));
//			Purchase.setDivyDate(rs.getString("dlvy_date"));
//			Purchase.setDivyRequest(rs.getString("dlvy_request"));
//			Purchase.setOrderDate(rs.getDate("order_data"));
//			Purchase.setPaymentOption(rs.getString("payment_option"));
//			Purchase.setReceiverName(rs.getString("receiver_name"));
//			Purchase.setReceiverPhone(rs.getString("receiver_phone"));
//			Purchase.setTranCode(rs.getString("tran_status_code"));
//			Purchase.setTranNo(rs.getInt("tran_no"));
//			Purchase.setPurchaseProd(pImpl.getProduct(rs.getInt("prod_no")));
//			
//			
//			String tran_status_code = rs.getString("tran_status_code");
//			
//			System.out.println("purchaseDao tran_status_code::" + tran_status_code);
//			
//			if (tran_status_code != null) {
//				
//				if(tran_status_code.trim().equals("1")) {
//					Purchase.getPurchaseProd().setProTranCode("구매완료"); //
//				}else if(tran_status_code.trim().equals("2")) {
//					Purchase.getPurchaseProd().setProTranCode("배송중"); //
//				}else if(tran_status_code.trim().equals("3")) {
//					Purchase.getPurchaseProd().setProTranCode("배송완료"); //
//				}
//			}
//			
//			list.add(Purchase);
//				
//		} // to bean	
//		
//		map.put("list", list);
//		
//		return map;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("buyerId", buyer_id);
		
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList",map);
		
	}
	
	
//	public Map<String,Object> getPurchaseListProdNo(String buyer_id,String tranCode) throws Exception{
//		System.out.println("getPurcahseListProdNo !!!:: "+ buyer_id);
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		String sql = "select * from transaction";
//		
//		if(buyer_id.equals("admin")) {
//			stmt = con.prepareStatement(sql);
//			
//			
//			System.out.println("purchaseDao getlistProdNo admin");
//		}else {
//			sql+= " where buyer_id = ? and tran_status_code = ?";
//			
//			stmt = con.prepareStatement(sql);
//			stmt.setString(1, buyer_id);
//			stmt.setString(2, tranCode);
//		}
//		
//		rs= stmt.executeQuery();
//		
//		List<Integer> list  = new ArrayList<Integer>();
//		
//		while(rs.next()) {
//			
//			if( !buyer_id.equals("admin") && !rs.getString("buyer_id").toString().equals(buyer_id)) {
//				continue;
//			}
//			
//			list.add(Integer.parseInt(rs.getString("prod_no")));
//				
//		} // to bean	
//		
//		System.out.println("purchasedao getPurchaseProdNo::" + list.toString());
//		map.put("prodNoList", list);
//		
//		return map;
//	}
	
	public int updatePurchase(Purchase purchase) throws Exception{
//		String sql = "Update transaction\n"
//				+ "SET payment_option=?, receiver_name=?, receiver_phone=?, dlvy_request=?, dlvy_date=?, demailaddr=?, tran_status_code=?\n"
//				+ "WHERE tran_no = ? and prod_no = ?";
//		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		
//		stmt = con.prepareStatement(sql);
//		stmt.setString(1, Purchase.getPaymentOption());
//		stmt.setString(2, Purchase.getReceiverName());
//		stmt.setString(3, Purchase.getReceiverPhone());
//		stmt.setString(4, Purchase.getDivyRequest());
//		stmt.setDate(5, Date.valueOf(Purchase.getDivyDate()));
//		stmt.setString(6, Purchase.getDivyAddr());
//		stmt.setString(7, Purchase.getTranCode());
//		stmt.setInt(8,Purchase.getTranNo());
//		stmt.setInt(9, Purchase.getPurchaseProd().getProdNo());
//		
//		stmt.executeUpdate();
//		
//		con.close();
//		stmt.close();
		
		return sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	
	public int deletePurchase(int tranNo) throws Exception{
		return sqlSession.delete("PurchaseMapper.deleteProduct",tranNo);
	}
	
	
	public int getTotalCount(String buyer_id) throws Exception{
//		sql = "SELECT COUNT(*) as count FROM ( "
//				+ sql +  ")";
//		
//		System.out.println("getTotalCount sql::" + sql);
//		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		stmt = con.prepareStatement(sql);
//		stmt.setString(1, buyer_id);
//		rs = stmt.executeQuery();
//		
//		int totalCount = 0;
//		
//		if(rs.next()) {
//			totalCount = rs.getInt(1);
//			System.out.println("getTotalCount ::" + totalCount);
//		}
//		
//		con.close();
//		stmt.close();
//		rs.close();
//				
//		return totalCount;
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("buyerId",buyer_id);
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount",map);
		
		
	}

	
	
	
//	private String makeCurrentPageSql(String sql, SearchVO searchVO) {
//		sql = "SELECT * FROM ("
//				+ sql + ") vt"
//						+ " WHERE vt.row_seq between "
//						+ ((searchVO.getPage() - 1) * searchVO.getPageSize() + 1) + " AND " + (searchVO.getPage() * searchVO.getPageSize());
//		
//		System.out.println("purchaseDao makeCurrentPageSql sql::" + sql);
//		return sql;
//	}

}