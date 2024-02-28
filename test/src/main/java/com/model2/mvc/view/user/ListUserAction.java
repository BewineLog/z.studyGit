package com.model2.mvc.view.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
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
		
		
		
		
		UserService service=new UserServiceImpl();
		HashMap<String,Object> map=service.getUserList(searchVO);

		Page pageInfo = new Page(page,Integer.parseInt(map.get("count").toString()) ,pageUnit,pageSize);
		System.out.println("pageInfo::" + pageInfo.toString());
		
		if(request.getParameter("removeUserId") != null) {
			service.removeUser(request.getParameter("removeUserId"));
		}
		
//		request.setAttribute("map", map);
		request.setAttribute("count", map.get("count"));
		request.setAttribute("list", map.get("list"));
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageInfo",pageInfo);
		
		return "forward:/user/listUser.jsp";
	}
}