package com.model2.mvc.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	
	private final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private final static String JDBC_URL = "jdbc:oracle:thin:system/pass@localhost:1521:XE?nls_lang=KO_KR.UTF8";
//	private final static String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final static String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"system","pass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DBUtil connection:" + conn);
		return conn;
	}
}