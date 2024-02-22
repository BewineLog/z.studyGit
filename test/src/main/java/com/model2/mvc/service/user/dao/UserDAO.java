package com.model2.mvc.service.user.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.user.vo.UserVO;


public class UserDAO {
	
	public UserDAO(){
	}

	public void insertUser(UserVO userVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userVO.getUserId());
		stmt.setString(2, userVO.getUserName());
		stmt.setString(3, userVO.getPassword());
		stmt.setString(4, userVO.getSsn());
		stmt.setString(5, userVO.getPhone());
		stmt.setString(6, userVO.getAddr());
		stmt.setString(7, userVO.getEmail());
		stmt.executeUpdate();
		
		con.close();
	}

	public UserVO findUser(String userId) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from USERS where USER_ID=?";
//		String sql = "select * from USERS where USER_ID LIKE %?%";
		
		System.out.println("con::" + con.toString());
		
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userId);

		ResultSet rs = stmt.executeQuery();

		UserVO userVO = null;
		while (rs.next()) {
			userVO = new UserVO();
			userVO.setUserId(rs.getString("USER_ID"));
			userVO.setUserName(rs.getString("USER_NAME"));
			userVO.setPassword(rs.getString("PASSWORD"));
			userVO.setRole(rs.getString("ROLE"));
			userVO.setSsn(rs.getString("SSN"));
			userVO.setPhone(rs.getString("CELL_PHONE"));
			userVO.setAddr(rs.getString("ADDR"));
			userVO.setEmail(rs.getString("EMAIL"));
			userVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();

		return userVO;
	}

	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
//		String sql = "select * from USERS ";
		
//		Select  ROW_NUMBER() OVER(ORDER BY users.user_id), users.* from USERS users
		
		
		//
		//pageCount가 만들어져 있으면 안하고, 아니면 하
		//
		
		
//		String sql = "select ( select count(*) as cnt from (";
//		String subSql = "select * from (Select  ROW_NUMBER() OVER(ORDER BY users.user_id) as num, users.* from USERS users"; 
//	
//		
//		if (searchVO.getSearchCondition() != null) {
//			if (searchVO.getSearchCondition().equals("0")) {
//				subSql += " where USER_ID LIKE '%" + searchVO.getSearchKeyword()
//						+ "%'";
//			} else if (searchVO.getSearchCondition().equals("1")) {
//				subSql += " where USER_NAME LIKE '%" + searchVO.getSearchKeyword()
//						+ "%'";
//			}
//		}
//		
//		subSql += ") where num between ? and ? ";
//		subSql += " order by USER_ID";
//		
//		sql += subSql;
//		sql += "), vt.* from (";
//		sql += subSql;
//		sql += ") vt";
		
		
		String sql = "SELECT ( SELECT COUNT(*) FROM (";
		String subSql = "SELECT * FROM (";
		subSql += "SELECT ROW_NUMBER() OVER (ORDER BY users.user_id) AS num, users.* FROM USERS users";

		if (searchVO.getSearchCondition() != null) {
		    if (searchVO.getSearchCondition().equals("0")) {
		        subSql += " WHERE USER_ID LIKE '%" + searchVO.getSearchKeyword() + "%'";
		    } else if (searchVO.getSearchCondition().equals("1")) {
		        subSql += " WHERE USER_NAME LIKE '%" + searchVO.getSearchKeyword() + "%'";
		    }
		}

		subSql += ")";
		sql += subSql;
		
		subSql += " WHERE num BETWEEN ? AND ?";
		subSql += " ORDER BY USER_ID";


		sql += ")) as cnt, vt.* FROM(";
		sql += subSql;
		sql += ") vt";
		
		System.out.println("sql::" + sql);

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		
		int startNum = searchVO.getPage() * searchVO.getPageSize() - searchVO.getPageSize()+1;
		
		System.out.println("getPage::" + searchVO.getPage() + "pageSize ::" + searchVO.getPageSize() );
		
		stmt.setInt(1, startNum);
		stmt.setInt(2, startNum + searchVO.getPageSize()-1);
//		stmt.setInt(3, startNum);
//		stmt.setInt(4, startNum + searchVO.getPageSize()-1);
		ResultSet rs = stmt.executeQuery();
		
		System.out.println("between value :: " + startNum +" " + (startNum + searchVO.getPageSize()-1));
		

		
		
//		rs.last();
//		int total = rs.getRow();
		int total = 0;
		if(rs.next())
			total = Integer.parseInt(rs.getString("cnt"));
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

//		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageSize():" + searchVO.getPageSize());

		ArrayList<UserVO> list = new ArrayList<UserVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageSize(); i++) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("USER_ID"));
				vo.setUserName(rs.getString("USER_NAME"));
				vo.setPassword(rs.getString("PASSWORD"));
				vo.setRole(rs.getString("ROLE"));
				vo.setSsn(rs.getString("SSN"));
				vo.setPhone(rs.getString("CELL_PHONE"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setRegDate(rs.getDate("REG_DATE"));

				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}

	public void updateUser(UserVO userVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userVO.getUserName());
		stmt.setString(2, userVO.getPhone());
		stmt.setString(3, userVO.getAddr());
		stmt.setString(4, userVO.getEmail());
		stmt.setString(5, userVO.getUserId());
		stmt.executeUpdate();
		
		con.close();
	}
}