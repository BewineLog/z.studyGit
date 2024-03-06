package com.model2.mvc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;

public class SpringUtil {
	private static ApplicationContext context;
	private static ApplicationContext productContext;
	

	public static Object getService() throws BeansException {
		
		if(context == null) {
			context = new ClassPathXmlApplicationContext(
							new String[] {
									"com/model2/mvc/resources/config/context-common.xml",
									"com/model2/mvc/resources/config/context-aspect.xml",
									"com/model2/mvc/resources/config/context-mybatis.xml",
									"com/model2/mvc/resources/config/context-transaction.xml"
							}
						);
		}
		
		return context.getBean("userServiceImpl");
	}
	
	public static Object getProductService() throws BeansException {
		
		if(productContext == null) {
			productContext = new ClassPathXmlApplicationContext(
							new String[] {
									"com/model2/mvc/resources/config/context-common.xml",
									"com/model2/mvc/resources/config/context-aspect.xml",
									"com/model2/mvc/resources/config/context-mybatis.xml",
									"com/model2/mvc/resources/config/context-transaction.xml"
							}
						);
		}
		
		return productContext.getBean("productServiceImpl");
	}

}
