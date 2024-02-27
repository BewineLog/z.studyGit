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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

/**
 * Servlet implementation class AddPurchaseView
 */
//@WebServlet("/AddPurchaseView")
public class AddPurchaseViewAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//prod_no
		//Session userID
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		HttpSession session = request.getSession(); // default true
		User user = (User)session.getAttribute("user");
		
		ProductServiceImpl pImpl = new ProductServiceImpl();
		Product product = pImpl.getProduct(prodNo);
		
		
		request.setAttribute("user", user);
		request.setAttribute("product", product);
		
		System.out.println("AddPurchaseViewAction ::" +user.toString());
		System.out.println("AddPurchaseViewAction ::" +product.toString());
		
		return "forward:/purchase/addPurchaseView.jsp";
		
	}

}
