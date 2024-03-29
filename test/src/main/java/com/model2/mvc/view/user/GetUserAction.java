package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class GetUserAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=request.getParameter("userId");
		
//		UserService service=new UserServiceImpl();
		UserService service = (UserService)SpringUtil.getService();
		User vo=service.getUser(userId);
		
		System.out.println("getUserAction vo::" + vo.toString());
		
		request.setAttribute("vo", vo);

		return "forward:/user/readUser.jsp";
	}
}