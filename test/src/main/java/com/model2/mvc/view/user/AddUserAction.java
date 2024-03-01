package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class AddUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		User User=new User();
		User.setUserId(request.getParameter("userId"));
		User.setPassword(request.getParameter("password"));
		User.setUserName(request.getParameter("userName"));
		User.setSsn(request.getParameter("ssn"));
		
		User.setAddr(request.getParameter("addr"));
		User.setPhone(request.getParameter("phone"));
		User.setEmail(request.getParameter("email"));
		
		System.out.println(User);
		
//		UserService service=new UserServiceImpl();
		UserService service = SpringUtil.getUserService();
		service.addUser(User);
		
		return "redirect:/user/loginView.jsp";
	}
}