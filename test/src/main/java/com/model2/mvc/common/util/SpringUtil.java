package com.model2.mvc.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.service.user.UserService;

public class SpringUtil {

	public static UserService getUserService() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
							new String[] {
									"com/model2/mvc/resources/config/commonService.xml",
									"com/model2/mvc/resources/config/userService.xml"
							}
						);
		
		UserService service = (UserService)context.getBean("userServiceImpl");
		return service;
	}

}
