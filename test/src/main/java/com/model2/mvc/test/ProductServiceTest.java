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

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/com/model2/mvc/resources/config/commonService.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �� ������ ����ü��  Meta-data �� �̿� Wiring
	//==> �� �� ��ü�� Injection ��
//	@Autowired
//	@Qualifier("productServiceImpl")
	private ProductService productService = new ProductServiceImpl();
//	
//	@Autowired
//	@Qualifier("productDao")
	private ProductDao productDao = new ProductDao();

	//==> Test�� �ڵ� �ۼ�
	@Test
	public void testAddUser() throws Exception {
		
		System.out.println("\n===================================");
		Product product = new Product(100, "상품상세정보","지우개");
		System.out.println("insert : " + productService.addProduct(product)); 
		
		

		Assert.assertEquals(1, productService.addProduct(product));
		System.out.println("===================================\n");

	}
	
	//==> @Test�� �ּ�ó�� �ϰ� �����ϸ�....
	//==> 
//	@Test
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