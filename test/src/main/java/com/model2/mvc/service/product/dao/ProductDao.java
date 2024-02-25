package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;


public class ProductDao {

	public ProductDao() {}
	
	public void addProduct(Product Product) throws Exception{
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO product values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		stmt = con.prepareStatement(sql);
		
		String date = Product.getManuDate().replaceAll("-", "");
		System.out.println("productDao addProduct ManuDate::" + date);
		stmt.setString(1, Product.getProdName());
		stmt.setString(2, Product.getProdDetail());
		stmt.setString(3, date);
		stmt.setInt(4, Product.getPrice());
		stmt.setString(5, Product.getFileName());
		
		stmt.executeUpdate();
		
		con.close();
		stmt.close();
		
	}
	
	public Product getProduct(int prodNo) throws Exception{
		Product Product = new Product();
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from product WHERE prod_no = ?";
		
		stmt = con.prepareStatement(sql);
//		stmt.setString(1, Integer.toString(prodNO));
		stmt.setInt(1,prodNo);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			Product.setFileName(rs.getString("image_file"));
			Product.setManuDate(rs.getString("manufacture_day"));
			Product.setPrice(rs.getInt("price"));
			Product.setProdDetail(rs.getString("prod_detail"));
			Product.setProdName(rs.getString("prod_name"));
			Product.setProdNo(rs.getInt("prod_no"));
			Product.setRegDate(rs.getDate("reg_date")); // DATETIME type?  casting?
			
		} // to bean
		
		System.out.println("in dao getProduct:" + Product.toString());
		
		
		
		con.close();
		stmt.close();
		rs.close(); // to bean
		
		
		return Product;
		
	}
	
	
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "select p.*, t.tran_status_code from product p , transaction t";
		
		if(searchVO.getSearchCondition() != null) {
			
			if (searchVO.getSearchCondition().equals("0") && !searchVO.getSearchKeyword().equals("")) {
				sql += " where p.prod_no='" + searchVO.getSearchKeyword() +"'";
			}
			else if (searchVO.getSearchCondition().equals("1") && !searchVO.getSearchKeyword().equals("")) {
				sql += " where p.prod_name='" + searchVO.getSearchKeyword() +"'";
			}
			else if (searchVO.getSearchCondition().equals("2") && !searchVO.getSearchKeyword().equals("")) {
				sql += " where p.price='" + searchVO.getSearchKeyword() +"'";
			}
			
			
		} //
		
		if(sql.contains("where")) {
			sql += " and p.prod_no=t.prod_no(+)";
		}else {
			sql += " WHERE p.prod_no=t.prod_no(+)";
		}
		sql += " order by reg_date desc"; // �������� ���� 
		
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
		ArrayList<Product> list = new ArrayList<Product>();
	
		//rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); // �ش� page �� ù��° row �� �̵��ϴ� ��
		rs.next();
		if(total > 0) {
			for(int i = 0; i < searchVO.getPageUnit(); i++) {
				Product Product = new Product();
				
				Product.setFileName(rs.getString("image_file"));
				Product.setManuDate(rs.getString("manufacture_day"));
				Product.setPrice(rs.getInt("price"));
				Product.setProdDetail(rs.getString("prod_detail"));
				Product.setProdName(rs.getString("prod_name"));
				Product.setProdNo(rs.getInt("prod_no"));
				Product.setRegDate(rs.getDate("reg_date")); // DATETIME type?  casting?
				
				
				System.out.println("productDao tranCode::" + rs.getString("tran_status_code"));
				String tsc = null;
				if(rs.getString("tran_status_code") != null) {
					tsc =  rs.getString("tran_status_code").toString().trim();
				}
				
				if(tsc == null) {
					Product.setProTranCode("판매중");
				}else if(tsc.equals("1")) {  // later, store tran_status_code to var
					System.out.println("tran_status_code == 1");
					Product.setProTranCode("구매완료");
				}else if(tsc.equals("2")) {
					System.out.println("tran_status_code == 2");
					Product.setProTranCode("배송중");
				}else if(tsc.equals("3")) {
					System.out.println("tran_status_code == 3");
					Product.setProTranCode("배송완료");
				}
				
				list.add(Product);
				
				
				if(Product.getProTranCode().trim().equals("구매완료")) {
					System.out.println("in Product get 구매완료:: "+ Product.toString());
				}
				
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
	

	public void updateProduct(Product Product) throws Exception{
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		String sql = "Update product SET prod_no = ? , prod_name = ? , prod_detail = ?, manufacture_day = ? , price =?, image_file = ? WHERE prod_no = ? ";
		
		stmt = con.prepareStatement(sql);
		
		String date = Product.getManuDate().replaceAll("-", "");
		System.out.println("productDao updateProduct ManuDate::" + date);
		stmt.setInt(1, Product.getProdNo());
		stmt.setString(2, Product.getProdName());
		stmt.setString(3, Product.getProdDetail());
		stmt.setString(4, date);
		stmt.setInt(5, Product.getPrice());
		stmt.setString(6, Product.getFileName());
		stmt.setInt(7, Product.getProdNo());
		
		stmt.executeUpdate();
		
		
		
		con.close();
		stmt.close();
	}
	
	
	
	private int getTotalCount(String sql, SearchVO searchVO) throws Exception {
		sql = "SELECT COUNT(*)"
				+ " FROM ("+sql+")";
		
		System.out.println(sql);
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
