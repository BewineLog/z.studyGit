package com.model2.mvc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;

public class SpringUtil {
	private static ApplicationContext userContext;
	private static ApplicationContext productContext;
	

	public static UserService getUserService() throws BeansException {
		
		if(userContext == null) {
			userContext = new ClassPathXmlApplicationContext(
							new String[] {
									"com/model2/mvc/resources/config/commonService.xml",
									"com/model2/mvc/resources/config/userService.xml"
							}
						);
		}
		
		UserService service = (UserService)userContext.getBean("userServiceImpl");
		return service;
	}
	
	
	public static ProductService getProductService() throws BeansException{
		
		if(productContext == null) {
			productContext = new ClassPathXmlApplicationContext(
					new String[] {
							"com/model2/mvc/resources/config/commonService.xml",
							"com/model2/mvc/resources/config/productService.xml"
					}
				);
		}
		
		ProductService service = (ProductService)productContext.getBean("productServiceImpl");
		return service;
	}

}
