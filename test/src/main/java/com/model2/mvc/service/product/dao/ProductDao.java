package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ProductDao {

	public ProductDao() {}
	
	public void addProduct(ProductVO productVO) throws Exception{
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO product values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		stmt = con.prepareStatement(sql);
		
		String date = productVO.getManuDate().replaceAll("-", "");
		
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, date);
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		
		stmt.executeUpdate();
		
		con.close();
		stmt.close();
		
	}
	
	public ProductVO getProduct(int prodNo) throws Exception{
		ProductVO productVO = new ProductVO();
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from product WHERE prod_no = ?";
		
		stmt = con.prepareStatement(sql);
//		stmt.setString(1, Integer.toString(prodNO));
		stmt.setInt(1,prodNo);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			productVO.setFileName(rs.getString("image_file"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setRegDate(rs.getDate("reg_date")); // DATETIME type?  casting?
			
		} // to bean
		
		System.out.println("in dao getProduct:" + productVO.toString());
		
		
		
		con.close();
		stmt.close();
		rs.close(); // to bean
		
		
		return productVO;
		
	}
	
	
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product ";
		
		if(searchVO.getSearchCondition() != null) {
			
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where prod_no='" + searchVO.getSearchKeyword() +"'";
			}
			else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where prod_name='" + searchVO.getSearchKeyword() +"'";
			}
			else if (searchVO.getSearchCondition().equals("2")) {
				sql += " where price='" + searchVO.getSearchKeyword() +"'";
			}
			
			
		} // �˻� ó�� 
		
		
		sql += "order by reg_date desc"; // �������� ���� 
		
		PreparedStatement stmt = 
				con.prepareStatement(	sql,
															ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.last();
		int total = rs.getRow(); // Row ���� get
		System.out.println("�ο��� ��:" + total);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("count", total);
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
	
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); // �ش� page �� ù��° row �� �̵��ϴ� ��
		if(total > 0) {
			for(int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO productVO = new ProductVO();
				
				productVO.setFileName(rs.getString("image_file"));
				productVO.setManuDate(rs.getString("manufacture_day"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setProdDetail(rs.getString("prod_detail"));
				productVO.setProdName(rs.getString("prod_name"));
				productVO.setProdNo(rs.getInt("prod_no"));
				productVO.setRegDate(rs.getDate("reg_date")); // DATETIME type?  casting?
				
				
				list.add(productVO);
				
				
//				if(productVO.getProTranCode().trim().equals("���ſϷ�")) {
//					System.out.println("in productVO get ���ſϷ�:: "+ productVO.toString());
//				}
//				
				if(!rs.next()) {
					break;
				}
				
			}
		}
		
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
		stmt.close();
		rs.close();
		return map;
		
	}
	
	public HashMap<String,Object> getProductList(SearchVO searchVO, List<Integer> list) throws Exception{
		
		System.out.println("productdao::" + list.toString());
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product ";
		
		if(searchVO.getSearchCondition() != null) {
			
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where prod_no='" + searchVO.getSearchKeyword() +"'";
			}
			else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where prod_name='" + searchVO.getSearchKeyword() +"'";
			}
			else if (searchVO.getSearchCondition().equals("2")) {
				sql += " where price='" + searchVO.getSearchKeyword() +"'";
			}
			
			
		} // �˻� ó�� 
		
		
		sql += "order by reg_date desc"; // �������� ���� 
		
		int total = this.getTotalCount(sql, searchVO);
		
		sql = this.makeCurrentPageSql(sql, searchVO);
		PreparedStatement stmt = 
				con.prepareStatement(	sql,
															ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = stmt.executeQuery();
		
//		rs.last();
//		int total = rs.getRow(); // Row ���� get
		
		System.out.println("totalCount:" + total);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("count", total);
		ArrayList<ProductVO> plist = new ArrayList<ProductVO>();
	
		//rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); // �ش� page �� ù��° row �� �̵��ϴ� ��
		rs.next();
		if(total > 0) {
			for(int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO productVO = new ProductVO();
				
				productVO.setFileName(rs.getString("image_file"));
				productVO.setManuDate(rs.getString("manufacture_day"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setProdDetail(rs.getString("prod_detail"));
				productVO.setProdName(rs.getString("prod_name"));
				productVO.setProdNo(rs.getInt("prod_no"));
				productVO.setRegDate(rs.getDate("reg_date")); // DATETIME type?  casting?
				
				System.out.println("productdao::" + productVO.getProdNo());
				if(list != null & list.contains(productVO.getProdNo())) {
					productVO.setProTranCode("구매완료");
				}
				
				plist.add(productVO);
				
				
				if(productVO.getProTranCode().trim().equals("구매완료")) {
					System.out.println("in productVO get 구매완료:: "+ productVO.toString());
				}
				
				if(!rs.next()) {
					break;
				}
				
			}
		}
		
		System.out.println("list.size() : "+ list.size());
		map.put("list", plist);
		System.out.println("map().size() : "+ map.size());

		con.close();
		stmt.close();
		rs.close();
		return map;
		
	}
	
	public void updateProduct(ProductVO productVO) throws Exception{
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		String sql = "Update product SET prod_no = ? , prod_name = ? , prod_detail = ?, manufacture_day = ? , price =?, image_file = ? WHERE prod_no = ? ";
		
		stmt = con.prepareStatement(sql);
		
		String date = productVO.getManuDate().replaceAll("-", "");
		
		stmt.setInt(1, productVO.getProdNo());
		stmt.setString(2, productVO.getProdName());
		stmt.setString(3, productVO.getProdDetail());
		stmt.setString(4, date);
		stmt.setInt(5, productVO.getPrice());
		stmt.setString(6, productVO.getFileName());
		stmt.setInt(7, productVO.getProdNo());
		
		stmt.executeUpdate();
		
		
		
		con.close();
		stmt.close();
	}
	
	private int getTotalCount(String sql, SearchVO searchVO) throws Exception {
		sql = "SELECT COUNT(*)"
				+ "FROM ( SELECT ROW_NUMBER() OVER (ORDER BY reg_date DESC), product.*"
				+ "FROM ("+sql+") product )";
		
		System.out.println("getTotalCount sql ::" + sql);
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		
		
		return totalCount;		
	}
	
	private String makeCurrentPageSql(String sql, SearchVO searchVO) {
		System.out.println("ProductDao Search::" + searchVO.getPage() +" " + searchVO.getPageSize());
		sql = "SELECT * "
				+ "FROM ( SELECT inner_table.*, ROWNUM AS row_seq "
				+ "FROM ( " + sql +" ) inner_table "
						+ "WHERE ROWNUM <=" +searchVO.getPage()*searchVO.getPageSize()+" ) " + 
						"WHERE row_seq BETWEEN "+((searchVO.getPage()-1)*searchVO.getPageSize()+1) +" AND "+searchVO.getPage()*searchVO.getPageSize();
		
		System.out.println("ProductDao :: make SQL :: "+ sql);	
		
		return sql;

	}
}