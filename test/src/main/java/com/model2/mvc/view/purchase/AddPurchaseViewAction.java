package com.model2.mvc.view.purchase;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

/**
 * Servlet implementation class AddPurchaseView
 */
//@WebServlet("/AddPurchaseView")
public class AddPurchaseViewAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//prod_no
		//Session userID
		
		int prodNo = Integer.parseInt(request.getParameter("prod_no"));
		
		HttpSession session = request.getSession(); // default true
		UserVO userVO = (UserVO)session.getAttribute("user");
		
		ProductServiceImpl pImpl = new ProductServiceImpl();
		ProductVO productVO = pImpl.getProduct(prodNo);
		
		
		request.setAttribute("userVO", userVO);
		request.setAttribute("productVO", productVO);
		
		System.out.println("AddPurchaseViewAction ::" +userVO.toString());
		System.out.println("AddPurchaseViewAction ::" +productVO.toString());
		
		return "forward:/purchase/addPurchaseView.jsp";
		
	}

}
