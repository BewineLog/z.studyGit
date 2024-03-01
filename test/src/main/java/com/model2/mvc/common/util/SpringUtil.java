package com.model2.mvc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.service.user.UserService;

public class SpringUtil {
	private static ApplicationContext context;
	

	public static UserService getUserService() throws BeansException {
		
		if(context == null) {
		 context = new ClassPathXmlApplicationContext(
							new String[] {
									"com/model2/mvc/resources/config/commonService.xml",
									"com/model2/mvc/resources/config/userService.xml"
							}
						);
		}
		
		UserService service = (UserService)context.getBean("userServiceImpl");
		return service;
	}

}
