package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class PurchaseDao {
	
	public PurchaseDao() {};
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception{
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO transaction values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		
		String keys[] = {"tran_no","order_data"};
		stmt = con.prepareStatement(sql, keys);
				
		
        stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
        stmt.setString(2, purchaseVO.getBuyer().getUserId());
        stmt.setString(3, purchaseVO.getPaymentOption());
        stmt.setString(4, purchaseVO.getReceiverName());
        stmt.setString(5, purchaseVO.getReceiverPhone());
        stmt.setString(6, purchaseVO.getDivyAddr());
        stmt.setString(7, purchaseVO.getDivyRequest());
        stmt.setString(8, "1");
        
        System.out.println(purchaseVO.getPurchaseProd().getProdNo());
        System.out.println(purchaseVO.getBuyer().getUserId());
        System.out.println(purchaseVO.getPaymentOption());
        System.out.println(purchaseVO.getReceiverName());
        System.out.println(purchaseVO.getReceiverPhone());
        System.out.println(purchaseVO.getDivyAddr());
        System.out.println(purchaseVO.getDivyRequest());
        System.out.println(purchaseVO.getTranCode());
        
//        stmt.setDate(9, purchaseVO.getOrderDate());
        stmt.setDate(9, Date.valueOf(purchaseVO.getDivyDate()));	
		stmt.executeUpdate();
		
		rs = stmt.getGeneratedKeys();
		if(rs.next()) {
		
		
		purchaseVO.setTranNo(rs.getInt(1));
		purchaseVO.setOrderDate(rs.getDate(2));
		}
		
		
		con.close();
		stmt.close();
	}
	
	public PurchaseVO getPurchase(int prodNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ProductServiceImpl pImpl = new ProductServiceImpl();
		UserServiceImpl uImpl = new UserServiceImpl();
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		String sql = "SELECT buyer_id, demailaddr, TO_CHAR(dlvy_date, 'YYYY-MM-DD') dlvy_date_f, dlvy_request, order_data,payment_option ,receiver_name,receiver_phone, tran_status_code, tran_no,prod_no FROM transaction WHERE prod_no = ?";
		
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		rs = stmt.executeQuery();
		

		if(rs.next()) {
			purchaseVO.setBuyer(uImpl.getUser(rs.getString("buyer_id")));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date_f"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPurchaseProd(pImpl.getProduct(rs.getInt("prod_no")));
		} // to bean	
		
		
		return purchaseVO;
		
		
	}
	
	public PurchaseVO getPurchaseByTranNo(int tranNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ProductServiceImpl pImpl = new ProductServiceImpl();
		UserServiceImpl uImpl = new UserServiceImpl();
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		String sql = "SELECT buyer_id, demailaddr, TO_CHAR(dlvy_date, 'YYYY-MM-DD') dlvy_date_f, dlvy_request, order_data,payment_option ,receiver_name,receiver_phone, tran_status_code, tran_no,prod_no FROM transaction WHERE tran_no = ?";
		
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		rs = stmt.executeQuery();
		

		if(rs.next()) {
			purchaseVO.setBuyer(uImpl.getUser(rs.getString("buyer_id")));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date_f"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPurchaseProd(pImpl.getProduct(rs.getInt("prod_no")));
		} // to bean	
		
		
		return purchaseVO;
		
		
	}
	

	public Map<String,Object> getPurchaseList(String buyer_id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		
		ProductServiceImpl pImpl = new ProductServiceImpl();
		UserServiceImpl uImpl = new UserServiceImpl();
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		String sql = "select * from transaction where buyer_id = ?";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, buyer_id);
		rs = stmt.executeQuery();
		
		int totalCount = this.getTotalCount(sql, buyer_id);
		System.out.println("purchaseDao totalCount: " + totalCount);
		
		map.put("count",totalCount);
		
		
		
		
		
		List<PurchaseVO> list  = new ArrayList<PurchaseVO>();
		
		while(rs.next()) {
			
			if(!rs.getString("buyer_id").toString().equals(buyer_id)) {
				continue;
			}
			
			PurchaseVO purchaseVO = new PurchaseVO();
			
			
			purchaseVO.setBuyer(uImpl.getUser(rs.getString("buyer_id")));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPurchaseProd(pImpl.getProduct(rs.getInt("prod_no")));
			
			
			String tran_status_code = rs.getString("tran_status_code");
			
			System.out.println("purchaseDao tran_status_code::" + tran_status_code);
			
			if (tran_status_code != null) {
				
				if(tran_status_code.trim().equals("1")) {
					purchaseVO.getPurchaseProd().setProTranCode("구매완료"); //
				}else if(tran_status_code.trim().equals("2")) {
					purchaseVO.getPurchaseProd().setProTranCode("배송중"); //
				}else if(tran_status_code.trim().equals("3")) {
					purchaseVO.getPurchaseProd().setProTranCode("배송완료"); //
				}
			}
			
			list.add(purchaseVO);
				
		} // to bean	
		
		map.put("list", list);
		
		return map;
		
	}
	
	
	public Map<String,Object> getPurchaseListProdNo(String buyer_id,String tranCode) throws Exception{
		System.out.println("getPurcahseListProdNo !!!:: "+ buyer_id);
		
		Map<String,Object> map = new HashMap<String,Object>();

		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from transaction";
		
		if(buyer_id.equals("admin")) {
			stmt = con.prepareStatement(sql);
			
			
			System.out.println("purchaseDao getlistProdNo admin");
		}else {
			sql+= " where buyer_id = ? and tran_status_code = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, buyer_id);
			stmt.setString(2, tranCode);
		}
		
		rs= stmt.executeQuery();
		
		List<Integer> list  = new ArrayList<Integer>();
		
		while(rs.next()) {
			
			if( !buyer_id.equals("admin") && !rs.getString("buyer_id").toString().equals(buyer_id)) {
				continue;
			}
			
			list.add(Integer.parseInt(rs.getString("prod_no")));
				
		} // to bean	
		
		System.out.println("purchasedao getPurchaseProdNo::" + list.toString());
		map.put("prodNoList", list);
		
		return map;
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception{
		String sql = "Update transaction\n"
				+ "SET payment_option=?, receiver_name=?, receiver_phone=?, dlvy_request=?, dlvy_date=?, demailaddr=?, tran_status_code=?\n"
				+ "WHERE tran_no = ? and prod_no = ?";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDivyRequest());
		stmt.setDate(5, Date.valueOf(purchaseVO.getDivyDate()));
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getTranCode());
		stmt.setInt(8,purchaseVO.getTranNo());
		stmt.setInt(9, purchaseVO.getPurchaseProd().getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
		stmt.close();
	}
	
	
	private int getTotalCount(String sql,String buyer_id) throws Exception{
		sql = "SELECT COUNT(*) as count FROM ( "
				+ sql +  ")";
		
		System.out.println("getTotalCount sql::" + sql);
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		stmt = con.prepareStatement(sql);
		stmt.setString(1, buyer_id);
		rs = stmt.executeQuery();
		
		int totalCount = 0;
		
		if(rs.next()) {
			totalCount = rs.getInt(1);
			System.out.println("getTotalCount ::" + totalCount);
		}
		
		con.close();
		stmt.close();
		rs.close();
				
		return totalCount;
		
		
	}

}