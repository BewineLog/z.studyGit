package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.impl.UserDaoImpl;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class LoginAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		User User=new User();
		User.setUserId(request.getParameter("userId"));
		User.setPassword(request.getParameter("password"));
		
		
//		SqlSession sqlSession = new SqlSessionFactoryBean().getSqlSession();
//		
//		UserDAO userDao = new UserDaoImpl();
//		userDao.setSqlSession(sqlSession);
//		
//		UserService service=new UserServiceImpl();
//		service.setUserDao(userDao);
		
		
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//											new String[] {
//													"com/model2/mvc/resources/config/commonService.xml",
//													"com/model2/mvc/resources/config/userService.xml"
//											}
//										);
//		
//		UserService service = (UserService)context.getBean("userServiceImpl");
		UserService service = (UserService)SpringUtil.getService();
		User dbVO=service.loginUser(User);
		
		
		
		HttpSession session=request.getSession();
		session.setAttribute("user", dbVO);
		
		System.out.println("Login Action::"+ dbVO.getUserId());
		
		return "redirect:/index.jsp";
	}
}