package com.model2.mvc.view.product;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ListProductAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("ListProductAction start");
		
		SearchVO searchVO = new SearchVO();
		
		
		int page = 1; // default 1
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		searchVO.setPage(page);
		
		String searchKeyword = "";
		String searchCondition = "";
		
		if(request.getParameter("searchCondition") != null ){
			searchCondition = request.getParameter("searchCondition");
			searchVO.setSearchCondition(searchCondition);
			if(request.getParameter("searchKeyword") != null) {
				searchKeyword = request.getParameter("searchKeyword");
				System.out.println("ListProductAction searchKeyword :: " + searchKeyword);
				searchVO.setSearchKeyword(searchKeyword);
			}
		}
		
		
		
		
		System.out.println("searchKeyword ::" + searchKeyword + " " + searchCondition);
		
//		searchVO.setSearchCondition(request.getParameter("searchCondition"));
//		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageSize = getServletContext().getInitParameter("pageSize");
		searchVO.setPageSize(Integer.parseInt(pageSize));
		
		String pageUnit = getServletContext().getInitParameter("pageUnit");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		System.out.println("list product Action ::" + pageSize + " " + pageUnit);
		
		
		if(request.getParameter("inventoryValue") != null && ! request.getParameter("inventoryValue").toString().equals("")) {
			System.out.println("list product action inventory::" + request.getParameter("inventoryValue"));
			searchVO.setShowOption(request.getParameter("inventoryValue"));
			
			request.setAttribute("inventoryValue", request.getParameter("inventoryValue"));
		}
		
		if(request.getParameter("rankingAscValue") != null && !request.getParameter("rankingAscValue").toString().equals("")) {
			System.out.println("list product action rankingAsc::" + request.getParameter("rankingAscValue"));
			searchVO.setOrderByOption(request.getParameter("rankingAscValue"));
			
			request.setAttribute("rankingAscValue", request.getParameter("rankingAscValue"));
		}else if(request.getParameter("rankingDescValue") != null && !request.getParameter("rankingDescValue").toString().equals("")) {
			System.out.println("list product action rankingDesc::" + request.getParameter("rankingDescValue"));
			searchVO.setOrderByOption(request.getParameter("rankingDescValue"));
			
			request.setAttribute("rankingDescValue", request.getParameter("rankingDescValue"));
		}
		
		
		if(request.getParameter("fixedSearchRangeOne") != null) {
			System.out.println("fixedSearchRangeOne :: " + Boolean.getBoolean(request.getParameter("fixedSearchRangeOne").toString()));
			searchVO.setFixedSearchRangeOne(Boolean.getBoolean(request.getParameter("fixedSearchRangeOne").toString()));
		}
		
		if(request.getParameter("fixedSearchRangeTwo") != null) {
			System.out.println("fixedSearchRangeTwo :: " + Boolean.getBoolean(request.getParameter("fixedSearchRangeTwo").toString()));
			searchVO.setFixedSearchRangeOne(Boolean.getBoolean(request.getParameter("fixedSearchRangeTwo").toString()));
		}
		
		if(request.getParameter("fixedSearchRangeThree") != null) {
			System.out.println("fixedSearchRangeThree :: " + Boolean.getBoolean(request.getParameter("fixedSearchRangeThree").toString()));
			searchVO.setFixedSearchRangeOne(Boolean.getBoolean(request.getParameter("fixedSearchRangeThree").toString()));
		}
		
		
		if(request.getParameter("searchRangeLow") != null) {
			System.out.println("searchRangeLow :: " + request.getParameter("searchRangeLow"));
			searchVO.setSearchRangeLow(Integer.parseInt(request.getParameter("searchRangeLow")));
		}
		
		if(request.getParameter("searchRangeHigh") != null) {
			System.out.println("searchRangeHigh :: " + request.getParameter("searchRangeHigh"));
			searchVO.setSearchRangeHigh(Integer.parseInt(request.getParameter("searchRangeHigh")));
		}
		
		

		ProductService impl = (ProductService)SpringUtil.getProductService();
//		PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
//		String tranCode = "";
		
//		
//		PurchaseServiceImpl puImpl = new PurchaseServiceImpl();
		User user = (User)request.getSession().getAttribute("user");
//		String tranCode = request.getParameter("tranCode");
		
//		System.out.println("listproductAction User::" + User);
//		if (User != null) {
//			if (tranCode == null) {
//				System.out.println("listproductAction getListProdNo ::" + User.getUserId());
//				puMap = puImpl.getPurchaseListProdNo(User.getUserId(), "1");
//			}else if(tranCode.equals("2")) {
//				puMap = puImpl.getPurchaseListProdNo(User.getUserId(), "2");
//			}
//		}

		
		System.out.println("ListProductAction in puMap");
		List<Object> map = impl.getProductList(searchVO);
		new SqlSessionFactoryBean().printList(map);
		
		System.out.println("getTotalCount :: " + impl.getTotalCount(searchVO));
//			request.setAttribute("map", map);
		request.setAttribute("count", impl.getTotalCount(searchVO));
		request.setAttribute("list", map);
	
		Page pageInfo = new Page(searchVO.getPage(),((Integer)impl.getTotalCount(searchVO)).intValue(),searchVO.getPageUnit(),searchVO.getPageSize());
		
		
		request.setAttribute("map", map); // remove later
		
		if(request.getParameter("menu") != null) {
			request.setAttribute("menu", request.getParameter("menu").toString());
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/product/listProduct.jsp";
		
	}

}