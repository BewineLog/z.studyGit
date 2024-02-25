package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class UpdateUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=(String)request.getParameter("userId");
		
		User User=new User();
		User.setUserId(userId);
		User.setUserName(request.getParameter("userName"));
		User.setAddr(request.getParameter("addr"));
		User.setPhone(request.getParameter("phone"));
		User.setEmail(request.getParameter("email"));
		
		UserService service=new UserServiceImpl();
		service.updateUser(User);
		
		User = service.getUser(userId);
		request.setAttribute("vo", User);
//		System.out.println("updateUserAction foward:: " + "forward:/getUser.do?userId="+userId+"&isUpdate=clear");
		return "forward:/user/readUser.jsp?isUpdate=clear";
	}
}