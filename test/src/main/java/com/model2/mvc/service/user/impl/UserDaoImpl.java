package com.model2.mvc.service.user.impl;

import java.io.IOException;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.dao.UserDAO;


@Repository("userDaoImpl")
public class UserDaoImpl implements UserDAO {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) throws Exception{
		this.sqlSession = sqlSession;
	}

	public int insertUser(User user) throws Exception {
		
//		Connection con = DBUtil.getConnection();
//
//		String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, User.getUserId());
//		stmt.setString(2, User.getUserName());
//		stmt.setString(3, User.getPassword());
//		stmt.setString(4, User.getSsn());
//		stmt.setString(5, User.getPhone());
//		stmt.setString(6, User.getAddr());
//		stmt.setString(7, User.getEmail());
//		stmt.executeUpdate();
//		
//		con.close();
		
		return sqlSession.insert("UserMapper.insertUser",user);
	}

	public User findUser(String userId) throws Exception {
		
//		Connection con = DBUtil.getConnection();
//
//		String sql = "select * from USERS where USER_ID=?";
////		String sql = "select * from USERS where USER_ID LIKE %?%";
//		
//		System.out.println("con::" + con.toString());
//		
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, userId);
//
//		ResultSet rs = stmt.executeQuery();
//
//		User User = null;
//		while (rs.next()) {
//			User = new User();
//			User.setUserId(rs.getString("USER_ID"));
//			User.setUserName(rs.getString("USER_NAME"));
//			User.setPassword(rs.getString("PASSWORD"));
//			User.setRole(rs.getString("ROLE"));
//			User.setSsn(rs.getString("SSN"));
//			User.setPhone(rs.getString("CELL_PHONE"));
//			User.setAddr(rs.getString("ADDR"));
//			User.setEmail(rs.getString("EMAIL"));
//			User.setRegDate(rs.getDate("REG_DATE"));
//		}
//		
//		con.close();
//
//		return User;
		
		return sqlSession.selectOne("UserMapper.selectUser",userId);
	}

	public List<Object> getUserList(SearchVO search) throws Exception {
		
		return sqlSession.selectList("UserMapper.getUserList",search);
//		
//		Map<String , Object>  map = new HashMap<String, Object>();
//		
//		Connection con = DBUtil.getConnection();
//		
//		// Original Query ����
//		String sql = "SELECT user_id ,  user_name , email  FROM  users ";
//		
//		if (search.getSearchCondition() != null) {
//			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
//				sql += " WHERE user_id = '" + search.getSearchKeyword()+"'";
//			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
//				sql += " WHERE user_name ='" + search.getSearchKeyword()+"'";
//			}
//		}
//		sql += " ORDER BY user_id";
//		
//		System.out.println("UserDAO::Original SQL :: " + sql);
//		
//		//==> TotalCount GET
//		int totalCount = this.getTotalCount(sql);
//		System.out.println("UserDAO :: totalCount  :: " + totalCount);
//		
//		//==> CurrentPage 
//		sql = makeCurrentPageSql(sql, search);
//		PreparedStatement pStmt = con.prepareStatement(sql);
//		ResultSet rs = pStmt.executeQuery();
//	
//		System.out.println(search);
//
//		List<User> list = new ArrayList<User>();
//		
//		while(rs.next()){
//			User user = new User();
//			user.setUserId(rs.getString("user_id"));
//			user.setUserName(rs.getString("user_name"));
//			user.setEmail(rs.getString("email"));
//			list.add(user);
//		}
//		
//		//==> totalCount ���� ����
//		map.put("totalCount", new Integer(totalCount));
//		//==> currentPage �� �Խù� ���� ���� List ����
//		map.put("list", list);
//
//		rs.close();
//		pStmt.close();
//		con.close();
//
//		return map;
	}

	public int updateUser(User user) throws Exception {
		
		return sqlSession.update("UserMapper.updateUser",user);
//		Connection con = DBUtil.getConnection();
//
//		String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, User.getUserName());
//		stmt.setString(2, User.getPhone());
//		stmt.setString(3, User.getAddr());
//		stmt.setString(4, User.getEmail());
//		stmt.setString(5, User.getUserId());
//		stmt.executeUpdate();
//		
//		con.close();
	}
	
	public int removeUser(String userId) throws Exception{
		return sqlSession.delete("UserMapper.deleteUser",userId);
//		Connection con = DBUtil.getConnection();
//		
//		String sql = "DELETE FROM users WHERE user_id = ?";
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, userId);
//		
//		stmt.executeUpdate();
//		
//		con.close();
	}
	
	public int getTotalCount() {
		return sqlSession.selectOne("UserMapper.getTotalCount");
	}
	
//	private int getTotalCount(String sql) throws Exception {
//		
//		sql = "SELECT COUNT(*) "+
//		          "FROM ( " +sql+ ") countTable";
//		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement pStmt = con.prepareStatement(sql);
//		ResultSet rs = pStmt.executeQuery();
//		
//		int totalCount = 0;
//		if( rs.next() ){
//			totalCount = rs.getInt(1);
//		}
//		
//		pStmt.close();
//		con.close();
//		rs.close();
//		
//		return totalCount;
//	}
//	
//	private String makeCurrentPageSql(String sql , SearchVO search){
//		sql = 	"SELECT * "+ 
//					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
//									" 	FROM (	"+sql+" ) inner_table "+
//									"	WHERE ROWNUM <="+search.getPage()*search.getPageSize()+" ) " +
//					"WHERE row_seq BETWEEN "+((search.getPage()-1)*search.getPageSize()+1) +" AND "+search.getPage()*search.getPageSize();
//		
//		System.out.println("UserDAO :: make SQL :: "+ sql);	
//		
//		return sql;
//	}
}
