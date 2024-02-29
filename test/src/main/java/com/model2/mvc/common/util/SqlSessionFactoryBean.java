package com.model2.mvc.common.util;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {

	public static SqlSession getSqlSession() throws IOException {
		System.out.println("sqlSession open start::");
		Reader reader = Resources.getResourceAsReader("com/model2/mvc/resources/config/mybatis-config.xml");
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession(true); //default false not auto Commit
		System.out.println("sqlSession open::");
		return sqlSession;
	}
	
	
	public static void printList(List<Object> list) {
		for(int i = 0; i < list.size();i++) {
			System.out.println("<" + i + "> 번째 요소 :::" + list.get(i));
		}
		System.out.println("\n");
	}

}
