package com.model2.mvc.view.purchase;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.User;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListPurchaseAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("ListPurchasetAction start");
		
		SearchVO searchVO = new SearchVO();
		
		
		int page = 1; //�̷��� ����� default 1
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		searchVO.setPage(page);
		
//		String searchKeyword = "";
//		String searchCondition = "";
//		
//		if(request.getParameter("searchCondition") != null & request.getParameter("searchKeyword") != null ){
//			searchCondition = request.getParameter("searchCondition");
//			searchKeyword = request.getParameter("searchKeyword");
//		}
//		
//		searchVO.setSearchCondition(searchCondition);
//		searchVO.setSearchKeyword(searchKeyword);
//		
//		System.out.println("searchKeyword ::" + searchKeyword + " " + searchCondition);
		
//		searchVO.setSearchCondition(request.getParameter("searchCondition"));
//		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageSize = getServletContext().getInitParameter("pageSize");
		searchVO.setPageSize(Integer.parseInt(pageSize));
		
		String pageUnit = getServletContext().getInitParameter("pageUnit");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		System.out.println("list purchase Action ::" + pageSize + " " + pageUnit);
		
		
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		User User = (User)request.getSession().getAttribute("user");
		
		
		if(User != null) {
			map = impl.getPurchaseList(User.getUserId(),searchVO);
		}
		
		
		if(map != null) {
			request.setAttribute("count",map.get("count"));
			request.setAttribute("list", map.get("list"));
		}
		
		Page pageInfo = new Page(searchVO.getPage(),((Integer)map.get("count")).intValue(),searchVO.getPageUnit(),searchVO.getPageSize());
		
		
		request.setAttribute("pageInfo", pageInfo);
		
//		request.setAttribute("searchVO", searchVO);
		
		return "forward:/purchase/listPurchase.jsp";
		
	}

}