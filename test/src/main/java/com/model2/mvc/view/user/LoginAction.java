package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class LoginAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		User User=new User();
		User.setUserId(request.getParameter("userId"));
		User.setPassword(request.getParameter("password"));
		
		
		
		UserService service=new UserServiceImpl();
		User dbVO=service.loginUser(User);
		
		
		
		HttpSession session=request.getSession();
		session.setAttribute("user", dbVO);
		
		System.out.println("Login Action::"+ dbVO.getUserId());
		
		return "redirect:/index.jsp";
	}
}