package com.model2.mvc.view.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.impl.UserDaoImpl;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		
		String searchKeyword = "";
		String searchCondition = "";
		
		if(request.getParameter("searchCondition") != null & request.getParameter("searchKeyword") != null ){
			searchCondition = request.getParameter("searchCondition");
			searchKeyword = request.getParameter("searchKeyword");
		}
		
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		System.out.println("searchKeyword ::" + searchKeyword + " " + searchCondition);
//		
		int pageSize= Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit= Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		
		System.out.println("ListUserAction::" + searchVO.toString());
		System.out.println("size:" + pageSize  + "Unit:" + pageUnit);
		
		// this.getServletContext().getInitParameter("pageSize");  ==> Action�� this�� ����   // abstract class�� ���� ��� 
		
		//request.getServletContext() �ص� ��.
		
		
		
		searchVO.setPageSize(pageSize);
		
		
//		SqlSession sqlSession = new SqlSessionFactoryBean().getSqlSession();
//		
//		UserDAO userDao = new UserDaoImpl();
//		userDao.setSqlSession(sqlSession);
//		
//		UserService service=new UserServiceImpl();
//		service.setUserDao(userDao);
		
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				new String[] {
//						"com/model2/mvc/resources/config/commonService.xml",
//						"com/model2/mvc/resources/config/userService.xml"
//				}
//			);
//
//		UserService service = (UserService)context.getBean("userServiceImpl");
		
		UserService service = SpringUtil.getUserService();

		List<Object> map=service.getUserList(searchVO);

		Page pageInfo = new Page(page,service.getTotalCount() ,pageUnit,pageSize);
		System.out.println("pageInfo::" + pageInfo.toString());
		
		if(request.getParameter("removeUserId") != null) {
			service.removeUser(request.getParameter("removeUserId"));
		}
		
//		request.setAttribute("map", map);
		request.setAttribute("count", service.getTotalCount());
		request.setAttribute("list", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageInfo",pageInfo);
		
		return "forward:/user/listUser.jsp";
	}
}