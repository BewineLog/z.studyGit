package com.model2.mvc.test;

import mybatis.service.domain.User;
import mybatis.service.user.UserDao;
import mybatis.service.user.UserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice14.xml" })
public class UserServiceTest {

	//==>@RunWith,@ContextConfiguration �� ������ ����ü��  Meta-data �� �̿� Wiring
	//==> �� �� ��ü�� Injection ��
	@Autowired
	@Qualifier("userServiceImpl14")
	private UserService userService;
	
	@Autowired
	@Qualifier("userDaoImpl14")
	private UserDao userDAO;

	//==> Test�� �ڵ� �ۼ�
	@Test
	public void testAddUser() throws Exception {
		
		System.out.println("\n===================================");
		User user = new User("user04","�ָ�","user04",null,0);
		System.out.println("insert ��� : "+userService.addUser(user));
		System.out.println("delete ��� : "+ userDAO.removeUser(user.getUserId()));
		
		//==> APIȮ�� :: �ּ��� Ǯ�� Ȯ���ϸ�...
		//==> �Ʒ��� 1�� 0���� ���� �غ���...
		Assert.assertEquals(1, userService.addUser(user));
		Assert.assertEquals(1, userDAO.removeUser(user.getUserId()));
		System.out.println("===================================\n");

	}
	
	//==> @Test�� �ּ�ó�� �ϰ� �����ϸ�....
	//==> 
	@Test
	public void testGetUser() throws Exception {
		
		System.out.println("\n===================================");
		User user = userService.getUser("user01");
		System.out.println(user);
		
		//==> APIȮ�� :: �ּ��� Ǯ�� Ȯ���ϸ�...
		Assert.assertEquals("user01",user.getUserId());
		//Assert.assertEquals("user0",user.getUserId());
		Assert.assertNotNull(userService.getUser("user02"));
		//Assert.assertNotNull(userService.getUser("user05"));
		System.out.println("===================================\n");

	}
	
	//==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetUserList() throws Exception{
	 	//==> Test code �ۼ�
	 }

	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testUpdateUser() throws Exception{
	 	//==> Test code �ۼ�
	 }
}