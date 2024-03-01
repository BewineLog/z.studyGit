package com.model2.mvc.view.user;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class UpdateUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=(String)request.getParameter("userId");
		
		User user=new User();
		user.setUserId(userId);
		user.setUserName(request.getParameter("userName"));
		user.setAddr(request.getParameter("addr"));
		user.setPhone(request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3"));
		user.setEmail(request.getParameter("email"));
		user.setRegDate(Date.valueOf(request.getParameter("regDate")));
		user.setRole(request.getParameter("role"));
		
//		UserService service=new UserServiceImpl();
		UserService service = SpringUtil.getUserService();
		service.updateUser(user);
		
		user = service.getUser(userId);
		request.setAttribute("vo", user);
		System.out.println("updateUserAction ::" + user.toString());
//		System.out.println("updateUserAction foward:: " + "forward:/getUser.do?userId="+userId+"&isUpdate=clear");
		return "forward:/user/readUser.jsp?isUpdate=clear";
	}
}